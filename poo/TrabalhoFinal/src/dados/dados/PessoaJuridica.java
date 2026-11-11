package dados.dados;

import dados.Endereco;
import dados.Pessoa;

public class PessoaJuridica extends Pessoa {
    private String cnpj;
    private String razaoSocial;
    private String inscricaoEstadual;
    public PessoaJuridica (String nome, Endereco endereco, String telefone, String email, String cnpj, String razaoSocial, String inscricaoEstadual){
        super (nome, endereco, telefone, email);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    @Override
    public String getDocumento() {
        return cnpj;
    }
}
