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
        switch (coluna) {
            case 0:
                return Conversor.formatar(item.getData());
            case 1:
                return Conversor.formatar(item.getHora());
            case 2:
                return item.getTipo();
            case 3:
                return Conversor.textoOuTraco(item.getLocalizacao());
            case 4:
                return Conversor.textoOuTraco(item.getDescricao());
            case 5:
                return Conversor.textoOuTraco(item.getFotoComprobatoria());
            default:
                return "";
        }
    }
}
