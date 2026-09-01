public class Livro {
    private String ISBN;
    private String nome;
    private int quantidadeDeVendas;
    private double preco;

    Livro (){

    }
    Livro (String ISBN, String nome){
        this.ISBN = ISBN;
        this.nome = nome;
    }
    Livro (String nome, int quantidadeDeVendas){
        this.nome = nome;
        this.quantidadeDeVendas = quantidadeDeVendas;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidadeDeVendas() {
        return quantidadeDeVendas;
    }

    public void setQuantidadeDeVendas(int quantidadeDeVendas) {
        this.quantidadeDeVendas = quantidadeDeVendas;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Livro){
            Livro l = (Livro) obj;
            return this.ISBN.equals(l.getISBN());
        }
        return false;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "ISBN='" + ISBN + '\'' +
                ", nome='" + nome + '\'' +
                ", quantidadeDeVendas=" + quantidadeDeVendas +
                ", preco=" + preco +
                '}';
    }
}
