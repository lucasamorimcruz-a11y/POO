
package dados;

import enums.TipoDeServico;

public class TabelaPreco {

    private double valorPorKg;
    private double valorPorKm;
    private double valorMinimo;
    private double descontoContratado;

    public TabelaPreco(double valorPorKg, double valorPorKm,
                       double valorMinimo, double descontoContratado) {
        this.valorPorKg = valorPorKg;
        this.valorPorKm = valorPorKm;
        this.valorMinimo = valorMinimo;
        this.descontoContratado = descontoContratado;
    }
    public double calcularFrete(double peso, double distancia, TipoDeServico tipoDeServico,  boolean clienteContratado) {
        double frete = valorMinimo + peso * valorPorKg  + distancia * valorPorKm;
        switch (tipoDeServico) {
            case ECONOMICO:
                break;
            case EXPRESSO:
                frete *= 1.5;
                break;
            case AGENDADO:
                frete *= 1.2;
                break;
        }
        if (clienteContratado) {
            frete *= 1 - descontoContratado / 100;
        }
        return frete;
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
}
