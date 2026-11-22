public class Cliente {
    private String nome;
    private int codigoDeCadastro;
    private int quantidadeDeCupons;

    Cliente (){

    }
    Cliente (String nome, int codigoDeCadastro){
        this.nome = nome;
        this.codigoDeCadastro = codigoDeCadastro;
    }
    Cliente (int codigoDeCadastro, int quantidadeDeCupons){
        this.codigoDeCadastro = codigoDeCadastro;
        this.quantidadeDeCupons = quantidadeDeCupons;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigoDeCadastro() {
        return codigoDeCadastro;
    }

    public void setCodigoDeCadastro(int codigoDeCadastro) {
        this.codigoDeCadastro = codigoDeCadastro;
    }

    public int getQuantidadeDeCupons() {
        return quantidadeDeCupons;
    }

    public void setQuantidadeDeCupons(int quantidadeDeCupons) {
        this.quantidadeDeCupons = quantidadeDeCupons;
    }
    @Override public String toString() { return "Cliente{" + "nome='" + nome + '\'' + ", codigoDeCadastro=" + codigoDeCadastro + ", quantidadeDeCupons=" + quantidadeDeCupons + '}'; }
}
