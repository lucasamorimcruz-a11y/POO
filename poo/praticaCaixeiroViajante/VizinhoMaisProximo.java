/**
 * Heurística construtiva do vizinho mais próximo.
 *
 * TODO (Tarefa 2):
 *   1. Comece na cidade 0 e marque-a como visitada.
 *   2. Enquanto houver cidades não visitadas, vá para a não visitada mais próxima
 *      da cidade atual e adicione-a à rota.
 *   3. Retorne a rota (a volta para a cidade 0 é implícita na Rota).
 */
public class VizinhoMaisProximo implements Construtor {

    @Override
    public Rota construir(Instancia inst) {
        int n = inst.n();
        Rota rota = new Rota(inst);

        if (n == 0) {
            return rota;
        }

        boolean[] visitada = new boolean[n];
        int atual = 0;

        rota.adicionar(atual);
        visitada[atual] = true;

        for (int i = 1; i < n; i++) {
            int proxima = -1;
            double menorDistancia = Double.MAX_VALUE;

            for (int j = 0; j < n; j++) {
                if (!visitada[j] && inst.d(atual, j) < menorDistancia) {
                    menorDistancia = inst.d(atual, j);
                    proxima = j;
                }
            }

            rota.adicionar(proxima);
            visitada[proxima] = true;
            atual = proxima;
        }

        return rota;
    }
}
