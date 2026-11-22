package apresentacao;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;

public final class Conversor {

    private static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter FORMATO_DATA_HORA =
            DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm").withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
    private static final Locale BRASIL = Locale.forLanguageTag("pt-BR");

    private static String mensagemErro = "";

    private Conversor() {
    }

    public static void limparErro() {
        mensagemErro = "";
    }

    public static boolean temErro() {
        return !mensagemErro.isEmpty();
    }

    public static String getErro() {
        return mensagemErro;
    }

    public static void setErro(String mensagem) {
        if (mensagemErro.isEmpty()) {
            mensagemErro = mensagem;
        }
    }

    public static String texto(JTextField campo, String nome) {
        String valor = campo.getText().trim();
        if (valor.isEmpty()) {
            setErro("O campo \"" + nome + "\" é obrigatório.");
        }
        return valor;
    }

    public static String opcional(JTextField campo) {
        String valor = campo.getText().trim();
        if (valor.isEmpty()) {
            return null;
        }
        return valor;
    }

    public static int inteiro(JTextField campo, String nome) {
        String valor = texto(campo, nome);
        if (valor.isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            setErro("O campo \"" + nome + "\" deve ser um número inteiro.");
            return 0;
        }
    }

    public static double decimal(JTextField campo, String nome) {
        String valor = texto(campo, nome).replace(',', '.');
        if (valor.isEmpty()) {
            return 0;
        }
        try {
            double numero = Double.parseDouble(valor);
            if (Double.isNaN(numero) || Double.isInfinite(numero)) {
                setErro("O campo \"" + nome + "\" deve ser um número (ex.: 12,5).");
                return 0;
            }
            return numero;
        } catch (NumberFormatException e) {
            setErro("O campo \"" + nome + "\" deve ser um número (ex.: 12,5).");
            return 0;
        }
    }

    public static LocalDate data(JTextField campo, String nome) {
        String valor = texto(campo, nome);
        if (valor.isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(valor, FORMATO_DATA);
        } catch (DateTimeParseException e) {
            setErro("O campo \"" + nome + "\" deve ser uma data válida (dd/mm/aaaa).");
            return null;
        }
    }

    public static LocalDate dataOpcional(JTextField campo, String nome) {
        if (opcional(campo) == null) {
            return null;
        }
        return data(campo, nome);
    }

    public static LocalDateTime dataHora(JTextField campo, String nome) {
        String valor = texto(campo, nome);
        if (valor.isEmpty()) {
            return null;
        }
        try {
            return LocalDateTime.parse(valor, FORMATO_DATA_HORA);
        } catch (DateTimeParseException e) {
            setErro("O campo \"" + nome + "\" deve ser uma data e hora válidas (dd/mm/aaaa hh:mm).");
            return null;
        }
    }

    public static String formatar(LocalDate data) {
        if (data == null) {
            return "-";
        }
        return data.format(FORMATO_DATA);
    }

    public static String formatar(LocalDateTime dataHora) {
        if (dataHora == null) {
            return "-";
        }
        return dataHora.format(FORMATO_DATA_HORA);
    }

    public static String formatar(LocalTime hora) {
        if (hora == null) {
            return "-";
        }
        return hora.format(FORMATO_HORA);
    }

    public static String moeda(double valor) {
        return String.format(BRASIL, "R$ %,.2f", valor);
    }

    public static String numero(double valor) {
        return String.format(Locale.US, "%.2f", valor);
    }

    public static String textoOuTraco(String texto) {
        if (texto == null || texto.isBlank()) {
            return "-";
        }
        return texto;
    }

    public static void adicionar(JPanel painel, String rotulo, JComponent componente) {
        painel.add(new JLabel(rotulo));
        painel.add(componente);
    }

    public static void configurar(JTable tabela) {
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setFillsViewportHeight(true);
        tabela.setPreferredScrollableViewportSize(new Dimension(300, 120));
    }

    public static JPanel topo(JPanel formulario) {
        JPanel envoltorio = new JPanel(new BorderLayout());
        envoltorio.add(formulario, BorderLayout.NORTH);
        return envoltorio;
    }

    public static void erro(Component pai, String mensagem) {
        JOptionPane.showMessageDialog(pai, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public static void info(Component pai, String mensagem) {
        JOptionPane.showMessageDialog(pai, mensagem, "Informação", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirmar(Component pai, String mensagem) {
        Object[] botoes = {"Sim", "Não"};
        int escolha = JOptionPane.showOptionDialog(pai, mensagem, "Confirmação",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[1]);
        return escolha == 0;
    }
}
