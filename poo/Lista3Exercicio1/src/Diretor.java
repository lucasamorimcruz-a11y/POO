public class Diretor extends Funcionario{
    private double salarioMensal;
    private double bonus;

    public Diretor(String nome, String cpf, double salarioMensal, double bonus) {
        super (nome, cpf);
        this.salarioMensal = salarioMensal;
        this.bonus = bonus;
    }

    @Override
    public double calcularSalario (){
        return salarioMensal + bonus;
    }

    public double getSalarioMensal() {
        return salarioMensal;
    }

    public void setSalarioMensal(double salarioMensal) {
        this.salarioMensal = salarioMensal;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
}
