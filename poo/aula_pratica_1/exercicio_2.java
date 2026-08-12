package udesc.poo.aula_pratica_1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class exercicio_2 {
    static class Pessoa {
        String nome;
        int idade;

        Pessoa(String nome, int idade) {
            this.nome = nome;
            this.idade = idade;
        }
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        Pessoa[] pessoas = new Pessoa[5];
        for (int i = 0; i < 5; i++) {
            System.out.println("Qual o nome dessa pessoa?");
            String nome = reader.nextLine();
            System.out.println("Qual a idade dessa pessoa?");
            int idade = reader.nextInt();
            reader.nextLine();

            pessoas[i] = new Pessoa(nome, idade);
        }
        Arrays.sort(pessoas, new Comparator<Pessoa>() {
            public int compare(Pessoa p1, Pessoa p2) {
                return p2.idade - p1.idade;
            }
        });
        System.out.println("Idade das pessoas em ordem decrescente:");
        for (int i = 0; i < 5; i++) {
            System.out.println(pessoas[i].nome + " tem a seguinte idade: " + pessoas[i].idade);
        }

    }
}
