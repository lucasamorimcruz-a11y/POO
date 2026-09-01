import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Principal {
    public static List<Integer> tabuadaDe(int n) {
        List<Integer> tabuada = new ArrayList<Integer>();
        for (int i = 1; i <= 10; i++) {
            tabuada.add(i * n);
        }
        return tabuada;
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> todasAsTabuadas;
        todasAsTabuadas = new HashMap<Integer, List<Integer>>();
        for (int i = 1; i <= 10; i++) {
            todasAsTabuadas.put(i, tabuadaDe(i));
        }
        todasAsTabuadas.forEach((chave, valor) -> {
            System.out.println("Tabuada de " + chave + " :");
            valor.forEach((numero) -> {
                System.out.print(numero + " ");
            });
            System.out.println("\n");
        });
    }
}
