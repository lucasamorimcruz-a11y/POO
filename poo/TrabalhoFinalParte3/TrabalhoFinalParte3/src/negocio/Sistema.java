package negocio;

import dados.*;
import dados.enums.*;
import exceptions.*;
import persistencia.*;

import java.sql.SQLException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

public class Sistema {

    private static final double ALIQUOTA_IMPOSTOS = 0.12;
    private static final String BASE = "Base da transportadora";

    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Veiculo> veiculos = new ArrayList<>();
    private ArrayList<Motorista> motoristas = new ArrayList<>();
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private ArrayList<Rota> rotas = new ArrayList<>();
    private ArrayList<ConhecimentoTransporte> conhecimentos = new ArrayList<>();
    private TabelaPreco tabelaPadrao;

    private ClienteDAO clienteDAO = ClienteDAO.getInstance();
    private MotoristaDAO motoristaDAO = MotoristaDAO.getInstance();
    private VeiculoDAO veiculoDAO = VeiculoDAO.getInstance();
    private ManutencaoDAO manutencaoDAO = ManutencaoDAO.getInstance();
    private TabelaPrecoDAO tabelaPrecoDAO = TabelaPrecoDAO.getInstance();
    private PedidoDAO pedidoDAO = PedidoDAO.getInstance();
    private ItemDAO itemDAO = ItemDAO.getInstance();
    private EventoDAO eventoDAO = EventoDAO.getInstance();
    private EntregaDAO entregaDAO = EntregaDAO.getInstance();
    private FalhaNaEntregaDAO falhaNaEntregaDAO = FalhaNaEntregaDAO.getInstance();
    private ConhecimentoDAO conhecimentoDAO = ConhecimentoDAO.getInstance();
    private RotaDAO rotaDAO = RotaDAO.getInstance();
    private PedidoNaRotaDAO pedidoNaRotaDAO = PedidoNaRotaDAO.getInstance();
    private DespesaDAO despesaDAO = DespesaDAO.getInstance();

    private String erro = "";

    public Sistema() throws SelectException, InsertException {
        try {
            Conexao.criarTabelas();
        } catch (SQLException e) {
            throw new SelectException("Erro ao criar as tabelas do banco de dados.", e);
        }

        tabelaPadrao = tabelaPrecoDAO.selectPadrao();
        if (tabelaPadrao == null) {
            tabelaPadrao = criarTabelaPadrao();
            tabelaPrecoDAO.insert(tabelaPadrao, true);
        }
        carregar();
    }

    private TabelaPreco criarTabelaPadrao() {
        Map<TipoDeServico, Double> fatoresServico = new EnumMap<>(TipoDeServico.class);
        fatoresServico.put(TipoDeServico.ECONOMICO, 1.0);
        fatoresServico.put(TipoDeServico.EXPRESSO, 1.5);
        fatoresServico.put(TipoDeServico.AGENDADO, 1.2);

        Map<Regiao, Double> fatoresRegiao = new EnumMap<>(Regiao.class);
        fatoresRegiao.put(Regiao.LOCAL, 1.0);
        fatoresRegiao.put(Regiao.ESTADUAL, 1.3);
        fatoresRegiao.put(Regiao.INTERESTADUAL, 1.8);
        fatoresRegiao.put(Regiao.OUTRO, 2.0);

        return new TabelaPreco(2.0, 1.0, 50.0, 0.10, fatoresServico, fatoresRegiao);
    }

    private void carregar() throws SelectException {
        clientes = clienteDAO.selectAll();
        veiculos = veiculoDAO.selectAll();
        motoristas = motoristaDAO.selectAll();
        pedidos = pedidoDAO.selectAll(clientes);
        conhecimentos = conhecimentoDAO.selectAll(pedidos);
        rotas = rotaDAO.selectAll(veiculos, motoristas, pedidos);
    }

    private void falhou(Exception e) {
        Conexao.desfazer();
        e.printStackTrace();
        erro = e.getMessage();
        try {
            carregar();
        } catch (SelectException ex) {
            ex.printStackTrace();
        }
    }

    public String getErro() {
        return erro;
    }

