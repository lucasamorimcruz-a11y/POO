package apresentacao;

import dados.Contato;
import negocio.ListaTelefonica;

import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {
    private static ListaTelefonica listaTelefonica = new ListaTelefonica();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Seja bem-vindo! ");

        int escolha = -1;
        do {
            printarMenu();
            escolha = Integer.parseInt(scanner.nextLine());
            switch (escolha) {
                case 1:
                    adicionarContato();
                    break;
                case 2:
                    removerContato();
                    break;
                case 3:
                    exibirContatos();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (escolha != 0);
        System.out.println("Encerrando o programa...");
    }

    static void printarMenu() {
        System.out.println("1 - Adicionar contato");
        System.out.println("2 - Remover contato");
        System.out.println("3 - Exibir contatos");
        System.out.println("0 - Encerrar o programa");
    }

    public static void adicionarContato() {
        System.out.println("Qual o nome do contato a ser adicionado?");
        String nome = scanner.nextLine();
        if (nome.isEmpty()) {
            System.out.println("O nome não pode ser vazio!");
            return;
        }
        System.out.println("Qual o telefone do contato a ser adicionado?");
        int telefone = Integer.parseInt(scanner.nextLine());

        Contato contato = new Contato(nome, telefone);
        listaTelefonica.adicionaContato(contato);
        System.out.println("Feito!");
    }

    public static void removerContato() {

        System.out.println("Qual a letra inicial do contato?:");
        String s = scanner.nextLine().toUpperCase();
        if (listaTelefonica.buscarContatos(s.charAt(0)).size() > 0) {
            exibirContatos(s.charAt(0));

            System.out.println("Escolha um contato para remover:");
            int index = Integer.parseInt(scanner.nextLine());

            if (index < listaTelefonica.buscarContatos(s.charAt(0)).size()) {

                listaTelefonica.removeContato(listaTelefonica.buscarContatos(s.charAt(0)).get(index));

            }
        } else {
            System.out.println("Não existem contatos para serem removidos");
        }

    }
    public static void exibirContatos(char c) {
        for (int i = 0; i < listaTelefonica.buscarContatos(c).size(); i++) {
            System.out.println("Codigo: " + i);
            System.out.println(listaTelefonica.buscarContatos(c).get(i).toString());
        }

    }
    public static void exibirContatos() {
        Map<Character, List<Contato>> contatos = listaTelefonica.buscarContatos();
        if (contatos.isEmpty()) {
            System.out.println("Nenhum contato cadastrado.");
            return;
        }
        for (char letra = 'A'; letra <= 'Z'; letra++) {
            List<Contato> lista = contatos.get(letra);
            if (lista == null) continue;
            for (Contato c : lista) {
                System.out.println(c);
            }
        }
        for (Map.Entry<Character, List<Contato>> e : contatos.entrySet()) {
            char k = e.getKey();
            if (k < 'A' || k > 'Z') {
                for (Contato c : e.getValue()) {
                    System.out.println(c);
                }
            }
        }
    }
}