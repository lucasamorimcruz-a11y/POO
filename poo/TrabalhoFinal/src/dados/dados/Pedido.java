package dados.dados;

import dados.Cliente;
import dados.ConhecimentoTransporte;
import dados.Endereco;
import dados.Entrega;
import dados.EventoDeRastreamento;
import dados.FalhaNaEntrega;
import dados.Item;
import dados.enums.FormaDePagamento;
import dados.enums.Periodo;
import dados.enums.StatusPedido;
import dados.enums.TipoDeServico;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {
    private int numero;
    private LocalDate dataDeSolicitacao;
    private String contatoNoLocal;
    private LocalDate dataAgendadaPraColeta;
    private Periodo periodo;
    private String descricaoDaCarga;
    private double pesoTotal;
    private double volumeTotal;
    private double valorDeclarado;
    private String contatoNoDestino;
    private LocalDate prazoDeEntrega;
    private TipoDeServico tipoDeServico;
    private double valorFrete;
    private FormaDePagamento formaDePagamento;
    private StatusPedido statusPedido;
    private Cliente cliente;
    private dados.Endereco enderecoDeEntrega;
    private dados.Endereco enderecoDeColeta;
    private dados.Entrega entrega;
    private ArrayList<dados.Item> itens = new ArrayList<>();
    private ArrayList<dados.EventoDeRastreamento> eventos = new ArrayList<>();
    private ArrayList<dados.ConhecimentoTransporte> conhecimentos = new ArrayList<>();
    private ArrayList<FalhaNaEntrega> falhasNaEntrega = new ArrayList<>();


    public Pedido(int numero, LocalDate dataDeSolicitacao, Cliente cliente,
                  dados.Endereco enderecoDeColeta, String contatoNoLocal,
                  LocalDate dataAgendadaPraColeta, Periodo periodo,
                  String descricaoDaCarga, double valorDeclarado,
                  dados.Endereco enderecoDeEntrega, String contatoNoDestino,
                  LocalDate prazoDeEntrega, TipoDeServico tipoDeServico,
                  FormaDePagamento formaDePagamento) {
        this.numero = numero;
        this.dataDeSolicitacao = dataDeSolicitacao;
        this.cliente = cliente;
        this.enderecoDeColeta = enderecoDeColeta;
        this.contatoNoLocal = contatoNoLocal;
        this.dataAgendadaPraColeta = dataAgendadaPraColeta;
        this.periodo = periodo;
        this.descricaoDaCarga = descricaoDaCarga;
        this.valorDeclarado = valorDeclarado;
        this.enderecoDeEntrega = enderecoDeEntrega;
        this.contatoNoDestino = contatoNoDestino;
        this.prazoDeEntrega = prazoDeEntrega;
        this.tipoDeServico = tipoDeServico;
        this.formaDePagamento = formaDePagamento;
        this.statusPedido = StatusPedido.PENDENTE;
    }
    public void adicionarItem(dados.Item item) {
        itens.add(item);
        pesoTotal += item.getPeso();
    }

    public void adicionarEvento(dados.EventoDeRastreamento evento) {
        eventos.add(evento);
    }

    public void adicionarFalha(FalhaNaEntrega falha) {
        falhasNaEntrega.add(falha);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getDataDeSolicitacao() {
        return dataDeSolicitacao;
    }

    public void setDataDeSolicitacao(LocalDate dataDeSolicitacao) {
        this.dataDeSolicitacao = dataDeSolicitacao;
    }

    public String getContatoNoLocal() {
        return contatoNoLocal;
    }

    public void setContatoNoLocal(String contatoNoLocal) {
        this.contatoNoLocal = contatoNoLocal;
    }

    public LocalDate getDataAgendadaPraColeta() {
        return dataAgendadaPraColeta;
    }

    public void setDataAgendadaPraColeta(LocalDate dataAgendadaPraColeta) {
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

    public double getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(double pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public double getVolumeTotal() {
        return volumeTotal;
    }

    public void setVolumeTotal(double volumeTotal) {
        this.volumeTotal = volumeTotal;
    }

    public double getValorDeclarado() {
        return valorDeclarado;
    }

    public void setValorDeclarado(double valorDeclarado) {
        this.valorDeclarado = valorDeclarado;
    }

    public String getContatoNoDestino() {
        return contatoNoDestino;
    }

    public void setContatoNoDestino(String contatoNoDestino) {
        this.contatoNoDestino = contatoNoDestino;
    }

    public LocalDate getPrazoDeEntrega() {
        return prazoDeEntrega;
    }

    public void setPrazoDeEntrega(LocalDate prazoDeEntrega) {
        this.prazoDeEntrega = prazoDeEntrega;
    }

    public TipoDeServico getTipoDeServico() {
        return tipoDeServico;
    }

    public void setTipoDeServico(TipoDeServico tipoDeServico) {
        this.tipoDeServico = tipoDeServico;
    }

    public double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public dados.Endereco getEnderecoDeEntrega() {
        return enderecoDeEntrega;
    }

    public void setEnderecoDeEntrega(dados.Endereco enderecoDeEntrega) {
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public dados.Endereco getEnderecoDeColeta() {
        return enderecoDeColeta;
    }

    public void setEnderecoDeColeta(Endereco enderecoDeColeta) {
        this.enderecoDeColeta = enderecoDeColeta;
    }

    public dados.Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    public ArrayList<dados.Item> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Item> itens) {
        this.itens = itens;
    }

    public ArrayList<dados.EventoDeRastreamento> getEventos() {
        return eventos;
    }

    public void setEventos(ArrayList<EventoDeRastreamento> eventos) {
        this.eventos = eventos;
    }

    public ArrayList<dados.ConhecimentoTransporte> getConhecimentos() {
        return conhecimentos;
    }

    public void setConhecimentos(ArrayList<ConhecimentoTransporte> conhecimentos) {
        this.conhecimentos = conhecimentos;
    }

    public ArrayList<FalhaNaEntrega> getFalhasNaEntrega() {
        return falhasNaEntrega;
    }

    public void setFalhasNaEntrega(ArrayList<FalhaNaEntrega> falhasNaEntrega) {
        this.falhasNaEntrega = falhasNaEntrega;
    }

    @Override
    public String toString() {
        return "Pedido nº " + numero + " - " + cliente.getPessoa().getNome() + " [" + statusPedido + "]";
    }
}
