package dados;

import java.util.Date;

public class ConhecimentoTransporte {
    private int numero;
    private Date dataEmissao;
    private String remetente;
    private String destinatario;
    private String descriminacaoCarga;
    private float valorFrete;
    private float impostos;
    private float valorSeguro;
    private String notaFiscal;
    private Pedido pedido;

    public ConhecimentoTransporte(int numero, Date dataEmissao, String remetente, String destinatario, String descriminacaoCarga, float valorFrete, float impostos, float valorSeguro, String notaFiscal, Pedido pedido) {
        this.numero = numero;
        this.dataEmissao = dataEmissao;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.descriminacaoCarga = descriminacaoCarga;
        this.valorFrete = valorFrete;
        this.impostos = impostos;
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

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
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
