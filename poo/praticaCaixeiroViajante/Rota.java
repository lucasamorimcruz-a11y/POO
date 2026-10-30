import java.util.ArrayList;
import java.util.List;

/**
 * Uma solução do TSP: a ordem em que as cidades são visitadas.
 * A volta da última cidade para a primeira é implícita (não aparece na lista).
 *
 * Exemplo: cidades = [0, 3, 1, 2] representa a rota 0 -> 3 -> 1 -> 2 -> 0.
 */
public class Rota {
    private final Instancia inst;
    private final List<Integer> cidades;

    public Rota(Instancia inst) {
        this(inst, new ArrayList<>());
    }

    public Rota(Instancia inst, List<Integer> cidades) {
        this.inst = inst;
        this.cidades = new ArrayList<>(cidades);
    }

    public Instancia getInstancia() {
        return inst;
    }

    /** Lista (modificável) com a ordem de visita. */
    public List<Integer> getCidades() {
        return cidades;
    }

    public int tamanho() {
        return cidades.size();
    }

    /** Cidade na posição pos da rota. */
    public int get(int pos) {
        return cidades.get(pos);
    }

    public void adicionar(int cidade) {
        cidades.add(cidade);
    }

    /**
     * TODO (Tarefa 1): retorne a distância total da rota,
     * INCLUINDO a volta da última cidade para a primeira.
     * Use inst.d(i, j) para obter a distância entre duas cidades.
     */
    public double custo() {
        double total = 0;
        int n = cidades.size();
        if (n <= 1) {
            return total;
        }
        for (int i = 0; i < n; i++) {
            int atual = cidades.get(i);
            int proxima = cidades.get((i + 1) % n);

            total += inst.d(atual, proxima);
        }
        return total;
    }

    /** Verifica se a rota visita cada cidade exatamente uma vez. */
    public boolean ehValido() {
        if (cidades.size() != inst.n()) {
            return false;
        }
        boolean[] visitada = new boolean[inst.n()];
        for (int c : cidades) {
            if (c < 0 || c >= inst.n() || visitada[c]) {
                return false;
            }
            visitada[c] = true;
        }
        return true;
    }

    public Rota copia() {
        return new Rota(inst, cidades);
    }

    @Override
    public String toString() {
        return cidades.toString();
    }
}
