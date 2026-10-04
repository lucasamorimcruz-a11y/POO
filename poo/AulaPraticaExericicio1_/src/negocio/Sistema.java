package negocio;
import dados.ContaBancaria;
import dados.ContaCorrente;
import dados.ContaSalario;

import java.util.ArrayList;

public class Sistema {
    private int quantidade;
    private ArrayList<ContaBancaria> contasBancarias = new ArrayList<>();
    public void cadastrarConta (ContaBancaria conta){
        this.contasBancarias.add(conta);
        this.quantidade = this.contasBancarias.size();
    }
    public String realizarSaque (ContaBancaria conta, float valor){
        conta.sacar(valor);
        return this.obterExtrato(conta);

    }
    public boolean realizarDeposito (ContaCorrente conta, float valor){
        return conta.depositar(valor);
    }
    public boolean realizarDeposito (ContaSalario conta, float valor, int cnpj){
        return conta.depositar(valor, cnpj);
    }
    public ArrayList<ContaBancaria> getContas(){
        return this.contasBancarias;
    }
    public int getQuantidade(){
        return this.quantidade;
    }
    public ArrayList<ContaCorrente> getContasCorrentes(){
        ArrayList<ContaCorrente> contasCorrentes = new ArrayList<>();
        for (var conta : contasBancarias){
            if (conta instanceof  ContaCorrente){
                contasCorrentes.add((ContaCorrente)conta);
            }
        }
        return contasCorrentes;
    }
    public ArrayList<ContaSalario> getContasSalario(){
        ArrayList<ContaSalario> contasSalarios = new ArrayList<>();
        for (var conta : contasSalarios){
            if (conta instanceof ContaSalario){
                contasSalarios.add((ContaSalario) conta);
            }
        }
        return contasSalarios;
    }
    public String obterExtrato (ContaBancaria conta){
        return conta.gerarExtrato();
    }

}
