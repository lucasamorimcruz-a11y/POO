package apresentacao;

import dados.PedidoNaRota;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TabelaParadas extends AbstractTableModel {

    private String[] colunas = {"Seq.", "Pedido", "Cliente", "Entrega prevista", "Entrega real"};
    private ArrayList<PedidoNaRota> paradas = new ArrayList<>();

    public void setParadas(ArrayList<PedidoNaRota> paradas) {
        this.paradas = paradas;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return paradas.size();
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
        PedidoNaRota item = paradas.get(linha);
        switch (coluna) {
            case 0:
                return item.getSequenciaEntrega();
            case 1:
                return item.getPedido().getNumero();
            case 2:
                return item.getPedido().getCliente().getPessoa().getNome();
            case 3:
                return Conversor.formatar(item.getHorarioPrevistoDeEntrega());
            case 4:
                return Conversor.formatar(item.getHorarioRealDeEntrega());
            default:
                return "";
        }
    }
}
