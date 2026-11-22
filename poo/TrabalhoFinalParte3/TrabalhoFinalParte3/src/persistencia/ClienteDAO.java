package persistencia;

import dados.Cliente;
import dados.Endereco;
import dados.Pessoa;
import dados.PessoaFisica;
import dados.PessoaJuridica;
import dados.TabelaPreco;
import dados.enums.TipoCliente;
import exceptions.DeleteException;
import exceptions.InsertException;
import exceptions.SelectException;
import exceptions.UpdateException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {

    private static ClienteDAO instance;

    private ClienteDAO() {
    }

    public static ClienteDAO getInstance() {
        if (instance == null) {
            instance = new ClienteDAO();
        }
        return instance;
    }

    public void insert(Cliente cliente) throws InsertException {
        Pessoa pessoa = cliente.getPessoa();
        Integer idEndereco = EnderecoDAO.getInstance().insert(pessoa.getEndereco());
        Integer idTabela = TabelaPrecoDAO.getInstance().insert(cliente.getTabelaDePreco(), false);

        String tipoPessoa = "FISICA";
        String razaoSocial = null;
        String inscricaoEstadual = null;
        if (pessoa instanceof PessoaJuridica) {
            PessoaJuridica juridica = (PessoaJuridica) pessoa;
            tipoPessoa = "JURIDICA";
            razaoSocial = juridica.getRazaoSocial();
            inscricaoEstadual = juridica.getInscricaoEstadual();
        }

        try {
            Connection con = Conexao.getConexao();
            String sql = "insert into cliente(documento, tipo_pessoa, nome, telefone, email, razao_social, inscricao_estadual, id_endereco, tipo_cliente, id_tabela_preco, limite_credito) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement comando = con.prepareStatement(sql)) {
                comando.setString(1, pessoa.getDocumento());
                comando.setString(2, tipoPessoa);
                comando.setString(3, pessoa.getNome());
                comando.setString(4, pessoa.getTelefone());
                comando.setString(5, pessoa.getEmail());
                comando.setString(6, razaoSocial);
                comando.setString(7, inscricaoEstadual);
                comando.setObject(8, idEndereco);
                comando.setString(9, cliente.getTipoCliente().name());
                comando.setObject(10, idTabela);
                comando.setDouble(11, cliente.getLimiteDeCredito());
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new InsertException("Erro ao inserir o cliente.", e);
        }
    }

    public void update(Cliente cliente) throws UpdateException {
        Pessoa pessoa = cliente.getPessoa();
        String razaoSocial = null;
        String inscricaoEstadual = null;
        if (pessoa instanceof PessoaJuridica) {
            PessoaJuridica juridica = (PessoaJuridica) pessoa;
            razaoSocial = juridica.getRazaoSocial();
            inscricaoEstadual = juridica.getInscricaoEstadual();
        }

        try {
            Connection con = Conexao.getConexao();
            String sql = "update cliente set nome = ?, telefone = ?, email = ?, razao_social = ?, inscricao_estadual = ?, tipo_cliente = ?, limite_credito = ? where documento = ?";
            try (PreparedStatement comando = con.prepareStatement(sql)) {
                comando.setString(1, pessoa.getNome());
                comando.setString(2, pessoa.getTelefone());
                comando.setString(3, pessoa.getEmail());
                comando.setString(4, razaoSocial);
                comando.setString(5, inscricaoEstadual);
                comando.setString(6, cliente.getTipoCliente().name());
                comando.setDouble(7, cliente.getLimiteDeCredito());
                comando.setString(8, pessoa.getDocumento());
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new UpdateException("Erro ao atualizar o cliente.", e);
        }
    }

    public void delete(Cliente cliente) throws DeleteException {
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("delete from cliente where documento = ?")) {
                comando.setString(1, cliente.getPessoa().getDocumento());
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DeleteException("Erro ao excluir o cliente.", e);
        }
    }

    public Cliente select(String documento) throws SelectException {
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("select * from cliente where documento = ?")) {
                comando.setString(1, documento);
                try (ResultSet resultado = comando.executeQuery()) {
                    if (resultado.next()) {
                        return montar(resultado);
                    }
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new SelectException("Erro ao buscar o cliente.", e);
        }
    }

    public ArrayList<Cliente> selectAll() throws SelectException {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("select * from cliente order by nome");
                 ResultSet resultado = comando.executeQuery()) {
                while (resultado.next()) {
                    clientes.add(montar(resultado));
                }
            }
        } catch (SQLException e) {
            throw new SelectException("Erro ao buscar os clientes.", e);
        }
        return clientes;
    }

    private Cliente montar(ResultSet resultado) throws SQLException, SelectException {
        Endereco endereco = EnderecoDAO.getInstance().select(resultado.getObject("id_endereco", Integer.class));
        TabelaPreco tabela = TabelaPrecoDAO.getInstance().select(resultado.getObject("id_tabela_preco", Integer.class));

        String nome = resultado.getString("nome");
        String telefone = resultado.getString("telefone");
        String email = resultado.getString("email");
        String documento = resultado.getString("documento");

        Pessoa pessoa;
        if (resultado.getString("tipo_pessoa").equals("JURIDICA")) {
            pessoa = new PessoaJuridica(nome, endereco, telefone, email, documento,
                    resultado.getString("razao_social"), resultado.getString("inscricao_estadual"));
        } else {
            pessoa = new PessoaFisica(nome, endereco, telefone, email, documento);
        }

        return new Cliente(pessoa, TipoCliente.valueOf(resultado.getString("tipo_cliente")), tabela,
                resultado.getDouble("limite_credito"));
    }
}
