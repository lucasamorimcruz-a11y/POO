public class Perfeitos extends Gerador {

    @Override
    public void gerar(int n) {
        lista.clear();
        int p = 2;
        while (lista.size() < n) {
            long mersenne = (1L << p) - 1;
            if (ehPrimo(mersenne)) {
                lista.add((int) ((1L << (p - 1)) * mersenne));
            }
            p++;
        }
    }

    private boolean ehPrimo(long x) {
        if (x < 2) return false;
        for (long i = 2; i * i <= x; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }
}