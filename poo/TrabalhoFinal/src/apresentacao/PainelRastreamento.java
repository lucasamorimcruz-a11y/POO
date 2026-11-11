package apresentacao;

import dados.Pedido;
import negocio.Sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PainelRastreamento extends JPanel implements ActionListener {

    private Sistema sistema;

    private JTextField campoNumeroPedido = new JTextField(10);
    private JButton botaoRastrear = new JButton("Rastrear");
    private JLabel rotuloStatus = new JLabel("Informe o número do pedido.");
    private TabelaEventos modeloTabela = new TabelaEventos();
    private JTable tabela = new JTable(modeloTabela);

    public PainelRastreamento(Sistema sistema) {
        super(new BorderLayout(5, 5));
        this.sistema = sistema;
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JPanel busca = new JPanel(new BorderLayout(5, 5));
        JPanel campos = new JPanel(new BorderLayout(5, 5));
        campos.add(new JLabel("Nº do pedido:"), BorderLayout.WEST);
        campos.add(campoNumeroPedido, BorderLayout.CENTER);
        campos.add(botaoRastrear, BorderLayout.EAST);
        busca.add(campos, BorderLayout.NORTH);
        busca.add(rotuloStatus, BorderLayout.SOUTH);

        Conversor.configurar(tabela);
        JScrollPane rolagem = new JScrollPane(tabela);
        rolagem.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Histórico de eventos"));

        add(busca, BorderLayout.NORTH);
        add(rolagem, BorderLayout.CENTER);

        botaoRastrear.addActionListener(this);
        campoNumeroPedido.addActionListener(this);
    }

    // O histórico só muda quando o usuário busca; se já havia uma busca, ela é refeita.
    public void atualizar() {
        if (!campoNumeroPedido.getText().trim().isEmpty()) {
            try {
                mostrar(Conversor.inteiro(campoNumeroPedido, "Nº do pedido"));
            } catch (IllegalArgumentException | IllegalStateException e) {
                modeloTabela.setEventos(new ArrayList<>());
            }
        }
    }

    private void rastrear() {
        try {
            mostrar(Conversor.inteiro(campoNumeroPedido, "Nº do pedido"));
        } catch (IllegalArgumentException | IllegalStateException e) {
            modeloTabela.setEventos(new ArrayList<>());
            rotuloStatus.setText("Informe o número do pedido.");
            Conversor.erro(this, e.getMessage());
        }
    }

    private void mostrar(int numero) {
        modeloTabela.setEventos(sistema.rastrear(numero));
        Pedido pedido = sistema.buscarPedido(numero);
        rotuloStatus.setText("Pedido nº " + pedido.getNumero() + " — cliente: "
                + pedido.getCliente().getPessoa().getNome() + " — status: " + pedido.getStatusPedido()
                + " — prazo de entrega: " + Conversor.formatar(pedido.getPrazoDeEntrega()));
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == botaoRastrear || evento.getSource() == campoNumeroPedido) {
            rastrear();
        }
    }
}
