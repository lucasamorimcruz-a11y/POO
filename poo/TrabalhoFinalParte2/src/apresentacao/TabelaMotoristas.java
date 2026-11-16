package apresentacao;

import dados.Motorista;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TabelaMotoristas extends AbstractTableModel {

    private String[] colunas = {"CPF", "Nome", "Telefone", "CNH", "Categoria", "Validade da CNH", "Admissão", "Vínculo", "Situação", "Veículos habilitados"};
    private ArrayList<Motorista> motoristas = new ArrayList<>();

    public void setMotoristas(ArrayList<Motorista> motoristas) {
        this.motoristas = motoristas;
        fireTableDataChanged();
    }

    public Motorista getMotorista(int linha) {
        return motoristas.get(linha);
    }

    @Override
    public int getRowCount() {
        return motoristas.size();
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
        Motorista item = motoristas.get(linha);
        return switch (coluna) {
            case 0 -> item.getCPF();
            case 1 -> item.getNome();
            case 2 -> Conversor.textoOuTraco(item.getTelefone());
            case 3 -> item.getCnh().getNumeroDeCNH();
            case 4 -> item.getCnh().getCategoria();
            case 5 -> Conversor.formatar(item.getCnh().getDataDeValidade());
            case 6 -> Conversor.formatar(item.getDataDeAdmissao());
            case 7 -> item.getVinculo();
            case 8 -> item.getSituacao();
            case 9 -> item.getTipoDeVeiculosDisponiveis().toString().replace("[", "").replace("]", "");
            default -> "";
        };
    }
}
