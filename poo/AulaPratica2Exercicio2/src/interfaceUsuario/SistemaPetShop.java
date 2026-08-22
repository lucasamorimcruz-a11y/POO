package interfaceUsuario;
import dados.*;

import java.util.Scanner;

public class SistemaPetShop {
    Scanner scanner = new Scanner(System.in);
    private Veterinario[] _veterinarios = new Veterinario[50];
    private int _quantidadeDeVeterinarios = 0;
    SistemaPetShop(){

    }
    void cadastrarVeterinario(){
        System.out.println("Qual o nome deste novo funcionário?");
        String _nome = scanner.nextLine();
        System.out.println("Qual o salario do veterinário " + _nome);
        double _salario = Double.parseDouble(scanner.nextLine());
        Veterinario _veterinario = new Veterinario(_nome, _salario);
        _veterinarios[_quantidadeDeVeterinarios] = _veterinario;
        _quantidadeDeVeterinarios++;
    }
    void mostrarVeterinario(){
        int index = 1;
        for (int i = 0; i < _quantidadeDeVeterinarios; i++){
            Veterinario _veterinario = _veterinarios[i];
            System.out.println("ID: " + index);
            System.out.println("Nome: " + _veterinario.getNome());
            System.out.println("Salário: " + _veterinario.getSalario());
            index++;
        }
    }
    void cadastrarEnderecoVeterinario(){
        System.out.println("Qual veterinário você deseja cadastrar um novo endereço?");
        int index = 1;
        for (int i = 0; i < _quantidadeDeVeterinarios; i++){
            Veterinario _veterinario = _veterinarios[i];
            System.out.println("ID: " + index + " Nome: " + _veterinario.getNome());
            index++;
        }
        int _escolha = Integer.parseInt(scanner.nextLine());
        Veterinario _veterinario = _veterinarios[--_escolha];
        System.out.println("Qual a rua deve ser cadastrada?");
        String _rua = scanner.nextLine();
        System.out.println("Qual numero deve ser cadastrado?");
        int _numero =  Integer.parseInt(scanner.nextLine());
        System.out.println("Qual bairro deve ser cadastrado?");
        String _bairro = scanner.nextLine();
        System.out.println("Qual cidade deve ser cadastrada?");
        String _cidade = scanner.nextLine();
        System.out.println("Qual estado deve ser cadastrado?");
        String _estado = scanner.nextLine();
        System.out.println("Qual CEP deve ser cadastrado?");
        String _CEP = scanner.nextLine();
        Endereco _endereco = new Endereco(_rua, _numero, _bairro, _cidade, _estado, _CEP);
        _veterinario.setEndereco(_endereco);
    }

    void cadastrarAnimal(){
        int index = 1;
        for (int i = 0; i < _quantidadeDeVeterinarios; i++){
            Veterinario _veterinario = _veterinarios[i];
            System.out.println("ID: " + index + " Nome: " + _veterinario.getNome());
            index++;
        }
        System.out.println("Insira o id do veterinário que deseja cadastrar um novo animal.");
        int _escolha = Integer.parseInt(scanner.nextLine());
        Veterinario _veterinario = _veterinarios[_escolha - 1];
        System.out.println("Qual o nome do animal?");
        String _nome = scanner.nextLine();
        System.out.println("Qual a espécie do animal?");
        String _especie = scanner.nextLine();
        System.out.println("Qual a descrição do animal?");
        String _descricao = scanner.nextLine();
        Animal _animal = new Animal(_nome, _especie, _descricao);
        _veterinario.cadastrarAnimal(_animal);
    }
    void mostrarAnimais(){
        int index = 1;
        for (int i = 0; i < _quantidadeDeVeterinarios; i++){
            Veterinario _veterinario = _veterinarios[i];
            System.out.println("ID: " + index + " Nome: " + _veterinario.getNome());
            index++;
        }
        System.out.println("Qual veterinário você deseja verificar os animais?");
        int _escolha = Integer.parseInt(scanner.nextLine());
        Veterinario _veterinarioEscolhido = _veterinarios[_escolha - 1];
        index = 1;
        for (int i = 0; i < _veterinarioEscolhido.get_quantidadeDeAnimais(); i++){
            Animal _animal = _veterinarioEscolhido.getAnimais()[i];
            System.out.println("ID: " + index + _animal.toString());
            index++;
        }

    }
    void cadastrarDono(){
        System.out.println("Qual dos seguintes animais você deseja cadastrar?");
        int index = 1;
        for (int i = 0; i < _quantidadeDeVeterinarios; i++){
            Veterinario _veterinario = _veterinarios[i];
            for (int j = 0; j < _veterinario.get_quantidadeDeAnimais(); j++){
                Animal _animal = _veterinario.getAnimais()[j];
                System.out.println("ID : " + index + _animal.toString());
                index++;
            }
        }
        Animal _animalEscolhido = new Animal();
        int _escolha = Integer.parseInt(scanner.nextLine());
        index = 1;
        for (int i = 0; i < _quantidadeDeVeterinarios; i++){
            Veterinario _veterinario = _veterinarios[i];
            for (int j = 0; j < _veterinario.get_quantidadeDeAnimais(); j++){
                Animal _animal = _veterinario.getAnimais()[j];
                if (_escolha == index){
                    _animalEscolhido = _animal;
                }
                index++;
            }
        }
        System.out.println("Qual o nome do dono do animal?");
        String _nome = scanner.nextLine();
        System.out.println("Qual o CPF do dono do animal?");
        String _CPF = scanner.nextLine();
        Dono _dono = new Dono(_nome, _CPF);
        _animalEscolhido.setDono(_dono);
    }
    void cadastrarEnderecoDoDono(){
        System.out.println("Qual dos seguintes animais você deseja cadastrar o enderco do dono?");
        int index = 1;
        for (int i = 0; i < _quantidadeDeVeterinarios; i++){
            Veterinario _veterinario = _veterinarios[i];
            for (int j = 0; j < _veterinario.get_quantidadeDeAnimais(); j++){
                Animal _animal = _veterinario.getAnimais()[j];
                System.out.println("ID : " + index + _animal.toString());
                index++;
            }
        }
        Animal _animalEscolhido = new Animal();
        int _escolha = Integer.parseInt(scanner.nextLine());
        index = 1;
        for (int i = 0; i < _quantidadeDeVeterinarios; i++){
            Veterinario _veterinario = _veterinarios[i];
            for (int j = 0; j < _veterinario.get_quantidadeDeAnimais(); j++){
                Animal _animal = _veterinario.getAnimais()[j];
                if (_escolha == index){
                    _animalEscolhido = _animal;
                }
                index++;
            }
        }
        System.out.println("Qual a rua deve ser cadastrada?");
        String _rua = scanner.nextLine();
        System.out.println("Qual numero deve ser cadastrado?");
        int _numero =  Integer.parseInt(scanner.nextLine());
        System.out.println("Qual bairro deve ser cadastrado?");
        String _bairro = scanner.nextLine();
        System.out.println("Qual cidade deve ser cadastrada?");
        String _cidade = scanner.nextLine();
        System.out.println("Qual estado deve ser cadastrado?");
        String _estado = scanner.nextLine();
        System.out.println("Qual CEP deve ser cadastrado?");
        String _CEP = scanner.nextLine();
        Endereco _endereco = new Endereco(_rua, _numero, _bairro, _cidade, _estado, _CEP);
        _animalEscolhido.getDono().setEndereco(_endereco);
    }
}