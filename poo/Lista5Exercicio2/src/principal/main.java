package principal;

import dados.*;
import negocio.Zoologico;

import java.util.Scanner;

public class main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Zoologico zoologico = new Zoologico();
    public static void main(String[] args) {
        int opcao;
        do {
            imprimirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    cadastrarAnimal();
                    break;
                case 2:
                    cadastrarViveiro();
                    break;
                case 3:
                    alocarAnimal();
                    break;
                case 4:
                    listarViveiros();
                    break;
                case 5:
                    listarAquarios();
                    break;
                case 0:
                    System.out.println("Encerrando");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        } while (opcao != 0);
    }
    private static void imprimirMenu() {
        System.out.println(" Zoologico ");
        System.out.println("1 - Cadastrar animal");
        System.out.println("2 - Cadastrar viveiro");
        System.out.println("3 - Alocar animal em viveiro");
        System.out.println("4 - Exibir viveiros");
        System.out.println("5 - Exibir aquarios");
        System.out.println("0 - Sair");
        System.out.print("Opcao: ");
    }

    private static void cadastrarAnimal() {
        System.out.println("Escolha:");
        System.out.println("1 - Animal comum");
        System.out.println("2 - Peixe");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        Animal animal;
        if (tipo == 1) {
            animal = new Animal();
        }
        else if (tipo == 2) {
            animal = new Peixe();
        }
        else {
            System.out.println("Tipo invalido!");
            return;
        }
        System.out.print("Nome: ");
        animal.setNome(scanner.nextLine());
        System.out.print("Cor: ");
        animal.setCor(scanner.nextLine());
        System.out.print("Especie: ");
        animal.setEspecie(scanner.nextLine());
        System.out.print("Idade: ");
        animal.setIdade(scanner.nextInt());
        System.out.print("Largura: ");
        animal.setLargura(scanner.nextFloat());
        System.out.print("Comprimento: ");
        animal.setComprimento(scanner.nextFloat());
        System.out.print("Altura: ");
        animal.setAltura(scanner.nextFloat());
        if (animal instanceof Peixe) {
            Peixe peixe = (Peixe) animal;
            System.out.print("Temperatura ideal: ");
            peixe.setTemperaturaIdeal(scanner.nextFloat());
        }
        scanner.nextLine();
        zoologico.cadastrarAnimais(animal);
        System.out.println("Animal cadastrado com sucesso!");
    }
    private static void cadastrarViveiro() {
        System.out.println("1 - Viveiro comum");
        System.out.println("2 - Aquario");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        Viveiro viveiro;
        if (tipo == 1) {
            viveiro = new Viveiro();
        }
        else if (tipo == 2) {
            viveiro = new Aquario();
        }
        else {
            System.out.println("Tipo invalido!");
            return;
        }
        System.out.print("Nome: ");
        viveiro.setNome(scanner.nextLine());
        System.out.print("Comprimento: ");
        viveiro.setComprimento(scanner.nextFloat());
        System.out.print("Largura: ");
        viveiro.setLargura(scanner.nextFloat());
        if (viveiro instanceof Aquario) {
            Aquario aquario = (Aquario) viveiro;
            System.out.print("Altura: ");
            aquario.setAltura(scanner.nextFloat());
            System.out.print("Temperatura: ");
            aquario.setTemperatura(scanner.nextFloat());
        }
        scanner.nextLine();
        zoologico.cadastrarViveiro(viveiro);
        System.out.println("Viveiro cadastrado com sucesso!");
    }
    private static void alocarAnimal() {
        if (zoologico.getAnimais().isEmpty()) {
            System.out.println("Nao existem animais cadastrados.");
            return;
        }
        if (zoologico.getSoViveiro().isEmpty()) {
            System.out.println("Nao existem viveiros cadastrados.");
            return;
        }
        System.out.println("Animais:");
        for (int i = 0; i < zoologico.getAnimais().size(); i++) {
            Animal animal = zoologico.getAnimais().get(i);
            System.out.println(i + " - " + animal.getNome() + " (" + animal.getEspecie() + ")");
        }
        System.out.print("Escolha o animal: ");
        int indexAnimal = scanner.nextInt();
        if (indexAnimal < 0 || indexAnimal >= zoologico.getAnimais().size()) {
            System.out.println("Animal invalido!");
            return;
        }
        Animal animal = zoologico.getAnimais().get(indexAnimal);
        System.out.println("Viveiros:");
        for (int i = 0; i < zoologico.getSoViveiro().size(); i++) {
            Viveiro viveiro = zoologico.getSoViveiro().get(i);
            System.out.println(i + " - " + viveiro.getNome());
        }
        System.out.print("Escolha o viveiro: ");
        int indexViveiro = scanner.nextInt();
        if (indexViveiro < 0 || indexViveiro >= zoologico.getSoViveiro().size()) {
            System.out.println("Viveiro invalido!");
            return;
        }
        Viveiro viveiro = zoologico.getSoViveiro().get(indexViveiro);
        if (zoologico.alocarAnimal(animal, viveiro)) {
            System.out.println("Animal alocado com sucesso!");
        } else {
            System.out.println("Nao foi possivel alocar o animal.");
        }
    }
    private static void listarViveiros() {
        if (zoologico.getSoViveiro().isEmpty()) {
            System.out.println("Nao existem viveiros cadastrados.");
            return;
        }
        for (Viveiro viveiro : zoologico.getSoViveiro()) {
            System.out.println("Nome: " + viveiro.getNome());
            if (viveiro instanceof Aquario) {
                Aquario aquario = (Aquario) viveiro;
                System.out.println("Tipo: Aquario");
                System.out.println("Temperatura: " + aquario.getTemperatura());
            } else {
                System.out.println("Tipo: Viveiro");
            }
            if (viveiro.getAnimais().isEmpty()) {
                System.out.println("Viveiro vazio.");
            } else {
                System.out.println("Animais:");
                for (Animal animal : viveiro.getAnimais()) {
                    imprimirAnimal(animal);
                }
            }
        }
    }

    private static void listarAquarios() {
        if (zoologico.getSoAquarios().isEmpty()) {
            System.out.println("Nao existem aquarios cadastrados.");
            return;
        }
        for (Aquario aquario : zoologico.getSoAquarios()) {
            System.out.println("Nome: " + aquario.getNome());
            System.out.println("Temperatura: " + aquario.getTemperatura());
            if (aquario.getAnimais().isEmpty()) {
                System.out.println("Aquario vazio.");
            } else {
                for (Animal animal : aquario.getAnimais()) {
                    imprimirAnimal(animal);
                }
            }
        }
    }
    private static void imprimirAnimal(Animal animal) {
        System.out.println("Animal:");
        System.out.println("Nome: " + animal.getNome());
        System.out.println("Cor: " + animal.getCor());
        System.out.println("Especie: " + animal.getEspecie());
        if (animal instanceof Peixe) {
            Peixe peixe = (Peixe) animal;
            System.out.println("Temperatura ideal: " + peixe.getTemperaturaIdeal());
        }
    }
}