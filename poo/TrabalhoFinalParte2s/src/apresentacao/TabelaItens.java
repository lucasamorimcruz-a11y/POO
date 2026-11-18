package apresentacao;

import dados.Item;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TabelaItens extends AbstractTableModel {

    private String[] colunas = {"Embalagem", "Peso (kg)", "Dimensão", "Descrição"};
    private ArrayList<Item> itens = new ArrayList<>();

    public void setItens(ArrayList<Item> itens) {
        this.itens = itens;
        fireTableDataChanged();
    }

    public Item getItem(int linha) {
        return itens.get(linha);
    }

    @Override
    public int getRowCount() {
        return itens.size();
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
        Item item = itens.get(linha);
        switch (coluna) {
            case 0:
                return item.getTipoDeEmbalagem();
            case 1:
                return item.getPeso();
            case 2:
                return item.getDimensao();
            case 3:
                return Conversor.textoOuTraco(item.getDescricao());
            default:
                return "";
        }
    }
}
