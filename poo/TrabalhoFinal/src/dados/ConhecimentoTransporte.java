package dados;

import java.time.LocalDate;
import java.util.Date;

public class ConhecimentoTransporte {
    private int numero;
    private LocalDate dataEmissao;
    private String remetente;
    private String destinatario;
    private String descriminacaoCarga;
    private float valorFrete;
    private float impostos;
    private boolean seguroContratado;
    private float valorSeguro;
    private String notaFiscal;
    private Pedido pedido;

    public ConhecimentoTransporte(int numero, LocalDate dataEmissao, String remetente, String destinatario, String descriminacaoCarga, float valorFrete, float impostos, boolean seguroContratado, float valorSeguro, String notaFiscal, Pedido pedido) {
        this.numero = numero;
        this.dataEmissao = dataEmissao;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.descriminacaoCarga = descriminacaoCarga;
        this.valorFrete = valorFrete;
        this.impostos = impostos;
        this.seguroContratado = seguroContratado;
        this.valorSeguro = valorSeguro;
        this.notaFiscal = notaFiscal;
        this.pedido = pedido;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getDescriminacaoCarga() {
        return descriminacaoCarga;
    }

    public void setDescriminacaoCarga(String descriminacaoCarga) {
        this.descriminacaoCarga = descriminacaoCarga;
    }

    public float getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(float valorFrete) {
        this.valorFrete = valorFrete;
    }

    public float getImpostos() {
        return impostos;
    }

    public void setImpostos(float impostos) {
        this.impostos = impostos;
    }

    public boolean isSeguroContratado() {
        return seguroContratado;
    }

    public void setSeguroContratado(boolean seguroContratado) {
        this.seguroContratado = seguroContratado;
    }

    public float getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(float valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public String getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(String notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}
