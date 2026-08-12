package udesc.poo.aula_pratica2.petshop;

public class Veterinario {
    private String nome;
    private double salario;
    private Endereco endereco;
    private Animal[] animais;
    private int quantidade_de_animais;

    Veterinario(){
        animais = new Animal[50];
    }
    public void adicionarAnimal(Animal animal) {
        animais[quantidade_de_animais] = animal;
        quantidade_de_animais++;
    }

    public String getNome() {
        return nome;
    }

    public double getSalario() {
        return salario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Animal[] getAnimais() {
        return animais;
    }

    public int getQuantidade_de_animais() {
        return quantidade_de_animais;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setAnimais(Animal[] animais) {
        this.animais = animais;
    }

    public void setQuantidade_de_animais(int quantidade_de_animais) {
        this.quantidade_de_animais = quantidade_de_animais;
    }

}
