package persistencia;

import dados.Manutencao;
import dados.Veiculo;
import dados.enums.TipoDeManutencao;
import exceptions.InsertException;
import exceptions.SelectException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ManutencaoDAO {

    private static ManutencaoDAO instance;

    private ManutencaoDAO() {
    }

    public static ManutencaoDAO getInstance() {
        if (instance == null) {
            instance = new ManutencaoDAO();
        }
        return instance;
    }

    public void insert(Veiculo veiculo, Manutencao manutencao) throws InsertException {
        try {
            Connection con = Conexao.getConexao();
            int id = Conexao.proximoValor("id_manutencao");
            String sql = "insert into manutencao(id, placa, data_manutencao, tipo, quilometragem, descricao_servicos, pecas_trocadas, oficina, valor_total, proxima_manutencao) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement comando = con.prepareStatement(sql)) {
                comando.setInt(1, id);
                comando.setString(2, veiculo.getPlaca());
                comando.setObject(3, manutencao.getDataDaManutencao());
                comando.setString(4, manutencao.getTipoDeManutencao().name());
                comando.setDouble(5, manutencao.getQuilometragem());
                comando.setString(6, manutencao.getDescricaoDosServicos());
                comando.setString(7, manutencao.getPecasTrocadas());
                comando.setString(8, manutencao.getOficina());
                comando.setDouble(9, manutencao.getValorTotal());
                comando.setObject(10, manutencao.getProximaManutencao());
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new InsertException("Erro ao inserir a manutenção.", e);
        }
    }

    public ArrayList<Manutencao> selectByVeiculo(String placa) throws SelectException {
        ArrayList<Manutencao> manutencoes = new ArrayList<>();
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("select * from manutencao where placa = ? order by id")) {
                comando.setString(1, placa);
                try (ResultSet resultado = comando.executeQuery()) {
                    while (resultado.next()) {
                        manutencoes.add(new Manutencao(resultado.getObject("data_manutencao", LocalDate.class),
                                TipoDeManutencao.valueOf(resultado.getString("tipo")),
                                resultado.getDouble("quilometragem"),
                                resultado.getString("descricao_servicos"),
                                resultado.getString("pecas_trocadas"),
                                resultado.getString("oficina"),
                                resultado.getDouble("valor_total"),
                                resultado.getObject("proxima_manutencao", LocalDate.class)));
                    }
                }
            }
        } catch (SQLException e) {
            throw new SelectException("Erro ao buscar as manutenções.", e);
        }
        return manutencoes;
    }
}
