package dados;

public class PessoaFisica extends Pessoa {
    private String CPF;
    public PessoaFisica (String nome, String endereco, String telefone, String email, String cpf){
        super (nome, endereco, telefone, email);
        this.CPF = cpf;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
}
