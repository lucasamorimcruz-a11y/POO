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
        Pedido selecionado = linha >= 0 ? modeloTabela.getPedido(linha) : null;

        ArrayList<Pedido> lista = sistema.pedidosComStatus(StatusPedido.COLETADO);
        lista.addAll(sistema.pedidosComStatus(StatusPedido.EM_TRANSITO));
        modeloTabela.setPedidos(lista);

        int nova = selecionado == null ? -1 : modeloTabela.getLinha(selecionado);
        if (nova >= 0) {
            tabela.setRowSelectionInterval(nova, nova);
        }
    }

    private Pedido pedidoSelecionado() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            throw new IllegalArgumentException("Selecione um pedido na tabela.");
        }
        return modeloTabela.getPedido(linha);
    }

    private void registrarEntrega() {
        try {
            Pedido pedido = pedidoSelecionado();
            sistema.registrarEntrega(pedido,
                    Conversor.texto(campoNomeRecebedor, "Nome de quem recebeu"),
                    Conversor.texto(campoDocumento, "Documento de identificação"),
                    Conversor.opcional(campoParentesco),
                    Conversor.texto(campoAssinatura, "Assinatura / foto do comprovante"));
            Conversor.info(this, "Entrega do pedido nº " + pedido.getNumero() + " registrada.");
            campoNomeRecebedor.setText("");
            campoDocumento.setText("");
            campoParentesco.setText("");
            campoAssinatura.setText("");
            atualizar();
        } catch (IllegalArgumentException | IllegalStateException e) {
            Conversor.erro(this, e.getMessage());
        }
    }

    private void registrarFalha() {
        try {
            Pedido pedido = pedidoSelecionado();
            boolean novaTentativa = checkNovaTentativa.isSelected();
            sistema.registrarFalhaNaEntrega(pedido, Conversor.texto(campoMotivo, "Motivo"), novaTentativa);
            Conversor.info(this, novaTentativa
                    ? "Falha registrada. O pedido continua em trânsito para nova tentativa."
                    : "Falha registrada. Sem nova tentativa, o pedido foi encerrado como cancelado.");
            campoMotivo.setText("");
            checkNovaTentativa.setSelected(false);
            atualizar();
        } catch (IllegalArgumentException | IllegalStateException e) {
            Conversor.erro(this, e.getMessage());
        }
    }

    private void registrarEvento() {
        try {
            Pedido pedido = pedidoSelecionado();
            sistema.registrarEvento(pedido, (TipoDeEvento) comboTipoEvento.getSelectedItem(),
                    Conversor.texto(campoLocalEvento, "Localização"),
                    Conversor.opcional(campoDescricaoEvento),
                    Conversor.opcional(campoFoto));
            Conversor.info(this, "Evento registrado no rastreamento do pedido nº " + pedido.getNumero() + ".");
            campoLocalEvento.setText("");
            campoDescricaoEvento.setText("");
            campoFoto.setText("");
            atualizar();
        } catch (IllegalArgumentException | IllegalStateException e) {
            Conversor.erro(this, e.getMessage());
        }
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
