package apresentacao;

import dados.*;
import dados.enums.TipoCliente;
import negocio.Sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PainelClientes extends JPanel implements ActionListener {

    private Sistema sistema;

    private JComboBox<String> comboTipoPessoa = new JComboBox<>(new String[]{"Pessoa física", "Pessoa jurídica"});
    private JTextField campoNome = new JTextField(10);
    private JTextField campoDocumento = new JTextField(10);
    private JTextField campoRazaoSocial = new JTextField(10);
    private JTextField campoInscricaoEstadual = new JTextField(10);
    private JTextField campoTelefone = new JTextField(10);
    private JTextField campoEmail = new JTextField(10);
    private PainelEndereco painelEndereco = new PainelEndereco("Endereço");
    private JComboBox<TipoCliente> comboTipoCliente = new JComboBox<>(TipoCliente.values());
    private JTextField campoLimiteCredito = new JTextField(10);
    private PainelTabelaPreco painelTabelaPreco = new PainelTabelaPreco();
    private JButton botaoCadastrar = new JButton("Cadastrar");
    private JButton botaoLimpar = new JButton("Limpar");
    private JTextField campoBusca = new JTextField(15);
    private JButton botaoBuscar = new JButton("Buscar");
    private TabelaClientes modeloTabela = new TabelaClientes();
    private JTable tabela = new JTable(modeloTabela);

    public PainelClientes(Sistema sistema) {
        super(new BorderLayout(5, 5));
        this.sistema = sistema;
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JPanel dados = new JPanel(new GridLayout(0, 2, 5, 5));
        dados.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Dados do cliente"));
        Conversor.adicionar(dados, "Tipo de pessoa:", comboTipoPessoa);
        Conversor.adicionar(dados, "Nome / Nome fantasia:", campoNome);
        Conversor.adicionar(dados, "CPF / CNPJ:", campoDocumento);
        Conversor.adicionar(dados, "Razão social:", campoRazaoSocial);
        Conversor.adicionar(dados, "Inscrição estadual:", campoInscricaoEstadual);
        Conversor.adicionar(dados, "Telefone:", campoTelefone);
        Conversor.adicionar(dados, "E-mail:", campoEmail);
        Conversor.adicionar(dados, "Tipo de cliente:", comboTipoCliente);
        Conversor.adicionar(dados, "Limite de crédito (R$):", campoLimiteCredito);

        JPanel formulario = new JPanel(new GridLayout(1, 3, 5, 5));
        formulario.add(Conversor.topo(dados));
        formulario.add(painelEndereco);
        formulario.add(painelTabelaPreco);

        JPanel botoes = new JPanel();
        botoes.add(botaoCadastrar);
        botoes.add(botaoLimpar);

        JPanel norte = new JPanel(new BorderLayout(5, 5));
        norte.add(formulario, BorderLayout.CENTER);
        norte.add(botoes, BorderLayout.SOUTH);

        JPanel busca = new JPanel(new BorderLayout(5, 5));
        busca.add(new JLabel("Buscar por nome ou CPF/CNPJ (vazio = todos):"), BorderLayout.WEST);
        busca.add(campoBusca, BorderLayout.CENTER);
        busca.add(botaoBuscar, BorderLayout.EAST);
        Conversor.configurar(tabela);
        JScrollPane rolagem = new JScrollPane(tabela);
        rolagem.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Clientes cadastrados"));

        JPanel centro = new JPanel(new BorderLayout(5, 5));
        centro.add(busca, BorderLayout.NORTH);
        centro.add(rolagem, BorderLayout.CENTER);

        add(norte, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);

        comboTipoPessoa.addActionListener(this);
        comboTipoCliente.addActionListener(this);
        botaoCadastrar.addActionListener(this);
        botaoLimpar.addActionListener(this);
        botaoBuscar.addActionListener(this);
        campoBusca.addActionListener(this);

        atualizarCampos();
        atualizar();
    }

    public void atualizar() {
        modeloTabela.setClientes(sistema.pesquisarClientes(campoBusca.getText()));
    }

    private void atualizarCampos() {
        boolean juridica = comboTipoPessoa.getSelectedIndex() == 1;
        campoRazaoSocial.setEnabled(juridica);
        campoInscricaoEstadual.setEnabled(juridica);

        boolean contratado = comboTipoCliente.getSelectedItem() == TipoCliente.CONTRATADO;
        campoLimiteCredito.setEnabled(contratado);
        painelTabelaPreco.setEditavel(contratado);
    }

    private void cadastrar() {
        try {
            String nome = Conversor.texto(campoNome, "Nome");
            String documento = Conversor.texto(campoDocumento, "CPF/CNPJ");
            Endereco endereco = painelEndereco.getEndereco();
            String telefone = Conversor.texto(campoTelefone, "Telefone");
            String email = Conversor.opcional(campoEmail);

            Pessoa pessoa;
            if (comboTipoPessoa.getSelectedIndex() == 1) {
                String razaoSocial = Conversor.texto(campoRazaoSocial, "Razão social");
                pessoa = new PessoaJuridica(nome, endereco, telefone, email, documento, razaoSocial,
                        Conversor.opcional(campoInscricaoEstadual));
            } else {
                pessoa = new PessoaFisica(nome, endereco, telefone, email, documento);
            }

            TipoCliente tipo = (TipoCliente) comboTipoCliente.getSelectedItem();
            TabelaPreco tabelaPreco = null;
            double limiteDeCredito = 0;
            if (tipo == TipoCliente.CONTRATADO) {
                limiteDeCredito = Conversor.decimal(campoLimiteCredito, "Limite de crédito");
                if (limiteDeCredito < 0) {
                    throw new IllegalArgumentException("O limite de crédito não pode ser negativo.");
                }
                tabelaPreco = painelTabelaPreco.getTabelaPreco();
            }

            sistema.cadastrarCliente(new Cliente(pessoa, tipo, tabelaPreco, limiteDeCredito));
            Conversor.info(this, "Cliente cadastrado com sucesso.");
            limpar();
            campoBusca.setText("");   // senão o cliente novo ficaria escondido por um filtro antigo
            atualizar();
        } catch (IllegalArgumentException | IllegalStateException e) {
            Conversor.erro(this, e.getMessage());
        }
    }

    private void buscar() {
        String termo = campoBusca.getText().trim();
        atualizar();
        if (!termo.isEmpty() && modeloTabela.getRowCount() == 0) {
            Conversor.info(this, "Nenhum cliente encontrado para \"" + termo + "\".");
        }
    }

    private void limpar() {
        campoNome.setText("");
        campoDocumento.setText("");
        campoRazaoSocial.setText("");
        campoInscricaoEstadual.setText("");
        campoTelefone.setText("");
        campoEmail.setText("");
        campoLimiteCredito.setText("");
        painelEndereco.limpar();
        painelTabelaPreco.limpar();
        comboTipoPessoa.setSelectedIndex(0);
        comboTipoCliente.setSelectedIndex(0);
        atualizarCampos();
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        Object fonte = evento.getSource();
        if (fonte == botaoCadastrar) {
            cadastrar();
        } else if (fonte == botaoLimpar) {
            limpar();
        } else if (fonte == botaoBuscar || fonte == campoBusca) {
            buscar();
        } else if (fonte == comboTipoCliente) {
            // Cliente contratado já começa com a tabela padrão preenchida (evita digitar 11 campos do zero).
            if (comboTipoCliente.getSelectedItem() == TipoCliente.CONTRATADO) {
                painelTabelaPreco.preencher(sistema.getTabelaPadrao());
            }
            atualizarCampos();
        } else if (fonte == comboTipoPessoa) {
            atualizarCampos();
        }
    }
}
