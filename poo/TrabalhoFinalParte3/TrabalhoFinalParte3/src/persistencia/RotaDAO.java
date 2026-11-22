package persistencia;

import dados.Motorista;
import dados.Pedido;
import dados.Rota;
import dados.Veiculo;
import dados.enums.StatusRota;
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

public class RotaDAO {

    private static RotaDAO instance;

    private RotaDAO() {
    }

    public static RotaDAO getInstance() {
        if (instance == null) {
            instance = new RotaDAO();
        }
        return instance;
    }

    public void insert(Rota rota) throws InsertException {
        try {
            Connection con = Conexao.getConexao();
            String sql = "insert into rota(numero, data, placa_veiculo, cpf_motorista, quilometragem_inicial, quilometragem_final, status) values (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement comando = con.prepareStatement(sql)) {
                comando.setInt(1, rota.getNumero());
                comando.setObject(2, rota.getData());
                comando.setString(3, rota.getVeiculoAlocado().getPlaca());
                comando.setString(4, rota.getMotoristaResponsavel().getCPF());
                comando.setDouble(5, rota.getQuilometragemInicial());
                comando.setDouble(6, rota.getQuilometragemFinal());
                comando.setString(7, rota.getStatus().name());
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new InsertException("Erro ao inserir a rota.", e);
        }
    }

    public void update(Rota rota) throws UpdateException {
        try {
            Connection con = Conexao.getConexao();
            String sql = "update rota set data = ?, placa_veiculo = ?, cpf_motorista = ?, quilometragem_inicial = ?, quilometragem_final = ?, status = ? where numero = ?";
            try (PreparedStatement comando = con.prepareStatement(sql)) {
                comando.setObject(1, rota.getData());
                comando.setString(2, rota.getVeiculoAlocado().getPlaca());
                comando.setString(3, rota.getMotoristaResponsavel().getCPF());
                comando.setDouble(4, rota.getQuilometragemInicial());
                comando.setDouble(5, rota.getQuilometragemFinal());
                comando.setString(6, rota.getStatus().name());
                comando.setInt(7, rota.getNumero());
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new UpdateException("Erro ao atualizar a rota.", e);
        }
    }

    public void delete(Rota rota) throws DeleteException {
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("delete from rota where numero = ?")) {
                comando.setInt(1, rota.getNumero());
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DeleteException("Erro ao excluir a rota.", e);
        }
    }

    public ArrayList<Rota> selectAll(ArrayList<Veiculo> veiculos, ArrayList<Motorista> motoristas, ArrayList<Pedido> pedidos) throws SelectException {
        ArrayList<Rota> rotas = new ArrayList<>();
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("select * from rota order by numero");
                 ResultSet resultado = comando.executeQuery()) {
                while (resultado.next()) {
                    int numero = resultado.getInt("numero");
                    String placa = resultado.getString("placa_veiculo");
                    String cpf = resultado.getString("cpf_motorista");

                    Veiculo veiculo = null;
                    for (Veiculo candidato : veiculos) {
                        if (candidato.getPlaca().equals(placa)) {
                            veiculo = candidato;
                        }
                    }
                    Motorista motorista = null;
                    for (Motorista candidato : motoristas) {
                        if (candidato.getCPF().equals(cpf)) {
                            motorista = candidato;
                        }
                    }
                    Rota rota = new Rota(numero, resultado.getObject("data", LocalDate.class), veiculo, motorista);
                    rota.setQuilometragemInicial(resultado.getDouble("quilometragem_inicial"));
                    rota.setQuilometragemFinal(resultado.getDouble("quilometragem_final"));
                    rota.setStatus(StatusRota.valueOf(resultado.getString("status")));
                    rota.setPedidos(PedidoNaRotaDAO.getInstance().selectByRota(numero, pedidos));
                    rota.setDespesas(DespesaDAO.getInstance().selectByRota(numero));
                    rotas.add(rota);
                }
            }
        } catch (SQLException e) {
            throw new SelectException("Erro ao buscar as rotas.", e);
        }
        return rotas;
    }

    public int proximoNumero() throws SelectException {
        try {
            return Conexao.proximoValor("numero_rota");
        } catch (SQLException e) {
            throw new SelectException("Erro ao obter o número da rota.", e);
        }
    }
}
