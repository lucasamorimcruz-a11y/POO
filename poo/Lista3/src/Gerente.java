public class Gerente extends Funcionario {
    private double salarioMensal;
    private int horasExtras;
    private double valorHoraExtra;

    public Gerente(String nome, String cpf, double salarioMensal, int horasExtras, double valorHoraExtra) {
        super(nome, cpf);
        this.salarioMensal = salarioMensal;
        this.horasExtras = horasExtras;
        this.valorHoraExtra = valorHoraExtra;
    }

    @Override
    public double calcularSalario() {
        return salarioMensal + (horasExtras * valorHoraExtra);
    }

    public double getSalarioMensal() {
        return salarioMensal;
    }

    public void setSalarioMensal(double salarioMensal) {
        this.salarioMensal = salarioMensal;
    }

    public int getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(int horasExtras) {
        this.horasExtras = horasExtras;
    }

    public double getValorHoraExtra() {
        return valorHoraExtra;
    }

    public void setValorHoraExtra(double valorHoraExtra) {
        this.valorHoraExtra = valorHoraExtra;
    }
}
