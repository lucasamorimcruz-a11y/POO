import javax.swing.table.AbstractTableModel;

public class TabelaValores extends AbstractTableModel {
    private String[] colunas = { "Valores" };

    private CalculadoraEstatistica calculadora = CalculadoraEstatistica.getInstance();

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public int getRowCount() {
        return calculadora.getValores().size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        return calculadora.getValores().get(linha);
    }

    public void adicionaValor(int valor) {
        calculadora.adicionarValor(valor);
        fireTableStructureChanged();
    }

    public void limpar() {
        calculadora.limparValores();
        fireTableStructureChanged();
    }
}