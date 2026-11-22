package apresentacao;

import dados.Pedido;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TabelaPedidos extends AbstractTableModel {

    private String[] colunas = {"Nº", "Cliente", "Solicitação", "Coleta agendada", "Serviço", "Peso (kg)", "Volume (m³)", "Frete", "Prazo de entrega", "Status"};
    private ArrayList<Pedido> pedidos = new ArrayList<>();

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
        fireTableDataChanged();
    }

    public Pedido getPedido(int linha) {
        return pedidos.get(linha);
    }

    public int getLinha(Pedido procurado) {
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i) == procurado) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getRowCount() {
        return pedidos.size();
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
        Pedido item = pedidos.get(linha);
        switch (coluna) {
            case 0:
                return item.getNumero();
            case 1:
                return item.getCliente().getPessoa().getNome();
            case 2:
                return Conversor.formatar(item.getDataDeSolicitacao());
            case 3:
                return Conversor.formatar(item.getDataAgendadaPraColeta());
            case 4:
                return item.getTipoDeServico();
            case 5:
                return item.getPesoTotal();
            case 6:
                return Conversor.numero(item.getVolumeTotal());
            case 7:
                return Conversor.moeda(item.getValorFrete());
            case 8:
                return Conversor.formatar(item.getPrazoDeEntrega());
            case 9:
                return item.getStatusPedido();
            default:
                return "";
        }
    }
}
