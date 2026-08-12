package udesc.poo.aula_pratica2.petshop;

public class Endereco {
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String CEP;

    Endereco(){

    }

    public String getRua() {
        return rua;
    }

    public int getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCEP() {
        return CEP;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }
}
