package udesc.poo.aula_pratica2.petshop;

import java.util.Scanner;

public class SistemaPetShop {
    private Veterinario[] veterinarios = new Veterinario[50];
    private int quantidade_de_veterinarios;
    private Scanner scanner = new Scanner(System.in);
    public SistemaPetShop(){

    }
    public void cadastrarVeterinario(){
        System.out.println("Qual o nome deste veterinário?");
        String nome = scanner.nextLine();
        System.out.println("Qual o salário deste veterinário?");
        double salario = Double.parseDouble(scanner.nextLine());
        Veterinario _veterinario = new Veterinario();
        _veterinario.setNome(nome);
        _veterinario.setSalario(salario);
        veterinarios[quantidade_de_veterinarios] = _veterinario;
        quantidade_de_veterinarios++;
    }
    public void mostrarVeterinarios(){
        for (int i = 0; i < quantidade_de_veterinarios; i++){
            System.out.println(veterinarios[i].getNome() + ", com a id: " + (i+1));
        }
    }
    public void cadastrarEnderecoVeterinario(){
        for (int i = 0; i < quantidade_de_veterinarios; i++){
            System.out.println(veterinarios[i].getNome() + ", com a id: " + (i+1));
        }
        System.out.println("Qual dos veterinarios mostrados acima o novo endereço deve ser cadastro? (Insira o id)");
        int _idVeterinario = scanner.nextInt();
        _idVeterinario--;
        System.out.println("Insira a rua desse veterinário.");
        String _rua = scanner.nextLine();
        System.out.println("Insira o bairro desse veterinário.");
        String _bairro = scanner.nextLine();
        System.out.println("Insira a cidade desse veterinário.");
        String _cidade= scanner.nextLine();
        System.out.println("Insira o estado desse veterinário.");
        String _estado = scanner.nextLine();
        System.out.println("Insira o CEP desse veterinário.");
        String _cep = scanner.nextLine();
        Endereco _endereco = new Endereco();
        _endereco.setRua(_rua);
        _endereco.setBairro(_bairro);
        _endereco.setCidade(_cidade);
        _endereco.setEstado(_estado);
        _endereco.setCEP(_cep);
        this.veterinarios[_idVeterinario].setEndereco(_endereco);
    }
    public void cadastrarAnimal(){
        for (int i = 0; i < quantidade_de_veterinarios; i++){
            System.out.println(veterinarios[i].getNome() + ", com a id: " + (i+1));
        }
        System.out.println("Qual dos veterinarios mostrados acima o novo animal deve ser cadastro? (Insira o id)");
        int _idVeterinario = scanner.nextInt();
        _idVeterinario--;
        System.out.println("Qual o nome do animal a ser cadastrado?");
        String _nomeAnimal = scanner.nextLine();
        System.out.println("Qual a espécie do animal a ser cadastrado?");
        String _especieAnimal = scanner.nextLine();
        System.out.println("Qual a descrição do animal a ser cadastrado?");
        String _descricaoAnimal = scanner.nextLine();
        Animal _animal = new Animal();
        _animal.setNome(_nomeAnimal);
        _animal.setEspecie(_especieAnimal);
        _animal.setDescricao(_descricaoAnimal);
        veterinarios[_idVeterinario].adicionarAnimal(_animal);
    }
    public void mostrarAnimal(){

    }
    public void cadastrarDono(){

    }
    public void cadastrarEnderecoDono(){

    }


}
