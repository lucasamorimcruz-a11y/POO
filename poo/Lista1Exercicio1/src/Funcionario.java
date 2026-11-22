public class Funcionario {
    private String nome;
    private int idade;
    private double salario;

    Funcionario (){

    }
    Funcionario (String nome, int idade){
        this.nome = nome;
        this.idade = idade;
    }
    Funcionario (String nome, double salario){
        this.nome = nome;
        this.salario = salario;
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

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    @Override public String toString() { return "Funcionario{" + "nome='" + nome + '\'' + ", idade=" + idade + ", salario=" + salario + '}'; }
}
