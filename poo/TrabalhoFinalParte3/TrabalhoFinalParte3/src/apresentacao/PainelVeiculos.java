package apresentacao;

import dados.Veiculo;
import dados.enums.StatusVeiculo;
import dados.enums.TipoDeManutencao;
import dados.enums.TipoDeVeiculo;
import negocio.Sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class PainelVeiculos extends JPanel implements ActionListener {

    private Sistema sistema;

    private JTextField campoPlaca = new JTextField(10);
    private JTextField campoMarca = new JTextField(10);
    private JTextField campoModelo = new JTextField(10);
    private JTextField campoAno = new JTextField(10);
    private JComboBox<TipoDeVeiculo> comboTipo = new JComboBox<>(TipoDeVeiculo.values());
    private JTextField campoCapacidadeQuilos = new JTextField(10);
    private JTextField campoCapacidadeM3 = new JTextField(10);
    private JTextField campoQuilometragem = new JTextField(10);
    private JTextField campoDataAquisicao = new JTextField(10);
    private JTextField campoProximaManutencao = new JTextField(10);
    private JButton botaoCadastrar = new JButton("Cadastrar veículo");
    private JButton botaoLimpar = new JButton("Limpar");

    private JComboBox<TipoDeVeiculo> comboFiltroTipo = new JComboBox<>(TipoDeVeiculo.values());
    private JButton botaoDisponiveis = new JButton("Disponíveis do tipo");
    private JButton botaoManutencaoPendente = new JButton("Manutenção pendente");
    private JButton botaoTodos = new JButton("Mostrar todos");

    private JComboBox<TipoDeManutencao> comboTipoManutencao = new JComboBox<>(TipoDeManutencao.values());
    private JTextField campoServicos = new JTextField(10);
    private JTextField campoPecas = new JTextField(10);
    private JTextField campoOficina = new JTextField(10);
    private JTextField campoValorManutencao = new JTextField(10);
    private JTextField campoProximaData = new JTextField(10);
    private JButton botaoRegistrarManutencao = new JButton("Registrar manutenção");
    private JButton botaoEnviarManutencao = new JButton("Enviar para manutenção");

    private TabelaVeiculos modeloTabela = new TabelaVeiculos();
    private JTable tabela = new JTable(modeloTabela);

    public PainelVeiculos(Sistema sistema) {
        super(new BorderLayout(5, 5));
        this.sistema = sistema;
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JPanel cadastro = new JPanel(new GridLayout(0, 2, 5, 5));
        cadastro.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Cadastro de veículo"));
        Conversor.adicionar(cadastro, "Placa:", campoPlaca);
        Conversor.adicionar(cadastro, "Marca:", campoMarca);
        Conversor.adicionar(cadastro, "Modelo:", campoModelo);
        Conversor.adicionar(cadastro, "Ano de fabricação:", campoAno);
        Conversor.adicionar(cadastro, "Tipo:", comboTipo);
        Conversor.adicionar(cadastro, "Capacidade (kg):", campoCapacidadeQuilos);
        Conversor.adicionar(cadastro, "Capacidade (m³):", campoCapacidadeM3);
        Conversor.adicionar(cadastro, "Quilometragem atual:", campoQuilometragem);
        Conversor.adicionar(cadastro, "Data de aquisição:", campoDataAquisicao);
        Conversor.adicionar(cadastro, "Próx. manutenção preventiva:", campoProximaManutencao);
        Conversor.adicionar(cadastro, "", botaoCadastrar);
        Conversor.adicionar(cadastro, "", botaoLimpar);

        JPanel manutencao = new JPanel(new GridLayout(0, 2, 5, 5));
        manutencao.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Manutenção (selecione um veículo na tabela)"));
        Conversor.adicionar(manutencao, "Tipo:", comboTipoManutencao);
        Conversor.adicionar(manutencao, "Descrição dos serviços:", campoServicos);
        Conversor.adicionar(manutencao, "Peças trocadas:", campoPecas);
        Conversor.adicionar(manutencao, "Oficina:", campoOficina);
        Conversor.adicionar(manutencao, "Valor total (R$):", campoValorManutencao);
        Conversor.adicionar(manutencao, "Próxima manutenção:", campoProximaData);
        Conversor.adicionar(manutencao, "", botaoRegistrarManutencao);
        Conversor.adicionar(manutencao, "", botaoEnviarManutencao);
        botaoRegistrarManutencao.setToolTipText("Registra o serviço realizado; se o veículo estava em manutenção, ele volta a ficar disponível.");
        botaoEnviarManutencao.setToolTipText("Muda o status do veículo selecionado para \"em manutenção\" (não pode entrar em rotas).");

        JPanel formulario = new JPanel(new GridLayout(1, 2, 5, 5));
        formulario.add(Conversor.topo(cadastro));
        formulario.add(Conversor.topo(manutencao));

        JPanel filtro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filtro.add(new JLabel("Consultas:"));
        filtro.add(comboFiltroTipo);
        filtro.add(botaoDisponiveis);
        filtro.add(botaoManutencaoPendente);
        filtro.add(botaoTodos);
        Conversor.configurar(tabela);
        JScrollPane rolagem = new JScrollPane(tabela);
        rolagem.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Frota"));

        JPanel centro = new JPanel(new BorderLayout(5, 5));
        centro.add(filtro, BorderLayout.NORTH);
        centro.add(rolagem, BorderLayout.CENTER);

        add(formulario, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);

        botaoCadastrar.addActionListener(this);
        botaoLimpar.addActionListener(this);
        botaoDisponiveis.addActionListener(this);
        botaoManutencaoPendente.addActionListener(this);
        botaoTodos.addActionListener(this);
        botaoRegistrarManutencao.addActionListener(this);
        botaoEnviarManutencao.addActionListener(this);

        atualizar();
    }

    public void atualizar() {
        modeloTabela.setVeiculos(new ArrayList<>(sistema.getVeiculos()));
    }

    private void cadastrar() {
        Conversor.limparErro();
        String placa = Conversor.texto(campoPlaca, "Placa");
        String marca = Conversor.texto(campoMarca, "Marca");
        String modelo = Conversor.texto(campoModelo, "Modelo");
        int ano = Conversor.inteiro(campoAno, "Ano de fabricação");
        double capacidadeQuilos = Conversor.decimal(campoCapacidadeQuilos, "Capacidade (kg)");
        double capacidadeM3 = Conversor.decimal(campoCapacidadeM3, "Capacidade (m³)");
        double quilometragem = Conversor.decimal(campoQuilometragem, "Quilometragem atual");
        LocalDate aquisicao = Conversor.data(campoDataAquisicao, "Data de aquisição");
        LocalDate proximaManutencao = Conversor.dataOpcional(campoProximaManutencao, "Próxima manutenção preventiva");

        if (Conversor.temErro()) {
            Conversor.erro(this, Conversor.getErro());
            return;
        }
        if (capacidadeQuilos <= 0 || quilometragem < 0) {
            Conversor.erro(this, "Capacidade deve ser maior que zero e a quilometragem não pode ser negativa.");
            return;
        }

        Veiculo veiculo = new Veiculo(placa, marca, modelo, ano, capacidadeQuilos, capacidadeM3, quilometragem,
                aquisicao, proximaManutencao, (TipoDeVeiculo) comboTipo.getSelectedItem(),
                StatusVeiculo.DISPONIVEL, null);
        if (!sistema.cadastrarVeiculo(veiculo)) {
            Conversor.erro(this, sistema.getErro());
            return;
        }
        Conversor.info(this, "Veículo cadastrado com sucesso.");
        limpar();
        atualizar();
    }

    private void listarDisponiveis() {
        TipoDeVeiculo tipo = (TipoDeVeiculo) comboFiltroTipo.getSelectedItem();
        modeloTabela.setVeiculos(sistema.veiculosDisponiveis(tipo));
    }

    private void listarManutencaoPendente() {
        modeloTabela.setVeiculos(sistema.veiculosComManutencaoPendente());
    }

    private void registrarManutencao() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            Conversor.erro(this, "Selecione um veículo na tabela.");
            return;
        }
        Veiculo veiculo = modeloTabela.getVeiculo(linha);

        Conversor.limparErro();
        double valor = Conversor.decimal(campoValorManutencao, "Valor total");
        LocalDate proxima = Conversor.dataOpcional(campoProximaData, "Próxima manutenção");
        String servicos = Conversor.texto(campoServicos, "Descrição dos serviços");
        String pecas = Conversor.opcional(campoPecas);
        String oficina = Conversor.texto(campoOficina, "Oficina");
        if (Conversor.temErro()) {
            Conversor.erro(this, Conversor.getErro());
            return;
        }

        if (!sistema.registrarManutencao(veiculo, (TipoDeManutencao) comboTipoManutencao.getSelectedItem(),
                servicos, pecas, oficina, valor, proxima)) {
            Conversor.erro(this, sistema.getErro());
            return;
        }
        Conversor.info(this, "Manutenção registrada com sucesso.");
        campoServicos.setText("");
        campoPecas.setText("");
        campoOficina.setText("");
        campoValorManutencao.setText("");
        campoProximaData.setText("");
        atualizar();
    }

    private void enviarParaManutencao() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            Conversor.erro(this, "Selecione um veículo na tabela.");
            return;
        }
        if (!sistema.enviarParaManutencao(modeloTabela.getVeiculo(linha))) {
            Conversor.erro(this, sistema.getErro());
            return;
        }
        atualizar();
    }

    private void limpar() {
        campoPlaca.setText("");
        campoMarca.setText("");
        campoModelo.setText("");
        campoAno.setText("");
        campoCapacidadeQuilos.setText("");
        campoCapacidadeM3.setText("");
        campoQuilometragem.setText("");
        campoDataAquisicao.setText("");
        campoProximaManutencao.setText("");
        comboTipo.setSelectedIndex(0);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        Object fonte = evento.getSource();
        if (fonte == botaoCadastrar) {
            cadastrar();
        } else if (fonte == botaoLimpar) {
            limpar();
        } else if (fonte == botaoDisponiveis) {
            listarDisponiveis();
        } else if (fonte == botaoManutencaoPendente) {
            listarManutencaoPendente();
        } else if (fonte == botaoTodos) {
            atualizar();
        } else if (fonte == botaoRegistrarManutencao) {
            registrarManutencao();
        } else if (fonte == botaoEnviarManutencao) {
            enviarParaManutencao();
        }
    }
}
