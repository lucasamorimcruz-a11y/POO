package apresentacao;

import dados.Endereco;

import javax.swing.*;
import java.awt.*;

public class PainelEndereco extends JPanel {

    private String titulo;
    private JTextField campoRua = new JTextField(10);
    private JTextField campoNumero = new JTextField(10);
    private JTextField campoBairro = new JTextField(10);
    private JTextField campoCidade = new JTextField(10);
    private JTextField campoEstado = new JTextField(10);
    private JTextField campoReferencia = new JTextField(10);

    public PainelEndereco(String titulo) {
        super(new BorderLayout());
        this.titulo = titulo;
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), titulo));

        JPanel campos = new JPanel(new GridLayout(0, 2, 5, 5));
        Conversor.adicionar(campos, "Rua:", campoRua);
        Conversor.adicionar(campos, "Número:", campoNumero);
        Conversor.adicionar(campos, "Bairro:", campoBairro);
        Conversor.adicionar(campos, "Cidade:", campoCidade);
        Conversor.adicionar(campos, "Estado:", campoEstado);
        Conversor.adicionar(campos, "Ponto de referência:", campoReferencia);
        add(campos, BorderLayout.NORTH);
    }

    public Endereco getEndereco() {
        String rua = Conversor.texto(campoRua, "Rua (" + titulo + ")");
        int numero = Conversor.inteiro(campoNumero, "Número (" + titulo + ")");
        String bairro = Conversor.texto(campoBairro, "Bairro (" + titulo + ")");
        String cidade = Conversor.texto(campoCidade, "Cidade (" + titulo + ")");
        String estado = Conversor.texto(campoEstado, "Estado (" + titulo + ")");
        String referencia = Conversor.opcional(campoReferencia);
        return new Endereco(numero, rua, bairro, cidade, estado, referencia);
    }

    public void limpar() {
        campoRua.setText("");
        campoNumero.setText("");
        campoBairro.setText("");
        campoCidade.setText("");
        campoEstado.setText("");
        campoReferencia.setText("");
    }
}
