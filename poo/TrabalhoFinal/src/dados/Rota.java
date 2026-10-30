package dados;

import dados.enums.StatusRota;

import java.time.LocalDate;
import java.util.ArrayList;

public class Rota {
    private int numero;
    private LocalDate data;
    private double quilometragemInicial;
    private double quilometragemFinal;
    private StatusRota statusRota;
    private Veiculo veiculoAlocado;
    private ArrayList<Pedido> pedidos;
    private Motorista motoristaResponsavel;

    public Rota(int numero, LocalDate data, Veiculo veiculoAlocado, Motorista motoristaResponsavel, ArrayList<Pedido> pedidos, float quilometragemInicial, float quilometragemFinal) {
        this.numero = numero;
        this.data = data;
        this.veiculoAlocado = veiculoAlocado;
        this.motoristaResponsavel = motoristaResponsavel;
        this.pedidos = pedidos;
        this.quilometragemInicial = quilometragemInicial;
        this.quilometragemFinal = quilometragemFinal;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
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

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public float getQuilometragemInicial() {
        return quilometragemInicial;
    }

    public void setQuilometragemInicial(float quilometragemInicial) {
        this.quilometragemInicial = quilometragemInicial;
    }

    public float getQuilometragemFinal() {
        return quilometragemFinal;
    }

    public void setQuilometragemFinal(float quilometragemFinal) {
        this.quilometragemFinal = quilometragemFinal;
    }

    public StatusRota getStatus() {
        return status;
    }

    public void setStatus(StatusRota status) {
        this.status = status;
    }
}
