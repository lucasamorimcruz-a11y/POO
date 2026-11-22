package sistema;

import dados.*;
import negocio.Clinica;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    static Clinica clinica = new Clinica();
    static Scanner scanner = new Scanner(System.in);

    static ArrayList<Dono> donos = new ArrayList<>();
    static ArrayList<Animal> animais = new ArrayList<>();
    static ArrayList<Veterinario> veterinarios = new ArrayList<>();

    public static void main(String[] args) {
        int opcao = -1;
        do {
            exibirMenu();
            opcao = Integer.parseInt(scanner.nextLine());
            switch (opcao) {
                case 1 -> adicionarDono();
                case 2 -> adicionarAnimal();
                case 3 -> adicionarVeterinario();
                case 4 -> adicionarConsulta();
                case 5 -> listarConsultas();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    static void exibirMenu() {
        System.out.println("\n===== CLÍNICA VETERINÁRIA =====");
        System.out.println("1 - Cadastrar dono");
        System.out.println("2 - Cadastrar animal");
        System.out.println("3 - Cadastrar veterinário");
        System.out.println("4 - Cadastrar consulta");
        System.out.println("5 - Listar consultas por data");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    static void adicionarDono() {
        Dono dono = new Dono();
        System.out.println("Nome do dono:");
        dono.setNome(scanner.nextLine());
        System.out.println("CPF do dono:");
        dono.setCpf(scanner.nextLine());

        clinica.cadastrarDono(dono);
        donos.add(dono);
        System.out.println("Dono cadastrado!");
    }

    static void adicionarAnimal() {
        System.out.println("CPF do dono:");
        String cpf = scanner.nextLine();
        Dono dono = null;
        for (Dono d : donos) {
            if (d.getCpf().equals(cpf)) dono = d;
        }
        if (dono == null) {
            System.out.println("Dono não encontrado. Cadastre-o primeiro.");
            return;
        }

        System.out.println("Nome do animal:");
        String nome = scanner.nextLine();
        System.out.println("Espécie:");
        String especie = scanner.nextLine();
        System.out.println("Idade:");
        int idade;
        try {
            idade = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Idade inválida.");
            return;
        }

        Animal animal = new Animal(nome, especie, idade, dono);
        dono.cadastrarAnimal(animal);
        clinica.cadastrarAnimal(animal);
        animais.add(animal);
        System.out.println("Animal cadastrado!");
    }

    static void adicionarVeterinario() {
        System.out.println("Nome do veterinário:");
        String nome = scanner.nextLine();
        System.out.println("CPF do veterinário:");
        String cpf = scanner.nextLine();
        System.out.println("Salário:");
        double salario;
        try {
            salario = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Salário inválido.");
            return;
        }

        Veterinario vet = new Veterinario(nome, cpf, salario);
        clinica.cadastrarVeterinario(vet);
        veterinarios.add(vet);
        System.out.println("Veterinário cadastrado!");
    }

    static void adicionarConsulta() {
        System.out.println("CPF do veterinário:");
        String cpfVet = scanner.nextLine();
        Veterinario vet = null;
        for (Veterinario v : veterinarios) {
            if (v.getCpf().equals(cpfVet)) vet = v;
        }
        if (vet == null) {
            System.out.println("Veterinário não encontrado.");
            return;
        }

        System.out.println("CPF do dono do animal:");
        String cpfDono = scanner.nextLine();
        System.out.println("Nome do animal:");
        String nomeAnimal = scanner.nextLine();
        Animal animal = null;
        for (Animal a : animais) {
            if (a.getDono().getCpf().equals(cpfDono)
                    && a.getNome().equalsIgnoreCase(nomeAnimal)) {
                animal = a;
            }
        }
        if (animal == null) {
            System.out.println("Animal não encontrado.");
            return;
        }

        try {
            System.out.println("Data (aaaa-mm-dd):");
            LocalDate data = LocalDate.parse(scanner.nextLine());
            System.out.println("Horário (hh:mm):");
            LocalTime horario = LocalTime.parse(scanner.nextLine());

            Consulta consulta = new Consulta();
            consulta.setCodigo(clinica.buscarConsultasPorData(data).size() + 1);
            consulta.setData(data);
            consulta.setHorario(horario);
            consulta.alocarVeterinario(vet);
            consulta.alocarAnimal(animal);

            clinica.marcarConsulta(consulta);
            System.out.println("Consulta marcada!");
        } catch (DateTimeParseException e) {
            System.out.println("Data ou horário em formato inválido.");
        }
    }

    static void listarConsultas() {
        System.out.println("Data (aaaa-mm-dd):");
        try {
            LocalDate data = LocalDate.parse(scanner.nextLine());
            ArrayList<Consulta> lista = clinica.buscarConsultasPorData(data);
            if (lista.isEmpty()) {
                System.out.println("Nenhuma consulta nessa data.");
                return;
            }
            for (Consulta c : lista) {
                System.out.println(c.getHorario()
                        + " | Vet: " + c.getVeterinario().getNome()
                        + " | Animal: " + c.getPaciente().getNome()
                        + " | Dono: " + c.getPaciente().getDono().getNome());
            }
        } catch (DateTimeParseException e) {
            System.out.println("Data inválida.");
        }
    }
}