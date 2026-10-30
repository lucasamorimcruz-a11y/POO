import java.util.ArrayList;
import java.util.List;

/**
 * Enumera todas as permutações começando pela cidade 0 e devolve a melhor.
 * Complexidade O(n!): use apenas com n pequeno (até ~11).
 */
public class ForcaBruta implements Construtor {
    private Rota melhor;

    @Override
    public Rota construir(Instancia inst) {
        melhor = null;
        List<Integer> parcial = new ArrayList<>();
        parcial.add(0);
        boolean[] usada = new boolean[inst.n()];
        usada[0] = true;
        enumerar(inst, parcial, usada);
        return melhor;
    }

    private void enumerar(Instancia inst, List<Integer> parcial, boolean[] usada) {
        if (parcial.size() == inst.n()) {
            Rota t = new Rota(inst, parcial);
            if (melhor == null || t.custo() < melhor.custo()) {
                melhor = t;
            }
            return;
        }
        for (int c = 1; c < inst.n(); c++) {
            if (!usada[c]) {
                usada[c] = true;
                parcial.add(c);
                enumerar(inst, parcial, usada);
                parcial.remove(parcial.size() - 1);
                usada[c] = false;
            }
        }
    }
}
