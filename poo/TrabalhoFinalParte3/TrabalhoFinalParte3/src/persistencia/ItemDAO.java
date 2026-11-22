package persistencia;

import dados.Item;
import dados.Pedido;
import exceptions.InsertException;
import exceptions.SelectException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAO {

    private static ItemDAO instance;

    private ItemDAO() {
    }

    public static ItemDAO getInstance() {
        if (instance == null) {
            instance = new ItemDAO();
        }
        return instance;
    }

    public void insert(Pedido pedido, Item item) throws InsertException {
        try {
            Connection con = Conexao.getConexao();
            int id = Conexao.proximoValor("id_item");
            String sql = "insert into item(id, numero_pedido, tipo_embalagem, peso, dimensao, descricao) values (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement comando = con.prepareStatement(sql)) {
                comando.setInt(1, id);
                comando.setInt(2, pedido.getNumero());
                comando.setString(3, item.getTipoDeEmbalagem());
                comando.setFloat(4, item.getPeso());
                comando.setFloat(5, item.getDimensao());
                comando.setString(6, item.getDescricao());
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new InsertException("Erro ao inserir o item.", e);
        }
    }

    public ArrayList<Item> selectByPedido(int numeroPedido) throws SelectException {
        ArrayList<Item> itens = new ArrayList<>();
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("select * from item where numero_pedido = ? order by id")) {
                comando.setInt(1, numeroPedido);
                try (ResultSet resultado = comando.executeQuery()) {
                    while (resultado.next()) {
                        itens.add(new Item(resultado.getString("tipo_embalagem"), resultado.getFloat("peso"),
                                resultado.getFloat("dimensao"), resultado.getString("descricao")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new SelectException("Erro ao buscar os itens.", e);
        }
        return itens;
    }
}
