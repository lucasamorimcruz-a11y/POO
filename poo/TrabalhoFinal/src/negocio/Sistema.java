package negocio;

import dados.*;
import dados.enums.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map;

public class Sistema {

    // Alíquota aplicada sobre o frete no conhecimento de transporte (ajuste se a disciplina definir outra regra).
    private static final double ALIQUOTA_IMPOSTOS = 0.12;
    private static final String BASE = "Base da transportadora";

    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Veiculo> veiculos = new ArrayList<>();
    private ArrayList<Motorista> motoristas = new ArrayList<>();
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private ArrayList<Rota> rotas = new ArrayList<>();
    private ArrayList<ConhecimentoTransporte> conhecimentos = new ArrayList<>();
    private TabelaPreco tabelaPadrao;

    private int proximoNumeroPedido = 1;
    private int proximoNumeroRota = 1;
    private int proximoNumeroConhecimento = 1;

    // Começa com uma tabela de preços padrão; ela pode ser alterada na aba "Tabela de preços".
    public Sistema() {
        Map<TipoDeServico, Double> fatoresServico = new EnumMap<>(TipoDeServico.class);
        fatoresServico.put(TipoDeServico.ECONOMICO, 1.0);
        fatoresServico.put(TipoDeServico.EXPRESSO, 1.5);
        fatoresServico.put(TipoDeServico.AGENDADO, 1.2);

        Map<Regiao, Double> fatoresRegiao = new EnumMap<>(Regiao.class);
        fatoresRegiao.put(Regiao.LOCAL, 1.0);
        fatoresRegiao.put(Regiao.ESTADUAL, 1.3);
        fatoresRegiao.put(Regiao.INTERESTADUAL, 1.8);
        fatoresRegiao.put(Regiao.OUTRO, 2.0);

        tabelaPadrao = new TabelaPreco(2.0, 1.0, 50.0, 0.10, fatoresServico, fatoresRegiao);
    }

    // =====================================================
    // CADASTROS E BUSCAS
    // =====================================================

    public void cadastrarCliente (Cliente cliente){
        if (cliente == null || cliente.getPessoa() == null) {
            throw new IllegalArgumentException("Cliente inválido.");
        }
        String documento = normalizar(documentoDe(cliente.getPessoa()));
        if (documento == null || documento.isEmpty()) {
            throw new IllegalArgumentException("O cliente precisa ter CPF ou CNPJ.");
        }
        if (buscarCliente(documento) != null) {
            throw new IllegalArgumentException("Já existe um cliente com este documento.");
        }
        if (cliente.getTipoCliente() == TipoCliente.CONTRATADO && cliente.getTabelaDePreco() == null) {
            throw new IllegalArgumentException("Cliente contratado precisa de tabela de preços.");
        }
        clientes.add(cliente);
    }

