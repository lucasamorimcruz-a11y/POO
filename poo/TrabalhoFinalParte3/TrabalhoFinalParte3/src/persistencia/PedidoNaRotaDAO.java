package persistencia;

import dados.Pedido;
import dados.PedidoNaRota;
import dados.Rota;
import exceptions.InsertException;
import exceptions.SelectException;
import exceptions.UpdateException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PedidoNaRotaDAO {

    private static PedidoNaRotaDAO instance;

    private PedidoNaRotaDAO() {
    }

    public static PedidoNaRotaDAO getInstance() {
        if (instance == null) {
            instance = new PedidoNaRotaDAO();
        }
        return instance;
    }

    public void insert(Rota rota, PedidoNaRota parada) throws InsertException {
        try {
            Connection con = Conexao.getConexao();
            String sql = "insert into pedido_na_rota(numero_rota, numero_pedido, sequencia_entrega, horario_previsto, horario_real) values (?, ?, ?, ?, ?)";
            try (PreparedStatement comando = con.prepareStatement(sql)) {
                comando.setInt(1, rota.getNumero());
                comando.setInt(2, parada.getPedido().getNumero());
                comando.setInt(3, parada.getSequenciaEntrega());
                comando.setObject(4, parada.getHorarioPrevistoDeEntrega());
                comando.setObject(5, parada.getHorarioRealDeEntrega());
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new InsertException("Erro ao inserir o pedido na rota.", e);
        }
    }

    public void update(Rota rota, PedidoNaRota parada) throws UpdateException {
        try {
            Connection con = Conexao.getConexao();
            String sql = "update pedido_na_rota set sequencia_entrega = ?, horario_previsto = ?, horario_real = ? where numero_rota = ? and numero_pedido = ?";
            try (PreparedStatement comando = con.prepareStatement(sql)) {
                comando.setInt(1, parada.getSequenciaEntrega());
                comando.setObject(2, parada.getHorarioPrevistoDeEntrega());
                comando.setObject(3, parada.getHorarioRealDeEntrega());
                comando.setInt(4, rota.getNumero());
                comando.setInt(5, parada.getPedido().getNumero());
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new UpdateException("Erro ao atualizar o pedido na rota.", e);
        }
    }

    public ArrayList<PedidoNaRota> selectByRota(int numeroRota, ArrayList<Pedido> pedidos) throws SelectException {
        ArrayList<PedidoNaRota> paradas = new ArrayList<>();
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("select * from pedido_na_rota where numero_rota = ? order by sequencia_entrega")) {
                comando.setInt(1, numeroRota);
                try (ResultSet resultado = comando.executeQuery()) {
                    while (resultado.next()) {
                        int numeroPedido = resultado.getInt("numero_pedido");
                        Pedido pedido = null;
                        for (Pedido candidato : pedidos) {
                            if (candidato.getNumero() == numeroPedido) {
                                pedido = candidato;
                            }
                        }
                        PedidoNaRota parada = new PedidoNaRota(resultado.getInt("sequencia_entrega"), pedido,
                                resultado.getObject("horario_previsto", LocalDateTime.class));
                        parada.setHorarioRealDeEntrega(resultado.getObject("horario_real", LocalDateTime.class));
                        paradas.add(parada);
                    }
                }
            }
        } catch (SQLException e) {
            throw new SelectException("Erro ao buscar os pedidos da rota.", e);
        }
        return paradas;
    }
}
