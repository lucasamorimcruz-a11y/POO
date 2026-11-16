package apresentacao;

import negocio.Sistema;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JanelaPrincipal extends JFrame {

    private Sistema sistema;
    private PainelPrincipal painelPrincipal;

    public JanelaPrincipal() {
        super("Transportadora - Sistema de Controle");
        sistema = new Sistema();
        painelPrincipal = new PainelPrincipal(sistema);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evento) {
                if (Conversor.confirmar(JanelaPrincipal.this, "Deseja fechar o sistema?")) {
                    dispose();
                    System.exit(0);
                }
            }
        });

        setBounds(50, 50, 1300, 820);
        setLocationRelativeTo(null);
        add(painelPrincipal);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JanelaPrincipal().setVisible(true));
    }
}
