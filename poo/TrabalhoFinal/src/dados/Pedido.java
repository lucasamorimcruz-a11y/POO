package dados;

import dados.enums.Periodo;
import dados.enums.Status;
import dados.enums.TipoDeServico;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {
    private int numero;
    private Date dataDeSolicitacao;
    private Cliente clienteDoPedido;
    private Endereco enderecoDeColeta;
    private String dataAgendadaPraColeta;
    private Periodo periodo;
    private String descricaoDaCarga;
    private float pesoTotal;
    private float volumeTotal;
    private float valorDaMercadoria;
    private Endereco enderecoDeEntrega;
    private String contatoNoDestino;
    private String prazoDeEntrega;
    private float valorDoFrete;
    private String formaDePagamento;
    private Status status;
    private ArrayList<Itens> itens = new ArrayList<>();
    private Date horarioPrevistoDeEntrega;
    private Date horarioRealDeEntrega;
    private TipoDeServico tipoDeServico;

    public Pedido(int numero, Date dataDeSolicitacao, Cliente clienteDoPedido, Endereco enderecoDeColeta, String dataAgendadaPraColeta, Periodo periodo, String descricaoDaCarga, float pesoTotal, float volumeTotal, float valorDaMercadoria, Endereco enderecoDeEntrega, String contatoNoDestino, String prazoDeEntrega, float valorDoFrete, String formaDePagamento, Status status, ArrayList<Itens> itens, Date horarioPrevistoDeEntrega, Date horarioRealDeEntrega, TipoDeServico tipoDeServico) {
        this.numero = numero;
        this.dataDeSolicitacao = dataDeSolicitacao;
        this.clienteDoPedido = clienteDoPedido;
        this.enderecoDeColeta = enderecoDeColeta;
        this.dataAgendadaPraColeta = dataAgendadaPraColeta;
        this.periodo = periodo;
        this.descricaoDaCarga = descricaoDaCarga;
        this.pesoTotal = pesoTotal;
        this.volumeTotal = volumeTotal;
        this.valorDaMercadoria = valorDaMercadoria;
        this.enderecoDeEntrega = enderecoDeEntrega;
        this.contatoNoDestino = contatoNoDestino;
        this.prazoDeEntrega = prazoDeEntrega;
        this.valorDoFrete = valorDoFrete;
        this.formaDePagamento = formaDePagamento;
        this.status = status;
        this.itens = itens;
        this.horarioPrevistoDeEntrega = horarioPrevistoDeEntrega;
        this.horarioRealDeEntrega = horarioRealDeEntrega;
        this.tipoDeServico = tipoDeServico;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getDataDeSolicitacao() {
        return dataDeSolicitacao;
    }

    public void setDataDeSolicitacao(Date dataDeSolicitacao) {
        this.dataDeSolicitacao = dataDeSolicitacao;
    }

    public Cliente getClienteDoPedido() {
        return clienteDoPedido;
    }

    public void setClienteDoPedido(Cliente clienteDoPedido) {
        this.clienteDoPedido = clienteDoPedido;
    }

    public Endereco getEnderecoDeColeta() {
        return enderecoDeColeta;
    }

    public void setEnderecoDeColeta(Endereco enderecoDeColeta) {
        this.enderecoDeColeta = enderecoDeColeta;
    }

    public String getDataAgendadaPraColeta() {
        return dataAgendadaPraColeta;
    }

    public void setDataAgendadaPraColeta(String dataAgendadaPraColeta) {
        this.dataAgendadaPraColeta = dataAgendadaPraColeta;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public String getDescricaoDaCarga() {
        return descricaoDaCarga;
    }

    public void setDescricaoDaCarga(String descricaoDaCarga) {
        this.descricaoDaCarga = descricaoDaCarga;
    }

    public float getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(float pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public float getVolumeTotal() {
        return volumeTotal;
    }

    public void setVolumeTotal(float volumeTotal) {
        this.volumeTotal = volumeTotal;
    }

    public float getValorDaMercadoria() {
        return valorDaMercadoria;
    }

    public void setValorDaMercadoria(float valorDaMercadoria) {
        this.valorDaMercadoria = valorDaMercadoria;
    }

    public Endereco getEnderecoDeEntrega() {
        return enderecoDeEntrega;
    }

    public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public String getContatoNoDestino() {
        return contatoNoDestino;
    }

    public void setContatoNoDestino(String contatoNoDestino) {
        this.contatoNoDestino = contatoNoDestino;
    }

    public String getPrazoDeEntrega() {
        return prazoDeEntrega;
    }

    public void setPrazoDeEntrega(String prazoDeEntrega) {
        this.prazoDeEntrega = prazoDeEntrega;
    }

    public float getValorDoFrete() {
        return valorDoFrete;
    }

    public void setValorDoFrete(float valorDoFrete) {
        this.valorDoFrete = valorDoFrete;
    }

    public String getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(String formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ArrayList<Itens> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Itens> itens) {
        this.itens = itens;
    }

    public Date getHorarioPrevistoDeEntrega() {
        return horarioPrevistoDeEntrega;
    }

    public void setHorarioPrevistoDeEntrega(Date horarioPrevistoDeEntrega) {
        this.horarioPrevistoDeEntrega = horarioPrevistoDeEntrega;
    }

    public Date getHorarioRealDeEntrega() {
        return horarioRealDeEntrega;
    }

    public void setHorarioRealDeEntrega(Date horarioRealDeEntrega) {
        this.horarioRealDeEntrega = horarioRealDeEntrega;
    }

    public TipoDeServico getTipoDeServico() {
        return tipoDeServico;
    }

    public void setTipoDeServico(TipoDeServico tipoDeServico) {
        this.tipoDeServico = tipoDeServico;
    }
}
