package persistencia;

import dados.EventoDeRastreamento;
import dados.Pedido;
import dados.enums.TipoDeEvento;
import exceptions.InsertException;
import exceptions.SelectException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class EventoDAO {

    private static EventoDAO instance;

    private EventoDAO() {
    }

    public static EventoDAO getInstance() {
        if (instance == null) {
            instance = new EventoDAO();
        }
        return instance;
    }

    public void insert(Pedido pedido, EventoDeRastreamento evento) throws InsertException {
        try {
            Connection con = Conexao.getConexao();
            int id = Conexao.proximoValor("id_evento");
            String sql = "insert into evento(id, numero_pedido, data, hora, tipo, localizacao, descricao, foto) values (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement comando = con.prepareStatement(sql)) {
                comando.setInt(1, id);
                comando.setInt(2, pedido.getNumero());
                comando.setObject(3, evento.getData());
                comando.setObject(4, evento.getHora());
                comando.setString(5, evento.getTipo().name());
                comando.setString(6, evento.getLocalizacao());
                comando.setString(7, evento.getDescricao());
                comando.setString(8, evento.getFotoComprobatoria());
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new InsertException("Erro ao inserir o evento de rastreamento.", e);
        }
    }

    public ArrayList<EventoDeRastreamento> selectByPedido(int numeroPedido) throws SelectException {
        ArrayList<EventoDeRastreamento> eventos = new ArrayList<>();
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("select * from evento where numero_pedido = ? order by id")) {
                comando.setInt(1, numeroPedido);
                try (ResultSet resultado = comando.executeQuery()) {
                    while (resultado.next()) {
                        eventos.add(new EventoDeRastreamento(resultado.getObject("data", LocalDate.class),
                                resultado.getObject("hora", LocalTime.class),
                                TipoDeEvento.valueOf(resultado.getString("tipo")),
                                resultado.getString("localizacao"), resultado.getString("descricao"),
                                resultado.getString("foto")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new SelectException("Erro ao buscar os eventos de rastreamento.", e);
        }
        return eventos;
    }
}
