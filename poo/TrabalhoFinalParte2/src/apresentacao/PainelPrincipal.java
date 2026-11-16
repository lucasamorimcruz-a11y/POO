package apresentacao;

import negocio.Sistema;

import javax.swing.*;
import java.awt.*;

public class PainelPrincipal extends JTabbedPane {

    private Sistema sistema;
    private PainelClientes painelClientes;
    private PainelMotoristas painelMotoristas;
    private PainelVeiculos painelVeiculos;
    private PainelTabelaPadrao painelTabelaPadrao;
    private PainelPedidos painelPedidos;
    private PainelColeta painelColeta;
    private PainelRotas painelRotas;
    private PainelEntregas painelEntregas;
    private PainelRastreamento painelRastreamento;

    public PainelPrincipal(Sistema sistema) {
        this.sistema = sistema;

        painelClientes = new PainelClientes(sistema);
        painelMotoristas = new PainelMotoristas(sistema);
        painelVeiculos = new PainelVeiculos(sistema);
        painelTabelaPadrao = new PainelTabelaPadrao(sistema);
        painelPedidos = new PainelPedidos(sistema);
        painelColeta = new PainelColeta(sistema);
        painelRotas = new PainelRotas(sistema);
        painelEntregas = new PainelEntregas(sistema);
        painelRastreamento = new PainelRastreamento(sistema);

        adicionarAba("Clientes", painelClientes);
        adicionarAba("Motoristas", painelMotoristas);
        adicionarAba("Veículos", painelVeiculos);
        adicionarAba("Tabela de preços", painelTabelaPadrao);
        adicionarAba("Pedidos", painelPedidos);
        adicionarAba("Coleta", painelColeta);
        adicionarAba("Rotas", painelRotas);
        adicionarAba("Entregas", painelEntregas);
        adicionarAba("Rastreamento", painelRastreamento);

        addChangeListener(e -> atualizarAbas());
    }

    private void adicionarAba(String titulo, JComponent painel) {
        addTab(titulo, new JScrollPane(new AbaRolavel(painel)));
    }

    public void atualizarAbas() {
        painelClientes.atualizar();
        painelMotoristas.atualizar();
        painelVeiculos.atualizar();
        painelTabelaPadrao.atualizar();
        painelPedidos.atualizar();
        painelColeta.atualizar();
        painelRotas.atualizar();
        painelEntregas.atualizar();
        painelRastreamento.atualizar();
    }

    private static class AbaRolavel extends JPanel implements Scrollable {

        AbaRolavel(JComponent conteudo) {
            super(new BorderLayout());
            add(conteudo, BorderLayout.CENTER);
        }

        @Override
        public Dimension getPreferredScrollableViewportSize() {
            return getPreferredSize();
        }

        @Override
        public int getScrollableUnitIncrement(Rectangle area, int orientacao, int direcao) {
            return 16;
        }

        @Override
        public int getScrollableBlockIncrement(Rectangle area, int orientacao, int direcao) {
            return orientacao == SwingConstants.VERTICAL ? area.height : area.width;
        }

        @Override
        public boolean getScrollableTracksViewportWidth() {
            return true;
        }

        @Override
        public boolean getScrollableTracksViewportHeight() {
            return getParent() instanceof JViewport viewport && viewport.getHeight() > getPreferredSize().height;
        }
    }
}
