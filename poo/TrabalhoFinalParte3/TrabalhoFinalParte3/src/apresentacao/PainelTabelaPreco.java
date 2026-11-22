package apresentacao;

import dados.TabelaPreco;
import dados.enums.Regiao;
import dados.enums.TipoDeServico;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

public class PainelTabelaPreco extends JPanel {

    private JTextField campoValorPorKg = new JTextField(10);
    private JTextField campoValorPorKm = new JTextField(10);
    private JTextField campoValorMinimo = new JTextField(10);
    private JTextField campoDesconto = new JTextField(10);
    private Map<TipoDeServico, JTextField> camposFatorServico = new EnumMap<>(TipoDeServico.class);
    private Map<Regiao, JTextField> camposFatorRegiao = new EnumMap<>(Regiao.class);

    public PainelTabelaPreco() {
        super(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Tabela de preços"));

        JPanel campos = new JPanel(new GridLayout(0, 2, 5, 5));
        Conversor.adicionar(campos, "Valor por kg (R$):", campoValorPorKg);
        Conversor.adicionar(campos, "Valor por km (R$):", campoValorPorKm);
        Conversor.adicionar(campos, "Valor mínimo (R$):", campoValorMinimo);
        Conversor.adicionar(campos, "Desconto contratado (%):", campoDesconto);
        for (TipoDeServico tipo : TipoDeServico.values()) {
            JTextField campo = new JTextField(10);
            camposFatorServico.put(tipo, campo);
            Conversor.adicionar(campos, "Fator " + tipo + ":", campo);
        }
        for (Regiao regiao : Regiao.values()) {
            JTextField campo = new JTextField(10);
            camposFatorRegiao.put(regiao, campo);
            Conversor.adicionar(campos, "Fator " + regiao + ":", campo);
        }
        add(campos, BorderLayout.NORTH);
    }

    public TabelaPreco getTabelaPreco() {
        double valorPorKg = Conversor.decimal(campoValorPorKg, "Valor por kg");
        double valorPorKm = Conversor.decimal(campoValorPorKm, "Valor por km");
        double valorMinimo = Conversor.decimal(campoValorMinimo, "Valor mínimo");
        double desconto = Conversor.decimal(campoDesconto, "Desconto contratado");
        if (valorPorKg < 0 || valorPorKm < 0 || valorMinimo < 0) {
            Conversor.setErro("Os valores da tabela de preços não podem ser negativos.");
        }
        if (desconto < 0 || desconto > 100) {
            Conversor.setErro("O desconto deve estar entre 0 e 100%.");
        }

        Map<TipoDeServico, Double> fatoresServico = new EnumMap<>(TipoDeServico.class);
        for (TipoDeServico tipo : TipoDeServico.values()) {
            fatoresServico.put(tipo, fator(camposFatorServico.get(tipo), "Fator " + tipo));
        }
        Map<Regiao, Double> fatoresRegiao = new EnumMap<>(Regiao.class);
        for (Regiao regiao : Regiao.values()) {
            fatoresRegiao.put(regiao, fator(camposFatorRegiao.get(regiao), "Fator " + regiao));
        }
        if (Conversor.temErro()) {
            return null;
        }
        return new TabelaPreco(valorPorKg, valorPorKm, valorMinimo, desconto / 100.0, fatoresServico, fatoresRegiao);
    }

    private double fator(JTextField campo, String nome) {
        double valor = Conversor.decimal(campo, nome);
        if (valor <= 0) {
            Conversor.setErro("O campo \"" + nome + "\" deve ser maior que zero.");
        }
        return valor;
    }

    public void preencher(TabelaPreco tabela) {
        if (tabela == null) {
            limpar();
            return;
        }
        campoValorPorKg.setText(Conversor.numero(tabela.getValorPorKg()));
        campoValorPorKm.setText(Conversor.numero(tabela.getValorPorKm()));
        campoValorMinimo.setText(Conversor.numero(tabela.getValorMinimo()));
        campoDesconto.setText(Conversor.numero(tabela.getDescontoContratado() * 100));
        for (TipoDeServico tipo : TipoDeServico.values()) {
            Double fator = tabela.getFatorPorTipoDeServico().get(tipo);
            if (fator == null) {
                camposFatorServico.get(tipo).setText("");
            } else {
                camposFatorServico.get(tipo).setText(Conversor.numero(fator));
            }
        }
        for (Regiao regiao : Regiao.values()) {
            Double fator = tabela.getFatorPorRegiao().get(regiao);
            if (fator == null) {
                camposFatorRegiao.get(regiao).setText("");
            } else {
                camposFatorRegiao.get(regiao).setText(Conversor.numero(fator));
            }
        }
    }

    public void limpar() {
        campoValorPorKg.setText("");
        campoValorPorKm.setText("");
        campoValorMinimo.setText("");
        campoDesconto.setText("");
        for (JTextField campo : camposFatorServico.values()) {
            campo.setText("");
        }
        for (JTextField campo : camposFatorRegiao.values()) {
            campo.setText("");
        }
    }

    public void setEditavel(boolean editavel) {
        campoValorPorKg.setEnabled(editavel);
        campoValorPorKm.setEnabled(editavel);
        campoValorMinimo.setEnabled(editavel);
        campoDesconto.setEnabled(editavel);
        for (JTextField campo : camposFatorServico.values()) {
            campo.setEnabled(editavel);
        }
        for (JTextField campo : camposFatorRegiao.values()) {
            campo.setEnabled(editavel);
        }
    }
}
