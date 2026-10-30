import java.util.Random;

/**
 * Instância do TSP: n cidades em um plano, com distâncias euclidianas.
 * As distâncias são pré-calculadas em uma matriz para acesso O(1).
 */
public class Instancia {
    private final double[] x;
    private final double[] y;
    private final double[][] dist;

    public Instancia(double[] x, double[] y) {
        this.x = x;
        this.y = y;
        int n = x.length;
        dist = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = Math.hypot(x[i] - x[j], y[i] - y[j]);
            }
        }
    }

    /** Gera n cidades aleatórias no quadrado [0,1000] x [0,1000]. A mesma semente gera a mesma instância. */
    public static Instancia aleatoria(int n, long semente) {
        Random rnd = new Random(semente);
        double[] x = new double[n];
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = rnd.nextDouble() * 1000;
            y[i] = rnd.nextDouble() * 1000;
        }
        return new Instancia(x, y);
    }

    /** Número de cidades. */
    public int n() {
        return x.length;
    }

    /** Distância entre as cidades i e j. */
    public double d(int i, int j) {
        return dist[i][j];
    }

    public double x(int i) {
        return x[i];
    }

    public double y(int i) {
        return y[i];
    }
}
