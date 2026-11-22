public class Estagiario extends Funcionario{
    private double salarioMinimo;
    private double auxilio;

    public Estagiario(String nome, String cpf, double salarioMinimo, double auxilio) {
        super(nome, cpf);
        this.salarioMinimo = salarioMinimo;
        this.auxilio = auxilio;
    }

    @Override
    public double calcularSalario (){
        return this.salarioMinimo + this.auxilio;
    }

    public double getSalarioMinimo() {
        return salarioMinimo;
    }

    public void setSalarioMinimo(double salarioMinimo) {
        this.salarioMinimo = salarioMinimo;
    }

    public double getAuxilio() {
        return auxilio;
    }

    public void setAuxilio(double auxilio) {
        this.auxilio = auxilio;
    }
}
