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
        switch (coluna) {
            case 0:
                return item.getCPF();
            case 1:
                return item.getNome();
            case 2:
                return Conversor.textoOuTraco(item.getTelefone());
            case 3:
                return item.getCnh().getNumeroDeCNH();
            case 4:
                return item.getCnh().getCategoria();
            case 5:
                return Conversor.formatar(item.getCnh().getDataDeValidade());
            case 6:
                return Conversor.formatar(item.getDataDeAdmissao());
            case 7:
                return item.getVinculo();
            case 8:
                return item.getSituacao();
            case 9:
                return item.getTipoDeVeiculosDisponiveis().toString().replace("[", "").replace("]", "");
            default:
                return "";
        }
    }
}
