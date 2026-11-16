package apresentacao;

import dados.EventoDeRastreamento;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TabelaEventos extends AbstractTableModel {

    private String[] colunas = {"Data", "Hora", "Evento", "Localização", "Descrição", "Foto comprobatória"};
    private ArrayList<EventoDeRastreamento> eventos = new ArrayList<>();

    public void setEventos(ArrayList<EventoDeRastreamento> eventos) {
        this.eventos = eventos;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return eventos.size();
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
        EventoDeRastreamento item = eventos.get(linha);
        return switch (coluna) {
            case 0 -> Conversor.formatar(item.getData());
            case 1 -> Conversor.formatar(item.getHora());
            case 2 -> item.getTipo();
            case 3 -> Conversor.textoOuTraco(item.getLocalizacao());
            case 4 -> Conversor.textoOuTraco(item.getDescricao());
            case 5 -> Conversor.textoOuTraco(item.getFotoComprobatoria());
            default -> "";
        };
    }
}
