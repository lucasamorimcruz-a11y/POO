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

        atualizar();
    }

    public void atualizar() {
        modeloTabela.setVeiculos(new ArrayList<>(sistema.getVeiculos()));
    }

    private void cadastrar() {
        try {
            Veiculo veiculo = new Veiculo(
                    Conversor.texto(campoPlaca, "Placa"),
                    Conversor.texto(campoMarca, "Marca"),
                    Conversor.texto(campoModelo, "Modelo"),
                    Conversor.inteiro(campoAno, "Ano de fabricação"),
                    Conversor.decimal(campoCapacidadeQuilos, "Capacidade (kg)"),
                    Conversor.decimal(campoCapacidadeM3, "Capacidade (m³)"),
                    Conversor.decimal(campoQuilometragem, "Quilometragem atual"),
                    Conversor.data(campoDataAquisicao, "Data de aquisição"),
                    Conversor.dataOpcional(campoProximaManutencao, "Próxima manutenção preventiva"),
                    (TipoDeVeiculo) comboTipo.getSelectedItem(),
                    StatusVeiculo.DISPONIVEL,
                    null);
            if (veiculo.getCapacidadeEmQuilos() <= 0 || veiculo.getQuilometragemAtual() < 0) {
                throw new IllegalArgumentException("Capacidade deve ser maior que zero e a quilometragem não pode ser negativa.");
            }
            sistema.cadastrarVeiculo(veiculo);
            Conversor.info(this, "Veículo cadastrado com sucesso.");
            limpar();
            atualizar();
        } catch (IllegalArgumentException | IllegalStateException e) {
            Conversor.erro(this, e.getMessage());
        }
    }

    private void listarDisponiveis() {
        TipoDeVeiculo tipo = (TipoDeVeiculo) comboFiltroTipo.getSelectedItem();
        modeloTabela.setVeiculos(sistema.veiculosDisponiveis(tipo));
    }

    private void listarManutencaoPendente() {
        modeloTabela.setVeiculos(sistema.veiculosComManutencaoPendente());
    }

    private void registrarManutencao() {
        try {
            int linha = tabela.getSelectedRow();
            if (linha < 0) {
                throw new IllegalArgumentException("Selecione um veículo na tabela.");
            }
            Veiculo veiculo = modeloTabela.getVeiculo(linha);
            double valor = Conversor.decimal(campoValorManutencao, "Valor total");
            LocalDate proxima = Conversor.dataOpcional(campoProximaData, "Próxima manutenção");
            sistema.registrarManutencao(veiculo, (TipoDeManutencao) comboTipoManutencao.getSelectedItem(),
                    Conversor.texto(campoServicos, "Descrição dos serviços"),
                    Conversor.opcional(campoPecas),
                    Conversor.texto(campoOficina, "Oficina"),
                    valor, proxima);
            Conversor.info(this, "Manutenção registrada com sucesso.");
            campoServicos.setText("");
            campoPecas.setText("");
            campoOficina.setText("");
            campoValorManutencao.setText("");
            campoProximaData.setText("");
            atualizar();
        } catch (IllegalArgumentException | IllegalStateException e) {
            Conversor.erro(this, e.getMessage());
        }
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
        }
    }
}
