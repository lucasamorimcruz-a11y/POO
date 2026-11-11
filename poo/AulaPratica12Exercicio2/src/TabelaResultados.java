import javax.swing.table.AbstractTableModel;

public class TabelaResultados extends AbstractTableModel {
    private String[] colunas = { "Sorteado", "Somatorio", "Média Aritmética", "Média Geométrica", "Variancia",
            "Desvio Padrão", "Amplitude" };

    private CalculadoraEstatistica calculadora = CalculadoraEstatistica.getInstance();

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        if (calculadora.getValores().isEmpty()) {
            return "-";
        }
        switch (coluna) {
            case 0:
                return calculadora.sortear();
            case 1:
                return calculadora.somatorio();
            case 2:
                return calculadora.mediaAritmetica();
            case 3:
                return calculadora.mediaGeometrica();
            case 4:
                return calculadora.variancia();
            case 5:
                return calculadora.desvioPadrao();
            case 6:
                return calculadora.amplitude();
        }
        return null;
    }

    public void atualizar() {
        fireTableStructureChanged();
    }
}