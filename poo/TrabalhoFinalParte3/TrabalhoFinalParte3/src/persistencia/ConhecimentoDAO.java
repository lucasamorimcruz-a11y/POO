package persistencia;

import dados.ConhecimentoTransporte;
import dados.Pedido;
import exceptions.InsertException;
import exceptions.SelectException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ConhecimentoDAO {

    private static ConhecimentoDAO instance;

    private ConhecimentoDAO() {
    }

    public static ConhecimentoDAO getInstance() {
        if (instance == null) {
            instance = new ConhecimentoDAO();
        }
        return instance;
    }

    public void insert(ConhecimentoTransporte conhecimento) throws InsertException {
        try {
            Connection con = Conexao.getConexao();
            String sql = "insert into conhecimento(numero, numero_pedido, data_emissao, remetente, destinatario, discriminacao_carga, valor_frete, impostos, seguro_contratado, valor_seguro, nota_fiscal) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement comando = con.prepareStatement(sql)) {
                comando.setInt(1, conhecimento.getNumero());
                comando.setInt(2, conhecimento.getPedido().getNumero());
                comando.setObject(3, conhecimento.getDataEmissao());
                comando.setString(4, conhecimento.getRemetente());
                comando.setString(5, conhecimento.getDestinatario());
                comando.setString(6, conhecimento.getDescriminacaoCarga());
                comando.setFloat(7, conhecimento.getValorFrete());
                comando.setFloat(8, conhecimento.getImpostos());
                comando.setBoolean(9, conhecimento.isSeguroContratado());
                comando.setFloat(10, conhecimento.getValorSeguro());
                comando.setString(11, conhecimento.getNotaFiscal());
                comando.executeUpdate();
            }
        } catch (SQLException e) {
            throw new InsertException("Erro ao inserir o conhecimento de transporte.", e);
        }
    }

    public ArrayList<ConhecimentoTransporte> selectAll(ArrayList<Pedido> pedidos) throws SelectException {
        ArrayList<ConhecimentoTransporte> conhecimentos = new ArrayList<>();
        try {
            Connection con = Conexao.getConexao();
            try (PreparedStatement comando = con.prepareStatement("select * from conhecimento order by numero");
                 ResultSet resultado = comando.executeQuery()) {
                while (resultado.next()) {
                    int numeroPedido = resultado.getInt("numero_pedido");
                    Pedido pedido = null;
                    for (Pedido candidato : pedidos) {
                        if (candidato.getNumero() == numeroPedido) {
                            pedido = candidato;
                        }
                    }
                    ConhecimentoTransporte conhecimento = new ConhecimentoTransporte(resultado.getInt("numero"),
                            resultado.getObject("data_emissao", LocalDate.class),
                            resultado.getString("remetente"), resultado.getString("destinatario"),
                            resultado.getString("discriminacao_carga"), resultado.getFloat("valor_frete"),
                            resultado.getFloat("impostos"), resultado.getBoolean("seguro_contratado"),
                            resultado.getFloat("valor_seguro"), resultado.getString("nota_fiscal"), pedido);
                    if (pedido != null) {
                        pedido.getConhecimentos().add(conhecimento);
                    }
                    conhecimentos.add(conhecimento);
                }
            }
        } catch (SQLException e) {
            throw new SelectException("Erro ao buscar os conhecimentos de transporte.", e);
        }
        return conhecimentos;
    }

    public int proximoNumero() throws SelectException {
        try {
            return Conexao.proximoValor("numero_conhecimento");
        } catch (SQLException e) {
            throw new SelectException("Erro ao obter o número do conhecimento.", e);
        }
    }
}
