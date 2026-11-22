package persistencia;

import dados.Cliente;
import dados.Endereco;
import dados.Pedido;
import dados.enums.FormaDePagamento;
import dados.enums.Periodo;
import dados.enums.StatusPedido;
import dados.enums.TipoDeServico;
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

public class PedidoDAO {

    private static PedidoDAO instance;

    private PedidoDAO() {
    }

    public static PedidoDAO getInstance() {
        if (instance == null) {
            instance = new PedidoDAO();
        }
        return instance;
    }

    public void insert(Pedido pedido) throws InsertException {
        Integer idColeta = EnderecoDAO.getInstance().insert(pedido.getEnderecoDeColeta());
        Integer idEntrega = EnderecoDAO.getInstance().insert(pedido.getEnderecoDeEntrega());
        try {
            Connection con = Conexao.getConexao();
            String sql = "insert into pedido(numero, data_solicitacao, documento_cliente, id_endereco_coleta, contato_local, data_agendada_coleta, periodo, descricao_carga, peso_total, volume_total, valor_declarado, id_endereco_entrega, contato_destino, prazo_entrega, tipo_servico, valor_frete, forma_pagamento, status) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement comando = con.prepareStatement(sql)) {
                comando.setInt(1, pedido.getNumero());
                comando.setObject(2, pedido.getDataDeSolicitacao());
                comando.setString(3, pedido.getCliente().getPessoa().getDocumento());
                comando.setObject(4, idColeta);
                comando.setString(5, pedido.getContatoNoLocal());
                comando.setObject(6, pedido.getDataAgendadaPraColeta());
                comando.setString(7, pedido.getPeriodo().name());
                comando.setString(8, pedido.getDescricaoDaCarga());
                comando.setDouble(9, pedido.getPesoTotal());
                comando.setDouble(10, pedido.getVolumeTotal());
                comando.setDouble(11, pedido.getValorDeclarado());
                comando.setObject(12, idEntrega);
                comando.setString(13, pedido.getContatoNoDestino());
                comando.setObject(14, pedido.getPrazoDeEntrega());
                comando.setString(15, pedido.getTipoDeServico().name());
                comando.setDouble(16, pedido.getValorFrete());
                comando.setString(17, pedido.getFormaDePagamento().name());
                comando.setString(18, pedido.getStatusPedido().name());
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new InsertException("Erro ao inserir o pedido.", e);
        }
    }

    public void update(Pedido pedido) throws UpdateException {
        try {
            Connection con = Conexao.getConexao();
            String sql = "update pedido set peso_total = ?, volume_total = ?, valor_declarado = ?, valor_frete = ?, status = ? where numero = ?";
            try (PreparedStatement comando = con.prepareStatement(sql)) {
                comando.setDouble(1, pedido.getPesoTotal());
                comando.setDouble(2, pedido.getVolumeTotal());
                comando.setDouble(3, pedido.getValorDeclarado());
                comando.setDouble(4, pedido.getValorFrete());
                comando.setString(5, pedido.getStatusPedido().name());
                comando.setInt(6, pedido.getNumero());
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new UpdateException("Erro ao atualizar o pedido.", e);
        }
    }

    public void delete(Pedido pedido) throws DeleteException {
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("delete from pedido where numero = ?")) {
                comando.setInt(1, pedido.getNumero());
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DeleteException("Erro ao excluir o pedido.", e);
        }
    }

    public Pedido select(int numero, ArrayList<Cliente> clientes) throws SelectException {
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("select * from pedido where numero = ?")) {
                comando.setInt(1, numero);
                try (ResultSet resultado = comando.executeQuery()) {
                    if (resultado.next()) {
                        return montar(resultado, clientes);
                    }
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new SelectException("Erro ao buscar o pedido.", e);
        }
    }

    public ArrayList<Pedido> selectAll(ArrayList<Cliente> clientes) throws SelectException {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("select * from pedido order by numero");
                 ResultSet resultado = comando.executeQuery()) {
                while (resultado.next()) {
                    pedidos.add(montar(resultado, clientes));
                }
            }
        } catch (SQLException e) {
            throw new SelectException("Erro ao buscar os pedidos.", e);
        }
        return pedidos;
    }

    public int proximoNumero() throws SelectException {
        try {
            return Conexao.proximoValor("numero_pedido");
        } catch (SQLException e) {
            throw new SelectException("Erro ao obter o número do pedido.", e);
        }
    }

    private Pedido montar(ResultSet resultado, ArrayList<Cliente> clientes) throws SQLException, SelectException {
        int numero = resultado.getInt("numero");
        String documento = resultado.getString("documento_cliente");
        Cliente cliente = null;
        for (Cliente candidato : clientes) {
            if (candidato.getPessoa().getDocumento().equals(documento)) {
                cliente = candidato;
            }
        }
        Endereco coleta = EnderecoDAO.getInstance().select(resultado.getObject("id_endereco_coleta", Integer.class));
        Endereco entrega = EnderecoDAO.getInstance().select(resultado.getObject("id_endereco_entrega", Integer.class));
        Pedido pedido = new Pedido(numero, resultado.getObject("data_solicitacao", LocalDate.class), cliente, coleta, resultado.getString("contato_local"), resultado.getObject("data_agendada_coleta", LocalDate.class), Periodo.valueOf(resultado.getString("periodo")), resultado.getString("descricao_carga"), resultado.getDouble("valor_declarado"), entrega, resultado.getString("contato_destino"), resultado.getObject("prazo_entrega", LocalDate.class), TipoDeServico.valueOf(resultado.getString("tipo_servico")), FormaDePagamento.valueOf(resultado.getString("forma_pagamento")));
        pedido.setPesoTotal(resultado.getDouble("peso_total"));
        pedido.setVolumeTotal(resultado.getDouble("volume_total"));
        pedido.setValorFrete(resultado.getDouble("valor_frete"));
        pedido.setStatusPedido(StatusPedido.valueOf(resultado.getString("status")));
        pedido.setItens(ItemDAO.getInstance().selectByPedido(numero));
        pedido.setEventos(EventoDAO.getInstance().selectByPedido(numero));
        pedido.setFalhasNaEntrega(FalhaNaEntregaDAO.getInstance().selectByPedido(numero));
        pedido.setEntrega(EntregaDAO.getInstance().selectByPedido(numero));
        return pedido;
    }
}
