package apresentacao;

import dados.Pessoa;

import java.util.Scanner;

public class Main {
    private static Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        int escolha = -1;
        do {
            exibirMenu();
            escolha = Integer.parseInt(s.nextLine());
            switch (escolha) {
                case 1:
                    cadastrarPessoa(novaPessoa());
                    break;
                case 2:
                    removerPessoa(escolherPessoa());
                    break;
                case 3:
                    mostrarPessoas();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Escolha inválida");
                    break;
            }
        } while (escolha != 4);
    }


    public static Pessoa novaPessoa(){
        Pessoa p = new Pessoa();
        System.out.println("digite o nome");
        p.setNome(s.next());
        System.out.println("Digite a idade");
        p.setIdade(s.nextInt());
        System.out.println("Digite a altura");
        p.setAltura(s.nextFloat());
        System.out.println("Digite a massa");
        p.setMassa(s.nextFloat());
        return p;
    }
    public static void exibirMenu() {
        System.out.println(" 1 - cadastrar pessoa");
        System.out.println(" 2 - remover pessoa");
        System.out.println(" 3 - mostrar pessoas");
        System.out.println(" 4 - para encerrar");
    }

    public static void cadastrarPessoa(Pessoa p) {

    }

    public static void removerPessoa() {

    }

    public static void mostrarPessoas() {

    }
}
