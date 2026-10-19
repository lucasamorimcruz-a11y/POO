package dados;

import java.time.*;
import java.util.*;

public class Rota {
    private int numero;
    private LocalDate data;
    private Veiculo veiculoAlocado;
    private Motorista motoristaResponsavel;
    private List<Pedido> pedidos = new ArrayList<>();
    private double quilometragemInicial;
    private double quilometragemFinal;
    private StatusRota status = StatusRota.PLANEJADA;
    private List<DespesaRota> despesas = new ArrayList<>();

    public Rota(int numero, LocalDate data, Veiculo veiculoAlocado, Motorista motoristaResponsavel) {
        this.numero = numero;
        this.data = data;
        this.veiculoAlocado = veiculoAlocado;
        this.motoristaResponsavel = motoristaResponsavel;
    }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public Veiculo getVeiculoAlocado() { return veiculoAlocado; }
    public void setVeiculoAlocado(Veiculo veiculoAlocado) { this.veiculoAlocado = veiculoAlocado; }
    public Motorista getMotoristaResponsavel() { return motoristaResponsavel; }
    public void setMotoristaResponsavel(Motorista motoristaResponsavel) { this.motoristaResponsavel = motoristaResponsavel; }
    public List<Pedido> getPedidos() { return pedidos; }
    public void setPedidos(List<Pedido> pedidos) { this.pedidos = pedidos; }
    public double getQuilometragemInicial() { return quilometragemInicial; }
    public void setQuilometragemInicial(double quilometragemInicial) { this.quilometragemInicial = quilometragemInicial; }
    public double getQuilometragemFinal() { return quilometragemFinal; }
    public void setQuilometragemFinal(double quilometragemFinal) { this.quilometragemFinal = quilometragemFinal; }
    public StatusRota getStatus() { return status; }
    public void setStatus(StatusRota status) { this.status = status; }
    public List<DespesaRota> getDespesas() { return despesas; }
    public void setDespesas(List<DespesaRota> despesas) { this.despesas = despesas; }

    public double getPesoTotal() { return pedidos.stream().mapToDouble(Pedido::getPesoTotal).sum(); }

    public double getVolumeTotal() { return pedidos.stream().mapToDouble(Pedido::getVolumeTotal).sum(); }

    @Override
    public String toString() { return "Rota #" + numero + " [" + status + "]"; }
}
