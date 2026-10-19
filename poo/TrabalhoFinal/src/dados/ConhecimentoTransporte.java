package dados;

import java.time.*;
import java.util.*;

public class ConhecimentoTransporte {
    private int numero;
    private LocalDate dataEmissao;
    private String remetente;
    private String destinatario;
    private String discriminacaoDaCarga;
    private double valorFrete;
    private double impostos;
    private double valorSeguro;
    private String notaFiscal;
    private Pedido pedido;

    public ConhecimentoTransporte(int numero, LocalDate dataEmissao, String remetente, String destinatario, String discriminacaoDaCarga, double valorFrete, double impostos, double valorSeguro, String notaFiscal, Pedido pedido) {
        this.numero = numero;
        this.dataEmissao = dataEmissao;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.discriminacaoDaCarga = discriminacaoDaCarga;
        this.valorFrete = valorFrete;
        this.impostos = impostos;
        this.valorSeguro = valorSeguro;
        this.notaFiscal = notaFiscal;
        this.pedido = pedido;
    }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
    public LocalDate getDataEmissao() { return dataEmissao; }
    public void setDataEmissao(LocalDate dataEmissao) { this.dataEmissao = dataEmissao; }
    public String getRemetente() { return remetente; }
    public void setRemetente(String remetente) { this.remetente = remetente; }
    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }
    public String getDiscriminacaoDaCarga() { return discriminacaoDaCarga; }
    public void setDiscriminacaoDaCarga(String discriminacaoDaCarga) { this.discriminacaoDaCarga = discriminacaoDaCarga; }
    public double getValorFrete() { return valorFrete; }
    public void setValorFrete(double valorFrete) { this.valorFrete = valorFrete; }
    public double getImpostos() { return impostos; }
    public void setImpostos(double impostos) { this.impostos = impostos; }
    public double getValorSeguro() { return valorSeguro; }
    public void setValorSeguro(double valorSeguro) { this.valorSeguro = valorSeguro; }
    public String getNotaFiscal() { return notaFiscal; }
    public void setNotaFiscal(String notaFiscal) { this.notaFiscal = notaFiscal; }
    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }
}
