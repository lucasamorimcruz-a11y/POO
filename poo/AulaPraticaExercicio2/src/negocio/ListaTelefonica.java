package negocio;

import dados.Contato;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListaTelefonica {

    private List<Contato> contatos = new ArrayList<>();

    public void adicionarContato(Contato contato){
        contatos.add(contato);
    }

    public void removerContato(Contato contato){
        contatos.remove(contato);
    }

    public List<Contato> buscarContatos(char letra){
        List<Contato> resultado = new ArrayList<>();

        for (Contato contato : contatos) {
            if (Character.toLowerCase(contato.getNome().charAt(0))
                    == Character.toLowerCase(letra)) {

                resultado.add(contato);
            }
        }

        return resultado;
    }

    public Map<Character, List<Contato>> buscarContatos(){
        Map<Character, List<Contato>> resultado = new HashMap<>();

        for (Contato contato : contatos) {
            char letra = Character.toUpperCase(contato.getNome().charAt(0));

            resultado.putIfAbsent(letra, new ArrayList<>());
            resultado.get(letra).add(contato);
        }

        return resultado;
    }
}