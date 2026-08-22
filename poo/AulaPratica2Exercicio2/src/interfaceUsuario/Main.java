package interfaceUsuario;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        loop();
    }

    public static void loop() {
        Scanner scanner = new Scanner(System.in);
        SistemaPetShop sistema = new SistemaPetShop();
        int escolha;
        System.out.println("=================================");
        System.out.println("   BEM-VINDO AO SISTEMA PETSHOP");
        System.out.println("=================================");
        do {
            System.out.println("\n---------- MENU ----------");
            System.out.println("1 - Cadastrar veterinário");
            System.out.println("2 - Mostrar veterinários");
            System.out.println("3 - Cadastrar endereço do veterinário");
            System.out.println("4 - Cadastrar animal");
            System.out.println("5 - Mostrar animais");
            System.out.println("6 - Cadastrar dono");
            System.out.println("7 - Cadastrar endereço do dono");
            System.out.println("0 - Sair");
            System.out.println("--------------------------");
            System.out.print("Escolha uma opção: ");
            escolha = Integer.parseInt(scanner.nextLine());
            switch (escolha) {
                case 1:
                    sistema.cadastrarVeterinario();
                    break;
                case 2:
                    sistema.mostrarVeterinario();
                    break;
                case 3:
                    sistema.cadastrarEnderecoVeterinario();
                    break;
                case 4:
                    sistema.cadastrarAnimal();
                    break;
                case 5:
                    sistema.mostrarAnimais();
                    break;
                case 6:
                    sistema.cadastrarDono();
                    break;
                case 7:
                    sistema.cadastrarEnderecoDoDono();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (escolha != 0);

    }
}