package dados;

import java.util.ArrayList;

public class Cliente {
    private int CPF;
    private String nome;
    private String endereco;
    private int telefone;
    private ArrayList<Reserva> reservas = new ArrayList<>();
    public void reservarIda(Reserva reserva){
        this.reservas.add(reserva);
    }
    public void reservarVolta(Reserva ida, Reserva volta){

    }
    public int getCPF() {
        return CPF;
    }

    public void setCPF(int CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
