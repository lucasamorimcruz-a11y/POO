package apresentacao;

import dados.Cnh;
import dados.Endereco;
import dados.Motorista;
import dados.enums.Situacao;
import dados.enums.TipoDeVeiculo;
import dados.enums.TipoDeVinculo;
import negocio.Sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class PainelMotoristas extends JPanel implements ActionListener {

    private Sistema sistema;

    private JTextField campoNome = new JTextField(10);
    private JTextField campoCpf = new JTextField(10);
    private JTextField campoTelefone = new JTextField(10);
    private JTextField campoEmail = new JTextField(10);
    private PainelEndereco painelEndereco = new PainelEndereco("Endereço");
    private JTextField campoNumeroCnh = new JTextField(10);
    private JTextField campoCategoriaCnh = new JTextField(10);
    private JTextField campoValidadeCnh = new JTextField(10);
    private JTextField campoDataAdmissao = new JTextField(10);
    private JComboBox<TipoDeVinculo> comboVinculo = new JComboBox<>(TipoDeVinculo.values());
    private JComboBox<Situacao> comboSituacao = new JComboBox<>(Situacao.values());
    private ArrayList<JCheckBox> checksTiposDeVeiculo = new ArrayList<>();
    private JButton botaoCadastrar = new JButton("Cadastrar");
    private JButton botaoLimpar = new JButton("Limpar");
    private JComboBox<TipoDeVeiculo> comboFiltroTipo = new JComboBox<>(TipoDeVeiculo.values());
    private JButton botaoFiltrarHabilitados = new JButton("Filtrar habilitados");
    private JButton botaoTodos = new JButton("Mostrar todos");
    private TabelaMotoristas modeloTabela = new TabelaMotoristas();
    private JTable tabela = new JTable(modeloTabela);

    public PainelMotoristas(Sistema sistema) {
        super(new BorderLayout(5, 5));
        this.sistema = sistema;
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JPanel dados = new JPanel(new GridLayout(0, 2, 5, 5));
        dados.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Dados do motorista"));
        Conversor.adicionar(dados, "Nome:", campoNome);
        Conversor.adicionar(dados, "CPF:", campoCpf);
        Conversor.adicionar(dados, "Telefone:", campoTelefone);
        Conversor.adicionar(dados, "E-mail:", campoEmail);
        Conversor.adicionar(dados, "Data de admissão:", campoDataAdmissao);
        Conversor.adicionar(dados, "Vínculo:", comboVinculo);
        Conversor.adicionar(dados, "Situação:", comboSituacao);

        JPanel cnh = new JPanel(new GridLayout(0, 2, 5, 5));
        cnh.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "CNH"));
        Conversor.adicionar(cnh, "Número:", campoNumeroCnh);
        Conversor.adicionar(cnh, "Categoria:", campoCategoriaCnh);
        Conversor.adicionar(cnh, "Validade:", campoValidadeCnh);

        JPanel tipos = new JPanel(new GridLayout(0, 1));
        tipos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Habilitado para dirigir"));
        for (TipoDeVeiculo tipo : TipoDeVeiculo.values()) {
            JCheckBox check = new JCheckBox(tipo.toString());
            checksTiposDeVeiculo.add(check);
            tipos.add(check);
        }

        JPanel cnhETipos = new JPanel(new BorderLayout(5, 5));
        cnhETipos.add(cnh, BorderLayout.NORTH);
        cnhETipos.add(tipos, BorderLayout.CENTER);

        JPanel formulario = new JPanel(new GridLayout(1, 3, 5, 5));
        formulario.add(Conversor.topo(dados));
        formulario.add(painelEndereco);
        formulario.add(cnhETipos);

        JPanel botoes = new JPanel();
        botoes.add(botaoCadastrar);
        botoes.add(botaoLimpar);

        JPanel norte = new JPanel(new BorderLayout(5, 5));
        norte.add(formulario, BorderLayout.CENTER);
        norte.add(botoes, BorderLayout.SOUTH);

        JPanel filtro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filtro.add(new JLabel("Motoristas ativos habilitados para:"));
        filtro.add(comboFiltroTipo);
        filtro.add(botaoFiltrarHabilitados);
        filtro.add(botaoTodos);
        Conversor.configurar(tabela);
        JScrollPane rolagem = new JScrollPane(tabela);
        rolagem.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Motoristas cadastrados"));

        JPanel centro = new JPanel(new BorderLayout(5, 5));
        centro.add(filtro, BorderLayout.NORTH);
        centro.add(rolagem, BorderLayout.CENTER);

        add(norte, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);

        botaoCadastrar.addActionListener(this);
        botaoLimpar.addActionListener(this);
        botaoFiltrarHabilitados.addActionListener(this);
        botaoTodos.addActionListener(this);

        atualizar();
    }

    public void atualizar() {
        modeloTabela.setMotoristas(new ArrayList<>(sistema.getMotoristas()));
    }

    private void cadastrar() {
        Conversor.limparErro();
        String nome = Conversor.texto(campoNome, "Nome");
        String cpf = Conversor.texto(campoCpf, "CPF");
        String telefone = Conversor.texto(campoTelefone, "Telefone");
        String email = Conversor.opcional(campoEmail);
        Endereco endereco = painelEndereco.getEndereco();
        String numeroCnh = Conversor.texto(campoNumeroCnh, "Número da CNH");
        String categoriaCnh = Conversor.texto(campoCategoriaCnh, "Categoria da CNH");
        LocalDate validadeCnh = Conversor.data(campoValidadeCnh, "Validade da CNH");
        LocalDate admissao = Conversor.data(campoDataAdmissao, "Data de admissão");

        ArrayList<TipoDeVeiculo> habilitados = new ArrayList<>();
        for (int i = 0; i < checksTiposDeVeiculo.size(); i++) {
            if (checksTiposDeVeiculo.get(i).isSelected()) {
                habilitados.add(TipoDeVeiculo.values()[i]);
            }
        }

        if (Conversor.temErro()) {
            Conversor.erro(this, Conversor.getErro());
            return;
        }
        if (habilitados.isEmpty()) {
            Conversor.erro(this, "Marque ao menos um tipo de veículo para o motorista.");
            return;
        }

        Cnh cnh = new Cnh(numeroCnh, categoriaCnh, validadeCnh);
        Motorista motorista = new Motorista(nome, endereco, telefone, email, cpf, cnh, admissao,
                (TipoDeVinculo) comboVinculo.getSelectedItem(),
                (Situacao) comboSituacao.getSelectedItem(), habilitados);
        if (!sistema.cadastrarMotorista(motorista)) {
            Conversor.erro(this, sistema.getErro());
            return;
        }
        Conversor.info(this, "Motorista cadastrado com sucesso.");
        limpar();
        atualizar();
    }

    private void filtrarHabilitados() {
        TipoDeVeiculo tipo = (TipoDeVeiculo) comboFiltroTipo.getSelectedItem();
        modeloTabela.setMotoristas(sistema.motoristasHabilitados(tipo));
    }

    private void limpar() {
        campoNome.setText("");
        campoCpf.setText("");
        campoTelefone.setText("");
        campoEmail.setText("");
        campoNumeroCnh.setText("");
        campoCategoriaCnh.setText("");
        campoValidadeCnh.setText("");
        campoDataAdmissao.setText("");
        painelEndereco.limpar();
        comboVinculo.setSelectedIndex(0);
        comboSituacao.setSelectedIndex(0);
        for (JCheckBox check : checksTiposDeVeiculo) {
            check.setSelected(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        Object fonte = evento.getSource();
        if (fonte == botaoCadastrar) {
            cadastrar();
        } else if (fonte == botaoLimpar) {
            limpar();
        } else if (fonte == botaoFiltrarHabilitados) {
            filtrarHabilitados();
        } else if (fonte == botaoTodos) {
            atualizar();
        }
    }
}
