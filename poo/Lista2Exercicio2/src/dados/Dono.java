package dados;

import java.util.ArrayList;

public class Dono {
    private String nome;
    private String cpf;
    private ArrayList<Animal> animais;

    public Dono (){

    }

    Dono(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
    }
    public void cadastrarAnimal (Animal animal){
        this.animais.add(animal);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ArrayList<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(ArrayList<Animal> animais) {
        this.animais = animais;
    }
    @Override
    public String toString() {
        return this.nome  + " - " + this.cpf + " - " + this.animais + '\n';
    }
}
