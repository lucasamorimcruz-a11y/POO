package apresentacao;

import dados.ConhecimentoTransporte;
import dados.Pedido;
import dados.enums.StatusPedido;
import negocio.Sistema;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PainelColeta extends JPanel implements ActionListener {

    private Sistema sistema;

    private JTextField campoLocal = new JTextField(15);
    private JTextField campoNotaFiscal = new JTextField(15);
    private JTextField campoDestinatario = new JTextField(15);
    private JCheckBox caixaSeguro = new JCheckBox("Seguro contratado");
    private JTextField campoValorSeguro = new JTextField(15);
    private JButton botaoRegistrarColeta = new JButton("Registrar coleta");
    private JLabel rotuloConhecimento = new JLabel("Selecione um pedido.");
    private TabelaPedidos modeloTabela = new TabelaPedidos();
    private JTable tabela = new JTable(modeloTabela);

    public PainelColeta(Sistema sistema) {
        super(new BorderLayout(5, 5));
        this.sistema = sistema;
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JPanel formulario = new JPanel(new GridLayout(0, 2, 5, 5));
        formulario.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Coleta do pedido selecionado (gera o conhecimento de transporte)"));
        Conversor.adicionar(formulario, "Local da coleta:", campoLocal);
        Conversor.adicionar(formulario, "Nota fiscal da mercadoria:", campoNotaFiscal);
        Conversor.adicionar(formulario, "Destinatário (nome):", campoDestinatario);
        Conversor.adicionar(formulario, "Seguro:", caixaSeguro);
        Conversor.adicionar(formulario, "Valor do seguro (R$):", campoValorSeguro);
        Conversor.adicionar(formulario, "", botaoRegistrarColeta);

        rotuloConhecimento.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Conhecimento de transporte"));
        rotuloConhecimento.setVerticalAlignment(JLabel.TOP);

        JPanel norte = new JPanel(new GridLayout(1, 2, 5, 5));
        norte.add(Conversor.topo(formulario));
        norte.add(rotuloConhecimento);
        Conversor.configurar(tabela);
        JScrollPane rolagem = new JScrollPane(tabela);
        rolagem.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Pedidos pendentes e coletados"));

        add(norte, BorderLayout.NORTH);
        add(rolagem, BorderLayout.CENTER);

        botaoRegistrarColeta.addActionListener(this);
        caixaSeguro.addActionListener(this);
        campoValorSeguro.setEnabled(false);
        tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    mostrarConhecimento();
                }
            }
        });

        atualizar();
    }

    public void atualizar() {
        int linha = tabela.getSelectedRow();
        Pedido selecionado = null;
        if (linha >= 0) {
            selecionado = modeloTabela.getPedido(linha);
        }

        ArrayList<Pedido> lista = sistema.pedidosComStatus(StatusPedido.PENDENTE);
        lista.addAll(sistema.pedidosComStatus(StatusPedido.COLETADO));
        modeloTabela.setPedidos(lista);

        int nova = -1;
        if (selecionado != null) {
            nova = modeloTabela.getLinha(selecionado);
        }
        if (nova >= 0) {
            tabela.setRowSelectionInterval(nova, nova);
        }
        mostrarConhecimento();
    }

    private void registrarColeta() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            Conversor.erro(this, "Selecione um pedido na tabela.");
            return;
        }
        Pedido pedido = modeloTabela.getPedido(linha);

        Conversor.limparErro();
        boolean seguro = caixaSeguro.isSelected();
        double valorSeguro = 0;
        if (seguro) {
            valorSeguro = Conversor.decimal(campoValorSeguro, "Valor do seguro");
        }
        String local = Conversor.texto(campoLocal, "Local da coleta");
        String notaFiscal = Conversor.texto(campoNotaFiscal, "Nota fiscal");
        String destinatario = Conversor.texto(campoDestinatario, "Destinatário");
        if (Conversor.temErro()) {
            Conversor.erro(this, Conversor.getErro());
            return;
        }

        if (sistema.registrarColeta(pedido, local, notaFiscal, destinatario, seguro, valorSeguro) == null) {
            Conversor.erro(this, sistema.getErro());
            return;
        }
        Conversor.info(this, "Coleta registrada e conhecimento de transporte gerado.");
        campoLocal.setText("");
        campoNotaFiscal.setText("");
        campoDestinatario.setText("");
        caixaSeguro.setSelected(false);
        campoValorSeguro.setText("");
        campoValorSeguro.setEnabled(false);
        atualizar();
        int nova = modeloTabela.getLinha(pedido);
        if (nova >= 0) {
            tabela.setRowSelectionInterval(nova, nova);
        }
    }

    private void mostrarConhecimento() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            rotuloConhecimento.setText("Selecione um pedido.");
            return;
        }
        ConhecimentoTransporte c = sistema.buscarConhecimento(modeloTabela.getPedido(linha));
        if (c == null) {
            rotuloConhecimento.setText("Pedido ainda sem conhecimento de transporte (coleta pendente).");
            return;
        }
        String seguro = "não contratado";
        if (c.isSeguroContratado()) {
            seguro = Conversor.moeda(c.getValorSeguro());
        }
        rotuloConhecimento.setText("<html>Nº " + c.getNumero() + " — emitido em " + Conversor.formatar(c.getDataEmissao())
                + "<br>Remetente: " + c.getRemetente()
                + "<br>Destinatário: " + c.getDestinatario()
                + "<br>Carga: " + c.getDescriminacaoCarga()
                + "<br>Frete: " + Conversor.moeda(c.getValorFrete())
                + " | Impostos: " + Conversor.moeda(c.getImpostos())
                + "<br>Seguro: " + seguro
                + "<br>Nota fiscal: " + c.getNotaFiscal() + "</html>");
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == botaoRegistrarColeta) {
            registrarColeta();
        } else if (evento.getSource() == caixaSeguro) {
            campoValorSeguro.setEnabled(caixaSeguro.isSelected());
            if (!caixaSeguro.isSelected()) {
                campoValorSeguro.setText("");
            }
        }
    }
}
