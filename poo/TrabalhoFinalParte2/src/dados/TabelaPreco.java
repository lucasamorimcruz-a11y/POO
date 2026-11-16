
package dados;

import dados.enums.Regiao;
import dados.enums.TipoDeServico;

import java.util.Map;

public class TabelaPreco {

    private double valorPorKg;
    private double valorPorKm;
    private double valorMinimo;
    private double descontoContratado;
    private Map<TipoDeServico, Double> fatorPorTipoDeServico;
    private Map <Regiao, Double> fatorPorRegiao;

    public TabelaPreco(double valorPorKg, double valorPorKm, double valorMinimo, double descontoContratado, Map<TipoDeServico, Double> fatorPorTipoDeServico, Map<Regiao, Double> fatorPorRegiao) {
        this.valorPorKg = valorPorKg;
        this.valorPorKm = valorPorKm;
        this.valorMinimo = valorMinimo;
        this.descontoContratado = descontoContratado;
        this.fatorPorTipoDeServico = fatorPorTipoDeServico;
        this.fatorPorRegiao = fatorPorRegiao;
    }

    public double calcularFrete (double peso, double distanciaKm, TipoDeServico tipo, Regiao regiao, boolean clienteContratado){
        double valor = (peso * valorPorKg) + (distanciaKm * valorPorKm);

        Double fatorServico = fatorPorTipoDeServico.get(tipo);

        if (fatorServico == null) {
            throw new IllegalArgumentException("Tipo de serviço não cadastrado.");
        }
        Double fatorRegiao = fatorPorRegiao.get(regiao);
        if (fatorRegiao == null){
            throw new IllegalArgumentException("Tipo de região não cadastrado");
        }
        valor *= fatorServico;
        valor *= fatorRegiao;

        if (valor < valorMinimo) {
            valor = valorMinimo;
        }

        if (clienteContratado) {
            valor *= (1 - descontoContratado);
        }

        return valor;
    }

    public double getValorPorKg() {
        return valorPorKg;
    }

    public void setValorPorKg(double valorPorKg) {
        this.valorPorKg = valorPorKg;
    }

    public double getValorPorKm() {
        return valorPorKm;
    }

    public void setValorPorKm(double valorPorKm) {
        this.valorPorKm = valorPorKm;
    }

    public double getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(double valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public double getDescontoContratado() {
        return descontoContratado;
    }

    public void setDescontoContratado(double descontoContratado) {
        this.descontoContratado = descontoContratado;
    }

    public Map<TipoDeServico, Double> getFatorPorTipoDeServico() {
        return fatorPorTipoDeServico;
    }

    public void setFatorPorTipoDeServico(Map<TipoDeServico, Double> fatorPorTipoDeServico) {
        this.fatorPorTipoDeServico = fatorPorTipoDeServico;
    }

    public Map<Regiao, Double> getFatorPorRegiao() {
        return fatorPorRegiao;
    }

    public void setFatorPorRegiao(Map<Regiao, Double> fatorPorRegiao) {
        this.fatorPorRegiao = fatorPorRegiao;
    }
}
