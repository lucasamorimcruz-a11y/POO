package dados;

import java.time.*;
import java.util.*;

public class Pedido {
    private int numero;
    private LocalDate dataDeSolicitacao = LocalDate.now();
    private Cliente cliente;
    private Endereco enderecoDeColeta;
    private String contatoNoLocal;
    private LocalDate dataAgendadaPraColeta;
    private Periodo periodo;
    private String descricaoDaCarga;
    private double valorDaMercadoria;
    private Endereco enderecoDeEntrega;
    private String contatoNoDestino;
    private LocalDate prazoDeEntrega;
    private TipoDeServico tipoDeServico;
    private double valorDeFrete;
    private String formaDePagamento;
    private StatusPedido status = StatusPedido.PENDENTE;
    private List<Item> itens = new ArrayList<>();
    private List<EventoRastreamento> eventos = new ArrayList<>();
    private Entrega entrega;
    private LocalDateTime horarioPrevistoDeEntrega;
    private LocalDateTime horarioRealDeEntrega;

    public Pedido(int numero, Cliente cliente, Endereco enderecoDeColeta, String contatoNoLocal, LocalDate dataAgendadaPraColeta, Periodo periodo, String descricaoDaCarga, double valorDaMercadoria, Endereco enderecoDeEntrega, String contatoNoDestino, LocalDate prazoDeEntrega, TipoDeServico tipoDeServico, String formaDePagamento) {
        this.numero = numero;
        this.cliente = cliente;
        this.enderecoDeColeta = enderecoDeColeta;
        this.contatoNoLocal = contatoNoLocal;
        this.dataAgendadaPraColeta = dataAgendadaPraColeta;
        this.periodo = periodo;
        this.descricaoDaCarga = descricaoDaCarga;
        this.valorDaMercadoria = valorDaMercadoria;
        this.enderecoDeEntrega = enderecoDeEntrega;
        this.contatoNoDestino = contatoNoDestino;
        this.prazoDeEntrega = prazoDeEntrega;
        this.tipoDeServico = tipoDeServico;
        this.formaDePagamento = formaDePagamento;
    }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
    public LocalDate getDataDeSolicitacao() { return dataDeSolicitacao; }
    public void setDataDeSolicitacao(LocalDate dataDeSolicitacao) { this.dataDeSolicitacao = dataDeSolicitacao; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Endereco getEnderecoDeColeta() { return enderecoDeColeta; }
    public void setEnderecoDeColeta(Endereco enderecoDeColeta) { this.enderecoDeColeta = enderecoDeColeta; }
    public String getContatoNoLocal() { return contatoNoLocal; }
    public void setContatoNoLocal(String contatoNoLocal) { this.contatoNoLocal = contatoNoLocal; }
    public LocalDate getDataAgendadaPraColeta() { return dataAgendadaPraColeta; }
    public void setDataAgendadaPraColeta(LocalDate dataAgendadaPraColeta) { this.dataAgendadaPraColeta = dataAgendadaPraColeta; }
    public Periodo getPeriodo() { return periodo; }
    public void setPeriodo(Periodo periodo) { this.periodo = periodo; }
    public String getDescricaoDaCarga() { return descricaoDaCarga; }
    public void setDescricaoDaCarga(String descricaoDaCarga) { this.descricaoDaCarga = descricaoDaCarga; }
    public double getValorDaMercadoria() { return valorDaMercadoria; }
    public void setValorDaMercadoria(double valorDaMercadoria) { this.valorDaMercadoria = valorDaMercadoria; }
    public Endereco getEnderecoDeEntrega() { return enderecoDeEntrega; }
    public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) { this.enderecoDeEntrega = enderecoDeEntrega; }
    public String getContatoNoDestino() { return contatoNoDestino; }
    public void setContatoNoDestino(String contatoNoDestino) { this.contatoNoDestino = contatoNoDestino; }
    public LocalDate getPrazoDeEntrega() { return prazoDeEntrega; }
    public void setPrazoDeEntrega(LocalDate prazoDeEntrega) { this.prazoDeEntrega = prazoDeEntrega; }
    public TipoDeServico getTipoDeServico() { return tipoDeServico; }
    public void setTipoDeServico(TipoDeServico tipoDeServico) { this.tipoDeServico = tipoDeServico; }
    public double getValorDeFrete() { return valorDeFrete; }
    public void setValorDeFrete(double valorDeFrete) { this.valorDeFrete = valorDeFrete; }
    public String getFormaDePagamento() { return formaDePagamento; }
    public void setFormaDePagamento(String formaDePagamento) { this.formaDePagamento = formaDePagamento; }
    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }
    public List<Item> getItens() { return itens; }
    public void setItens(List<Item> itens) { this.itens = itens; }
    public List<EventoRastreamento> getEventos() { return eventos; }
    public void setEventos(List<EventoRastreamento> eventos) { this.eventos = eventos; }
    public Entrega getEntrega() { return entrega; }
    public void setEntrega(Entrega entrega) { this.entrega = entrega; }
    public LocalDateTime getHorarioPrevistoDeEntrega() { return horarioPrevistoDeEntrega; }
    public void setHorarioPrevistoDeEntrega(LocalDateTime horarioPrevistoDeEntrega) { this.horarioPrevistoDeEntrega = horarioPrevistoDeEntrega; }
    public LocalDateTime getHorarioRealDeEntrega() { return horarioRealDeEntrega; }
    public void setHorarioRealDeEntrega(LocalDateTime horarioRealDeEntrega) { this.horarioRealDeEntrega = horarioRealDeEntrega; }

    public void adicionarItem(Item item) { itens.add(item); }

    public double getPesoTotal() { return itens.stream().mapToDouble(Item::getPeso).sum(); }

    public double getVolumeTotal() { return itens.stream().mapToDouble(Item::getDimensao).sum(); }

    public void adicionarEvento(EventoRastreamento e) { eventos.add(e); }

    @Override
    public String toString() { return "Pedido #" + numero + " [" + status + "]"; }
}
