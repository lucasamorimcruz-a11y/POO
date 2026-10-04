package dados;

public class ContaBancaria {
    private String CPF;
    protected float saldo;
    public ContaBancaria(){
        this.saldo = 0;
    }
    public float sacar (float valor){
        this.saldo -= valor;
        return valor;
    }
    public String gerarExtrato (){
        return "O saldo é de" + this.saldo;
    }
    public String toString(){
        return "CPF: " + this.CPF;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
