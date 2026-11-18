package apresentacao;

import dados.TabelaPreco;
import negocio.Sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelTabelaPadrao extends JPanel implements ActionListener {

    private Sistema sistema;
    private PainelTabelaPreco painelTabelaPreco = new PainelTabelaPreco();
    private JButton botaoSalvar = new JButton("Salvar tabela padrão");

    public PainelTabelaPadrao(Sistema sistema) {
        super(new BorderLayout(5, 5));
        this.sistema = sistema;
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JPanel centro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        centro.add(painelTabelaPreco);

        JPanel botoes = new JPanel();
        botoes.add(botaoSalvar);

        add(centro, BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);

        botaoSalvar.addActionListener(this);
        atualizar();
    }

    public void atualizar() {
        painelTabelaPreco.preencher(sistema.getTabelaPadrao());
    }

    private void salvar() {
        Conversor.limparErro();
        TabelaPreco tabela = painelTabelaPreco.getTabelaPreco();
        if (Conversor.temErro()) {
            Conversor.erro(this, Conversor.getErro());
            return;
        }
        sistema.setTabelaPadrao(tabela);
        Conversor.info(this, "Tabela de preços padrão atualizada.");
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == botaoSalvar) {
            salvar();
        }
    }
}
