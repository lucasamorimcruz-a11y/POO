package persistencia;
import dados.Pessoa;
import java.util.List;

public class PessoaDAO {

    public void insert(Pessoa p) {
        ArquivoPessoaDAO.salvaPessoaArquivo(p);
    }

    public void delete(Pessoa p) {
        List<Pessoa> pessoas = ArquivoPessoaDAO.lePessoasArquivo();
        pessoas.remove(p);
        ArquivoPessoaDAO dao = new ArquivoPessoaDAO();
        dao.salvaPessoasArquivo(pessoas);
    }
    public List<Pessoa> getAll() {
        return ArquivoPessoaDAO.lePessoasArquivo();
    }
}
