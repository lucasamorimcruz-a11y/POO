package negocio;

import dados.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/** Camada de negócio: manipula as listas de objetos do pacote de dados. */
public class Sistema {

    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Motorista> motoristas = new ArrayList<>();
    private final List<Veiculo> veiculos = new ArrayList<>();
    private final List<Pedido> pedidos = new ArrayList<>();
    private final List<Rota> rotas = new ArrayList<>();
    private final List<ConhecimentoTransporte> conhecimentos = new ArrayList<>();
    private int proximoPedido = 1;
    private int proximoConhecimento = 1;
    private int proximaRota = 1;
    public void cadastrarCliente(Cliente c) {
        if (c == null) {
            System.out.println("Aviso: Cliente inválido.");
            return;
        }
        String doc = c.getPessoa().getDocumento();
        if (buscarCliente(doc) != null) {
            System.out.println("Aviso: Cliente já cadastrado: " + doc);
            return;
        }
        clientes.add(c);
    }
    public void cadastrarMotorista(Motorista m) {
        if (m == null) {
            System.out.println("Aviso: Motorista inválido.");
            return;
        }
        if (buscarMotorista(m.getCpf()) != null) {
            System.out.println("Aviso: Motorista já cadastrado: " + m.getCpf());
            return;
        }
        motoristas.add(m);
    }
    public void cadastrarVeiculo(Veiculo v) {
        if (v == null) {
            System.out.println("Aviso: Veículo inválido.");
            return;
        }
        if (buscarVeiculo(v.getPlaca()) != null) {
            System.out.println("Aviso: Veículo já cadastrado: " + v.getPlaca());
            return;
        }

        veiculos.add(v);
    }

    public Cliente buscarCliente(String documento) {
        return clientes.stream()
                .filter(c -> c.getPessoa().getDocumento().equals(documento))
                .findFirst()
                .orElse(null);
    }

