package dados;

public class Pedido {
    private int numero;
    private String dataDeSolicitacao;
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

    public Pedido(int numero, String dataDeSolicitacao, Cliente clienteDoPedido, Endereco enderecoDeColeta, String dataAgendadaPraColeta, Periodo periodo, String descricaoDaCarga, float pesoTotal, float volumeTotal, float valorDaMercadoria, Endereco enderecoDeEntrega, String contatoNoDestino, String prazoDeEntrega, float valorDoFrete, String formaDePagamento, Status status) {
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
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDataDeSolicitacao() {
        return dataDeSolicitacao;
    }

    public void setDataDeSolicitacao(String dataDeSolicitacao) {
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
}
