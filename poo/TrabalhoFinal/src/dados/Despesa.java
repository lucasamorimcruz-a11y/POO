package dados;

import dados.enums.TipoDeDespesa;

import java.util.Date;

public class Despesa {
    private Date data;
    private TipoDeDespesa tipoDeDespesa;
    private float valor;
    private String comprovante;

    public Despesa(Date data, TipoDeDespesa tipoDeDespesa, float valor, String comprovante) {
        this.data = data;
        this.tipoDeDespesa = tipoDeDespesa;
        this.valor = valor;
        this.comprovante = comprovante;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public TipoDeDespesa getTipoDeDespesa() {
        return tipoDeDespesa;
    }

    public void setTipoDeDespesa(TipoDeDespesa tipoDeDespesa) {
        this.tipoDeDespesa = tipoDeDespesa;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getComprovante() {
        return comprovante;
    }

    public void setComprovante(String comprovante) {
        this.comprovante = comprovante;
    }
}
