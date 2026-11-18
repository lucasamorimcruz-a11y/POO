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
        switch (coluna) {
            case 0:
                return item.getPessoa().getDocumento();
            case 1:
                return item.getPessoa().getNome();
            case 2:
                if (item.getPessoa() instanceof PessoaJuridica) {
                    return "Jurídica";
                }
                return "Física";
            case 3:
                return Conversor.textoOuTraco(item.getPessoa().getTelefone());
            case 4:
                return Conversor.textoOuTraco(item.getPessoa().getEmail());
            case 5:
                return item.getTipoCliente();
            case 6:
                return Conversor.moeda(item.getLimiteDeCredito());
            default:
                return "";
        }
    }
}
