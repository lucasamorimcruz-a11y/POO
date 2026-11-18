package apresentacao;

import dados.Pedido;
import dados.enums.StatusPedido;
import dados.enums.TipoDeEvento;
import negocio.Sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PainelEntregas extends JPanel implements ActionListener {

    private Sistema sistema;

    private JTextField campoNomeRecebedor = new JTextField(10);
    private JTextField campoDocumento = new JTextField(10);
    private JTextField campoParentesco = new JTextField(10);
    private JTextField campoAssinatura = new JTextField(10);
    private JButton botaoRegistrarEntrega = new JButton("Registrar entrega");

    private JTextField campoMotivo = new JTextField(10);
    private JCheckBox checkNovaTentativa = new JCheckBox("Haverá nova tentativa");
    private JButton botaoRegistrarFalha = new JButton("Registrar falha");

    private JComboBox<TipoDeEvento> comboTipoEvento = new JComboBox<>(TipoDeEvento.values());
    private JTextField campoLocalEvento = new JTextField(10);
    private JTextField campoDescricaoEvento = new JTextField(10);
    private JTextField campoFoto = new JTextField(10);
    private JButton botaoRegistrarEvento = new JButton("Registrar evento");

    private TabelaPedidos modeloTabela = new TabelaPedidos();
    private JTable tabela = new JTable(modeloTabela);

    public PainelEntregas(Sistema sistema) {
        super(new BorderLayout(5, 5));
        this.sistema = sistema;
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JPanel entrega = new JPanel(new GridLayout(0, 2, 5, 5));
        entrega.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Entrega realizada (pedido em trânsito)"));
        Conversor.adicionar(entrega, "Nome de quem recebeu:", campoNomeRecebedor);
        Conversor.adicionar(entrega, "Documento de identificação:", campoDocumento);
        Conversor.adicionar(entrega, "Parentesco ou função:", campoParentesco);
        Conversor.adicionar(entrega, "Assinatura / foto:", campoAssinatura);
        Conversor.adicionar(entrega, "", botaoRegistrarEntrega);

        JPanel falha = new JPanel(new GridLayout(0, 2, 5, 5));
        falha.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Falha na entrega (pedido em trânsito)"));
        Conversor.adicionar(falha, "Motivo:", campoMotivo);
        Conversor.adicionar(falha, "", checkNovaTentativa);
        Conversor.adicionar(falha, "", botaoRegistrarFalha);

        JPanel evento = new JPanel(new GridLayout(0, 2, 5, 5));
        evento.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Evento de rastreamento"));
        Conversor.adicionar(evento, "Tipo de evento:", comboTipoEvento);
        Conversor.adicionar(evento, "Localização:", campoLocalEvento);
        Conversor.adicionar(evento, "Descrição:", campoDescricaoEvento);
        Conversor.adicionar(evento, "Foto comprobatória:", campoFoto);
        Conversor.adicionar(evento, "", botaoRegistrarEvento);

        JPanel formularios = new JPanel(new GridLayout(1, 3, 5, 5));
        formularios.add(Conversor.topo(entrega));
        formularios.add(Conversor.topo(falha));
        formularios.add(Conversor.topo(evento));
        Conversor.configurar(tabela);
        JScrollPane rolagem = new JScrollPane(tabela);
        rolagem.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Pedidos coletados e em trânsito (selecione um)"));

        add(formularios, BorderLayout.NORTH);
        add(rolagem, BorderLayout.CENTER);

        botaoRegistrarEntrega.addActionListener(this);
        botaoRegistrarFalha.addActionListener(this);
        botaoRegistrarEvento.addActionListener(this);

        atualizar();
    }

    public void atualizar() {
        int linha = tabela.getSelectedRow();
        Pedido selecionado = null;
        if (linha >= 0) {
            selecionado = modeloTabela.getPedido(linha);
        }

        ArrayList<Pedido> lista = sistema.pedidosComStatus(StatusPedido.COLETADO);
        lista.addAll(sistema.pedidosComStatus(StatusPedido.EM_TRANSITO));
        modeloTabela.setPedidos(lista);

        int nova = -1;
        if (selecionado != null) {
            nova = modeloTabela.getLinha(selecionado);
        }
        if (nova >= 0) {
            tabela.setRowSelectionInterval(nova, nova);
        }
    }

    private Pedido pedidoSelecionado() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            return null;
        }
        return modeloTabela.getPedido(linha);
    }

    private void registrarEntrega() {
        Pedido pedido = pedidoSelecionado();
        if (pedido == null) {
            Conversor.erro(this, "Selecione um pedido na tabela.");
            return;
        }

        Conversor.limparErro();
        String nome = Conversor.texto(campoNomeRecebedor, "Nome de quem recebeu");
        String documento = Conversor.texto(campoDocumento, "Documento de identificação");
        String parentesco = Conversor.opcional(campoParentesco);
        String assinatura = Conversor.texto(campoAssinatura, "Assinatura / foto do comprovante");
        if (Conversor.temErro()) {
            Conversor.erro(this, Conversor.getErro());
            return;
        }

        if (!sistema.registrarEntrega(pedido, nome, documento, parentesco, assinatura)) {
            Conversor.erro(this, sistema.getErro());
            return;
        }
        Conversor.info(this, "Entrega do pedido nº " + pedido.getNumero() + " registrada.");
        campoNomeRecebedor.setText("");
        campoDocumento.setText("");
        campoParentesco.setText("");
        campoAssinatura.setText("");
        atualizar();
    }

    private void registrarFalha() {
        Pedido pedido = pedidoSelecionado();
        if (pedido == null) {
            Conversor.erro(this, "Selecione um pedido na tabela.");
            return;
        }

        Conversor.limparErro();
        boolean novaTentativa = checkNovaTentativa.isSelected();
        String motivo = Conversor.texto(campoMotivo, "Motivo");
        if (Conversor.temErro()) {
            Conversor.erro(this, Conversor.getErro());
            return;
        }

        if (!sistema.registrarFalhaNaEntrega(pedido, motivo, novaTentativa)) {
            Conversor.erro(this, sistema.getErro());
            return;
        }
        if (novaTentativa) {
            Conversor.info(this, "Falha registrada. O pedido continua em trânsito para nova tentativa.");
        } else {
            Conversor.info(this, "Falha registrada. Sem nova tentativa, o pedido foi encerrado como cancelado.");
        }
        campoMotivo.setText("");
        checkNovaTentativa.setSelected(false);
        atualizar();
    }

    private void registrarEvento() {
        Pedido pedido = pedidoSelecionado();
        if (pedido == null) {
            Conversor.erro(this, "Selecione um pedido na tabela.");
            return;
        }

        Conversor.limparErro();
        String local = Conversor.texto(campoLocalEvento, "Localização");
        String descricao = Conversor.opcional(campoDescricaoEvento);
        String foto = Conversor.opcional(campoFoto);
        if (Conversor.temErro()) {
            Conversor.erro(this, Conversor.getErro());
            return;
        }

        if (!sistema.registrarEvento(pedido, (TipoDeEvento) comboTipoEvento.getSelectedItem(), local, descricao, foto)) {
            Conversor.erro(this, sistema.getErro());
            return;
        }
        Conversor.info(this, "Evento registrado no rastreamento do pedido nº " + pedido.getNumero() + ".");
        campoLocalEvento.setText("");
        campoDescricaoEvento.setText("");
        campoFoto.setText("");
        atualizar();
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        Object fonte = evento.getSource();
        if (fonte == botaoRegistrarEntrega) {
            registrarEntrega();
        } else if (fonte == botaoRegistrarFalha) {
            registrarFalha();
        } else if (fonte == botaoRegistrarEvento) {
            registrarEvento();
        }
    }
}
