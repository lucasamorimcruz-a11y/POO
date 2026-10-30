/**
 * Vizinho mais próximo para o VRP.
 *
 * TODO (Tarefa 2):
 *   Enquanto houver clientes não atendidos:
 *     - crie uma rota vazia; atual = 0 (depósito)
 *     - repita: escolha o cliente c NÃO ATENDIDO mais próximo de 'atual' que CABE na rota:
 *         carga + q(c) <= Q
 *         distância até agora + d(atual, c) + d(c, 0) <= L    (precisa conseguir voltar ao depósito!)
 *       se não existir, feche a rota; senão, adicione c e faça atual = c
 *     - adicione a rota à solução
 *
 * Dica: mantenha 'carga' e 'distância até agora' (sem a volta) em variáveis locais,
 * em vez de chamar rota.carga() / rota.distancia() a cada teste.
 */
public class VizinhoMaisProximo implements Construtor {

    @Override
    public Solucao construir(Instancia inst) {
        Solucao solucao = new Solucao(inst);

        boolean[] atendidos = new boolean[inst.nClientes() + 1];
        int restantes = inst.nClientes();

        while (restantes > 0) {
            Rota rota = new Rota(inst);

            int atual = 0;
            int carga = 0;
            double distancia = 0;

            while (true) {
                int melhor = -1;
                double menorDistancia = Double.MAX_VALUE;

                for (int c = 1; c <= inst.nClientes(); c++) {
                    if (atendidos[c])
                        continue;

                    if (carga + inst.q(c) > inst.Q())
                        continue;

                    double d = inst.d(atual, c);

                    if (distancia + d + inst.d(c, 0) > inst.L())
                        continue;

                    if (d < menorDistancia) {
                        menorDistancia = d;
                        melhor = c;
                    }
                }

                if (melhor == -1)
                    break;

                rota.adicionar(melhor);
                atendidos[melhor] = true;

                restantes--;
                carga += inst.q(melhor);
                distancia += menorDistancia;

                atual = melhor;
            }

            solucao.adicionar(rota);
        }

        return solucao;
    }
}