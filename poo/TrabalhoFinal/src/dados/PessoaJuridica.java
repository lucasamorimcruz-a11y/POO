package dados;

public class PessoaJuridica extends Pessoa {
    private String cnpj;
    PessoaJuridica (String nome, String endereco, String telefone, String email, String cnpj){
        super (nome, endereco, telefone, email);
        this.cnpj = cnpj;
    }
}
