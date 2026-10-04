package apresentacao;
import dados.*;
import negocio.Sistema;
import java.util.Scanner;

public class main {
    private static Sistema sistema = new Sistema();
    private static Scanner scanner = new Scanner(System.in);
    static void main() {
        int escolha = -1;
        while (escolha != 0){
            imprimirMenu();
            escolha = scanner.nextInt();
            switch (escolha){
                case 0:
                    break;
                case 1:
                    cadastrarConta();
                    break;
                case 2:
                    realizarSaque();
                    break;
                case 3:
                    realizarDeposito();
                    break;
                case 4:
                    mostrarExtrato();
                    break;
            }
        }
    }
    private static ContaCorrente novaContaCorrente (){
        ContaCorrente conta = new ContaCorrente();
        System.out.println("Digite o CPF desejado:");
        conta.setCPF(scanner.nextLine());
        return conta;
    }
    private static ContaSalario novaContaSalario(){
        ContaSalario conta = new ContaSalario();
        System.out.println("Digite o CPF desejado:");
        conta.setCPF(scanner.nextLine());
        System.out.println("Digite o CNPJ desejado: ");
        conta.setCNPJEmpresa(scanner.nextInt());
        return conta;
    }
    private static void cadastrarConta(){
        System.out.println("Digite o tipo de conta que deseja cadastrar");
        System.out.println("1 - Conta Corrente");
        System.out.println("2 - Conta Salário");
        int escolha = scanner.nextInt();
        switch(escolha){
            case 1:
                sistema.cadastrarConta(novaContaCorrente());
                break;
            case 2:
                sistema.cadastrarConta(novaContaSalario());
                break;
            default:
                System.out.println("Escolha não é válida!");
                break;
        }
    }
    private static void mostrarContas(){
        for (int i = 0; i < sistema.getQuantidade(); i++){
            System.out.println("Conta: " + i + sistema.getContas().get(i).toString());
        }
    }
    private static ContaBancaria escolherContaBancaria (){
        mostrarContas();
        System.out.println("Escolha uma conta");
        int conta = scanner.nextInt();
        if (conta < sistema.getQuantidade()){
            return sistema.getContas().get(conta);
        }
        return null;
    }
    private static void realizarSaque (){
        ContaBancaria conta = escolherContaBancaria();
        if (conta != null){
            System.out.println("Digite o valor pra ser sacado");
            float valor = scanner.nextFloat();
            System.out.println(sistema.realizarSaque(conta, valor));
        }
    }
    private static void realizarDeposito (){
        ContaBancaria conta = escolherContaBancaria();
        if (conta != null){
            if (conta instanceof ContaCorrente){
                System.out.println("Digite o valor que deve  ser depositado");
                int valor = scanner.nextInt();
                sistema.realizarDeposito((ContaCorrente) (conta), valor);
                System.out.println("Deposito realizada !!");
                System.out.println(sistema.obterExtrato((ContaCorrente) (conta)));
            } else {
                System.out.println("Digite o valor que deve  ser depositado");
                int valor = scanner.nextInt();
                System.out.println("Digite o cnpj da empresa que vai depositar");
                int cnpj = scanner.nextInt();
                if (sistema.realizarDeposito((ContaSalario) (conta), valor, cnpj)){
                    System.out.println("Deposito realizado!");
                    System.out.println(sistema.obterExtrato((ContaSalario) (conta)));
                } else{
                    System.out.println("Falha ao realizar ato");
                }

            }
        }
    }
    private static void mostrarExtrato (){
        ContaBancaria conta = escolherContaBancaria();
        if (conta != null){
            System.out.println(sistema.obterExtrato(conta));
        }
    }
    public static void imprimirMenu (){
        System.out.println( "Escolha uma opcao:");
        System.out.println( "0 - SAIR");
        System.out.println( "1 - CADASTRAR CONTA");
        System.out.println( "2 - REALIZAR SAQUE");
        System.out.println( "3 - REALIZAR DEPOSITO");
        System.out.println( "4 - MOSTRAR EXTRATO");

    }
}
