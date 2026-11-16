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
        return switch (coluna) {
            case 0 -> item.getPlaca();
            case 1 -> item.getMarca();
            case 2 -> item.getModelo();
            case 3 -> item.getAnoDeFabricacao();
            case 4 -> item.getTipoDeVeiculo();
            case 5 -> item.getCapacidadeEmQuilos();
            case 6 -> item.getCapacidadeEmMetroCubicos();
            case 7 -> item.getQuilometragemAtual();
            case 8 -> item.getStatusVeiculo();
            case 9 -> Conversor.formatar(item.getDataProximaManuntencao());
            default -> "";
        };
    }
}
