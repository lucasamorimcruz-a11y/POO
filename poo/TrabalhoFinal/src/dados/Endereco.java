package dados;

public class Endereco {
    private int numeroDoLocal;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;

    public Endereco(int numeroDoLocal, String rua, String bairro, String cidade, String estado) {
        this.numeroDoLocal = numeroDoLocal;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public int getNumeroDoLocal() {
        return numeroDoLocal;
    }

    public void setNumeroDoLocal(int numeroDoLocal) {
        this.numeroDoLocal = numeroDoLocal;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
