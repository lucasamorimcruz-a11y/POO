import java.util.Collections;
import java.util.List;

/**
 * Busca local 2-opt com estratégia de primeira melhora.
 *
 * TODO (Tarefa 3):
 *   Para cada par de posições i < j (com j >= i + 2):
 *     u = rota[i], v = rota[i+1], w = rota[j], z = rota[(j+1) % n]
 *     delta = d(u,w) + d(v,z) - d(u,v) - d(w,z)
 *     se delta < -EPS: inverta o trecho rota[i+1..j] e marque que houve melhora.
 *   Repita até que nenhuma troca melhore a rota.
 *
 * Dica: Collections.reverse(lista.subList(i + 1, j + 1)) inverte o trecho in-place.
 * Não recalcule rota.custo() dentro dos laços: use apenas o delta!
 */
public class DoisOpt implements BuscaLocal {
    private static final double EPS = 1e-9;

    @Override
    public Rota melhorar(Rota rota) {
        Rota resultado = rota.copia();
        List<Integer> cidades = resultado.getCidades();
        Instancia inst = resultado.getInstancia();
        int n = resultado.tamanho();

        if (n < 4) {
            return resultado;
        }

        boolean melhorou;

        do {
            melhorou = false;

            for (int i = 0; i < n - 1 && !melhorou; i++) {
                for (int j = i + 2; j < n; j++) {
                    if (i == 0 && j == n - 1) {
                        continue;
                    }

                    int u = cidades.get(i);
                    int v = cidades.get(i + 1);
                    int w = cidades.get(j);
                    int z = cidades.get((j + 1) % n);

                    double delta = inst.d(u, w)
                            + inst.d(v, z)
                            - inst.d(u, v)
                            - inst.d(w, z);

                    if (delta < -EPS) {
                        Collections.reverse(
                                cidades.subList(i + 1, j + 1)
                        );

                        melhorou = true;
                        break;
                    }
                }
            }
        } while (melhorou);

        return resultado;
    }
}
