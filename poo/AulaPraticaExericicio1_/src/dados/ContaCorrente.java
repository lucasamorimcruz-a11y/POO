package dados;

public class ContaCorrente extends ContaBancaria {
    public ContaCorrente (){
        super();
    }
    public boolean depositar (float valor){
        this.saldo += valor;
        return true;
    }
    public String gerarExtrato(){
        return "Conta corrente: " + "CPF : " + this.getCPF() + "\n" + super.gerarExtrato();
    }
    public String toString(){
        return "Conta corrente: " + super.toString();
    }

}