    public boolean cadastrarCliente(Cliente cliente) {
        if (cliente == null || cliente.getPessoa() == null) {
            erro = "Cliente inválido.";
            return false;
        }
        Pessoa pessoa = cliente.getPessoa();
        if (vazio(pessoa.getNome())) {
            erro = "O cliente precisa ter nome.";
            return false;
        }
        String bruto = documentoDe(pessoa);
        if (vazio(bruto)) {
            erro = "O cliente precisa ter CPF ou CNPJ.";
            return false;
        }
        int digitosEsperados = 11;
        String nomeDoDocumento = "CPF";
        if (pessoa instanceof PessoaJuridica) {
            digitosEsperados = 14;
            nomeDoDocumento = "CNPJ";
        }
        String documento = normalizar(bruto);
        if (!bruto.matches("[\\d.\\-/\\s]+") || documento.length() != digitosEsperados) {
            erro = nomeDoDocumento + " inválido: informe " + digitosEsperados + " dígitos.";
            return false;
        }
        if (buscarCliente(documento) != null) {
            erro = "Já existe um cliente com este documento.";
            return false;
        }
        String email = pessoa.getEmail();
        if (!vazio(email) && !email.trim().matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+")) {
            erro = "E-mail inválido.";
            return false;
        }
        if (cliente.getLimiteDeCredito() < 0) {
            erro = "O limite de crédito não pode ser negativo.";
            return false;
        }
        if (cliente.getTipoCliente() == TipoCliente.CONTRATADO && cliente.getTabelaDePreco() == null) {
            erro = "Cliente contratado precisa de tabela de preços.";
            return false;
        }
        try {
            Conexao.iniciarTransacao();
            clienteDAO.insert(cliente);
            Conexao.confirmar();
        } catch (Exception e) {
            falhou(e);
            return false;
        }
        clientes.add(cliente);
        return true;
    }

    public boolean cadastrarMotorista(Motorista motorista) {
        if (motorista == null) {
            erro = "Motorista inválido.";
            return false;
        }
        String cpf = normalizar(motorista.getCPF());
        if (cpf == null || cpf.isEmpty()) {
            erro = "O motorista precisa ter CPF.";
            return false;
        }
        if (buscarMotorista(cpf) != null) {
            erro = "Já existe um motorista com este CPF.";
            return false;
        }
        if (motorista.getCnh() == null || motorista.getCnh().getDataDeValidade() == null) {
            erro = "O motorista precisa ter CNH com data de validade.";
            return false;
        }
        if (!cnhValidaEm(motorista, LocalDate.now())) {
            erro = "CNH vencida.";
            return false;
        }
        try {
            Conexao.iniciarTransacao();
            motoristaDAO.insert(motorista);
            Conexao.confirmar();
        } catch (Exception e) {
            falhou(e);
            return false;
        }
        motoristas.add(motorista);
        return true;
    }

    public boolean cadastrarVeiculo(Veiculo veiculo) {
        if (veiculo == null || vazio(veiculo.getPlaca())) {
            erro = "Veículo inválido: placa obrigatória.";
            return false;
        }
        if (buscarVeiculo(veiculo.getPlaca()) != null) {
            erro = "Já existe um veículo com esta placa.";
            return false;
        }
        if (veiculo.getCapacidadeEmMetroCubicos() <= 0) {
            erro = "A capacidade em m³ deve ser maior que zero.";
            return false;
        }
        try {
            Conexao.iniciarTransacao();
            veiculoDAO.insert(veiculo);
            Conexao.confirmar();
        } catch (Exception e) {
            falhou(e);
            return false;
        }
        veiculos.add(veiculo);
        return true;
    }

    public Cliente buscarCliente(String documento) {
        String alvo = normalizar(documento);
        if (alvo == null || alvo.isEmpty()) {
            return null;
        }
        for (Cliente cliente : clientes) {
            if (alvo.equals(normalizar(documentoDe(cliente.getPessoa())))) {
                return cliente;
            }
        }
        return null;
    }

    public ArrayList<Cliente> pesquisarClientes(String termo) {
        ArrayList<Cliente> resultado = new ArrayList<>();
        String texto = "";
        if (termo != null) {
            texto = termo.trim();
        }
        if (texto.isEmpty()) {
            resultado.addAll(clientes);
            return resultado;
        }
        String buscaTexto = semAcento(texto);
        String buscaDigitos = normalizar(texto);
        for (Cliente cliente : clientes) {
            Pessoa pessoa = cliente.getPessoa();
            boolean achou = semAcento(pessoa.getNome()).contains(buscaTexto);
            if (!achou && pessoa instanceof PessoaJuridica) {
                PessoaJuridica juridica = (PessoaJuridica) pessoa;
                achou = semAcento(juridica.getRazaoSocial()).contains(buscaTexto);
            }
            if (!achou && !buscaDigitos.isEmpty()) {
                String documento = normalizar(documentoDe(pessoa));
                achou = documento != null && documento.contains(buscaDigitos);
            }
            if (achou) {
                resultado.add(cliente);
            }
        }
        return resultado;
    }

    public Motorista buscarMotorista(String cpf) {
        String alvo = normalizar(cpf);
        if (alvo == null || alvo.isEmpty()) {
            return null;
        }
        for (Motorista motorista : motoristas) {
            if (alvo.equals(normalizar(motorista.getCPF()))) {
                return motorista;
            }
        }
        return null;
    }