    public void cadastrarMotorista (Motorista motorista){
        if (motorista == null) {
            throw new IllegalArgumentException("Motorista inválido.");
        }
        String cpf = normalizar(motorista.getCPF());
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("O motorista precisa ter CPF.");
        }
        if (buscarMotorista(cpf) != null) {
            throw new IllegalArgumentException("Já existe um motorista com este CPF.");
        }
        if (motorista.getCnh() == null || motorista.getCnh().getDataDeValidade() == null) {
            throw new IllegalArgumentException("O motorista precisa ter CNH com data de validade.");
        }
        if (!cnhValidaEm(motorista, LocalDate.now())) {
            throw new IllegalArgumentException("CNH vencida.");
        }
        motoristas.add(motorista);
    }

    public void cadastrarVeiculo (Veiculo veiculo){
        if (veiculo == null || veiculo.getPlaca() == null || veiculo.getPlaca().isBlank()) {
            throw new IllegalArgumentException("Veículo inválido: placa obrigatória.");
        }
        if (buscarVeiculo(veiculo.getPlaca()) != null) {
            throw new IllegalArgumentException("Já existe um veículo com esta placa.");
        }
        veiculos.add(veiculo);
    }

    // As buscas devolvem null quando não encontram.
    public Cliente buscarCliente (String documento){
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

    public Motorista buscarMotorista (String cpf) {
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

    public Veiculo buscarVeiculo (String placa ){
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

    public Pedido buscarPedido (int numero){
        for (Pedido pedido : pedidos) {
            if (pedido.getNumero() == numero) {
                return pedido;
            }
        }
        return null;
    }

    public Rota buscarRota (int numero){
        for (Rota rota : rotas) {
            if (rota.getNumero() == numero) {
                return rota;
            }
        }
        return null;
    }

    // =====================================================
    // PEDIDOS
    // =====================================================

    public Pedido criarPedido (Cliente cliente, Endereco coleta, String contatoColeta, LocalDate dataColeta, Periodo periodo, String descricao, double valorDeclarado, Endereco entrega, String contatoDestino, TipoDeServico tipo, FormaDePagamento formaDePagamento){
        if (cliente == null || !clientes.contains(cliente)) {
            throw new IllegalArgumentException("Cliente não cadastrado.");
        }
        if (coleta == null || entrega == null) {
            throw new IllegalArgumentException("Informe os endereços de coleta e de entrega.");
        }
        if (periodo == null || tipo == null || formaDePagamento == null) {
            throw new IllegalArgumentException("Período, tipo de serviço e forma de pagamento são obrigatórios.");
        }
        if (dataColeta == null || dataColeta.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data de coleta não pode estar no passado.");
        }
        if (valorDeclarado < 0) {
            throw new IllegalArgumentException("Valor declarado inválido.");
        }

        LocalDate prazoDeEntrega = dataColeta.plusDays(diasDePrazo(tipo));
        Pedido pedido = new Pedido(proximoNumeroPedido++, LocalDate.now(), cliente, coleta, contatoColeta,
                dataColeta, periodo, descricao, valorDeclarado, entrega, contatoDestino,
                prazoDeEntrega, tipo, formaDePagamento);
        pedidos.add(pedido);
        return pedido;
    }

    public void adicionarItem (Pedido pedido, Item item){
        exigirPedidoCadastrado(pedido);
        if (item == null || item.getPeso() <= 0) {
            throw new IllegalArgumentException("Item inválido: o peso deve ser maior que zero.");
        }
        if (pedido.getStatusPedido() != StatusPedido.PENDENTE) {
            throw new IllegalStateException("Só é possível adicionar itens a pedidos pendentes.");
        }
        pedido.adicionarItem(item);
    }

    // Calcula o frete pela tabela do cliente contratado (ou a tabela padrão) e grava no pedido.
    public double calcularFrete (Pedido pedido, double distanciaKm, Regiao regiao){
        exigirPedidoCadastrado(pedido);
        if (pedido.getStatusPedido() != StatusPedido.PENDENTE) {
            throw new IllegalStateException("O frete só pode ser calculado para pedidos pendentes.");
        }
        if (distanciaKm <= 0 || regiao == null) {
            throw new IllegalArgumentException("Informe uma distância maior que zero e a região de destino.");
        }
        if (pedido.getPesoTotal() <= 0) {
            throw new IllegalStateException("Adicione itens ao pedido antes de calcular o frete.");
        }

        Cliente cliente = pedido.getCliente();
        boolean contratado = cliente.getTipoCliente() == TipoCliente.CONTRATADO;
        TabelaPreco tabela = (contratado && cliente.getTabelaDePreco() != null)
                ? cliente.getTabelaDePreco()
                : tabelaPadrao;
        if (tabela == null) {
            throw new IllegalStateException("Nenhuma tabela de preços disponível.");
        }

        double valor = tabela.calcularFrete(pedido.getPesoTotal(), distanciaKm, pedido.getTipoDeServico(), regiao, contratado);
        pedido.setValorFrete(valor);
        return valor;
    }

    public void cancelarPedido (Pedido pedido){
        exigirPedidoCadastrado(pedido);
        if (pedido.getStatusPedido() != StatusPedido.PENDENTE) {
            throw new IllegalStateException("Só é possível cancelar pedidos pendentes.");
        }
        pedido.setStatusPedido(StatusPedido.CANCELADO);
    }

    public ArrayList<Pedido> pedidosDoCliente (Cliente cliente){
        ArrayList<Pedido> resultado = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.getCliente() == cliente) {
                resultado.add(pedido);
            }
        }
        return resultado;
    }

    public ArrayList<Pedido> pedidosComStatus (StatusPedido status){
        ArrayList<Pedido> resultado = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.getStatusPedido() == status) {
                resultado.add(pedido);
            }
        }
        return resultado;
    }

    public ArrayList<Rota> rotasComStatus (StatusRota status){
        ArrayList<Rota> resultado = new ArrayList<>();
        for (Rota rota : rotas) {
            if (rota.getStatus() == status) {
                resultado.add(rota);
            }
        }
        return resultado;
    }

    // =====================================================
    // COLETA E CONHECIMENTO DE TRANSPORTE
    // =====================================================

    public ConhecimentoTransporte registrarColeta (Pedido pedido, String local, String notaFiscal){
        exigirPedidoCadastrado(pedido);
        if (pedido.getStatusPedido() != StatusPedido.PENDENTE) {
            throw new IllegalStateException("A coleta só pode ser registrada para pedidos pendentes.");
        }
        if (pedido.getValorFrete() <= 0) {
            throw new IllegalStateException("Calcule o frete do pedido antes de registrar a coleta.");
        }
        if (local == null || local.isBlank() || notaFiscal == null || notaFiscal.isBlank()) {
            throw new IllegalArgumentException("Informe o local da coleta e a nota fiscal.");
        }

        double frete = pedido.getValorFrete();
        ConhecimentoTransporte conhecimento = new ConhecimentoTransporte(
                proximoNumeroConhecimento++,
                LocalDate.now(),
                pedido.getCliente().getPessoa().getNome(),
                pedido.getContatoNoDestino(),
                pedido.getDescricaoDaCarga(),
                (float) frete,
                (float) (frete * ALIQUOTA_IMPOSTOS),
                false,   // seguro não contratado por padrão (use setSeguroContratado/setValorSeguro se houver)
                0f,
                notaFiscal,
                pedido);

        conhecimentos.add(conhecimento);
        pedido.getConhecimentos().add(conhecimento);
        registrarEvento(pedido, TipoDeEvento.COLETA_REALIZADA, local, "Coleta realizada. Nota fiscal " + notaFiscal, null);
        return conhecimento;
    }

    public ConhecimentoTransporte buscarConhecimento (Pedido pedido){
        for (ConhecimentoTransporte conhecimento : conhecimentos) {
            if (conhecimento.getPedido() == pedido) {
                return conhecimento;
            }
        }
        return null;
    }

    // =====================================================
    // ROTAS
    // =====================================================

    public Rota criarRota (LocalDate data, Veiculo veiculo, Motorista motorista){
        if (data == null || data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data da rota não pode estar no passado.");
        }
        if (veiculo == null || !veiculos.contains(veiculo)) {
            throw new IllegalArgumentException("Veículo não cadastrado.");
        }
        if (motorista == null || !motoristas.contains(motorista)) {
            throw new IllegalArgumentException("Motorista não cadastrado.");
        }
        if (veiculo.getStatusVeiculo() != StatusVeiculo.DISPONIVEL) {
            throw new IllegalStateException("O veículo não está disponível.");
        }
        if (motorista.getSituacao() != Situacao.ATIVO) {
            throw new IllegalStateException("O motorista não está ativo.");
        }
        if (!motorista.podeDirigir(veiculo)) {
            throw new IllegalStateException("O motorista não está habilitado para este tipo de veículo.");
        }
        if (!cnhValidaEm(motorista, data)) {
            throw new IllegalStateException("A CNH do motorista estará vencida na data da rota.");
        }
        for (Rota existente : rotas) {
            boolean mesmoDia = existente.getData().equals(data);
            boolean aberta = existente.getStatus() != StatusRota.CONCLUIDA;
            boolean recursoRepetido = existente.getVeiculoAlocado() == veiculo
                    || existente.getMotoristaResponsavel() == motorista;
            if (mesmoDia && aberta && recursoRepetido) {
                throw new IllegalStateException("Veículo ou motorista já alocado em outra rota nesta data.");
            }
        }

        Rota rota = new Rota(proximoNumeroRota++, data, veiculo, motorista);
        rotas.add(rota);
        return rota;
    }

    public void adicionarPedidoARota (Rota rota, Pedido pedido, LocalDateTime horarioPrevisto){
        exigirRotaCadastrada(rota);
        exigirPedidoCadastrado(pedido);
        if (horarioPrevisto == null) {
            throw new IllegalArgumentException("Informe o horário previsto de entrega.");
        }
        if (rota.getStatus() != StatusRota.PLANEJADA) {
            throw new IllegalStateException("Só é possível adicionar pedidos a rotas planejadas.");
        }
        if (pedido.getStatusPedido() != StatusPedido.COLETADO) {
            throw new IllegalStateException("Apenas pedidos já coletados entram em rotas de entrega.");
        }
        if (buscarParada(pedido) != null) {
            throw new IllegalStateException("O pedido já está em uma rota.");
        }

        double pesoTotal = pedido.getPesoTotal();
        for (PedidoNaRota parada : rota.getPedidos()) {
            pesoTotal += parada.getPedido().getPesoTotal();
        }
        if (pesoTotal > rota.getVeiculoAlocado().getCapacidadeEmQuilos()) {
            throw new IllegalStateException("O peso total excede a capacidade do veículo.");
        }

        rota.adicionarPedido(pedido, horarioPrevisto);
    }

    public void iniciarRota (Rota rota, double kmInicial){
        exigirRotaCadastrada(rota);
        if (rota.getStatus() != StatusRota.PLANEJADA) {
            throw new IllegalStateException("Só é possível iniciar rotas planejadas.");
        }
        if (rota.getPedidos().isEmpty()) {
            throw new IllegalStateException("A rota não tem pedidos.");
        }
        Veiculo veiculo = rota.getVeiculoAlocado();
        if (veiculo.getStatusVeiculo() != StatusVeiculo.DISPONIVEL) {
            throw new IllegalStateException("O veículo não está disponível.");
        }
        if (kmInicial < veiculo.getQuilometragemAtual()) {
            throw new IllegalArgumentException("A quilometragem inicial não pode ser menor que a atual do veículo.");
        }

        rota.setQuilometragemInicial(kmInicial);
        rota.setStatus(StatusRota.EM_ANDAMENTO);
        veiculo.setStatusVeiculo(StatusVeiculo.EM_ROTA);
        for (PedidoNaRota parada : rota.getPedidos()) {
            registrarEvento(parada.getPedido(), TipoDeEvento.SAIU_PARA_ENTREGA, BASE,
                    "Saiu para entrega na rota " + rota.getNumero(), null);
        }
    }

    public void concluirRota (Rota rota, double kmFinal){
        exigirRotaCadastrada(rota);
        if (rota.getStatus() != StatusRota.EM_ANDAMENTO) {
            throw new IllegalStateException("Só é possível concluir rotas em andamento.");
        }
        if (kmFinal < rota.getQuilometragemInicial()) {
            throw new IllegalArgumentException("A quilometragem final não pode ser menor que a inicial.");
        }

        rota.setQuilometragemFinal(kmFinal);
        rota.setStatus(StatusRota.CONCLUIDA);
        Veiculo veiculo = rota.getVeiculoAlocado();
        veiculo.setQuilometragemAtual(kmFinal);
        veiculo.setStatusVeiculo(StatusVeiculo.DISPONIVEL);
    }

    public void registrarDespesa (Rota rota, LocalDate data, TipoDeDespesa tipo, double valor, String comprovante){
        exigirRotaCadastrada(rota);
        if (data == null || tipo == null) {
            throw new IllegalArgumentException("Informe a data e o tipo da despesa.");
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor da despesa deve ser maior que zero.");
        }
        if (rota.getStatus() == StatusRota.PLANEJADA) {
            throw new IllegalStateException("A rota ainda não foi iniciada.");
        }
        rota.adicionarDespesa(new Despesa(data, tipo, valor, comprovante));
    }

    // =====================================================
    // RASTREAMENTO, ENTREGA E FALHA
    // =====================================================

    // Registra o evento e avança o status do pedido (o status nunca volta para trás).
    public void registrarEvento (Pedido pedido, TipoDeEvento tipo, String local, String descricao, String foto){
        exigirPedidoCadastrado(pedido);
        if (tipo == null || local == null || local.isBlank()) {
            throw new IllegalArgumentException("Informe o tipo do evento e a localização.");
        }
        if (pedido.getStatusPedido() == StatusPedido.CANCELADO || pedido.getStatusPedido() == StatusPedido.ENTREGUE) {
            throw new IllegalStateException("O pedido já foi finalizado.");
        }

        pedido.adicionarEvento(new EventoDeRastreamento(LocalDate.now(), LocalTime.now(), tipo, local, descricao, foto));

        StatusPedido novo = switch (tipo) {
            case COLETA_REALIZADA -> StatusPedido.COLETADO;
            case CARGA_EM_TRANSITO, SAIU_PARA_ENTREGA -> StatusPedido.EM_TRANSITO;
            case ENTREGUE -> StatusPedido.ENTREGUE;
            default -> pedido.getStatusPedido();
        };
        if (novo.ordinal() > pedido.getStatusPedido().ordinal()) {
            pedido.setStatusPedido(novo);
        }
    }

    public void registrarEntrega (Pedido pedido, String nome, String documento, String parentesco, String assinatura){
        exigirPedidoCadastrado(pedido);
        if (pedido.getStatusPedido() != StatusPedido.EM_TRANSITO) {
            throw new IllegalStateException("Só é possível entregar pedidos em trânsito.");
        }
        if (nome == null || nome.isBlank() || documento == null || documento.isBlank()) {
            throw new IllegalArgumentException("Informe o nome e o documento de quem recebeu.");
        }
        if (assinatura == null || assinatura.isBlank()) {
            throw new IllegalArgumentException("Informe a assinatura ou a foto do comprovante.");
        }

        LocalDateTime agora = LocalDateTime.now();
        pedido.setEntrega(new Entrega(nome, documento, parentesco, agora.toLocalDate(), agora.toLocalTime(), assinatura));

        PedidoNaRota parada = buscarParada(pedido);
        if (parada != null) {
            parada.setHorarioRealDeEntrega(agora);
        }
        registrarEvento(pedido, TipoDeEvento.ENTREGUE, descreverEndereco(pedido.getEnderecoDeEntrega()),
                "Entregue a " + nome, assinatura);
    }

    // Sem nova tentativa o pedido é encerrado como CANCELADO (único status final disponível para falhas).
    public void registrarFalhaNaEntrega (Pedido pedido, String motivo, boolean novaTentativa){
        exigirPedidoCadastrado(pedido);
        if (pedido.getStatusPedido() != StatusPedido.EM_TRANSITO) {
            throw new IllegalStateException("Só é possível registrar falha em pedidos em trânsito.");
        }
        if (motivo == null || motivo.isBlank()) {
            throw new IllegalArgumentException("Informe o motivo da falha.");
        }

        pedido.adicionarFalha(new FalhaNaEntrega(motivo, novaTentativa));
        if (!novaTentativa) {
            pedido.setStatusPedido(StatusPedido.CANCELADO);
        }
    }

    // Devolve os eventos em ordem cronológica.
    public ArrayList<EventoDeRastreamento> rastrear (int numeroDoPedido){
        Pedido pedido = buscarPedido(numeroDoPedido);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado.");
        }
        ArrayList<EventoDeRastreamento> eventos = new ArrayList<>(pedido.getEventos());
        eventos.sort(Comparator.comparing(EventoDeRastreamento::getData)
                .thenComparing(EventoDeRastreamento::getHora));
        return eventos;
    }

    // =====================================================
    // MANUTENÇÃO E CONSULTAS DA FROTA
    // =====================================================

    public void registrarManutencao (Veiculo veiculo, TipoDeManutencao tipo,String servico, String pecas, String oficina, double valor, LocalDate proximaManutencao){
        if (veiculo == null || !veiculos.contains(veiculo)) {
            throw new IllegalArgumentException("Veículo não cadastrado.");
        }
        if (tipo == null || servico == null || servico.isBlank() || oficina == null || oficina.isBlank()) {
            throw new IllegalArgumentException("Informe o tipo, a descrição do serviço e a oficina.");
        }
        if (valor < 0) {
            throw new IllegalArgumentException("Valor inválido.");
        }
        if (veiculo.getStatusVeiculo() == StatusVeiculo.EM_ROTA) {
            throw new IllegalStateException("O veículo está em rota.");
        }

        veiculo.getManutencoes().add(new Manutencao(LocalDate.now(), tipo, veiculo.getQuilometragemAtual(),
                servico, pecas, oficina, valor, proximaManutencao));
        if (proximaManutencao != null) {
            veiculo.setDataProximaManuntencao(proximaManutencao);
        }
    }

    // Manutenção pendente = data da próxima manutenção é hoje ou já passou.
    public ArrayList<Veiculo> veiculosComManutencaoPendente (){
        ArrayList<Veiculo> resultado = new ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            LocalDate proxima = veiculo.getDataProximaManuntencao();
            if (proxima != null && !proxima.isAfter(LocalDate.now())) {
                resultado.add(veiculo);
            }
        }
        return resultado;
    }

    public ArrayList<Veiculo> veiculosDisponiveis (TipoDeVeiculo tipo){
        ArrayList<Veiculo> resultado = new ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getStatusVeiculo() == StatusVeiculo.DISPONIVEL && veiculo.getTipoDeVeiculo() == tipo) {
                resultado.add(veiculo);
            }
        }
        return resultado;
    }

    public ArrayList<Motorista> motoristasHabilitados (TipoDeVeiculo tipo){
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

    // =====================================================
    // MÉTODOS AUXILIARES (privados)
    // =====================================================

    private String documentoDe(Pessoa pessoa) {
        if (pessoa instanceof PessoaFisica fisica) {
            return fisica.getCPF();
        }
        if (pessoa instanceof PessoaJuridica juridica) {
            return juridica.getCnpj();
        }
        return null;
    }

    // Deixa só os dígitos, para "123.456.789-00" e "12345678900" serem o mesmo documento.
    private String normalizar(String documento) {
        return documento == null ? null : documento.replaceAll("\\D", "");
    }

    private boolean cnhValidaEm(Motorista motorista, LocalDate data) {
        return motorista.getCnh() != null
                && motorista.getCnh().getDataDeValidade() != null
                && !motorista.getCnh().getDataDeValidade().isBefore(data);
    }

    // Prazo de entrega, em dias a partir da coleta, conforme o tipo de serviço.
    private int diasDePrazo(TipoDeServico tipo) {
        return switch (tipo) {
            case EXPRESSO -> 1;
            case AGENDADO -> 3;
            case ECONOMICO -> 5;
        };
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

    private void exigirPedidoCadastrado(Pedido pedido) {
        if (pedido == null || !pedidos.contains(pedido)) {
            throw new IllegalArgumentException("Pedido não cadastrado.");
        }
    }

    private void exigirRotaCadastrada(Rota rota) {
        if (rota == null || !rotas.contains(rota)) {
            throw new IllegalArgumentException("Rota não cadastrada.");
        }
    }

    // =====================================================
    // GETTERS E SETTERS
    // =====================================================

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
}
