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
        System.out.println("1 - Mostrar todas as tabuadas");
        System.out.println("2 - Mostrar uma tabuada");
        choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1) {
            todasAsTabuadas.forEach((chave, valor) -> {
                System.out.println("Tabuada de " + chave + " :");
                valor.forEach((numero) -> {
                    System.out.print(numero + " ");
                });
                System.out.println("\n");
            });
        } else if (choice == 2) {
            System.out.println("Digite a tabuada que deseja:");
            int tabuadaEscolhida = Integer.parseInt(scanner.nextLine());

            List<Integer> tabuada = todasAsTabuadas.get(tabuadaEscolhida);

            System.out.println("Tabuada de " + tabuadaEscolhida + " :");
            tabuada.forEach((numero) -> {
                System.out.print(numero + " ");
            });
            System.out.println();
        }
    }
}

