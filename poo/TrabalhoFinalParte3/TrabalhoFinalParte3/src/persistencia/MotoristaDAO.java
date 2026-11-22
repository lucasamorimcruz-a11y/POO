package persistencia;

import dados.Cnh;
import dados.Endereco;
import dados.Motorista;
import dados.enums.Situacao;
import dados.enums.TipoDeVeiculo;
import dados.enums.TipoDeVinculo;
import exceptions.DeleteException;
import exceptions.InsertException;
import exceptions.SelectException;
import exceptions.UpdateException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MotoristaDAO {

    private static MotoristaDAO instance;

    private MotoristaDAO() {
    }

    public static MotoristaDAO getInstance() {
        if (instance == null) {
            instance = new MotoristaDAO();
        }
        return instance;
    }

    public void insert(Motorista motorista) throws InsertException {
        Integer idEndereco = EnderecoDAO.getInstance().insert(motorista.getEndereco());
        try {
            Connection con = Conexao.getConexao();
            String sql = "insert into motorista(cpf, nome, telefone, email, id_endereco, cnh_numero, cnh_categoria, cnh_validade, data_admissao, vinculo, situacao) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement comando = con.prepareStatement(sql)) {
                comando.setString(1, motorista.getCPF());
                comando.setString(2, motorista.getNome());
                comando.setString(3, motorista.getTelefone());
                comando.setString(4, motorista.getEmail());
                comando.setObject(5, idEndereco);
                comando.setString(6, motorista.getCnh().getNumeroDeCNH());
                comando.setString(7, motorista.getCnh().getCategoria());
                comando.setObject(8, motorista.getCnh().getDataDeValidade());
                comando.setObject(9, motorista.getDataDeAdmissao());
                comando.setString(10, motorista.getVinculo().name());
                comando.setString(11, motorista.getSituacao().name());
                comando.executeUpdate();
            }
            inserirTipos(con, motorista);
        } catch (SQLException e) {
            throw new InsertException("Erro ao inserir o motorista.", e);
        }
    }

    public void update(Motorista motorista) throws UpdateException {
        try {
            Connection con = Conexao.getConexao();
            String sql = "update motorista set nome = ?, telefone = ?, email = ?, cnh_numero = ?, cnh_categoria = ?, cnh_validade = ?, data_admissao = ?, vinculo = ?, situacao = ? where cpf = ?";
            try (PreparedStatement comando = con.prepareStatement(sql)) {
                comando.setString(1, motorista.getNome());
                comando.setString(2, motorista.getTelefone());
                comando.setString(3, motorista.getEmail());
                comando.setString(4, motorista.getCnh().getNumeroDeCNH());
                comando.setString(5, motorista.getCnh().getCategoria());
                comando.setObject(6, motorista.getCnh().getDataDeValidade());
                comando.setObject(7, motorista.getDataDeAdmissao());
                comando.setString(8, motorista.getVinculo().name());
                comando.setString(9, motorista.getSituacao().name());
                comando.setString(10, motorista.getCPF());
                comando.executeUpdate();
            }
            try (PreparedStatement comando = con.prepareStatement("delete from motorista_tipo_veiculo where cpf = ?")) {
                comando.setString(1, motorista.getCPF());
                comando.executeUpdate();
            }
            inserirTipos(con, motorista);
        } catch (SQLException e) {
            throw new UpdateException("Erro ao atualizar o motorista.", e);
        }
    }

    public void delete(Motorista motorista) throws DeleteException {
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("delete from motorista where cpf = ?")) {
                comando.setString(1, motorista.getCPF());
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DeleteException("Erro ao excluir o motorista.", e);
        }
    }

    public Motorista select(String cpf) throws SelectException {
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("select * from motorista where cpf = ?")) {
                comando.setString(1, cpf);
                try (ResultSet resultado = comando.executeQuery()) {
                    if (resultado.next()) {
                        return montar(resultado);
                    }
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new SelectException("Erro ao buscar o motorista.", e);
        }
    }

    public ArrayList<Motorista> selectAll() throws SelectException {
        ArrayList<Motorista> motoristas = new ArrayList<>();
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("select * from motorista order by nome");
                 ResultSet resultado = comando.executeQuery()) {
                while (resultado.next()) {
                    motoristas.add(montar(resultado));
                }
            }
        } catch (SQLException e) {
            throw new SelectException("Erro ao buscar os motoristas.", e);
        }
        return motoristas;
    }

    private void inserirTipos(Connection con, Motorista motorista) throws SQLException {
        for (TipoDeVeiculo tipo : motorista.getTipoDeVeiculosDisponiveis()) {
            try (PreparedStatement comando = con.prepareStatement("insert into motorista_tipo_veiculo(cpf, tipo) values (?, ?)")) {
                comando.setString(1, motorista.getCPF());
                comando.setString(2, tipo.name());
                comando.executeUpdate();
            }
        }
    }

    private ArrayList<TipoDeVeiculo> selecionarTipos(Connection con, String cpf) throws SQLException {
        ArrayList<TipoDeVeiculo> tipos = new ArrayList<>();
        try (PreparedStatement comando = con.prepareStatement("select tipo from motorista_tipo_veiculo where cpf = ?")) {
            comando.setString(1, cpf);
            try (ResultSet resultado = comando.executeQuery()) {
                while (resultado.next()) {
                    tipos.add(TipoDeVeiculo.valueOf(resultado.getString("tipo")));
                }
            }
        }
        return tipos;
    }

    private Motorista montar(ResultSet resultado) throws SQLException, SelectException {
        Connection con = Conexao.getConexao();
        String cpf = resultado.getString("cpf");
        Endereco endereco = EnderecoDAO.getInstance().select(resultado.getObject("id_endereco", Integer.class));
        Cnh cnh = new Cnh(resultado.getString("cnh_numero"), resultado.getString("cnh_categoria"),
                resultado.getObject("cnh_validade", LocalDate.class));
        return new Motorista(resultado.getString("nome"), endereco, resultado.getString("telefone"),
                resultado.getString("email"), cpf, cnh, resultado.getObject("data_admissao", LocalDate.class),
                TipoDeVinculo.valueOf(resultado.getString("vinculo")),
                Situacao.valueOf(resultado.getString("situacao")), selecionarTipos(con, cpf));
    }
}
