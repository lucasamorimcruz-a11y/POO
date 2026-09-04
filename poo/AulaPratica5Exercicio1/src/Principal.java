import java.util.*;

public class Principal {
    public static List<Integer> tabuadaDe(int n) {
        List<Integer> tabuada = new ArrayList<Integer>();
        for (int i = 1; i <= 10; i++) {
            tabuada.add(i * n);
        }
        return tabuada;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, List<Integer>> todasAsTabuadas;
        todasAsTabuadas = new HashMap<Integer, List<Integer>>();
        for (int i = 1; i <= 10; i++) {
            todasAsTabuadas.put(i, tabuadaDe(i));
        }
        int choice;
        System.out.println("");
        choice = Integer.parseInt(scanner.nextLine());
        todasAsTabuadas.forEach((chave, valor) -> {
            System.out.println("Tabuada de " + chave + " :");
            valor.forEach((numero) -> {
                System.out.print(numero + " ");
            });
            System.out.println("\n");
        });
    }
}