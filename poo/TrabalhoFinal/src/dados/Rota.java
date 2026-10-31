package dados;

import dados.enums.StatusRota;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Rota {
    private int numero;
    private LocalDate data;
    private Veiculo veiculoAlocado;
    private Motorista motoristaResponsavel;
    private ArrayList<PedidoNaRota> pedidos = new ArrayList<>();
    private double quilometragemInicial;
    private double quilometragemFinal;
    private StatusRota status;
    private ArrayList<Despesa> despesas = new ArrayList<>();

    public Rota(int numero, LocalDate data, Veiculo veiculoAlocado, Motorista motoristaResponsavel) {
        this.numero = numero;
        this.data = data;
        this.veiculoAlocado = veiculoAlocado;
        this.motoristaResponsavel = motoristaResponsavel;
        this.status = StatusRota.PLANEJADA;
    }

    public PedidoNaRota adicionarPedido(Pedido pedido, LocalDateTime horarioPrevisto) {
        PedidoNaRota parada = new PedidoNaRota(pedidos.size() + 1, pedido, horarioPrevisto);
        pedidos.add(parada);
        return parada;
    }

    public void adicionarDespesa(Despesa despesa) {
        despesas.add(despesa);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Veiculo getVeiculoAlocado() {
        return veiculoAlocado;
    }

    public void setVeiculoAlocado(Veiculo veiculoAlocado) {
        this.veiculoAlocado = veiculoAlocado;
    }

    public Motorista getMotoristaResponsavel() {
        return motoristaResponsavel;
    }

    public void setMotoristaResponsavel(Motorista motoristaResponsavel) {
        this.motoristaResponsavel = motoristaResponsavel;
    }

    public ArrayList<PedidoNaRota> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<PedidoNaRota> pedidos) {
        this.pedidos = pedidos;
    }

    public double getQuilometragemInicial() {
        return quilometragemInicial;
    }

    public void setQuilometragemInicial(double quilometragemInicial) {
        this.quilometragemInicial = quilometragemInicial;
    }

    public double getQuilometragemFinal() {
        return quilometragemFinal;
    }

    public void setQuilometragemFinal(double quilometragemFinal) {
        this.quilometragemFinal = quilometragemFinal;
    }

    public StatusRota getStatus() {
        return status;
    }

    public void setStatus(StatusRota status) {
        this.status = status;
    }

    public ArrayList<Despesa> getDespesas() {
        return despesas;
    }

    public void setDespesas(ArrayList<Despesa> despesas) {
        this.despesas = despesas;
    }
}
