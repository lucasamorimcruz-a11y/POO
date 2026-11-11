import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Calculadora extends JFrame {
    private JPanel painel = new JPanel();
    private JPanel painelEntrada = new JPanel();
    private JPanel painelGerar = new JPanel();

    private JLabel infoCaixaTexto = new JLabel("Digite um valor:");
    private JTextField caixaTexto = new JTextField();
    private JButton botaoAdicionar = new JButton("adicionar");
    private JButton botaoLimpar = new JButton("limpar");

    private JTextField caixaQuantidade = new JTextField();
    private JButton botaoFibonacci = new JButton("Fibonacci");
    private JButton botaoPrimos = new JButton("Primos");
    private JButton botaoFatorial = new JButton("Fatorial");

    private JScrollPane painelScrollTabelaResultados = new JScrollPane();
    private JScrollPane paineScrollTabelaValores = new JScrollPane();

    private JTable tabelaResultados;
    private JTable tabelaValores;
    private TabelaResultados resultados = new TabelaResultados();
    private TabelaValores valores = new TabelaValores();

    public static void main(String[] args) {
        Calculadora frame = new Calculadora();
        frame.setVisible(true);
    }

    public Calculadora() {
        setTitle("Calculadora Estatística");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 300);

        setContentPane(painel);
        painel.setLayout(null);

        painelEntrada.setBounds(15, 80, 280, 173);
        painelEntrada.setLayout(null);
        painel.add(painelEntrada);

        infoCaixaTexto.setBounds(30, 30, 200, 15);
        painelEntrada.add(infoCaixaTexto);

        caixaTexto.setBounds(30, 50, 200, 20);
        painelEntrada.add(caixaTexto);

        botaoAdicionar.setBounds(77, 94, 117, 25);
        painelEntrada.add(botaoAdicionar);

        botaoLimpar.setBounds(77, 136, 117, 25);
        painelEntrada.add(botaoLimpar);

        painelGerar.setBounds(500, 80, 200, 173);
        painelGerar.setLayout(null);
        painelGerar.setBorder(BorderFactory.createTitledBorder("Gerar Valores"));
        painel.add(painelGerar);

        caixaQuantidade.setBounds(15, 30, 170, 20);
        painelGerar.add(caixaQuantidade);

        botaoFibonacci.setBounds(42, 70, 117, 25);
        painelGerar.add(botaoFibonacci);

        botaoPrimos.setBounds(42, 102, 117, 25);
        painelGerar.add(botaoPrimos);

        botaoFatorial.setBounds(42, 134, 117, 25);
        painelGerar.add(botaoFatorial);

        painelScrollTabelaResultados.setBounds(10, 10, 880, 50);
        painel.add(painelScrollTabelaResultados);
        tabelaResultados = new JTable(resultados);
        painelScrollTabelaResultados.setViewportView(tabelaResultados);

        paineScrollTabelaValores.setBounds(307, 80, 173, 173);
        painel.add(paineScrollTabelaValores);
        tabelaValores = new JTable(valores);
        paineScrollTabelaValores.setViewportView(tabelaValores);

        botaoAdicionar.addActionListener(e -> {
            valores.adicionaValor(Integer.parseInt(caixaTexto.getText()));
            resultados.atualizar();
            caixaTexto.setText("");
        });

        botaoLimpar.addActionListener(e -> {
            valores.limpar();
            resultados.atualizar();
        });

        botaoFibonacci.addActionListener(e -> gerar(new Fibonacci()));
        botaoPrimos.addActionListener(e -> gerar(new Primos()));
        botaoFatorial.addActionListener(e -> gerar(new Fatoriais()));
    }

    private void gerar(Gerador gerador) {
        gerador.gerar(Integer.parseInt(caixaQuantidade.getText()));
        for (var i : gerador.getLista()) {
            valores.adicionaValor(i);
        }
        resultados.atualizar();
        caixaQuantidade.setText("");
    }
}