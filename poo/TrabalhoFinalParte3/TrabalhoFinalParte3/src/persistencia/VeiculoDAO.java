package persistencia;

import dados.Manutencao;
import dados.Veiculo;
import dados.enums.StatusVeiculo;
import dados.enums.TipoDeVeiculo;
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

public class VeiculoDAO {

    private static VeiculoDAO instance;

    private VeiculoDAO() {
    }

    public static VeiculoDAO getInstance() {
        if (instance == null) {
            instance = new VeiculoDAO();
        }
        return instance;
    }

    public void insert(Veiculo veiculo) throws InsertException {
        try {
            Connection con = Conexao.getConexao();
            String sql = "insert into veiculo(placa, marca, modelo, ano_fabricacao, capacidade_quilos, capacidade_m3, quilometragem_atual, data_aquisicao, data_proxima_manutencao, tipo, status) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement comando = con.prepareStatement(sql)) {
                comando.setString(1, veiculo.getPlaca());
                comando.setString(2, veiculo.getMarca());
                comando.setString(3, veiculo.getModelo());
                comando.setInt(4, veiculo.getAnoDeFabricacao());
                comando.setDouble(5, veiculo.getCapacidadeEmQuilos());
                comando.setDouble(6, veiculo.getCapacidadeEmMetroCubicos());
                comando.setDouble(7, veiculo.getQuilometragemAtual());
                comando.setObject(8, veiculo.getDataDeAquisicao());
                comando.setObject(9, veiculo.getDataProximaManuntencao());
                comando.setString(10, veiculo.getTipoDeVeiculo().name());
                comando.setString(11, veiculo.getStatusVeiculo().name());
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new InsertException("Erro ao inserir o veículo.", e);
        }
        for (Manutencao manutencao : veiculo.getManutencoes()) {
            ManutencaoDAO.getInstance().insert(veiculo, manutencao);
        }
    }

    public void update(Veiculo veiculo) throws UpdateException {
        try {
            Connection con = Conexao.getConexao();
            String sql = "update veiculo set marca = ?, modelo = ?, ano_fabricacao = ?, capacidade_quilos = ?, capacidade_m3 = ?, quilometragem_atual = ?, data_aquisicao = ?, data_proxima_manutencao = ?, tipo = ?, status = ? where placa = ?";
            try (PreparedStatement comando = con.prepareStatement(sql)) {
                comando.setString(1, veiculo.getMarca());
                comando.setString(2, veiculo.getModelo());
                comando.setInt(3, veiculo.getAnoDeFabricacao());
                comando.setDouble(4, veiculo.getCapacidadeEmQuilos());
                comando.setDouble(5, veiculo.getCapacidadeEmMetroCubicos());
                comando.setDouble(6, veiculo.getQuilometragemAtual());
                comando.setObject(7, veiculo.getDataDeAquisicao());
                comando.setObject(8, veiculo.getDataProximaManuntencao());
                comando.setString(9, veiculo.getTipoDeVeiculo().name());
                comando.setString(10, veiculo.getStatusVeiculo().name());
                comando.setString(11, veiculo.getPlaca());
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new UpdateException("Erro ao atualizar o veículo.", e);
        }
    }

    public void delete(Veiculo veiculo) throws DeleteException {
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("delete from veiculo where placa = ?")) {
                comando.setString(1, veiculo.getPlaca());
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DeleteException("Erro ao excluir o veículo.", e);
        }
    }

    public Veiculo select(String placa) throws SelectException {
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("select * from veiculo where placa = ?")) {
                comando.setString(1, placa);
                try (ResultSet resultado = comando.executeQuery()) {
                    if (resultado.next()) {
                        return montar(resultado);
                    }
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new SelectException("Erro ao buscar o veículo.", e);
        }
    }

    public ArrayList<Veiculo> selectAll() throws SelectException {
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("select * from veiculo order by placa");
                 ResultSet resultado = comando.executeQuery()) {
                while (resultado.next()) {
                    veiculos.add(montar(resultado));
                }
            }
        } catch (SQLException e) {
            throw new SelectException("Erro ao buscar os veículos.", e);
        }
        return veiculos;
    }

    private Veiculo montar(ResultSet resultado) throws SQLException, SelectException {
        String placa = resultado.getString("placa");
        ArrayList<Manutencao> manutencoes = ManutencaoDAO.getInstance().selectByVeiculo(placa);
        return new Veiculo(placa, resultado.getString("marca"), resultado.getString("modelo"),
                resultado.getInt("ano_fabricacao"), resultado.getDouble("capacidade_quilos"),
                resultado.getDouble("capacidade_m3"), resultado.getDouble("quilometragem_atual"),
                resultado.getObject("data_aquisicao", LocalDate.class),
                resultado.getObject("data_proxima_manutencao", LocalDate.class),
                TipoDeVeiculo.valueOf(resultado.getString("tipo")),
                StatusVeiculo.valueOf(resultado.getString("status")), manutencoes);
    }
}
