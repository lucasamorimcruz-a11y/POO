public class Abundantes extends Gerador {
    @Override
    public void gerar(int quantidade) {
        lista.clear();
        int n = 1;
        while (lista.size() < quantidade) {
            n++;
            int soma = 0;
            for (int j = 1; j < n; j++) if (n % j == 0) soma += j;
            if (soma > n) lista.add(n);
        }
    }
}
