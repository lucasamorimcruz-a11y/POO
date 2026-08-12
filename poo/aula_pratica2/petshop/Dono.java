package udesc.poo.aula_pratica2.petshop;

public class Dono {
    private String nome;
    private Endereco endereco;
    private String CPF;

    Dono (){

    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getCPF() {
        return CPF;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
}
