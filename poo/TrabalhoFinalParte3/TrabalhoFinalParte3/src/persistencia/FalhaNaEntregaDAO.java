package persistencia;

import dados.FalhaNaEntrega;
import dados.Pedido;
import exceptions.InsertException;
import exceptions.SelectException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FalhaNaEntregaDAO {

    private static FalhaNaEntregaDAO instance;

    private FalhaNaEntregaDAO() {
    }

    public static FalhaNaEntregaDAO getInstance() {
        if (instance == null) {
            instance = new FalhaNaEntregaDAO();
        }
        return instance;
    }

    public void insert(Pedido pedido, FalhaNaEntrega falha) throws InsertException {
        try {
            Connection con = Conexao.getConexao();
            int id = Conexao.proximoValor("id_falha");
            String sql = "insert into falha_entrega(id, numero_pedido, motivo, nova_tentativa) values (?, ?, ?, ?)";
            try (PreparedStatement comando = con.prepareStatement(sql)) {
                comando.setInt(1, id);
                comando.setInt(2, pedido.getNumero());
                comando.setString(3, falha.getMotivo());
                comando.setBoolean(4, falha.isNovaTentativa());
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new InsertException("Erro ao inserir a falha na entrega.", e);
        }
    }

    public ArrayList<FalhaNaEntrega> selectByPedido(int numeroPedido) throws SelectException {
        ArrayList<FalhaNaEntrega> falhas = new ArrayList<>();
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("select * from falha_entrega where numero_pedido = ? order by id")) {
                comando.setInt(1, numeroPedido);
                try (ResultSet resultado = comando.executeQuery()) {
                    while (resultado.next()) {
                        falhas.add(new FalhaNaEntrega(resultado.getString("motivo"),
                                resultado.getBoolean("nova_tentativa")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new SelectException("Erro ao buscar as falhas na entrega.", e);
        }
        return falhas;
    }
}
