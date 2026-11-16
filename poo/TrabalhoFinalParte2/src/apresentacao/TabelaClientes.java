package apresentacao;

import dados.Cliente;
import dados.PessoaJuridica;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TabelaClientes extends AbstractTableModel {

    private String[] colunas = {"Documento", "Nome", "Pessoa", "Telefone", "E-mail", "Tipo", "Limite de crédito"};
    private ArrayList<Cliente> clientes = new ArrayList<>();

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
        fireTableDataChanged();
    }

    public Cliente getCliente(int linha) {
        return clientes.get(linha);
    }

    @Override
    public int getRowCount() {
        return clientes.size();
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
        Cliente item = clientes.get(linha);
        return switch (coluna) {
            case 0 -> item.getPessoa().getDocumento();
            case 1 -> item.getPessoa().getNome();
            case 2 -> (item.getPessoa() instanceof PessoaJuridica) ? "Jurídica" : "Física";
            case 3 -> Conversor.textoOuTraco(item.getPessoa().getTelefone());
            case 4 -> Conversor.textoOuTraco(item.getPessoa().getEmail());
            case 5 -> item.getTipoCliente();
            case 6 -> Conversor.moeda(item.getLimiteDeCredito());
            default -> "";
        };
    }
}
