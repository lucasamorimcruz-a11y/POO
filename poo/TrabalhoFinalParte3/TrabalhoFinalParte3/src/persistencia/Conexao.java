package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {

    private static Connection conexao;

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/transportadora";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgres";

    private Conexao() {
    }

    public static Connection getConexao() throws SQLException {
        try {
            if (conexao == null || conexao.isClosed()) {
                Class.forName(DRIVER);
                conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver do PostgreSQL não encontrado.", e);
        }
        return conexao;
    }

    public static void criarTabelas() throws SQLException {
        String[] comandos = {
                "create table if not exists endereco(" +
                        "id int, " +
                        "numero_local int, " +
                        "rua varchar(100), " +
                        "bairro varchar(100), " +
                        "cidade varchar(100), " +
                        "estado varchar(50), " +
                        "ponto_referencia varchar(200), " +
                        "primary key (id))",

                "create table if not exists tabela_preco(" +
                        "id int, " +
                        "valor_kg double precision, " +
                        "valor_km double precision, " +
                        "valor_minimo double precision, " +
                        "desconto_contratado double precision, " +
                        "fator_economico double precision, " +
                        "fator_expresso double precision, " +
                        "fator_agendado double precision, " +
                        "fator_local double precision, " +
                        "fator_estadual double precision, " +
                        "fator_interestadual double precision, " +
                        "fator_outro double precision, " +
                        "padrao boolean, " +
                        "primary key (id))",

                "create table if not exists cliente(" +
                        "documento varchar(20), " +
                        "tipo_pessoa varchar(10), " +
                        "nome varchar(100), " +
                        "telefone varchar(30), " +
                        "email varchar(100), " +
                        "razao_social varchar(100), " +
                        "inscricao_estadual varchar(30), " +
                        "id_endereco int, " +
                        "tipo_cliente varchar(20), " +
                        "id_tabela_preco int, " +
                        "limite_credito double precision, " +
                        "primary key (documento), " +
                        "foreign key (id_endereco) references endereco, " +
                        "foreign key (id_tabela_preco) references tabela_preco)",

                "create table if not exists motorista(" +
                        "cpf varchar(20), " +
                        "nome varchar(100), " +
                        "telefone varchar(30), " +
                        "email varchar(100), " +
                        "id_endereco int, " +
                        "cnh_numero varchar(30), " +
                        "cnh_categoria varchar(5), " +
                        "cnh_validade date, " +
                        "data_admissao date, " +
                        "vinculo varchar(20), " +
                        "situacao varchar(20), " +
                        "primary key (cpf), " +
                        "foreign key (id_endereco) references endereco)",

                "create table if not exists motorista_tipo_veiculo(" +
                        "cpf varchar(20), " +
                        "tipo varchar(30), " +
                        "primary key (cpf, tipo), " +
                        "foreign key (cpf) references motorista on delete cascade)",

                "create table if not exists veiculo(" +
                        "placa varchar(10), " +
                        "marca varchar(50), " +
                        "modelo varchar(50), " +
                        "ano_fabricacao int, " +
                        "capacidade_quilos double precision, " +
                        "capacidade_m3 double precision, " +
                        "quilometragem_atual double precision, " +
                        "data_aquisicao date, " +
                        "data_proxima_manutencao date, " +
                        "tipo varchar(30), " +
                        "status varchar(30), " +
                        "primary key (placa))",

                "create table if not exists manutencao(" +
                        "id int, " +
                        "placa varchar(10), " +
                        "data_manutencao date, " +
                        "tipo varchar(20), " +
                        "quilometragem double precision, " +
                        "descricao_servicos varchar(500), " +
                        "pecas_trocadas varchar(500), " +
                        "oficina varchar(100), " +
                        "valor_total double precision, " +
                        "proxima_manutencao date, " +
                        "primary key (id), " +
                        "foreign key (placa) references veiculo on delete cascade)",

                "create table if not exists pedido(" +
                        "numero int, " +
                        "data_solicitacao date, " +
                        "documento_cliente varchar(20), " +
                        "id_endereco_coleta int, " +
                        "contato_local varchar(100), " +
                        "data_agendada_coleta date, " +
                        "periodo varchar(10), " +
                        "descricao_carga varchar(500), " +
                        "peso_total double precision, " +
                        "volume_total double precision, " +
                        "valor_declarado double precision, " +
                        "id_endereco_entrega int, " +
                        "contato_destino varchar(100), " +
                        "prazo_entrega date, " +
                        "tipo_servico varchar(20), " +
                        "valor_frete double precision, " +
                        "forma_pagamento varchar(20), " +
                        "status varchar(20), " +
                        "primary key (numero), " +
                        "foreign key (documento_cliente) references cliente, " +
                        "foreign key (id_endereco_coleta) references endereco, " +
                        "foreign key (id_endereco_entrega) references endereco)",

                "create table if not exists item(" +
                        "id int, " +
                        "numero_pedido int, " +
                        "tipo_embalagem varchar(100), " +
                        "peso real, " +
                        "dimensao real, " +
                        "descricao varchar(500), " +
                        "primary key (id), " +
                        "foreign key (numero_pedido) references pedido on delete cascade)",

                "create table if not exists evento(" +
                        "id int, " +
                        "numero_pedido int, " +
                        "data date, " +
                        "hora time, " +
                        "tipo varchar(40), " +
                        "localizacao varchar(200), " +
                        "descricao varchar(500), " +
                        "foto varchar(500), " +
                        "primary key (id), " +
                        "foreign key (numero_pedido) references pedido on delete cascade)",

                "create table if not exists entrega(" +
                        "id int, " +
                        "numero_pedido int, " +
                        "nome_quem_recebeu varchar(100), " +
                        "documento_identificacao varchar(30), " +
                        "parentesco_funcao varchar(100), " +
                        "data date, " +
                        "hora time, " +
                        "assinatura_ou_foto varchar(500), " +
                        "primary key (id), " +
                        "foreign key (numero_pedido) references pedido on delete cascade)",

                "create table if not exists falha_entrega(" +
                        "id int, " +
                        "numero_pedido int, " +
                        "motivo varchar(500), " +
                        "nova_tentativa boolean, " +
                        "primary key (id), " +
                        "foreign key (numero_pedido) references pedido on delete cascade)",

                "create table if not exists conhecimento(" +
                        "numero int, " +
                        "numero_pedido int, " +
                        "data_emissao date, " +
                        "remetente varchar(100), " +
                        "destinatario varchar(100), " +
                        "discriminacao_carga varchar(500), " +
                        "valor_frete real, " +
                        "impostos real, " +
                        "seguro_contratado boolean, " +
                        "valor_seguro real, " +
                        "nota_fiscal varchar(50), " +
                        "primary key (numero), " +
                        "foreign key (numero_pedido) references pedido on delete cascade)",

                "create table if not exists rota(" +
                        "numero int, " +
                        "data date, " +
                        "placa_veiculo varchar(10), " +
                        "cpf_motorista varchar(20), " +
                        "quilometragem_inicial double precision, " +
                        "quilometragem_final double precision, " +
                        "status varchar(20), " +
                        "primary key (numero), " +
                        "foreign key (placa_veiculo) references veiculo, " +
                        "foreign key (cpf_motorista) references motorista)",

                "create table if not exists pedido_na_rota(" +
                        "numero_rota int, " +
                        "numero_pedido int, " +
                        "sequencia_entrega int, " +
                        "horario_previsto timestamp, " +
                        "horario_real timestamp, " +
                        "primary key (numero_rota, numero_pedido), " +
                        "foreign key (numero_rota) references rota on delete cascade, " +
                        "foreign key (numero_pedido) references pedido on delete cascade)",

                "create table if not exists despesa(" +
                        "id int, " +
                        "numero_rota int, " +
                        "data date, " +
                        "tipo varchar(20), " +
                        "valor double precision, " +
                        "comprovante varchar(500), " +
                        "primary key (id), " +
                        "foreign key (numero_rota) references rota on delete cascade)",

                "create sequence if not exists id_endereco",
                "create sequence if not exists id_tabela_preco",
                "create sequence if not exists id_manutencao",
                "create sequence if not exists numero_pedido",
                "create sequence if not exists id_item",
                "create sequence if not exists id_evento",
                "create sequence if not exists id_entrega",
                "create sequence if not exists id_falha",
                "create sequence if not exists numero_conhecimento",
                "create sequence if not exists numero_rota",
                "create sequence if not exists id_despesa"
        };

        Connection con = getConexao();
        try (Statement comando = con.createStatement()) {
            for (String sql : comandos) {
                comando.execute(sql);
            }
        }
    }

    public static int proximoValor(String sequencia) throws SQLException {
        Connection con = getConexao();
        try (Statement comando = con.createStatement();
             ResultSet resultado = comando.executeQuery("select nextval('" + sequencia + "')")) {
            resultado.next();
            return resultado.getInt(1);
        }
    }

    public static void iniciarTransacao() throws SQLException {
        getConexao().setAutoCommit(false);
    }

    public static void confirmar() throws SQLException {
        Connection con = getConexao();
        con.commit();
        con.setAutoCommit(true);
    }

    public static void desfazer() {
        try {
            Connection con = getConexao();
            con.rollback();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void fechar() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
