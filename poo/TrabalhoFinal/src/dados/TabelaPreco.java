package dados;

import java.time.*;
import java.util.*;

public class TabelaPreco {
    private double valorPorKg;
    private double valorPorKm;
    private double valorMinimo;
    private double descontoContratado;
    private Map<String, Double> fatorRegiao = new HashMap<>();

    public TabelaPreco(double valorPorKg, double valorPorKm, double valorMinimo, double descontoContratado) {
        this.valorPorKg = valorPorKg;
        this.valorPorKm = valorPorKm;
        this.valorMinimo = valorMinimo;
        this.descontoContratado = descontoContratado;
    }

    public double getValorPorKg() { return valorPorKg; }
    public void setValorPorKg(double valorPorKg) { this.valorPorKg = valorPorKg; }
    public double getValorPorKm() { return valorPorKm; }
    public void setValorPorKm(double valorPorKm) { this.valorPorKm = valorPorKm; }
    public double getValorMinimo() { return valorMinimo; }
    public void setValorMinimo(double valorMinimo) { this.valorMinimo = valorMinimo; }
    public double getDescontoContratado() { return descontoContratado; }
    public void setDescontoContratado(double descontoContratado) { this.descontoContratado = descontoContratado; }
    public Map<String, Double> getFatorRegiao() { return fatorRegiao; }
    public void setFatorRegiao(Map<String, Double> fatorRegiao) { this.fatorRegiao = fatorRegiao; }

    public double calculaFrete(double peso, double distancia, TipoDeServico tipo, String regiao, boolean clienteContratado) {
        double valor = peso * valorPorKg + distancia * valorPorKm;
        valor *= tipo.getFator();
        valor *= fatorRegiao.getOrDefault(regiao, 1.0);
        if (clienteContratado) valor *= (1 - descontoContratado);
        return Math.max(valor, valorMinimo);
    }
}
