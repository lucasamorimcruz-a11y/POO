package persistencia;

import dados.TabelaPreco;
import dados.enums.Regiao;
import dados.enums.TipoDeServico;
import exceptions.DeleteException;
import exceptions.InsertException;
import exceptions.SelectException;
import exceptions.UpdateException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumMap;
import java.util.Map;

public class TabelaPrecoDAO {

    private static TabelaPrecoDAO instance;

    private TabelaPrecoDAO() {
    }

    public static TabelaPrecoDAO getInstance() {
        if (instance == null) {
            instance = new TabelaPrecoDAO();
        }
        return instance;
    }

    public Integer insert(TabelaPreco tabela, boolean padrao) throws InsertException {
        if (tabela == null) {
            return null;
        }
        try {
            Connection con = Conexao.getConexao();
            int id = Conexao.proximoValor("id_tabela_preco");
            String sql = "insert into tabela_preco(id, valor_kg, valor_km, valor_minimo, desconto_contratado, fator_economico, fator_expresso, fator_agendado, fator_local, fator_estadual, fator_interestadual, fator_outro, padrao) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement comando = con.prepareStatement(sql)) {
                comando.setInt(1, id);
                preencher(comando, 2, tabela);
                comando.setBoolean(13, padrao);
                comando.executeUpdate();
            }
            return id;
        } catch (SQLException e) {
            throw new InsertException("Erro ao inserir a tabela de preços.", e);
        }
    }

    public void update(int id, TabelaPreco tabela) throws UpdateException {
        try {
            Connection con = Conexao.getConexao();
            String sql = "update tabela_preco set valor_kg = ?, valor_km = ?, valor_minimo = ?, desconto_contratado = ?, fator_economico = ?, fator_expresso = ?, fator_agendado = ?, fator_local = ?, fator_estadual = ?, fator_interestadual = ?, fator_outro = ? where id = ?";
            try (PreparedStatement comando = con.prepareStatement(sql)) {
                preencher(comando, 1, tabela);
                comando.setInt(12, id);
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new UpdateException("Erro ao atualizar a tabela de preços.", e);
        }
    }

    public void updatePadrao(TabelaPreco tabela) throws UpdateException {
        try {
            Connection con = Conexao.getConexao();
            String sql = "update tabela_preco set valor_kg = ?, valor_km = ?, valor_minimo = ?, desconto_contratado = ?, fator_economico = ?, fator_expresso = ?, fator_agendado = ?, fator_local = ?, fator_estadual = ?, fator_interestadual = ?, fator_outro = ? where padrao = true";
            try (PreparedStatement comando = con.prepareStatement(sql)) {
                preencher(comando, 1, tabela);
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new UpdateException("Erro ao atualizar a tabela de preços padrão.", e);
        }
    }

    public void delete(int id) throws DeleteException {
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("delete from tabela_preco where id = ?")) {
                comando.setInt(1, id);
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DeleteException("Erro ao excluir a tabela de preços.", e);
        }
    }

    public TabelaPreco select(Integer id) throws SelectException {
        if (id == null) {
            return null;
        }
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("select * from tabela_preco where id = ?")) {
                comando.setInt(1, id);
                try (ResultSet resultado = comando.executeQuery()) {
                    if (resultado.next()) {
                        return montar(resultado);
                    }
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new SelectException("Erro ao buscar a tabela de preços.", e);
        }
    }

    public TabelaPreco selectPadrao() throws SelectException {
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("select * from tabela_preco where padrao = true");
                 ResultSet resultado = comando.executeQuery()) {
                if (resultado.next()) {
                    return montar(resultado);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new SelectException("Erro ao buscar a tabela de preços padrão.", e);
        }
    }

    private void preencher(PreparedStatement comando, int primeiro, TabelaPreco tabela) throws SQLException {
        comando.setDouble(primeiro, tabela.getValorPorKg());
        comando.setDouble(primeiro + 1, tabela.getValorPorKm());
        comando.setDouble(primeiro + 2, tabela.getValorMinimo());
        comando.setDouble(primeiro + 3, tabela.getDescontoContratado());
        comando.setObject(primeiro + 4, tabela.getFatorPorTipoDeServico().get(TipoDeServico.ECONOMICO));
        comando.setObject(primeiro + 5, tabela.getFatorPorTipoDeServico().get(TipoDeServico.EXPRESSO));
        comando.setObject(primeiro + 6, tabela.getFatorPorTipoDeServico().get(TipoDeServico.AGENDADO));
        comando.setObject(primeiro + 7, tabela.getFatorPorRegiao().get(Regiao.LOCAL));
        comando.setObject(primeiro + 8, tabela.getFatorPorRegiao().get(Regiao.ESTADUAL));
        comando.setObject(primeiro + 9, tabela.getFatorPorRegiao().get(Regiao.INTERESTADUAL));
        comando.setObject(primeiro + 10, tabela.getFatorPorRegiao().get(Regiao.OUTRO));
    }

    private TabelaPreco montar(ResultSet resultado) throws SQLException {
        Map<TipoDeServico, Double> fatoresServico = new EnumMap<>(TipoDeServico.class);
        guardar(fatoresServico, TipoDeServico.ECONOMICO, resultado.getObject("fator_economico", Double.class));
        guardar(fatoresServico, TipoDeServico.EXPRESSO, resultado.getObject("fator_expresso", Double.class));
        guardar(fatoresServico, TipoDeServico.AGENDADO, resultado.getObject("fator_agendado", Double.class));

        Map<Regiao, Double> fatoresRegiao = new EnumMap<>(Regiao.class);
        guardar(fatoresRegiao, Regiao.LOCAL, resultado.getObject("fator_local", Double.class));
        guardar(fatoresRegiao, Regiao.ESTADUAL, resultado.getObject("fator_estadual", Double.class));
        guardar(fatoresRegiao, Regiao.INTERESTADUAL, resultado.getObject("fator_interestadual", Double.class));
        guardar(fatoresRegiao, Regiao.OUTRO, resultado.getObject("fator_outro", Double.class));

        return new TabelaPreco(resultado.getDouble("valor_kg"), resultado.getDouble("valor_km"),
                resultado.getDouble("valor_minimo"), resultado.getDouble("desconto_contratado"),
                fatoresServico, fatoresRegiao);
    }

    private <T> void guardar(Map<T, Double> mapa, T chave, Double valor) {
        if (valor != null) {
            mapa.put(chave, valor);
        }
    }
}
