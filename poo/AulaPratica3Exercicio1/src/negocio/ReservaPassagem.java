package negocio;
import dados.Cidade;
import dados.Cliente;
import dados.Reserva;

import java.util.ArrayList;

public class ReservaPassagem {
    Reserva reserva;
    private ArrayList<Cidade> listaDeCidades = new ArrayList<>();
    private ArrayList<Cliente> listaDeClientes = new ArrayList<>();
    private int quantidadeDeClientes;
    private int quantidadeDeCidades;

    public void cadastrarCidade (Cidade cidade){

    }
    public void cadastrarCliente (Cliente cliente){
        listaDeClientes.add(cliente);
    }
    public void reservarIda (Cliente cliente, Reserva reserva){
        cliente.reservarIda(reserva);
    }
    public void reservarVolta (Cliente cliente, Reserva reserva){
        cliente.reservarVolta(cliente.getReservas().getFirst(), reserva);
    }
    public ArrayList<Reserva> mostrarReservas(int CPFCliente){
        for (var cliente : listaDeClientes){
            if (cliente.getCPF() == CPFCliente){
                ArrayList<Reserva> reservasDoCliente = cliente.getReservas();
                for (var reserva : reservasDoCliente){
                    System.out.println(reserva.toString());
                }
                return reservasDoCliente;
            }
        }
        return null;
    }

    public ArrayList<Cliente> mostrarClientes() {
        for (var cliente : this.listaDeClientes){
            System.out.println(cliente.toString());
        }
        return this.listaDeClientes;
    }
    public ArrayList<Cidade> mostrarCidades(){
        for (var cidade :this.listaDeCidades){
            System.out.println(cidade.toString());
        }
        return this.listaDeCidades;
    }

}

