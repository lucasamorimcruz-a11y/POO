
public class Main {

    public static void main(String[] args) {

        System.out.println("Abundantes:");
        teste(new Abundantes());
        System.out.println("Fatorial:");
        teste(new Fatoriais());
        System.out.println("Fibonacci");
        teste(new Fibonacci());
        System.out.println("Naturais");
        teste(new Naturais());
        System.out.println("Primos");
        teste(new Primos());
        System.out.println("Perfeitos");
        teste(new Perfeitos());
        System.out.println("Quadrados");
        teste(new Quadrados());

    }

    public static void teste(Gerador g) {
        g.gerar(5);
        for (int x : g.getLista()) {
            System.out.print(x + " " + '\n');
        }

        System.out.println("Sortear: " + g.sortear());
        System.out.println("Somatorio: " + g.somatorio());
        System.out.println("Media aritmetica: " + g.mediaAritmetica());
        System.out.println("Media geometrica: " + g.mediaGeometrica());
        System.out.println("Variancia: " + g.variancia());
        System.out.println("Desvio padrão: " + g.desvioPadrao());
        System.out.println("Amplitude:" + g.amplitude() + '\n');

    }

}