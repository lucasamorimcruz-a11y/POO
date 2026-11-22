package persistencia;

import dados.Despesa;
import dados.Rota;
import dados.enums.TipoDeDespesa;
import exceptions.InsertException;
import exceptions.SelectException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DespesaDAO {

    private static DespesaDAO instance;

    private DespesaDAO() {
    }

    public static DespesaDAO getInstance() {
        if (instance == null) {
            instance = new DespesaDAO();
        }
        return instance;
    }

    public void insert(Rota rota, Despesa despesa) throws InsertException {
        try {
            Connection con = Conexao.getConexao();
            int id = Conexao.proximoValor("id_despesa");
            String sql = "insert into despesa(id, numero_rota, data, tipo, valor, comprovante) values (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement comando = con.prepareStatement(sql)) {
                comando.setInt(1, id);
                comando.setInt(2, rota.getNumero());
                comando.setObject(3, despesa.getData());
                comando.setString(4, despesa.getTipoDeDespesa().name());
                comando.setDouble(5, despesa.getValor());
                comando.setString(6, despesa.getComprovante());
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new InsertException("Erro ao inserir a despesa.", e);
        }
    }

    public ArrayList<Despesa> selectByRota(int numeroRota) throws SelectException {
        ArrayList<Despesa> despesas = new ArrayList<>();
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("select * from despesa where numero_rota = ? order by id")) {
                comando.setInt(1, numeroRota);
                try (ResultSet resultado = comando.executeQuery()) {
                    while (resultado.next()) {
                        despesas.add(new Despesa(resultado.getObject("data", LocalDate.class),
                                TipoDeDespesa.valueOf(resultado.getString("tipo")),
                                resultado.getDouble("valor"), resultado.getString("comprovante")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new SelectException("Erro ao buscar as despesas.", e);
        }
        return despesas;
    }
}
