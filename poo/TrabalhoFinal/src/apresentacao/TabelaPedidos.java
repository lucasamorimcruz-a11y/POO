package apresentacao;

import dados.Pedido;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TabelaPedidos extends AbstractTableModel {

    private String[] colunas = {"Nº", "Cliente", "Solicitação", "Coleta agendada", "Serviço", "Peso (kg)", "Frete", "Prazo de entrega", "Status"};
    private ArrayList<Pedido> pedidos = new ArrayList<>();

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
        fireTableDataChanged();
    }

    public Pedido getPedido(int linha) {
        return pedidos.get(linha);
    }

    // Posição do objeto na tabela (-1 se não estiver listado).
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
        return switch (coluna) {
            case 0 -> item.getNumero();
            case 1 -> item.getCliente().getPessoa().getNome();
            case 2 -> Conversor.formatar(item.getDataDeSolicitacao());
            case 3 -> Conversor.formatar(item.getDataAgendadaPraColeta());
            case 4 -> item.getTipoDeServico();
            case 5 -> item.getPesoTotal();
            case 6 -> Conversor.moeda(item.getValorFrete());
            case 7 -> Conversor.formatar(item.getPrazoDeEntrega());
            case 8 -> item.getStatusPedido();
            default -> "";
        };
    }
}
