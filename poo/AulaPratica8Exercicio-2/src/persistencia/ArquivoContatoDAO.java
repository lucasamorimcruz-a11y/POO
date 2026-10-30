package persistencia;

import dados.Contato;

import java.util.ArrayList;
import java.util.List;

public class ArquivoContatoDAO {
    private String caminho = "C:\\Users\\lucas\\IdeaProjects\\POO\\poo\\AulaPratica8Exercicio-2\\contatos.csv";
    private String toCSV(Contato contato) {
        return contato.getNome() + "," + contato.getTelefone();
    }

    private Contato fromCSV(String string) {
        String[] dados = string.split(",");
        Contato c = new Contato();
        c.setNome(dados[0]);
        c.setTelefone(Integer.parseInt(dados[1]));
        return c;
    }
    public List<Contato> leContatos() {
        List<Contato> contatos = new ArrayList<>();
        EditorTexto et = new EditorTexto();
        for (String l : et.leTexto(caminho)) {
                contatos.add(fromCSV(l));
        }
        return contatos;
    }

    public void salvaContatos(List<Contato> contatos) {
        EditorTexto et = new EditorTexto();
        List<String> linhas = new ArrayList<>();
        for (Contato contato : contatos) {
            linhas.add(toCSV(contato));
        }
        et.gravaTexto(caminho, linhas);
    }
    public void salvarContato(Contato contato) {
        EditorTexto et = new EditorTexto();
        et.gravaTexto(caminho, toCSV(contato));
    }
}
