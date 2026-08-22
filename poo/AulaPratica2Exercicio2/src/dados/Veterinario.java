package dados;

import java.util.Arrays;

public class Veterinario {
    private String nome;
    private double salario;
    private Endereco endereco;
    private Animal []animais = new Animal[10];
    private int _quantidadeDeAnimais = 0;

    public Veterinario(String nome, double salario, Endereco endereco, Animal[] animais, int _quantidadeDeAnimais) {
        this.nome = nome;
        this.salario = salario;
        this.endereco = endereco;
        this.animais = animais;
        this._quantidadeDeAnimais = _quantidadeDeAnimais;
    }
    public Veterinario(){

    }
    public Veterinario (String nome, double salario){
        this.nome = nome;
        this.salario = salario;
    }


    public int get_quantidadeDeAnimais() {
        return _quantidadeDeAnimais;
    }

    @Override
    public String toString() {
        return "Veterinario{" +
                "nome='" + nome + '\'' +
                ", salario=" + salario +
                ", endereco=" + endereco +
                ", animais=" + Arrays.toString(animais) +
                ", _quantidadeDeAnimais=" + _quantidadeDeAnimais +
                '}';
    }

    public void set_quantidadeDeAnimais(int _quantidadeDeAnimais) {
        this._quantidadeDeAnimais = _quantidadeDeAnimais;
    }

    public Animal[] getAnimais() {
        return animais;
    }

    public void setAnimais(Animal[] animais) {
        this.animais = animais;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void cadastrarAnimal(Animal _animal){
        if (_quantidadeDeAnimais < 10){
            this.animais[_quantidadeDeAnimais] = _animal;
            _quantidadeDeAnimais++;
        }
    }

}
