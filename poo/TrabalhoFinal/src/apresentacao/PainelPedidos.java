package apresentacao;

import dados.Cliente;
import dados.Endereco;
import dados.Item;
import dados.Pedido;
import dados.enums.*;
import negocio.Sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class PainelPedidos extends JPanel implements ActionListener {

    private Sistema sistema;

    private JComboBox<Cliente> comboCliente = new JComboBox<>();
    private PainelEndereco painelColeta = new PainelEndereco("Endereço de coleta");
    private PainelEndereco painelEntrega = new PainelEndereco("Endereço de entrega");
    private JTextField campoContatoColeta = new JTextField(10);
    private JTextField campoDataColeta = new JTextField(10);
    private JComboBox<Periodo> comboPeriodo = new JComboBox<>(Periodo.values());
    private JTextField campoDescricao = new JTextField(10);
    private JTextField campoValorDeclarado = new JTextField(10);
    private JTextField campoContatoDestino = new JTextField(10);
    private JComboBox<TipoDeServico> comboServico = new JComboBox<>(TipoDeServico.values());
    private JComboBox<FormaDePagamento> comboPagamento = new JComboBox<>(FormaDePagamento.values());
    private JButton botaoCriar = new JButton("Criar pedido");

    private JTextField campoEmbalagem = new JTextField(10);
    private JTextField campoPesoItem = new JTextField(10);
    private JTextField campoDimensoes = new JTextField(10);
    private JTextField campoDescricaoItem = new JTextField(10);
    private JButton botaoAdicionarItem = new JButton("Adicionar item");

    private JTextField campoDistancia = new JTextField(10);
    private JComboBox<Regiao> comboRegiao = new JComboBox<>(Regiao.values());
    private JButton botaoCalcularFrete = new JButton("Calcular frete");
    private JLabel rotuloFrete = new JLabel("Frete: -");
    private JButton botaoCancelar = new JButton("Cancelar pedido selecionado");

    private TabelaPedidos modeloTabela = new TabelaPedidos();
    private JTable tabela = new JTable(modeloTabela);
    private TabelaItens modeloItens = new TabelaItens();
    private JTable tabelaItens = new JTable(modeloItens);

    public PainelPedidos(Sistema sistema) {
        super(new BorderLayout(5, 5));
        this.sistema = sistema;
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JPanel dadosPedido = new JPanel(new GridLayout(0, 2, 5, 5));
        dadosPedido.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Novo pedido de coleta"));
        Conversor.adicionar(dadosPedido, "Cliente:", comboCliente);
        Conversor.adicionar(dadosPedido, "Contato no local:", campoContatoColeta);
        Conversor.adicionar(dadosPedido, "Data da coleta:", campoDataColeta);
        Conversor.adicionar(dadosPedido, "Período:", comboPeriodo);
        Conversor.adicionar(dadosPedido, "Descrição da carga:", campoDescricao);
        Conversor.adicionar(dadosPedido, "Valor declarado (R$):", campoValorDeclarado);
        Conversor.adicionar(dadosPedido, "Contato no destino:", campoContatoDestino);
        Conversor.adicionar(dadosPedido, "Tipo de serviço:", comboServico);
        Conversor.adicionar(dadosPedido, "Forma de pagamento:", comboPagamento);

        JPanel formulario = new JPanel(new GridLayout(1, 3, 5, 5));
        formulario.add(Conversor.topo(dadosPedido));
        formulario.add(painelColeta);
        formulario.add(painelEntrega);

        JPanel botaoNovo = new JPanel();
        botaoNovo.add(botaoCriar);

        JPanel norte = new JPanel(new BorderLayout(5, 5));
        norte.add(formulario, BorderLayout.CENTER);
        norte.add(botaoNovo, BorderLayout.SOUTH);
        Conversor.configurar(tabela);
        JScrollPane rolagemPedidos = new JScrollPane(tabela);
        rolagemPedidos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Pedidos"));
        Conversor.configurar(tabelaItens);
        JScrollPane rolagemItens = new JScrollPane(tabelaItens);
        rolagemItens.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Itens do pedido selecionado"));

        JPanel centro = new JPanel(new GridLayout(1, 2, 5, 5));
        centro.add(rolagemPedidos);
        centro.add(rolagemItens);

        JPanel item = new JPanel(new GridLayout(0, 2, 5, 5));
        item.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Adicionar item ao pedido selecionado"));
        Conversor.adicionar(item, "Tipo de embalagem:", campoEmbalagem);
        Conversor.adicionar(item, "Peso (kg):", campoPesoItem);
        Conversor.adicionar(item, "Dimensões (m³):", campoDimensoes);
        Conversor.adicionar(item, "Descrição:", campoDescricaoItem);
        Conversor.adicionar(item, "", botaoAdicionarItem);

        JPanel frete = new JPanel(new GridLayout(0, 2, 5, 5));
        frete.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Frete do pedido selecionado"));
        Conversor.adicionar(frete, "Distância (km):", campoDistancia);
        Conversor.adicionar(frete, "Região de destino:", comboRegiao);
        Conversor.adicionar(frete, "", botaoCalcularFrete);
        Conversor.adicionar(frete, "", rotuloFrete);

        JPanel cancelamento = new JPanel(new GridLayout(0, 1, 5, 5));
        cancelamento.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Cancelamento"));
        cancelamento.add(new JLabel("Só pedidos pendentes podem ser cancelados."));
        cancelamento.add(botaoCancelar);

        JPanel sul = new JPanel(new GridLayout(1, 3, 5, 5));
        sul.add(Conversor.topo(item));
        sul.add(Conversor.topo(frete));
        sul.add(Conversor.topo(cancelamento));

        add(norte, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);
        add(sul, BorderLayout.SOUTH);

        botaoCriar.addActionListener(this);
        botaoAdicionarItem.addActionListener(this);
        botaoCalcularFrete.addActionListener(this);
        botaoCancelar.addActionListener(this);
        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                atualizarItens();
            }
        });

        atualizar();
    }

    public void atualizar() {
        Cliente clienteSelecionado = (Cliente) comboCliente.getSelectedItem();
        comboCliente.removeAllItems();
        for (Cliente cliente : sistema.getClientes()) {
            comboCliente.addItem(cliente);
        }
        if (clienteSelecionado != null) {
            comboCliente.setSelectedItem(clienteSelecionado);
        }

        int linha = tabela.getSelectedRow();
        Pedido pedidoSelecionado = linha >= 0 ? modeloTabela.getPedido(linha) : null;
        modeloTabela.setPedidos(new ArrayList<>(sistema.getPedidos()));
        selecionar(pedidoSelecionado);
        atualizarItens();
    }

    private void selecionar(Pedido pedido) {
        int linha = pedido == null ? -1 : modeloTabela.getLinha(pedido);
        if (linha >= 0) {
            tabela.setRowSelectionInterval(linha, linha);
        }
    }

    private void atualizarItens() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            modeloItens.setItens(new ArrayList<>());
            rotuloFrete.setText("Frete: -");
        } else {
            Pedido pedido = modeloTabela.getPedido(linha);
            modeloItens.setItens(new ArrayList<>(pedido.getItens()));
            rotuloFrete.setText("Frete: " + Conversor.moeda(pedido.getValorFrete()));
        }
    }

    private Pedido pedidoSelecionado() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            throw new IllegalArgumentException("Selecione um pedido na tabela.");
        }
        return modeloTabela.getPedido(linha);
    }

    private void criarPedido() {
        try {
            Cliente cliente = (Cliente) comboCliente.getSelectedItem();
            if (cliente == null) {
                throw new IllegalArgumentException("Cadastre e selecione um cliente.");
            }
            Endereco coleta = painelColeta.getEndereco();
            Endereco entrega = painelEntrega.getEndereco();
            String contatoColeta = Conversor.texto(campoContatoColeta, "Contato no local");
            LocalDate dataColeta = Conversor.data(campoDataColeta, "Data da coleta");
            String descricao = Conversor.texto(campoDescricao, "Descrição da carga");
            double valorDeclarado = Conversor.decimal(campoValorDeclarado, "Valor declarado");
            String contatoDestino = Conversor.texto(campoContatoDestino, "Contato no destino");

            Pedido pedido = sistema.criarPedido(cliente, coleta, contatoColeta, dataColeta,
                    (Periodo) comboPeriodo.getSelectedItem(), descricao, valorDeclarado, entrega, contatoDestino,
                    (TipoDeServico) comboServico.getSelectedItem(),
                    (FormaDePagamento) comboPagamento.getSelectedItem());
            Conversor.info(this, "Pedido nº " + pedido.getNumero() + " criado. Agora adicione os itens e calcule o frete.");
            limpar();
            atualizar();
            selecionar(pedido);
        } catch (IllegalArgumentException | IllegalStateException e) {
            Conversor.erro(this, e.getMessage());
        }
    }

    private void adicionarItem() {
        try {
            Pedido pedido = pedidoSelecionado();
            Item item = new Item(Conversor.texto(campoEmbalagem, "Tipo de embalagem"),
                    (float) Conversor.decimal(campoPesoItem, "Peso"),
                    (float) Conversor.decimal(campoDimensoes, "Dimensões"),
                    Conversor.opcional(campoDescricaoItem));
            sistema.adicionarItem(pedido, item);
            campoEmbalagem.setText("");
            campoPesoItem.setText("");
            campoDimensoes.setText("");
            campoDescricaoItem.setText("");
            atualizar();
            selecionar(pedido);
            atualizarItens();
        } catch (IllegalArgumentException | IllegalStateException e) {
            Conversor.erro(this, e.getMessage());
        }
    }

    private void calcularFrete() {
        try {
            Pedido pedido = pedidoSelecionado();
            double distancia = Conversor.decimal(campoDistancia, "Distância");
            double valor = sistema.calcularFrete(pedido, distancia, (Regiao) comboRegiao.getSelectedItem());
            atualizar();
            selecionar(pedido);
            rotuloFrete.setText("Frete: " + Conversor.moeda(valor));
        } catch (IllegalArgumentException | IllegalStateException e) {
            Conversor.erro(this, e.getMessage());
        }
    }

    private void cancelarPedido() {
        try {
            Pedido pedido = pedidoSelecionado();
            if (pedido.getStatusPedido() != StatusPedido.PENDENTE) {
                throw new IllegalStateException("Só é possível cancelar pedidos pendentes.");
            }
            if (Conversor.confirmar(this, "Cancelar o pedido nº " + pedido.getNumero() + "?")) {
                sistema.cancelarPedido(pedido);
                atualizar();
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            Conversor.erro(this, e.getMessage());
        }
    }

    private void limpar() {
        campoContatoColeta.setText("");
        campoDataColeta.setText("");
        campoDescricao.setText("");
        campoValorDeclarado.setText("");
        campoContatoDestino.setText("");
        painelColeta.limpar();
        painelEntrega.limpar();
        comboPeriodo.setSelectedIndex(0);
        comboServico.setSelectedIndex(0);
        comboPagamento.setSelectedIndex(0);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        Object fonte = evento.getSource();
        if (fonte == botaoCriar) {
            criarPedido();
        } else if (fonte == botaoAdicionarItem) {
            adicionarItem();
        } else if (fonte == botaoCalcularFrete) {
            calcularFrete();
        } else if (fonte == botaoCancelar) {
            cancelarPedido();
        }
    }
}
