package persistencia;

import dados.Entrega;
import dados.Pedido;
import exceptions.InsertException;
import exceptions.SelectException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class EntregaDAO {

    private static EntregaDAO instance;

    private EntregaDAO() {
    }

    public static EntregaDAO getInstance() {
        if (instance == null) {
            instance = new EntregaDAO();
        }
        return instance;
    }

    public void insert(Pedido pedido, Entrega entrega) throws InsertException {
        try {
            Connection con = Conexao.getConexao();
            int id = Conexao.proximoValor("id_entrega");
            String sql = "insert into entrega(id, numero_pedido, nome_quem_recebeu, documento_identificacao, parentesco_funcao, data, hora, assinatura_ou_foto) values (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement comando = con.prepareStatement(sql)) {
                comando.setInt(1, id);
                comando.setInt(2, pedido.getNumero());
                comando.setString(3, entrega.getNomeDeQuemRecebeu());
                comando.setString(4, entrega.getDocumentoDeIdentificacao());
                comando.setString(5, entrega.getParentescoOuFuncao());
                comando.setObject(6, entrega.getData());
                comando.setObject(7, entrega.getHora());
                comando.setString(8, entrega.getAssinaturaOuFotoDeComprovante());
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new InsertException("Erro ao inserir a entrega.", e);
        }
    }

    public Entrega selectByPedido(int numeroPedido) throws SelectException {
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("select * from entrega where numero_pedido = ?")) {
                comando.setInt(1, numeroPedido);
                try (ResultSet resultado = comando.executeQuery()) {
                    if (resultado.next()) {
                        return new Entrega(resultado.getString("nome_quem_recebeu"),
                                resultado.getString("documento_identificacao"),
                                resultado.getString("parentesco_funcao"),
                                resultado.getObject("data", LocalDate.class),
                                resultado.getObject("hora", LocalTime.class),
                                resultado.getString("assinatura_ou_foto"));
                    }
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new SelectException("Erro ao buscar a entrega.", e);
        }
    }
}
