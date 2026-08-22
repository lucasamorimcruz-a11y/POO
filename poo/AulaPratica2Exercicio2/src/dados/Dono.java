package dados;

public class Dono {
    private String nome;
    private Endereco endereco;
    private String CPF;

    public Dono(String nome, Endereco endereco, String CPF) {
        this.nome = nome;
        this.endereco = endereco;
        this.CPF = CPF;
    }

    @Override
    public String toString() {
        return "Dono{" +
                "nome='" + nome + '\'' +
                ", endereco=" + endereco +
                ", CPF='" + CPF + '\'' +
                '}';
    }

    public Dono (String nome, String CPF){
        this.nome = nome;
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
}
