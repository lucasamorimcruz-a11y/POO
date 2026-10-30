package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;

public class EditorTexto {

    public void gravaTexto(String caminho, List<String> lista) {
        FileWriter arquivo;
        try {

            arquivo = new FileWriter(caminho);
            for (int i = 0; i < lista.size(); i++) {
                arquivo.write(lista.get(i) + "\n");
            }
            arquivo.close();
        } catch (Exception e) {
            System.err.println("Erro ao manipular o arquivo");
            System.exit(0);
        }
    }

    public List<String> leTexto(String caminho) {
        List<String> lista = new LinkedList<String>();
        FileReader arquivo;
        BufferedReader lerArquivo;

        try {
            arquivo = new FileReader(caminho);
            lerArquivo = new BufferedReader(arquivo);
            String s = lerArquivo.readLine();
            while (s != null) {
                lista.add(s);
                s = lerArquivo.readLine();
            }
            arquivo.close();
        } catch (Exception e) {
            System.err.println("Erro ao manipular o arquivo");
            System.exit(0);
        }
        return lista;
    }

    public void gravaTexto(String caminho, String linha) {
        FileWriter arquivo;
        try {
            arquivo = new FileWriter(caminho, true);
            arquivo.write(linha + "\n");
            arquivo.close();
        } catch (Exception e) {
            System.err.println("Erro ao manipular o arquivo");
            System.exit(0);

        }
    }
}