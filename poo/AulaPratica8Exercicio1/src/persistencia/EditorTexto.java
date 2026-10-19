package persistencia;

import java.rmi.server.ExportException;

import java.util.LinkedList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class EditorTexto {
    public void gravaTexto(String caminho, List<String> dados) {
        FileWriter arquivo;
        try {
            arquivo = new FileWriter(caminho);
            for (var s : dados){
                arquivo.write(s + '\n');
            }
            arquivo.close();
        } catch (Exception e) {
            System.err.println("Erro ao manipular o arquivo");
            System.exit(0);
        }
    }
    public void gravaTexto (String caminho, String linha){
        FileWriter arquivo;
        try{
            arquivo = new FileWriter(caminho, true);
            arquivo.write(linha  + '\n');
            arquivo.close();
        } catch (Exception e){
            System.err.println("Erro ao manipular o arquivo");
            System.exit(0);
        }

    }
    public List<String> leTexto(String caminho) {
        List<String> dados = new ArrayList<>();
        try {
            FileReader arquivo = new FileReader(caminho);
            Scanner lerArquivo = new Scanner(arquivo);
            while (lerArquivo.hasNextLine()) {
                String s = lerArquivo.nextLine();
                dados.add(s);
            }
            lerArquivo.close();
            arquivo.close();
        } catch (Exception e) {
            System.err.println("Erro ao manipular o arquivo: " + e.getMessage());
        }
        return dados;
    }
}
