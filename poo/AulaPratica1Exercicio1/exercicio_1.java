package udesc.poo.aula_pratica_1;

import java.util.Scanner;

public class exercicio_1 {
    /*
     * ESTUDANDO ANTERIORMENTE
     */
    public static void main(String[] args) {
        System.out.println("Insira os 5 valores");
        Scanner reader = new Scanner(System.in);
        int[] vals = new int[5];
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            vals[i] = Integer.parseInt(reader.nextLine());
            sum += vals[i];
        }
        System.out.println("A média dos valores é " + (sum / 5));
    }
}
