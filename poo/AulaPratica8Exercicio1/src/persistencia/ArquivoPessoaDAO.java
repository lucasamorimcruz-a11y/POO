package persistencia;
import dados.Pessoa;
import java.util.ArrayList;
import java.util.List;

public class ArquivoPessoaDAO {
    private static String caminho = "/home/udesc/Downloads/AulaPratica8Exercicio1/pessoas.csv";
    private static EditorTexto arquivo = new EditorTexto();



    private String toCSV(Pessoa pessoa) {
        String p = "";
        p += pessoa.getNome() + " , ";
        p += pessoa.getIdade() + " , ";
        p += pessoa.getMassa() + " ,  ";
        p += pessoa.getAltura() + " , ";
        return p;
    }
    private Pessoa fromCSV (String linhaCSV){
        String[] atributos = linhaCSV.split(",");
        Pessoa pessoa = new Pessoa ();
        pessoa.setNome(atributos[0]);
        pessoa.setIdade(Integer.parseInt(atributos[1]));
        pessoa.setAltura (Float.parseFloat(atributos[2]));
        pessoa.setMassa(Float.parseFloat(atributos[3]));
        return pessoa;
    }
    private List<String> listaPessoaToString (List<Pessoa>pessoas) {
        List<String> arquivo = new ArrayList<>();
        for (var pessoa : pessoas){
                arquivo.add(toCSV(pessoa));
            }
        return arquivo;
    }
    private List<Pessoa> stringToListaPessoa(List<String> arquivo){
        List<Pessoa> pessoas = new ArrayList<>();
        for (var l : arquivo){
            pessoas.add(fromCSV(l));
        }
        return pessoas;
    }
    public static List<Pessoa> lePessoasArquivo(){
        return stringToListaPessoa(arquivo.leTexto(caminho));
    }
    public void salvaPessoasArquivo (List<Pessoa> pessoas){
        arquivo.gravaTexto(caminho, listaPessoaToString(pessoas));
    }
    public static void salvaPessoaArquivo(Pessoa pessoa){
        arquivo.gravaTexto(caminho, toCSV(pessoa));
    }

}


