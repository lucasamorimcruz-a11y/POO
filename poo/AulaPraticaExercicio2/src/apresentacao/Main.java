package apresentacao;

import dados.Contato;
import negocio.ListaTelefonica;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    ListaTelefonica listaTelefonica = new ListaTelefonica();
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Main main = new Main();

        int opcao = 0;

        while (opcao != 5) {

            System.out.println("\n===== LISTA TELEFÔNICA =====");
            System.out.println("1 - Adicionar contato");
            System.out.println("2 - Remover contato");
            System.out.println("3 - Exibir contatos");
            System.out.println("4 - Buscar contatos por letra");
            System.out.println("5 - Sair");
            System.out.println("============================");
            System.out.print("Escolha uma opção: ");

            opcao = main.scanner.nextInt();
            main.scanner.nextLine();

            switch (opcao) {

                case 1:
                    main.adicionarContato();
                    break;

                case 2:
                    main.removerContato();
                    break;

                case 3:
                    main.exibirContatos();
                    break;

                case 4:
                    System.out.println("Digite a letra: ");
                    char letra = main.scanner.nextLine().charAt(0);
                    main.exibirContatos(letra);
                    break;

                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
    public Contato novoContato() {

        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("Telefone: ");
        String telefone = scanner.nextLine();
        return new Contato(nome, telefone);
    }

    public void adicionarContato() {
        Contato contato = novoContato();
        this.listaTelefonica.adicionarContato(contato);
        System.out.println("Contato adicionado!");
    }

    public void removerContato() {
        System.out.println("Qual contato você deseja remover?");
        Map<Character, List<Contato>> contatos =
                this.listaTelefonica.buscarContatos();
        int index = 0;
        for (List<Contato> lista : contatos.values()) {
            for (Contato contato : lista) {
                System.out.println(index + " - " + contato);
                index++;
            }
        }
        if (index == 0) {
            System.out.println("Não existem contatos.");
            return;
        }
        int escolha = scanner.nextInt();
        scanner.nextLine();
        index = 0;
        for (List<Contato> lista : contatos.values()) {
            for (Contato contato : lista) {
                if (index == escolha) {
                    this.listaTelefonica.removerContato(contato);
                    System.out.println("Contato removido.");
                    return;
                }
                index++;
            }
        }
        System.out.println("Contato inválido.");
    }

    public void exibirContatos() {
        Map<Character, List<Contato>> contatos =
                this.listaTelefonica.buscarContatos();
        for (Map.Entry<Character, List<Contato>> entrada : contatos.entrySet()) {
            System.out.println(entrada.getKey() + ":");
            for (Contato contato : entrada.getValue()) {
                System.out.println("  " + contato);
            }
        }
    }

    public void exibirContatos(char letra) {
        List<Contato> contatos =
                this.listaTelefonica.buscarContatos(letra);
        for (Contato contato : contatos) {
            System.out.println(contato);
        }
    }
}