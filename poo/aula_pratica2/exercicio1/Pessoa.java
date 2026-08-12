package udesc.poo.aula_pratica2.exercicio1;

public class Pessoa {
    private String nome;
    private int idade;
    private double massa;
    private double altura;

    public double getAltura() {
        return altura;
    }

    public int getIdade() {
        return idade;
    }

    public double getMassa() {
        return massa;
    }

    public String getNome() {
        return nome;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setMassa(double massa) {
        this.massa = massa;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + ", idade: " + this.idade + ", altura: " + this.altura + ", massa: " + this.massa + ".\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        if (obj instanceof Pessoa){
            Pessoa p = (Pessoa) obj;
            if (this.altura == p.getAltura() && this.idade == p.getIdade() && this.massa == p.getMassa() && this.nome.equals(p.getNome())){
                return true;
            }
        }
        return false;
    }
    public double calcularIMC (){
        return this.massa / (this.altura * this.altura);
    }
    Pessoa (){

    }
    Pessoa (int idade, double altura, double massa, String nome){
        this.idade = idade;
        this.altura = altura;
        this.massa = massa;
        this.nome = nome;
    }

}
