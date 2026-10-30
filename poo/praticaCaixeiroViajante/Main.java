/**
 * Compilar:  javac *.java
 * Executar:  java Main
 */
public class Main {

    public static void main(String[] args) {
        // n = 9: dá para comparar com o ótimo (força bruta)
        Instancia pequena = Instancia.aleatoria(9, 58);
        System.out.println("===== n = 9 =====");
        Rota otimo = executar("forca_bruta_9", pequena, new ForcaBruta(), null, null);
        executar("vmp_9", pequena, new VizinhoMaisProximo(), null, otimo);
        executar("vmp_2opt_9", pequena, new VizinhoMaisProximo(), new DoisOpt(), otimo);

        for (int n : new int[] {100, 1000}) {
            Instancia inst = Instancia.aleatoria(n, 42);
            System.out.println("===== n = " + n + " =====");
            executar("vmp_" + n, inst, new VizinhoMaisProximo(), null, null);
            executar("vmp_2opt_" + n, inst, new VizinhoMaisProximo(), new DoisOpt(), null);
        }
    }

    /**
     * Constrói uma rota com o construtor, aplica a busca local (se não for null),
     * imprime custo, tempo e gap em relação a 'referencia' (se não for null) e salva um SVG.
     */
    static Rota executar(String nome, Instancia inst, Construtor construtor, BuscaLocal busca, Rota referencia) {
        try {
            long inicio = System.nanoTime();
            Rota t = construtor.construir(inst);
            if (busca != null) {
                t = busca.melhorar(t);
            }
            double ms = (System.nanoTime() - inicio) / 1e6;

            if (!t.ehValido()) {
                System.out.printf("%-16s ROTA INVÁLIDA: %s%n", nome, t);
                return null;
            }
            String gap = "";
            if (referencia != null) {
                gap = String.format("  gap = %5.2f%%", 100 * (t.custo() - referencia.custo()) / referencia.custo());
            }
            System.out.printf("%-16s custo = %10.2f  tempo = %8.2f ms%s%n", nome, t.custo(), ms, gap);
            Svg.salvar(t, nome + ".svg");
            return t;
        } catch (UnsupportedOperationException e) {
            System.out.printf("%-16s (pendente) %s%n", nome, e.getMessage());
            return null;
        }
    }
}
