package dados;

public class ContaSalario extends ContaBancaria {
    private int CNPJEmpresa;
    public ContaSalario(){
        super();
    }
    public boolean depositar (float valor, int cnpj){
        if (this.CNPJEmpresa == cnpj){
            this.saldo += valor;
            return true;
        }
        return false;
    }
    public String gerarExtrato(){
        return "Conta salário com o CNPJ da empresa:" + this.CNPJEmpresa + "\n"  + super.gerarExtrato();
    }
    public String toString(){
        return "Conta salario de CNP " + this.CNPJEmpresa + " " +super.toString();
    }
    public int getCNPJEmpresa() {
        return CNPJEmpresa;
    }

    public void setCNPJEmpresa(int CNPJEmpresa) {
        this.CNPJEmpresa = CNPJEmpresa;
    }
}
