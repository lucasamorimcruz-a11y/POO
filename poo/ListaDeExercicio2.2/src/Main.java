import java.util.ArrayList;
import java.util.Scanner;
class Main{
    static void main() {
        Matriz<Integer> matriz = new Matriz<>(5,5);
        System.out.println("Seja bem-vindo! Preencha a matriz: ");
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                System.out.println( "Linha: " +  (i+1) + " e " + "Coluna: " +  (j + 1));
                int escolha = scanner.nextInt();
                matriz.set(escolha,i,j);
            }
        }
        ArrayList<Integer> Lista = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                Lista.add(matriz.get(i,j));
            }
        }
        Lista.sort(null);
        int contador = 0;
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                matriz.set(Lista.get(contador), i,j);
                contador++;
            }
        }
        System.out.println(matriz);
        System.out.println("As linhas principais são:");
        for (int i =0; i < 5; i++){
            System.out.println(matriz.getLinha(i));
        }
        System.out.println();
        System.out.println("As colunas principais são");
        for (int j = 0; j < 5; j++){
            System.out.println(matriz.getColuna(j));
        }
        System.out.println("Os elementos da diagonal são:");
        System.out.println(matriz.getDiagonalPrincipal());

    }


}