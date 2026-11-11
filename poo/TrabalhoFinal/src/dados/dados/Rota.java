package dados.dados;

import dados.Despesa;
import dados.Motorista;
import dados.Pedido;
import dados.PedidoNaRota;
import dados.Veiculo;
import dados.enums.StatusRota;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Rota {
    private int numero;
    private LocalDate data;
    private dados.Veiculo veiculoAlocado;
    private dados.Motorista motoristaResponsavel;
    private ArrayList<dados.PedidoNaRota> pedidos = new ArrayList<>();
    private double quilometragemInicial;
    private double quilometragemFinal;
    private StatusRota status;
    private ArrayList<dados.Despesa> despesas = new ArrayList<>();

    // Toda rota nasce PLANEJADA, sem pedidos, despesas ou quilometragem.
    public Rota(int numero, LocalDate data, dados.Veiculo veiculoAlocado, dados.Motorista motoristaResponsavel) {
        this.numero = numero;
        this.data = data;
        this.veiculoAlocado = veiculoAlocado;
        this.motoristaResponsavel = motoristaResponsavel;
        this.status = StatusRota.PLANEJADA;
    }

    // Adiciona o pedido ao final da rota; a sequência de entrega é a próxima posição.
    public dados.PedidoNaRota adicionarPedido(Pedido pedido, LocalDateTime horarioPrevisto) {
        dados.PedidoNaRota parada = new dados.PedidoNaRota(pedidos.size() + 1, pedido, horarioPrevisto);
        pedidos.add(parada);
        return parada;
    }

    public void adicionarDespesa(dados.Despesa despesa) {
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

    public dados.Veiculo getVeiculoAlocado() {
        return veiculoAlocado;
    }

    public void setVeiculoAlocado(Veiculo veiculoAlocado) {
        this.veiculoAlocado = veiculoAlocado;
    }

    public dados.Motorista getMotoristaResponsavel() {
        return motoristaResponsavel;
    }

    public void setMotoristaResponsavel(Motorista motoristaResponsavel) {
        this.motoristaResponsavel = motoristaResponsavel;
    }

    public ArrayList<dados.PedidoNaRota> getPedidos() {
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

    public ArrayList<dados.Despesa> getDespesas() {
        return despesas;
    }

    public void setDespesas(ArrayList<Despesa> despesas) {
        this.despesas = despesas;
    }

    @Override
    public String toString() {
        return "Rota nº " + numero + " - " + data;
    }
}
