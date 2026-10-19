package dados;

public class Pessoa {
    private String nome;
    private int idade;
    private float massa;
    private float altura;

    public Pessoa(){

    }

    public Pessoa(String nome, int idade, float massa, float altura) {
        this.nome = nome;
        this.idade = idade;
        this.massa = massa;
        this.altura = altura;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public float getMassa() {
        return massa;
    }

    public void setMassa(float massa) {
        this.massa = massa;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pessoa){
            Pessoa p = (Pessoa) obj;
            return this.idade == p.getIdade() && this.nome.equals(p.getNome()) && this.altura == p.getAltura() && this.massa == p.getMassa();
        }
        return false;
    }
    @Override
    public String toString() {
        return this.nome + " - " + this.altura + " - " +  this.massa + " - " + this.idade + '\n';
    }
}

