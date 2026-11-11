package apresentacao;

import dados.*;
import dados.enums.Situacao;
import dados.enums.StatusPedido;
import dados.enums.StatusVeiculo;
import dados.enums.TipoDeDespesa;
import negocio.Sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class PainelRotas extends JPanel implements ActionListener {

    private Sistema sistema;

    private JTextField campoData = new JTextField(10);
    private JComboBox<Veiculo> comboVeiculo = new JComboBox<>();
    private JComboBox<Motorista> comboMotorista = new JComboBox<>();
    private JButton botaoCriarRota = new JButton("Criar rota");

    private JComboBox<Pedido> comboPedido = new JComboBox<>();
    private JTextField campoHorarioPrevisto = new JTextField(10);
    private JButton botaoAdicionarPedido = new JButton("Adicionar à rota");

    private JTextField campoKm = new JTextField(10);
    private JButton botaoIniciar = new JButton("Iniciar rota");
    private JButton botaoConcluir = new JButton("Concluir rota");

    private JComboBox<TipoDeDespesa> comboTipoDespesa = new JComboBox<>(TipoDeDespesa.values());
    private JTextField campoDataDespesa = new JTextField(10);
    private JTextField campoValorDespesa = new JTextField(10);
    private JTextField campoComprovante = new JTextField(10);
    private JButton botaoRegistrarDespesa = new JButton("Registrar despesa");

    private TabelaRotas modeloRotas = new TabelaRotas();
    private JTable tabelaRotas = new JTable(modeloRotas);
    private TabelaParadas modeloParadas = new TabelaParadas();
    private JTable tabelaParadas = new JTable(modeloParadas);
    private TabelaDespesas modeloDespesas = new TabelaDespesas();
    private JTable tabelaDespesas = new JTable(modeloDespesas);

    public PainelRotas(Sistema sistema) {
        super(new BorderLayout(5, 5));
        this.sistema = sistema;
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JPanel nova = new JPanel(new GridLayout(0, 2, 5, 5));
        nova.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "1. Nova rota"));
        Conversor.adicionar(nova, "Data (dd/mm/aaaa):", campoData);
        Conversor.adicionar(nova, "Veículo disponível:", comboVeiculo);
        Conversor.adicionar(nova, "Motorista ativo:", comboMotorista);
        Conversor.adicionar(nova, "", botaoCriarRota);

        JPanel pedido = new JPanel(new GridLayout(0, 2, 5, 5));
        pedido.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "2. Pedidos da rota selecionada"));
        Conversor.adicionar(pedido, "Pedido coletado:", comboPedido);
        Conversor.adicionar(pedido, "Entrega prevista (dd/mm/aaaa hh:mm):", campoHorarioPrevisto);
        Conversor.adicionar(pedido, "", botaoAdicionarPedido);

        JPanel andamento = new JPanel(new GridLayout(0, 2, 5, 5));
        andamento.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "3. Andamento da rota selecionada"));
        Conversor.adicionar(andamento, "Quilometragem:", campoKm);
        Conversor.adicionar(andamento, "", botaoIniciar);
        Conversor.adicionar(andamento, "", botaoConcluir);

        JPanel despesa = new JPanel(new GridLayout(0, 2, 5, 5));
        despesa.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "4. Despesas da rota selecionada"));
        Conversor.adicionar(despesa, "Tipo:", comboTipoDespesa);
        Conversor.adicionar(despesa, "Data:", campoDataDespesa);
        Conversor.adicionar(despesa, "Valor (R$):", campoValorDespesa);
        Conversor.adicionar(despesa, "Comprovante:", campoComprovante);
        Conversor.adicionar(despesa, "", botaoRegistrarDespesa);

        JPanel formularios = new JPanel(new GridLayout(2, 2, 5, 5));
        formularios.add(Conversor.topo(nova));
        formularios.add(Conversor.topo(pedido));
        formularios.add(Conversor.topo(andamento));
        formularios.add(Conversor.topo(despesa));
        Conversor.configurar(tabelaRotas);
        Conversor.configurar(tabelaParadas);
        Conversor.configurar(tabelaDespesas);

        JScrollPane rolagemRotas = new JScrollPane(tabelaRotas);
        rolagemRotas.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Rotas"));
        JScrollPane rolagemParadas = new JScrollPane(tabelaParadas);
        rolagemParadas.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Pedidos na rota (por sequência de entrega)"));
        JScrollPane rolagemDespesas = new JScrollPane(tabelaDespesas);
        rolagemDespesas.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Despesas"));

        JPanel detalhes = new JPanel(new GridLayout(1, 2, 5, 5));
        detalhes.add(rolagemParadas);
        detalhes.add(rolagemDespesas);

        JPanel tabelas = new JPanel(new GridLayout(2, 1, 5, 5));
        tabelas.add(rolagemRotas);
        tabelas.add(detalhes);

        add(formularios, BorderLayout.NORTH);
        add(tabelas, BorderLayout.CENTER);

        botaoCriarRota.addActionListener(this);
        botaoAdicionarPedido.addActionListener(this);
        botaoIniciar.addActionListener(this);
        botaoConcluir.addActionListener(this);
        botaoRegistrarDespesa.addActionListener(this);
        tabelaRotas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                atualizarDetalhes();
            }
        });

        atualizar();
    }

    public void atualizar() {
        comboVeiculo.removeAllItems();
        for (Veiculo veiculo : sistema.getVeiculos()) {
            if (veiculo.getStatusVeiculo() == StatusVeiculo.DISPONIVEL) {
                comboVeiculo.addItem(veiculo);
            }
        }
        comboMotorista.removeAllItems();
        for (Motorista motorista : sistema.getMotoristas()) {
            if (motorista.getSituacao() == Situacao.ATIVO) {
                comboMotorista.addItem(motorista);
            }
        }
        comboPedido.removeAllItems();
        for (Pedido pedido : sistema.pedidosComStatus(StatusPedido.COLETADO)) {
            if (!estaEmAlgumaRota(pedido)) {
                comboPedido.addItem(pedido);
            }
        }

        int linha = tabelaRotas.getSelectedRow();
        Rota selecionada = linha >= 0 ? modeloRotas.getRota(linha) : null;
        modeloRotas.setRotas(new ArrayList<>(sistema.getRotas()));
        selecionar(selecionada);
        atualizarDetalhes();
    }

    private boolean estaEmAlgumaRota(Pedido pedido) {
        for (Rota rota : sistema.getRotas()) {
            for (PedidoNaRota parada : rota.getPedidos()) {
                if (parada.getPedido() == pedido) {
                    return true;
                }
            }
        }
        return false;
    }

    private void selecionar(Rota rota) {
        int linha = rota == null ? -1 : modeloRotas.getLinha(rota);
        if (linha >= 0) {
            tabelaRotas.setRowSelectionInterval(linha, linha);
        }
    }

    private void atualizarDetalhes() {
        int linha = tabelaRotas.getSelectedRow();
        if (linha < 0) {
            modeloParadas.setParadas(new ArrayList<>());
            modeloDespesas.setDespesas(new ArrayList<>());
        } else {
            Rota rota = modeloRotas.getRota(linha);
            modeloParadas.setParadas(new ArrayList<>(rota.getPedidos()));
            modeloDespesas.setDespesas(new ArrayList<>(rota.getDespesas()));
        }
    }

    private Rota rotaSelecionada() {
        int linha = tabelaRotas.getSelectedRow();
        if (linha < 0) {
            throw new IllegalArgumentException("Selecione uma rota na tabela.");
        }
        return modeloRotas.getRota(linha);
    }

    private void criarRota() {
        try {
            LocalDate data = Conversor.data(campoData, "Data da rota");
            Veiculo veiculo = (Veiculo) comboVeiculo.getSelectedItem();
            Motorista motorista = (Motorista) comboMotorista.getSelectedItem();
            if (veiculo == null || motorista == null) {
                throw new IllegalArgumentException("É preciso ter um veículo disponível e um motorista ativo.");
            }
            Rota rota = sistema.criarRota(data, veiculo, motorista);
            Conversor.info(this, "Rota nº " + rota.getNumero() + " criada.");
            campoData.setText("");
            atualizar();
            selecionar(rota);
        } catch (IllegalArgumentException | IllegalStateException e) {
            Conversor.erro(this, e.getMessage());
        }
    }

    private void adicionarPedido() {
        try {
            Rota rota = rotaSelecionada();
            Pedido pedido = (Pedido) comboPedido.getSelectedItem();
            if (pedido == null) {
                throw new IllegalArgumentException("Não há pedidos coletados disponíveis.");
            }
            sistema.adicionarPedidoARota(rota, pedido,
                    Conversor.dataHora(campoHorarioPrevisto, "Entrega prevista"));
            campoHorarioPrevisto.setText("");
            atualizar();
            selecionar(rota);
            atualizarDetalhes();
        } catch (IllegalArgumentException | IllegalStateException e) {
            Conversor.erro(this, e.getMessage());
        }
    }

    private void iniciarRota() {
        try {
            Rota rota = rotaSelecionada();
            sistema.iniciarRota(rota, Conversor.decimal(campoKm, "Quilometragem inicial"));
            campoKm.setText("");
            atualizar();
            selecionar(rota);
        } catch (IllegalArgumentException | IllegalStateException e) {
            Conversor.erro(this, e.getMessage());
        }
    }

    private void concluirRota() {
        try {
            Rota rota = rotaSelecionada();
            sistema.concluirRota(rota, Conversor.decimal(campoKm, "Quilometragem final"));
            campoKm.setText("");
            atualizar();
            selecionar(rota);
        } catch (IllegalArgumentException | IllegalStateException e) {
            Conversor.erro(this, e.getMessage());
        }
    }

    private void registrarDespesa() {
        try {
            Rota rota = rotaSelecionada();
            sistema.registrarDespesa(rota, Conversor.data(campoDataDespesa, "Data da despesa"),
                    (TipoDeDespesa) comboTipoDespesa.getSelectedItem(),
                    Conversor.decimal(campoValorDespesa, "Valor da despesa"),
                    Conversor.opcional(campoComprovante));
            campoDataDespesa.setText("");
            campoValorDespesa.setText("");
            campoComprovante.setText("");
            atualizar();
            selecionar(rota);
            atualizarDetalhes();
        } catch (IllegalArgumentException | IllegalStateException e) {
            Conversor.erro(this, e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        Object fonte = evento.getSource();
        if (fonte == botaoCriarRota) {
            criarRota();
        } else if (fonte == botaoAdicionarPedido) {
            adicionarPedido();
        } else if (fonte == botaoIniciar) {
            iniciarRota();
        } else if (fonte == botaoConcluir) {
            concluirRota();
        } else if (fonte == botaoRegistrarDespesa) {
            registrarDespesa();
        }
    }
}
