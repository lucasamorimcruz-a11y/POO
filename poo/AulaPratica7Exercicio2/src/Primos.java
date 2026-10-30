public class Primos extends Gerador {
    @Override
    public void gerar(int quantidade) {
        lista.clear();
        int n = 2;
        while (lista.size() < quantidade) {
            boolean primo = true;
            for (int j = 2; j * j <= n; j++) {
                if (n % j == 0) {
                    primo = false;
                    break;
                }
            }
            if (primo) lista.add(n);
            n++;
        }
    }
}
