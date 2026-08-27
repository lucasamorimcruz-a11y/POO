package dados;

import java.util.ArrayList;

public class Cliente {
    private String CPF;
    private String nome;
    private String endereco;
    private int telefone;
    private ArrayList<Reserva> reservas;

    public Cliente (){
        reservas = new ArrayList<>();
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
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
    public void reservarIda(Reserva reserva){
        this.reservas.add(reserva);
    }
    public void reservarVolta (Reserva ida, Reserva volta){
        ida.setVolta(volta);
    }
    public void decadastrarReserva (Reserva reserva){
        this.reservas.remove(reserva);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "CPF=" + CPF +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone=" + telefone +
                ", reservas=" + reservas +
                '}';
    }
    public boolean equals (Object o){
        if (o instanceof Cliente){
            Cliente c = (Cliente) o;
            return this.CPF.equals(c.getCPF());
        }
        return false;
    }


}