    public Veiculo buscarVeiculo(String placa) {
        if (placa == null) {
            return null;
        }
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca() != null && veiculo.getPlaca().trim().equalsIgnoreCase(placa.trim())) {
                return veiculo;
            }
        }
        return null;
    }

    public Pedido buscarPedido(int numero) {
        for (Pedido pedido : pedidos) {
            if (pedido.getNumero() == numero) {
                return pedido;
            }
        }
        return null;
    }

    public Rota buscarRota(int numero) {
        for (Rota rota : rotas) {
            if (rota.getNumero() == numero) {
                return rota;
            }
        }
        return null;
    }

    public Pedido criarPedido(Cliente cliente, Endereco coleta, String contatoColeta, LocalDate dataColeta, Periodo periodo, String descricao, double valorDeclarado, Endereco entrega, String contatoDestino, TipoDeServico tipo, FormaDePagamento formaDePagamento) {
        if (cliente == null || !clientes.contains(cliente)) {
            erro = "Cliente não cadastrado.";
            return null;
        }
        if (coleta == null || entrega == null) {
            erro = "Informe os endereços de coleta e de entrega.";
            return null;
        }
        if (periodo == null || tipo == null || formaDePagamento == null) {
            erro = "Período, tipo de serviço e forma de pagamento são obrigatórios.";
            return null;
        }
        if (dataColeta == null || dataColeta.isBefore(LocalDate.now())) {
            erro = "A data de coleta não pode estar no passado.";
            return null;
        }
        if (valorDeclarado < 0) {
            erro = "Valor declarado inválido.";
            return null;
        }

        LocalDate prazoDeEntrega = dataColeta.plusDays(diasDePrazo(tipo));
        Pedido pedido = null;
        try {
            Conexao.iniciarTransacao();
            int numero = pedidoDAO.proximoNumero();
            pedido = new Pedido(numero, LocalDate.now(), cliente, coleta, contatoColeta,
                    dataColeta, periodo, descricao, valorDeclarado, entrega, contatoDestino,
                    prazoDeEntrega, tipo, formaDePagamento);
            pedidoDAO.insert(pedido);
            Conexao.confirmar();
        } catch (Exception e) {
            falhou(e);
            return null;
        }
        pedidos.add(pedido);
        return pedido;
    }

    public boolean adicionarItem(Pedido pedido, Item item) {
        if (!pedidoCadastrado(pedido)) {
            return false;
        }
        if (item == null || item.getPeso() <= 0 || item.getDimensao() <= 0) {
            erro = "Item inválido: peso e dimensões (m³) devem ser maiores que zero.";
            return false;
        }
        if (pedido.getStatusPedido() != StatusPedido.PENDENTE) {
            erro = "Só é possível adicionar itens a pedidos pendentes.";
            return false;
        }
        try {
            Conexao.iniciarTransacao();
            pedido.adicionarItem(item);
            itemDAO.insert(pedido, item);
            pedidoDAO.update(pedido);
            Conexao.confirmar();
        } catch (Exception e) {
            falhou(e);
            return false;
        }
        return true;
    }

    public double calcularFrete(Pedido pedido, double distanciaKm, Regiao regiao) {
        if (!pedidoCadastrado(pedido)) {
            return -1;
        }
        if (pedido.getStatusPedido() != StatusPedido.PENDENTE) {
            erro = "O frete só pode ser calculado para pedidos pendentes.";
            return -1;
        }
        if (distanciaKm <= 0 || regiao == null) {
            erro = "Informe uma distância maior que zero e a região de destino.";
            return -1;
        }
        if (pedido.getPesoTotal() <= 0) {
            erro = "Adicione itens ao pedido antes de calcular o frete.";
            return -1;
        }

        Cliente cliente = pedido.getCliente();
        boolean contratado = cliente.getTipoCliente() == TipoCliente.CONTRATADO;
        TabelaPreco tabela = tabelaPadrao;
        if (contratado && cliente.getTabelaDePreco() != null) {
            tabela = cliente.getTabelaDePreco();
        }
        if (tabela == null) {
            erro = "Nenhuma tabela de preços disponível.";
            return -1;
        }
        if (tabela.getFatorPorTipoDeServico().get(pedido.getTipoDeServico()) == null) {
            erro = "Tipo de serviço não cadastrado.";
            return -1;
        }
        if (tabela.getFatorPorRegiao().get(regiao) == null) {
            erro = "Tipo de região não cadastrado.";
            return -1;
        }

        double valor = tabela.calcularFrete(pedido.getPesoTotal(), distanciaKm, pedido.getTipoDeServico(), regiao, contratado);
        try {
            Conexao.iniciarTransacao();
            pedido.setValorFrete(valor);
            pedidoDAO.update(pedido);
            Conexao.confirmar();
        } catch (Exception e) {
            falhou(e);
            return -1;
        }
        return valor;
    }

    public boolean cancelarPedido(Pedido pedido) {
        if (!pedidoCadastrado(pedido)) {
            return false;
        }
        if (pedido.getStatusPedido() != StatusPedido.PENDENTE) {
            erro = "Só é possível cancelar pedidos pendentes.";
            return false;
        }
        try {
            Conexao.iniciarTransacao();
            pedido.setStatusPedido(StatusPedido.CANCELADO);
            pedidoDAO.update(pedido);
            Conexao.confirmar();
        } catch (Exception e) {
            falhou(e);
            return false;
        }
        return true;
    }

    public ArrayList<Pedido> pedidosDoCliente(Cliente cliente) {
        ArrayList<Pedido> resultado = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.getCliente() == cliente) {
                resultado.add(pedido);
            }
        }
        return resultado;
    }

    public ArrayList<Pedido> pedidosComStatus(StatusPedido status) {
        ArrayList<Pedido> resultado = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.getStatusPedido() == status) {
                resultado.add(pedido);
            }
        }
        return resultado;
    }

    public ArrayList<Rota> rotasComStatus(StatusRota status) {
        ArrayList<Rota> resultado = new ArrayList<>();
        for (Rota rota : rotas) {
            if (rota.getStatus() == status) {
                resultado.add(rota);
            }
        }
        return resultado;
    }

    public ConhecimentoTransporte registrarColeta(Pedido pedido, String local, String notaFiscal, String destinatario, boolean seguroContratado, double valorSeguro) {
        if (!pedidoCadastrado(pedido)) {
            return null;
        }
        if (pedido.getStatusPedido() != StatusPedido.PENDENTE) {
            erro = "A coleta só pode ser registrada para pedidos pendentes.";
            return null;
        }
        if (pedido.getValorFrete() <= 0) {
            erro = "Calcule o frete do pedido antes de registrar a coleta.";
            return null;
        }
        if (vazio(local) || vazio(notaFiscal)) {
            erro = "Informe o local da coleta e a nota fiscal.";
            return null;
        }
        if (vazio(destinatario)) {
            erro = "Informe o nome do destinatário.";
            return null;
        }
        if (seguroContratado && valorSeguro <= 0) {
            erro = "Informe o valor do seguro (maior que zero).";
            return null;
        }

        double frete = pedido.getValorFrete();
        double valorDoSeguro = 0;
        if (seguroContratado) {
            valorDoSeguro = valorSeguro;
        }
        ConhecimentoTransporte conhecimento = null;
        try {
            Conexao.iniciarTransacao();
            conhecimento = new ConhecimentoTransporte(
                    conhecimentoDAO.proximoNumero(),
                    LocalDate.now(),
                    pedido.getCliente().getPessoa().getNome(),
                    destinatario.trim(),
                    pedido.getDescricaoDaCarga(),
                    (float) frete,
                    (float) (frete * ALIQUOTA_IMPOSTOS),
                    seguroContratado,
                    (float) valorDoSeguro,
                    notaFiscal,
                    pedido);
            conhecimentoDAO.insert(conhecimento);
            pedido.getConhecimentos().add(conhecimento);
            aplicarEvento(pedido, TipoDeEvento.COLETA_REALIZADA, local, "Coleta realizada. Nota fiscal " + notaFiscal, null);
            Conexao.confirmar();
        } catch (Exception e) {
            falhou(e);
            return null;
        }
        conhecimentos.add(conhecimento);
        return conhecimento;
    }

    public ConhecimentoTransporte buscarConhecimento(Pedido pedido) {
        for (ConhecimentoTransporte conhecimento : conhecimentos) {
            if (conhecimento.getPedido() == pedido) {
                return conhecimento;
            }
        }
        return null;
    }

    public Rota criarRota(LocalDate data, Veiculo veiculo, Motorista motorista) {
        if (data == null || data.isBefore(LocalDate.now())) {
            erro = "A data da rota não pode estar no passado.";
            return null;
        }
        if (veiculo == null || !veiculos.contains(veiculo)) {
            erro = "Veículo não cadastrado.";
            return null;
        }
        if (motorista == null || !motoristas.contains(motorista)) {
            erro = "Motorista não cadastrado.";
            return null;
        }
        if (veiculo.getStatusVeiculo() != StatusVeiculo.DISPONIVEL) {
            erro = "O veículo não está disponível.";
            return null;
        }
        if (motorista.getSituacao() != Situacao.ATIVO) {
            erro = "O motorista não está ativo.";
            return null;
        }
        if (!motorista.podeDirigir(veiculo)) {
            erro = "O motorista não está habilitado para este tipo de veículo.";
            return null;
        }
        if (!cnhValidaEm(motorista, data)) {
            erro = "A CNH do motorista estará vencida na data da rota.";
            return null;
        }
        for (Rota existente : rotas) {
            boolean mesmoDia = existente.getData().equals(data);
            boolean aberta = existente.getStatus() != StatusRota.CONCLUIDA;
            boolean recursoRepetido = existente.getVeiculoAlocado() == veiculo
                    || existente.getMotoristaResponsavel() == motorista;
            if (mesmoDia && aberta && recursoRepetido) {
                erro = "Veículo ou motorista já alocado em outra rota nesta data.";
                return null;
            }
        }

        Rota rota = null;
        try {
            Conexao.iniciarTransacao();
            rota = new Rota(rotaDAO.proximoNumero(), data, veiculo, motorista);
            rotaDAO.insert(rota);
            Conexao.confirmar();
        } catch (Exception e) {
            falhou(e);
            return null;
        }
        rotas.add(rota);
        return rota;
    }

    public boolean adicionarPedidoARota(Rota rota, Pedido pedido, LocalDateTime horarioPrevisto) {
        if (!rotaCadastrada(rota)) {
            return false;
        }
        if (!pedidoCadastrado(pedido)) {
            return false;
        }
        if (horarioPrevisto == null) {
            erro = "Informe o horário previsto de entrega.";
            return false;
        }
        if (rota.getStatus() != StatusRota.PLANEJADA) {
            erro = "Só é possível adicionar pedidos a rotas planejadas.";
            return false;
        }
        if (pedido.getStatusPedido() != StatusPedido.COLETADO) {
            erro = "Apenas pedidos já coletados entram em rotas de entrega.";
            return false;
        }
        if (buscarParada(pedido) != null) {
            erro = "O pedido já está em uma rota.";
            return false;
        }

        double pesoTotal = pedido.getPesoTotal();
        double volumeTotal = pedido.getVolumeTotal();
        for (PedidoNaRota parada : rota.getPedidos()) {
            pesoTotal += parada.getPedido().getPesoTotal();
            volumeTotal += parada.getPedido().getVolumeTotal();
        }
        if (pesoTotal > rota.getVeiculoAlocado().getCapacidadeEmQuilos()) {
            erro = "O peso total excede a capacidade do veículo.";
            return false;
        }
        if (volumeTotal > rota.getVeiculoAlocado().getCapacidadeEmMetroCubicos() + 1e-6) {
            erro = "O volume total excede a capacidade (m³) do veículo.";
            return false;
        }

        try {
            Conexao.iniciarTransacao();
            PedidoNaRota parada = rota.adicionarPedido(pedido, horarioPrevisto);
            pedidoNaRotaDAO.insert(rota, parada);
            Conexao.confirmar();
        } catch (Exception e) {
            falhou(e);
            return false;
        }
        return true;
    }

    public boolean iniciarRota(Rota rota, double kmInicial) {
        if (!rotaCadastrada(rota)) {
            return false;
        }
        if (rota.getStatus() != StatusRota.PLANEJADA) {
            erro = "Só é possível iniciar rotas planejadas.";
            return false;
        }
        if (rota.getPedidos().isEmpty()) {
            erro = "A rota não tem pedidos.";
            return false;
        }
        Veiculo veiculo = rota.getVeiculoAlocado();
        if (veiculo.getStatusVeiculo() != StatusVeiculo.DISPONIVEL) {
            erro = "O veículo não está disponível.";
            return false;
        }
        if (kmInicial < veiculo.getQuilometragemAtual()) {
            erro = "A quilometragem inicial não pode ser menor que a atual do veículo.";
            return false;
        }

        try {
            Conexao.iniciarTransacao();
            rota.setQuilometragemInicial(kmInicial);
            rota.setStatus(StatusRota.EM_ANDAMENTO);
            veiculo.setStatusVeiculo(StatusVeiculo.EM_ROTA);
            rotaDAO.update(rota);
            veiculoDAO.update(veiculo);
            for (PedidoNaRota parada : rota.getPedidos()) {
                aplicarEvento(parada.getPedido(), TipoDeEvento.SAIU_PARA_ENTREGA, BASE,
                        "Saiu para entrega na rota " + rota.getNumero(), null);
            }
            Conexao.confirmar();
        } catch (Exception e) {
            falhou(e);
            return false;
        }
        return true;
    }

    public boolean concluirRota(Rota rota, double kmFinal) {
        if (!rotaCadastrada(rota)) {
            return false;
        }
        if (rota.getStatus() != StatusRota.EM_ANDAMENTO) {
            erro = "Só é possível concluir rotas em andamento.";
            return false;
        }
        if (kmFinal < rota.getQuilometragemInicial()) {
            erro = "A quilometragem final não pode ser menor que a inicial.";
            return false;
        }

        Veiculo veiculo = rota.getVeiculoAlocado();
        try {
            Conexao.iniciarTransacao();
            rota.setQuilometragemFinal(kmFinal);
            rota.setStatus(StatusRota.CONCLUIDA);
            veiculo.setQuilometragemAtual(kmFinal);
            veiculo.setStatusVeiculo(StatusVeiculo.DISPONIVEL);
            rotaDAO.update(rota);
            veiculoDAO.update(veiculo);
            Conexao.confirmar();
        } catch (Exception e) {
            falhou(e);
            return false;
        }
        return true;
    }

    public boolean registrarDespesa(Rota rota, LocalDate data, TipoDeDespesa tipo, double valor, String comprovante) {
        if (!rotaCadastrada(rota)) {
            return false;
        }
        if (data == null || tipo == null) {
            erro = "Informe a data e o tipo da despesa.";
            return false;
        }
        if (valor <= 0) {
            erro = "O valor da despesa deve ser maior que zero.";
            return false;
        }
        if (rota.getStatus() == StatusRota.PLANEJADA) {
            erro = "A rota ainda não foi iniciada.";
            return false;
        }
        Despesa despesa = new Despesa(data, tipo, valor, comprovante);
        try {
            Conexao.iniciarTransacao();
            rota.adicionarDespesa(despesa);
            despesaDAO.insert(rota, despesa);
            Conexao.confirmar();
        } catch (Exception e) {
            falhou(e);
            return false;
        }
        return true;
    }

    public boolean registrarEvento(Pedido pedido, TipoDeEvento tipo, String local, String descricao, String foto) {
        if (!pedidoCadastrado(pedido)) {
            return false;
        }
        if (tipo == null || vazio(local)) {
            erro = "Informe o tipo do evento e a localização.";
            return false;
        }
        if (pedido.getStatusPedido() == StatusPedido.CANCELADO || pedido.getStatusPedido() == StatusPedido.ENTREGUE) {
            erro = "O pedido já foi finalizado.";
            return false;
        }

        try {
            Conexao.iniciarTransacao();
            aplicarEvento(pedido, tipo, local, descricao, foto);
            Conexao.confirmar();
        } catch (Exception e) {
            falhou(e);
            return false;
        }
        return true;
    }

    public boolean registrarEntrega(Pedido pedido, String nome, String documento, String parentesco, String assinatura) {
        if (!pedidoCadastrado(pedido)) {
            return false;
        }
        if (pedido.getStatusPedido() != StatusPedido.EM_TRANSITO) {
            erro = "Só é possível entregar pedidos em trânsito.";
            return false;
        }
        if (vazio(nome) || vazio(documento)) {
            erro = "Informe o nome e o documento de quem recebeu.";
            return false;
        }
        if (vazio(assinatura)) {
            erro = "Informe a assinatura ou a foto do comprovante.";
            return false;
        }

        LocalDateTime agora = LocalDateTime.now();
        Entrega entrega = new Entrega(nome, documento, parentesco, agora.toLocalDate(), agora.toLocalTime(), assinatura);
        try {
            Conexao.iniciarTransacao();
            pedido.setEntrega(entrega);
            entregaDAO.insert(pedido, entrega);
            Rota rota = buscarRotaDoPedido(pedido);
            PedidoNaRota parada = buscarParada(pedido);
            if (parada != null) {
                parada.setHorarioRealDeEntrega(agora);
                pedidoNaRotaDAO.update(rota, parada);
            }
            aplicarEvento(pedido, TipoDeEvento.ENTREGUE, descreverEndereco(pedido.getEnderecoDeEntrega()),
                    "Entregue a " + nome, assinatura);
            Conexao.confirmar();
        } catch (Exception e) {
            falhou(e);
            return false;
        }
        return true;
    }

    public boolean registrarFalhaNaEntrega(Pedido pedido, String motivo, boolean novaTentativa) {
        if (!pedidoCadastrado(pedido)) {
            return false;
        }
        if (pedido.getStatusPedido() != StatusPedido.EM_TRANSITO) {
            erro = "Só é possível registrar falha em pedidos em trânsito.";
            return false;
        }
        if (vazio(motivo)) {
            erro = "Informe o motivo da falha.";
            return false;
        }

        FalhaNaEntrega falha = new FalhaNaEntrega(motivo, novaTentativa);
        try {
            Conexao.iniciarTransacao();
            pedido.adicionarFalha(falha);
            falhaNaEntregaDAO.insert(pedido, falha);
            if (!novaTentativa) {
                pedido.setStatusPedido(StatusPedido.CANCELADO);
            }
            pedidoDAO.update(pedido);
            Conexao.confirmar();
        } catch (Exception e) {
            falhou(e);
            return false;
        }
        return true;
    }

    public ArrayList<EventoDeRastreamento> rastrear(int numeroDoPedido) {
        Pedido pedido = buscarPedido(numeroDoPedido);
        if (pedido == null) {
            erro = "Pedido não encontrado.";
            return null;
        }
        ArrayList<EventoDeRastreamento> eventos = new ArrayList<>(pedido.getEventos());
        for (int i = 0; i < eventos.size() - 1; i++) {
            for (int j = 0; j < eventos.size() - 1 - i; j++) {
                EventoDeRastreamento a = eventos.get(j);
                EventoDeRastreamento b = eventos.get(j + 1);
                int comparacao = a.getData().compareTo(b.getData());
                if (comparacao == 0) {
                    comparacao = a.getHora().compareTo(b.getHora());
                }
                if (comparacao > 0) {
                    eventos.set(j, b);
                    eventos.set(j + 1, a);
                }
            }
        }
        return eventos;
    }

    public boolean enviarParaManutencao(Veiculo veiculo) {
        if (veiculo == null || !veiculos.contains(veiculo)) {
            erro = "Veículo não cadastrado.";
            return false;
        }
        if (veiculo.getStatusVeiculo() == StatusVeiculo.EM_ROTA) {
            erro = "O veículo está em rota.";
            return false;
        }
        if (veiculo.getStatusVeiculo() == StatusVeiculo.EM_MANUTENCAO) {
            erro = "O veículo já está em manutenção.";
            return false;
        }
        try {
            Conexao.iniciarTransacao();
            veiculo.setStatusVeiculo(StatusVeiculo.EM_MANUTENCAO);
            veiculoDAO.update(veiculo);
            Conexao.confirmar();
        } catch (Exception e) {
            falhou(e);
            return false;
        }
        return true;
    }

    public boolean registrarManutencao(Veiculo veiculo, TipoDeManutencao tipo, String servico, String pecas, String oficina, double valor, LocalDate proximaManutencao) {
        if (veiculo == null || !veiculos.contains(veiculo)) {
            erro = "Veículo não cadastrado.";
            return false;
        }
        if (tipo == null || vazio(servico) || vazio(oficina)) {
            erro = "Informe o tipo, a descrição do serviço e a oficina.";
            return false;
        }
        if (valor < 0) {
            erro = "Valor inválido.";
            return false;
        }
        if (veiculo.getStatusVeiculo() == StatusVeiculo.EM_ROTA) {
            erro = "O veículo está em rota.";
            return false;
        }

        Manutencao manutencao = new Manutencao(LocalDate.now(), tipo, veiculo.getQuilometragemAtual(),
                servico, pecas, oficina, valor, proximaManutencao);
        try {
            Conexao.iniciarTransacao();
            veiculo.getManutencoes().add(manutencao);
            if (proximaManutencao != null) {
                veiculo.setDataProximaManuntencao(proximaManutencao);
            }
            if (veiculo.getStatusVeiculo() == StatusVeiculo.EM_MANUTENCAO) {
                veiculo.setStatusVeiculo(StatusVeiculo.DISPONIVEL);
            }
            manutencaoDAO.insert(veiculo, manutencao);
            veiculoDAO.update(veiculo);
            Conexao.confirmar();
        } catch (Exception e) {
            falhou(e);
            return false;
        }
        return true;
    }

    public ArrayList<Veiculo> veiculosComManutencaoPendente() {
        ArrayList<Veiculo> resultado = new ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            LocalDate proxima = veiculo.getDataProximaManuntencao();
            if (proxima != null && !proxima.isAfter(LocalDate.now())) {
                resultado.add(veiculo);
            }
        }
        return resultado;
    }

    public ArrayList<Veiculo> veiculosDisponiveis(TipoDeVeiculo tipo) {
        ArrayList<Veiculo> resultado = new ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getStatusVeiculo() == StatusVeiculo.DISPONIVEL && veiculo.getTipoDeVeiculo() == tipo) {
                resultado.add(veiculo);
            }
        }
        return resultado;
    }

    public ArrayList<Motorista> motoristasHabilitados(TipoDeVeiculo tipo) {
        ArrayList<Motorista> resultado = new ArrayList<>();
        for (Motorista motorista : motoristas) {
            if (motorista.getSituacao() == Situacao.ATIVO
                    && motorista.getTipoDeVeiculosDisponiveis().contains(tipo)
                    && cnhValidaEm(motorista, LocalDate.now())) {
                resultado.add(motorista);
            }
        }
        return resultado;
    }

    private boolean vazio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    private String documentoDe(Pessoa pessoa) {
        if (pessoa instanceof PessoaFisica) {
            PessoaFisica fisica = (PessoaFisica) pessoa;
            return fisica.getCPF();
        }
        if (pessoa instanceof PessoaJuridica) {
            PessoaJuridica juridica = (PessoaJuridica) pessoa;
            return juridica.getCnpj();
        }
        return null;
    }

    private String normalizar(String documento) {
        if (documento == null) {
            return null;
        }
        return documento.replaceAll("\\D", "");
    }

    private String semAcento(String texto) {
        if (texto == null) {
            return "";
        }
        return Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("\\p{M}", "").toLowerCase();
    }

    private boolean cnhValidaEm(Motorista motorista, LocalDate data) {
        return motorista.getCnh() != null
                && motorista.getCnh().getDataDeValidade() != null
                && !motorista.getCnh().getDataDeValidade().isBefore(data);
    }

    private int diasDePrazo(TipoDeServico tipo) {
        if (tipo == TipoDeServico.EXPRESSO) {
            return 1;
        }
        if (tipo == TipoDeServico.AGENDADO) {
            return 3;
        }
        return 5;
    }

    private boolean aplicarEvento(Pedido pedido, TipoDeEvento tipo, String local, String descricao, String foto) throws InsertException, UpdateException {
        if (pedido.getStatusPedido() == StatusPedido.CANCELADO || pedido.getStatusPedido() == StatusPedido.ENTREGUE) {
            return false;
        }
        EventoDeRastreamento evento = new EventoDeRastreamento(LocalDate.now(), LocalTime.now(), tipo, local, descricao, foto);
        pedido.adicionarEvento(evento);

        StatusPedido novo = pedido.getStatusPedido();
        switch (tipo) {
            case COLETA_REALIZADA:
                novo = StatusPedido.COLETADO;
                break;
            case CARGA_EM_TRANSITO:
            case SAIU_PARA_ENTREGA:
                novo = StatusPedido.EM_TRANSITO;
                break;
            case ENTREGUE:
                novo = StatusPedido.ENTREGUE;
                break;
        }
        if (novo.ordinal() > pedido.getStatusPedido().ordinal()) {
            pedido.setStatusPedido(novo);
        }
        eventoDAO.insert(pedido, evento);
        pedidoDAO.update(pedido);
        return true;
    }

    private Rota buscarRotaDoPedido(Pedido pedido) {
        for (Rota rota : rotas) {
            for (PedidoNaRota parada : rota.getPedidos()) {
                if (parada.getPedido() == pedido) {
                    return rota;
                }
            }
        }
        return null;
    }

    private PedidoNaRota buscarParada(Pedido pedido) {
        for (Rota rota : rotas) {
            for (PedidoNaRota parada : rota.getPedidos()) {
                if (parada.getPedido() == pedido) {
                    return parada;
                }
            }
        }
        return null;
    }

    private String descreverEndereco(Endereco endereco) {
        if (endereco == null) {
            return "Endereço de entrega";
        }
        return endereco.getRua() + ", " + endereco.getNumeroDoLocal() + " - "
                + endereco.getCidade() + "/" + endereco.getEstado();
    }

    private boolean pedidoCadastrado(Pedido pedido) {
        if (pedido == null || !pedidos.contains(pedido)) {
            erro = "Pedido não cadastrado.";
            return false;
        }
        return true;
    }

    private boolean rotaCadastrada(Rota rota) {
        if (rota == null || !rotas.contains(rota)) {
            erro = "Rota não cadastrada.";
            return false;
        }
        return true;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(ArrayList<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public ArrayList<Motorista> getMotoristas() {
        return motoristas;
    }

    public void setMotoristas(ArrayList<Motorista> motoristas) {
        this.motoristas = motoristas;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public ArrayList<Rota> getRotas() {
        return rotas;
    }

    public void setRotas(ArrayList<Rota> rotas) {
        this.rotas = rotas;
    }

    public ArrayList<ConhecimentoTransporte> getConhecimentos() {
        return conhecimentos;
    }

    public void setConhecimentos(ArrayList<ConhecimentoTransporte> conhecimentos) {
        this.conhecimentos = conhecimentos;
    }

    public TabelaPreco getTabelaPadrao() {
        return tabelaPadrao;
    }

    public void setTabelaPadrao(TabelaPreco tabelaPadrao) {
        this.tabelaPadrao = tabelaPadrao;
    }

    public boolean atualizarTabelaPadrao(TabelaPreco tabela) {
        try {
            Conexao.iniciarTransacao();
            tabelaPrecoDAO.updatePadrao(tabela);
            Conexao.confirmar();
        } catch (Exception e) {
            falhou(e);
            return false;
        }
        tabelaPadrao = tabela;
        return true;
    }
}
