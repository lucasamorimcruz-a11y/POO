package apresentacao;

import dados.Despesa;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TabelaDespesas extends AbstractTableModel {

    private String[] colunas = {"Data", "Tipo", "Valor", "Comprovante"};
    private ArrayList<Despesa> despesas = new ArrayList<>();

    public void setDespesas(ArrayList<Despesa> despesas) {
        this.despesas = despesas;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return despesas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int coluna) {
        return colunas[coluna];
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Despesa item = despesas.get(linha);
        return switch (coluna) {
            case 0 -> Conversor.formatar(item.getData());
            case 1 -> item.getTipoDeDespesa();
            case 2 -> Conversor.moeda(item.getValor());
            case 3 -> Conversor.textoOuTraco(item.getComprovante());
            default -> "";
        };
    }
}
