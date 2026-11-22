package persistencia;

import dados.Endereco;
import exceptions.DeleteException;
import exceptions.InsertException;
import exceptions.SelectException;
import exceptions.UpdateException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnderecoDAO {

    private static EnderecoDAO instance;

    private EnderecoDAO() {
    }

    public static EnderecoDAO getInstance() {
        if (instance == null) {
            instance = new EnderecoDAO();
        }
        return instance;
    }

    public Integer insert(Endereco endereco) throws InsertException {
        if (endereco == null) {
            return null;
        }
        try {
            Connection con = Conexao.getConexao();
            int id = Conexao.proximoValor("id_endereco");
            String sql = "insert into endereco(id, numero_local, rua, bairro, cidade, estado, ponto_referencia) values (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement comando = con.prepareStatement(sql)) {
                comando.setInt(1, id);
                comando.setInt(2, endereco.getNumeroDoLocal());
                comando.setString(3, endereco.getRua());
                comando.setString(4, endereco.getBairro());
                comando.setString(5, endereco.getCidade());
                comando.setString(6, endereco.getEstado());
                comando.setString(7, endereco.getPontoDeReferencia());
                comando.executeUpdate();
            }
            return id;
        } catch (SQLException e) {
            throw new InsertException("Erro ao inserir o endereço.", e);
        }
    }

    public void update(int id, Endereco endereco) throws UpdateException {
        try {
            Connection con = Conexao.getConexao();
            String sql = "update endereco set numero_local = ?, rua = ?, bairro = ?, cidade = ?, estado = ?, ponto_referencia = ? where id = ?";
            try (PreparedStatement comando = con.prepareStatement(sql)) {
                comando.setInt(1, endereco.getNumeroDoLocal());
                comando.setString(2, endereco.getRua());
                comando.setString(3, endereco.getBairro());
                comando.setString(4, endereco.getCidade());
                comando.setString(5, endereco.getEstado());
                comando.setString(6, endereco.getPontoDeReferencia());
                comando.setInt(7, id);
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new UpdateException("Erro ao atualizar o endereço.", e);
        }
    }

    public void delete(int id) throws DeleteException {
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("delete from endereco where id = ?")) {
                comando.setInt(1, id);
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DeleteException("Erro ao excluir o endereço.", e);
        }
    }

    public Endereco select(Integer id) throws SelectException {
        if (id == null) {
            return null;
        }
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("select * from endereco where id = ?")) {
                comando.setInt(1, id);
                try (ResultSet resultado = comando.executeQuery()) {
                    if (resultado.next()) {
                        return new Endereco(resultado.getInt("numero_local"), resultado.getString("rua"),
                                resultado.getString("bairro"), resultado.getString("cidade"),
                                resultado.getString("estado"), resultado.getString("ponto_referencia"));
                    }
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new SelectException("Erro ao buscar o endereço.", e);
        }
    }
}
