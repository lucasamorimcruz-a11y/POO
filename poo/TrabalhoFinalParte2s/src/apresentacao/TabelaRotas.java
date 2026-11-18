package apresentacao;

import dados.Rota;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TabelaRotas extends AbstractTableModel {

    private String[] colunas = {"Nº", "Data", "Veículo", "Motorista", "Pedidos", "Km inicial", "Km final", "Status"};
    private ArrayList<Rota> rotas = new ArrayList<>();

    public void setRotas(ArrayList<Rota> rotas) {
        this.rotas = rotas;
        fireTableDataChanged();
    }

    public Rota getRota(int linha) {
        return rotas.get(linha);
    }

    public int getLinha(Rota procurado) {
        for (int i = 0; i < rotas.size(); i++) {
            if (rotas.get(i) == procurado) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getRowCount() {
        return rotas.size();
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
        Rota item = rotas.get(linha);
        switch (coluna) {
            case 0:
                return item.getNumero();
            case 1:
                return Conversor.formatar(item.getData());
            case 2:
                return item.getVeiculoAlocado().getPlaca();
            case 3:
                return item.getMotoristaResponsavel().getNome();
            case 4:
                return item.getPedidos().size();
            case 5:
                return item.getQuilometragemInicial();
            case 6:
                return item.getQuilometragemFinal();
            case 7:
                return item.getStatus();
            default:
                return "";
        }
    }
}
