package apresentacao;

import dados.Veiculo;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TabelaVeiculos extends AbstractTableModel {

    private String[] colunas = {"Placa", "Marca", "Modelo", "Ano", "Tipo", "Capacidade (kg)", "Capacidade (m³)", "Km atual", "Status", "Próx. manutenção"};
    private ArrayList<Veiculo> veiculos = new ArrayList<>();

    public void setVeiculos(ArrayList<Veiculo> veiculos) {
        this.veiculos = veiculos;
        fireTableDataChanged();
    }

    public Veiculo getVeiculo(int linha) {
        return veiculos.get(linha);
    }

    @Override
    public int getRowCount() {
        return veiculos.size();
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
        Veiculo item = veiculos.get(linha);
        switch (coluna) {
            case 0:
                return item.getPlaca();
            case 1:
                return item.getMarca();
            case 2:
                return item.getModelo();
            case 3:
                return item.getAnoDeFabricacao();
            case 4:
                return item.getTipoDeVeiculo();
            case 5:
                return item.getCapacidadeEmQuilos();
            case 6:
                return item.getCapacidadeEmMetroCubicos();
            case 7:
                return item.getQuilometragemAtual();
            case 8:
                return item.getStatusVeiculo();
            case 9:
                return Conversor.formatar(item.getDataProximaManuntencao());
            default:
                return "";
        }
    }
}
