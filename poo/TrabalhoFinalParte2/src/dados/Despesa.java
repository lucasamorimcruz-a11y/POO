package dados;

import dados.enums.TipoDeDespesa;

import java.time.LocalDate;
import java.util.Date;

public class Despesa {
    private LocalDate data;
    private TipoDeDespesa tipoDeDespesa;
    private double valor;
    private String comprovante;

    public Despesa(LocalDate data, TipoDeDespesa tipoDeDespesa, double valor, String comprovante) {
        this.data = data;
        this.tipoDeDespesa = tipoDeDespesa;
        this.valor = valor;
        this.comprovante = comprovante;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public TipoDeDespesa getTipoDeDespesa() {
        return tipoDeDespesa;
    }

    public void setTipoDeDespesa(TipoDeDespesa tipoDeDespesa) {
        this.tipoDeDespesa = tipoDeDespesa;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getComprovante() {
        return comprovante;
    }

    public void setComprovante(String comprovante) {
        this.comprovante = comprovante;
    }
}
