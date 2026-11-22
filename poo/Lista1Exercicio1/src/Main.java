import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente ("Lucas", 0);
        Cliente cliente2 = new Cliente(0,0);
        Cliente cliente3 = new Cliente ("Ana", 1);
        Cliente cliente4 = new Cliente ("Joao", 2);

        Funcionario funcionario1 = new Funcionario("Juca", 2000.3);
        Funcionario funcionario2 = new Funcionario ("Seu joão", 90);

        Livro livro1 = new Livro ("978-65-5525-044-2", "Fausto");
        Livro livro2 = new Livro ("Diário de um Banana", 100000000);
        Livro livro3 = new Livro ("978-85-359-0277-5", "Dom Casmurro");
        Livro livro4 = new Livro ("978-85-635-6092-6", "As afinidades eletivas ");

        cliente1.setNome("Anao");
        int codigoDeCadastroCleinte = cliente2.getCodigoDeCadastro();
        System.out.println(codigoDeCadastroCleinte);
        livro2.setNome("Ulysses");

        livro1.setPreco(50);
        livro2.setPreco(35);
        livro3.setPreco(40);
        livro4.setPreco(30);
        livro2.setISBN(("SEM ISBN"));

        System.out.println(livro1.toString());
        System.out.println(livro2.toString());
        System.out.println(livro3.toString());
        System.out.println(livro4.toString());

        System.out.println(funcionario1.toString());
        System.out.println(funcionario2.toString());

        System.out.println(cliente1.toString());
        System.out.println(cliente2.toString());
        System.out.println(cliente3.toString());
        System.out.println(cliente4.toString());


        RegistroVenda registro1 = new RegistroVenda(cliente1, funcionario1);
        registro1.setCodigoDaVenda("001");
        registro1.setLivrosVendidos(new ArrayList<>());
        registro1.getLivrosVendidos().add(livro1);
        registro1.getLivrosVendidos().add(livro1);
        registro1.getLivrosVendidos().add(livro2);
        registro1.getLivrosVendidos().add(livro2);


        RegistroVenda registro2 = new RegistroVenda(cliente2, funcionario2);
        registro2.setCodigoDaVenda("002");
        registro2.setLivrosVendidos(new ArrayList<>());
        registro2.getLivrosVendidos().add(livro3);



        RegistroVenda registro3 = new RegistroVenda(cliente3, funcionario1);
        registro3.setCodigoDaVenda("003");
        registro3.setLivrosVendidos(new ArrayList<>());
        registro3.getLivrosVendidos().add(livro2);
        registro3.getLivrosVendidos().add(livro2);
        registro3.getLivrosVendidos().add(livro4);


        RegistroVenda registro4 = new RegistroVenda(cliente4, funcionario2);
        registro4.setCodigoDaVenda("004");
        registro4.setLivrosVendidos(new ArrayList<>());
        registro4.getLivrosVendidos().add(livro1);
        registro4.getLivrosVendidos().add(livro3);
        registro4.getLivrosVendidos().add(livro4);

        System.out.println("Registro 1 possui livros repetidos? " + registro1.possuiLivrosRepetidos()
                + (registro1.possuiLivrosRepetidos() ? " -> cliente tem direito ao desconto" : " -> cliente nao tem direito ao desconto"));
        System.out.println("Registro 2 possui livros repetidos? " + registro2.possuiLivrosRepetidos()
                + (registro2.possuiLivrosRepetidos() ? " -> cliente tem direito ao desconto" : " -> cliente nao tem direito ao desconto"));
        System.out.println("Registro 3 possui livros repetidos? " + registro3.possuiLivrosRepetidos()
                + (registro3.possuiLivrosRepetidos() ? " -> cliente tem direito ao desconto" : " -> cliente nao tem direito ao desconto"));
        System.out.println("Registro 4 possui livros repetidos? " + registro4.possuiLivrosRepetidos()
                + (registro4.possuiLivrosRepetidos() ? " -> cliente tem direito ao desconto" : " -> cliente nao tem direito ao desconto"));

        registro1.calcularValorFinal();
        registro2.calcularValorFinal();
        registro3.calcularValorFinal();
        registro4.calcularValorFinal();

        System.out.println("Registro 1 - valor total: " + registro1.calcularValorVenda() + " | valor final: " + registro1.getValorFinal());
        System.out.println("Registro 2 - valor total: " + registro2.calcularValorVenda() + " | valor final: " + registro2.getValorFinal());
        System.out.println("Registro 3 - valor total: " + registro3.calcularValorVenda() + " | valor final: " + registro3.getValorFinal());
        System.out.println("Registro 4 - valor total: " + registro4.calcularValorVenda() + " | valor final: " + registro4.getValorFinal());
    }
}
