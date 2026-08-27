package apresentacao;

import dados.Cidade;
import dados.Cliente;
import dados.Reserva;
import negocio.ReservaPassagem;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private ReservaPassagem reservaPassagem;
    private Scanner scanner;

    public Main() {
        reservaPassagem = new ReservaPassagem();
        scanner = new Scanner(System.in);
    }
    public static void main(String[] args) {
        Main principal = new Main();
        principal.executar();
    }
    public void executar() {
        int opcao;
        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    cadastrarCidade();
                    break;
                case 2:
                    cadastrarCliente();
                    break;
                case 3:
                    fazerReserva();
                    break;
                case 4:
                    mostrarReservas();
                    break;
                case 5:
                    reservaPassagem.mostrarClientes();
                    break;
                case 6:
                    reservaPassagem.mostrarCidades();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }
    private void exibirMenu() {
        System.out.println();
        System.out.println("===== SISTEMA DE VIAGEM =====");
        System.out.println("1 - Cadastrar Cidade");
        System.out.println("2 - Cadastrar Cliente");
        System.out.println("3 - Fazer Reserva");
        System.out.println("4 - Mostrar Reservas de um Cliente");
        System.out.println("5 - Mostrar Clientes");
        System.out.println("6 - Mostrar Cidades");
        System.out.println("0 - Sair");
    }
    public void fazerReserva() {
        ArrayList<Cliente> clientes = reservaPassagem.mostrarClientes();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado. Cadastre um cliente primeiro.");
            return;
        }
        ArrayList<Cidade> cidades = reservaPassagem.mostrarCidades();
        if (cidades.size() < 2) {
            System.out.println("É necessário ter ao menos 2 cidades cadastradas.");
            return;
        }
        System.out.print("Informe o CPF do cliente: ");
        String cpf = scanner.nextLine();
        Cliente clienteEncontrado = null;
        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);
            if (c.getCPF().equals(cpf)) {
                clienteEncontrado = c;
            }
        }
        if (clienteEncontrado == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        System.out.println("--- Dados da reserva de ida ---");
        Reserva ida = criarReserva(cidades);
        reservaPassagem.reservarIda(clienteEncontrado, ida);
        System.out.println("Reserva de ida criada com sucesso!");
        System.out.println(ida);
        System.out.print("Deseja cadastrar volta? (S/N): ");
        String resp = scanner.nextLine();
        if (resp.equals("S") || resp.equals("s")) {
            System.out.println("--- Dados da reserva de volta ---");
            Reserva volta = criarReserva(cidades);
            reservaPassagem.reservarVolta(clienteEncontrado, ida, volta);
            System.out.println("Reserva de volta vinculada com sucesso!");
            System.out.println(volta);
        }
    }

    private Reserva criarReserva(ArrayList<Cidade> cidades) {
        Reserva reserva = new Reserva();
        System.out.println("Cidades disponíveis:");
        for (int i = 0; i < cidades.size(); i++) {
            System.out.println(i + " - " + cidades.get(i));
        }
        System.out.print("Número da cidade de origem: ");
        int idxOrigem = Integer.parseInt(scanner.nextLine());
        System.out.print("Número da cidade de destino: ");
        int idxDestino = Integer.parseInt(scanner.nextLine());
        reserva.setOrigem(cidades.get(idxOrigem));
        reserva.setDestino(cidades.get(idxDestino));
        System.out.print("Número da reserva: ");
        reserva.setNumReserva(Integer.parseInt(scanner.nextLine()));
        System.out.print("Data do voo: ");
        reserva.setDataVoo(scanner.nextLine());
        System.out.print("Hora do voo: ");
        reserva.setHoraVoo(scanner.nextLine());
        System.out.print("Classe do voo (Economica/Executiva): ");
        reserva.setClasseVoo(scanner.nextLine());
        System.out.print("Poltrona: ");
        reserva.setPoltrona(Integer.parseInt(scanner.nextLine()));
        System.out.print("Preço: ");
        reserva.setPreco(Float.parseFloat(scanner.nextLine()));
        return reserva;
    }

    public void cadastrarCliente() {
        Cliente cliente = new Cliente();
        System.out.print("CPF: ");
        cliente.setCPF(scanner.nextLine());
        System.out.print("Nome: ");
        cliente.setNome(scanner.nextLine());
        System.out.print("Endereço: ");
        cliente.setEndereco(scanner.nextLine());
        System.out.print("Telefone: ");
        cliente.setTelefone(Integer.parseInt(scanner.nextLine()));
        reservaPassagem.cadastrarCliente(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    public void cadastrarCidade() {
        Cidade cidade = new Cidade();
        System.out.print("Nome da cidade: ");
        cidade.setNome(scanner.nextLine());
        System.out.print("Estado: ");
        cidade.setEstado(scanner.nextLine());
        reservaPassagem.cadastrarCidade(cidade);
        System.out.println("Cidade cadastrada com sucesso.");
    }

    public void mostrarReservas() {
        System.out.print("Informe o CPF do cliente: ");
        String cpf = scanner.nextLine();
        ArrayList<Reserva> reservas = reservaPassagem.mostrarReservas(cpf);
        if (reservas == null) {
            System.out.println("Cliente não encontrado.");
        } else if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva encontrada para este cliente.");
        }
    }
}