    public Motorista buscarMotorista(String cpf) {
        return motoristas.stream()
                .filter(m -> m.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }

    public Veiculo buscarVeiculo(String placa) {
        return veiculos.stream()
                .filter(v -> v.getPlaca().equalsIgnoreCase(placa))
                .findFirst()
                .orElse(null);
    }

    public Pedido buscarPedido(int numero) {
        return pedidos.stream()
                .filter(p -> p.getNumero() == numero)
                .findFirst()
                .orElse(null);
    }

    public Pedido criarPedido(
            Cliente cliente,
            Endereco coleta,
            String contatoColeta,
            LocalDate dataColeta,
            Periodo periodo,
            String descricao,
            double valorDeclarado,
            Endereco entrega,
            String contatoDestino,
            LocalDate prazo,
            TipoDeServico tipo,
            String formaPagamento) {

        if (cliente == null || !clientes.contains(cliente)) {
            System.out.println("Aviso: Cliente não cadastrado.");
            return null;
        }

        Pedido p = new Pedido(
                proximoPedido++, cliente, coleta, contatoColeta,
                dataColeta, periodo, descricao, valorDeclarado,
                entrega, contatoDestino, prazo, tipo, formaPagamento
        );

        pedidos.add(p);
        return p;
    }

    public void adicionarItem(Pedido p, Item item) {
        if (p == null) {
            System.out.println("Aviso: Pedido inválido.");
            return;
        }

        if (p.getStatus() != StatusPedido.PENDENTE) {
            System.out.println("Aviso: Só é possível alterar itens de pedidos pendentes.");
            return;
        }

        p.adicionarItem(item);
    }

    public double calcularFrete(Pedido p, double distanciaKm) {
        if (p == null) {
            System.out.println("Aviso: Pedido inválido.");
            return -1.0;
        }

        Cliente c = p.getCliente();

        double frete = c.getTabelaDePreco().calculaFrete(
                p.getPesoTotal(),
                distanciaKm,
                p.getTipoDeServico(),
                p.getEnderecoDeEntrega().getRegiao()
        );

        // Ajuste esta condição conforme os atributos existentes em Cliente.
        // Se não existir isContratado(), substitua pela condição correta.
        if (c.isContratado() && frete > c.getLimiteDeCredito()) {
            System.out.println("Aviso: Limite de crédito excedido.");
            return -1.0;
        }

        p.setValorDeFrete(frete);
        return frete;
    }

    public void cancelarPedido(Pedido p) {
        if (p == null) {
            System.out.println("Aviso: Pedido inválido.");
            return;
        }

        if (p.getStatus() == StatusPedido.ENTREGUE
                || p.getStatus() == StatusPedido.EM_TRANSITO) {
            System.out.println("Aviso: Não é possível cancelar um pedido entregue ou em trânsito.");
            return;
        }

        p.setStatus(StatusPedido.CANCELADO);
    }

    public List<Pedido> pedidosPorStatus(StatusPedido status) {
        return pedidos.stream()
                .filter(p -> p.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Pedido> pedidosDoCliente(Cliente c) {
        return pedidos.stream()
                .filter(p -> p.getCliente() == c)
                .collect(Collectors.toList());
    }

    public ConhecimentoTransporte registrarColeta(
            Pedido p,
            String local,
            boolean contratarSeguro,
            String notaFiscal) {

        if (p == null) {
            System.out.println("Aviso: Pedido inválido.");
            return null;
        }

        if (p.getStatus() != StatusPedido.PENDENTE) {
            System.out.println("Aviso: Apenas pedidos pendentes podem ser coletados.");
            return null;
        }

        if (p.getItens().isEmpty()) {
            System.out.println("Aviso: Pedido sem itens.");
            return null;
        }

        p.setStatus(StatusPedido.COLETADO);

        registrarEvento(
                p, Evento.COLETA_REALIZADA, local,
                "Mercadoria coletada.", null
        );

        ConhecimentoTransporte ct = new ConhecimentoTransporte(
                proximoConhecimento++,
                LocalDate.now(),
                p.getCliente().getPessoa().getNome(),
                p.getContatoNoDestino(),
                p.getDescricaoDaCarga(),
                p.getValorDeFrete(),
                p.getValorDeFrete() * 0.12,
                contratarSeguro ? p.getValorDaMercadoria() * 0.01 : 0,
                notaFiscal,
                p
        );

        conhecimentos.add(ct);
        return ct;
    }

    public ConhecimentoTransporte buscarConhecimento(Pedido p) {
        return conhecimentos.stream()
                .filter(c -> c.getPedido() == p)
                .findFirst()
                .orElse(null);
    }

    public Rota criarRota(LocalDate data, Veiculo v, Motorista m) {
        if (v == null || m == null) {
            System.out.println("Aviso: Veículo ou motorista inválido.");
            return null;
        }

        if (v.getStatus() != StatusVeiculo.DISPONIVEL) {
            System.out.println("Aviso: Veículo indisponível: " + v);
            return null;
        }

        if (!m.podeDirigir(v)) {
            System.out.println("Aviso: Motorista " + m.getNome()
                    + " não pode dirigir " + v);
            return null;
        }

        Rota r = new Rota(proximaRota++, data, v, m);
        rotas.add(r);
        return r;
    }

    public void adicionarPedidoARota(Rota r, Pedido p) {
        if (r == null || p == null) {
            System.out.println("Aviso: Rota ou pedido inválido.");
            return;
        }

        if (r.getStatus() != StatusRota.PLANEJADA) {
            System.out.println("Aviso: Rota já iniciada.");
            return;
        }

        if (p.getStatus() != StatusPedido.COLETADO) {
            System.out.println("Aviso: Somente pedidos coletados entram em rota.");
            return;
        }

        Veiculo v = r.getVeiculoAlocado();

        if (r.getPesoTotal() + p.getPesoTotal() > v.getCapacidadeEmQuilos()) {
            System.out.println("Aviso: Capacidade em quilos excedida.");
            return;
        }

        if (r.getVolumeTotal() + p.getVolumeTotal()
                > v.getCapacidadeEmMetroCubicos()) {
            System.out.println("Aviso: Capacidade em m³ excedida.");
            return;
        }

        if (r.getPedidos().contains(p)) {
            System.out.println("Aviso: Pedido já adicionado à rota.");
            return;
        }

        r.getPedidos().add(p);
    }

    public void iniciarRota(Rota r, String local) {
        if (r == null) {
            System.out.println("Aviso: Rota inválida.");
            return;
        }

        if (r.getStatus() != StatusRota.PLANEJADA
                || r.getPedidos().isEmpty()) {
            System.out.println("Aviso: Rota não pode ser iniciada.");
            return;
        }

        r.setStatus(StatusRota.EM_ANDAMENTO);
        r.setQuilometragemInicial(
                r.getVeiculoAlocado().getQuilometragemAtual()
        );
        r.getVeiculoAlocado().setStatus(StatusVeiculo.EM_ROTA);

        int hora = 9;

        for (Pedido p : r.getPedidos()) {
            p.setStatus(StatusPedido.EM_TRANSITO);
            p.setHorarioPrevistoDeEntrega(r.getData().atTime(hora++, 0));

            registrarEvento(
                    p, Evento.CARGA_EM_TRANSITO,
                    local, "Carga em trânsito.", null
            );

            registrarEvento(
                    p, Evento.SAIU_PRA_ENTREGA,
                    local, "Saiu para entrega.", null
            );
        }
    }

    public void concluirRota(Rota r, double kmFinal) {
        if (r == null) {
            System.out.println("Aviso: Rota inválida.");
            return;
        }

        if (r.getStatus() != StatusRota.EM_ANDAMENTO) {
            System.out.println("Aviso: Rota não está em andamento.");
            return;
        }

        if (kmFinal < r.getQuilometragemInicial()) {
            System.out.println("Aviso: Quilometragem final menor que a inicial.");
            return;
        }

        r.setQuilometragemFinal(kmFinal);
        r.setStatus(StatusRota.CONCLUIDA);
        r.getVeiculoAlocado().setQuilometragemAtual(kmFinal);
        r.getVeiculoAlocado().setStatus(StatusVeiculo.DISPONIVEL);
    }

    public void registrarDespesa(
            Rota r, TipoDespesa tipo, double valor, String comprovante) {

        if (r == null) {
            System.out.println("Aviso: Rota inválida.");
            return;
        }

        if (valor < 0) {
            System.out.println("Aviso: O valor da despesa não pode ser negativo.");
            return;
        }

        r.getDespesas().add(
                new DespesaRota(LocalDate.now(), tipo, valor, comprovante)
        );
    }

    public double totalDespesas(Rota r) {
        if (r == null) {
            System.out.println("Aviso: Rota inválida.");
            return -1.0;
        }

        return r.getDespesas().stream()
                .mapToDouble(DespesaRota::getValor)
                .sum();
    }

    public void registrarEvento(
            Pedido p, Evento e, String local, String descricao, String foto) {

        if (p == null || e == null) {
            System.out.println("Aviso: Pedido ou evento inválido.");
            return;
        }

        p.adicionarEvento(
                new EventoRastreamento(
                        e, LocalDateTime.now(), local, descricao, foto
                )
        );
    }

    public void registrarEntrega(
            Pedido p,
            String nome,
            String documento,
            String parentesco,
            String assinatura,
            String local) {

        if (p == null) {
            System.out.println("Aviso: Pedido inválido.");
            return;
        }

        if (p.getStatus() != StatusPedido.EM_TRANSITO) {
            System.out.println("Aviso: Pedido não está em trânsito.");
            return;
        }

        LocalDateTime agora = LocalDateTime.now();

        p.setEntrega(
                new Entrega(
                        true, nome, documento, parentesco,
                        agora, assinatura, null, false
                )
        );

        p.setHorarioRealDeEntrega(agora);
        p.setStatus(StatusPedido.ENTREGUE);

        registrarEvento(
                p, Evento.ENTREGUE, local,
                "Recebido por " + nome + " (" + parentesco + ").",
                assinatura
        );
    }

    public void registrarFalhaEntrega(
            Pedido p,
            Evento motivo,
            String descricao,
            boolean novaTentativa,
            String local) {

        if (p == null || motivo == null) {
            System.out.println("Aviso: Pedido ou motivo inválido.");
            return;
        }

        if (p.getStatus() != StatusPedido.EM_TRANSITO) {
            System.out.println("Aviso: Pedido não está em trânsito.");
            return;
        }

        if (motivo == Evento.ENTREGUE
                || motivo == Evento.COLETA_REALIZADA
                || motivo == Evento.CARGA_EM_TRANSITO
                || motivo == Evento.SAIU_PRA_ENTREGA) {
            System.out.println("Aviso: Evento não representa falha de entrega.");
            return;
        }

        p.setEntrega(
                new Entrega(
                        false, null, null, null,
                        LocalDateTime.now(), null, descricao, novaTentativa
                )
        );

        registrarEvento(p, motivo, local, descricao, null);

        if (!novaTentativa) {
            p.setStatus(StatusPedido.CANCELADO);
        }
    }

    public List<EventoRastreamento> rastrear(int numeroPedido) {
        Pedido p = buscarPedido(numeroPedido);

        if (p == null) {
            System.out.println("Aviso: Pedido inexistente.");
            return Collections.emptyList();
        }

        return p.getEventos();
    }

    public void registrarManutencao(
            Veiculo v,
            TipoManutencao tipo,
            String servicos,
            String pecas,
            String oficina,
            double valor,
            LocalDate proxima) {

        if (v == null) {
            System.out.println("Aviso: Veículo inválido.");
            return;
        }

        if (v.getStatus() == StatusVeiculo.EM_ROTA) {
            System.out.println("Aviso: Veículo em rota.");
            return;
        }

        if (valor < 0) {
            System.out.println("Aviso: O valor da manutenção não pode ser negativo.");
            return;
        }

        v.getManutencoes().add(
                new Manutencao(
                        LocalDate.now(),
                        tipo,
                        v.getQuilometragemAtual(),
                        servicos,
                        pecas,
                        oficina,
                        valor,
                        proxima
                )
        );

        v.setDataProximaManutencao(proxima);
        v.setStatus(StatusVeiculo.DISPONIVEL);
    }

    public List<Veiculo> veiculosComManutencaoPendente() {
        return veiculos.stream()
                .filter(v -> v.getDataProximaManutencao() != null
                        && !v.getDataProximaManutencao().isAfter(LocalDate.now()))
                .collect(Collectors.toList());
    }

    public List<Veiculo> veiculosDisponiveis(TipoDeVeiculo tipo) {
        return veiculos.stream()
                .filter(v -> v.getStatus() == StatusVeiculo.DISPONIVEL
                        && v.getTipoDeVeiculo() == tipo)
                .collect(Collectors.toList());
    }

    public List<Motorista> motoristasHabilitados(TipoDeVeiculo tipo) {
        return motoristas.stream()
                .filter(m -> m.getSituacao() == Situacao.ATIVO
                        && m.getTiposDeVeiculosDisponiveis().contains(tipo))
                .collect(Collectors.toList());
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Motorista> getMotoristas() {
        return motoristas;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public List<Rota> getRotas() {
        return rotas;
    }

    public List<ConhecimentoTransporte> getConhecimentos() {
        return conhecimentos;
    }
}
