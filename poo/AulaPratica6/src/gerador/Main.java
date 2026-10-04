package gerador;

public class Main {

    public static void main(String[] args) {
        Gerador gerador = new Naturais();
        gerador.gerar(10);
        System.out.println(gerador);
        gerador = new Fibonacci();
        gerador.gerar(5);
        System.out.println(gerador);
        gerador = new Primos ();
        gerador.gerar(12);
        System.out.println(gerador);
        gerador = new Fatoriais();
        gerador.gerar(5);
        System.out.println(gerador);
        gerador = new Perfeitos ();
        gerador.gerar(100);
        System.out.println(gerador);
        gerador = new Quadrados();
        gerador.gerar(30);
        System.out.println(gerador);
        gerador = new Abundantes ();
        gerador.gerar(30);
        System.out.println(gerador);
    }
}