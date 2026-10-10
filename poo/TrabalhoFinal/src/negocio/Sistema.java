
package negocio;

import dados.*;

import java.util.ArrayList;

public class Sistema {

    private ArrayList<Cliente> clientes;
    private ArrayList<Veiculo> veiculos;
    private ArrayList<Motorista> motoristas;
    private ArrayList<Pedido> pedidos;
    private ArrayList<Rota> rotas;
    private ArrayList<EventoDeRastreamento> eventoDeRastreamentos;

    public Sistema() {
        clientes = new ArrayList<>();
        veiculos = new ArrayList<>();
        motoristas = new ArrayList<>();
        pedidos = new ArrayList<>();
        rotas = new ArrayList<>();
        eventoDeRastreamentos = new ArrayList<>();
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(ArrayList<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public ArrayList<Motorista> getMotoristas() {
        return motoristas;
    }

    public void setMotoristas(ArrayList<Motorista> motoristas) {
        this.motoristas = motoristas;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public ArrayList<Rota> getRotas() {
        return rotas;
    }

    public void setRotas(ArrayList<Rota> rotas) {
        this.rotas = rotas;
    }

    public ArrayList<EventoDeRastreamento> getEventoDeRastreamentos() {
        return eventoDeRastreamentos;
    }

    public void setEventoDeRastreamentos(ArrayList<EventoDeRastreamento> eventoDeRastreamentos) {
        this.eventoDeRastreamentos = eventoDeRastreamentos;
    }
}
