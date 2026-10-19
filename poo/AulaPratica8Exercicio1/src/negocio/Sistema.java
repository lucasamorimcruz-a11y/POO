package negocio;
import dados.Pessoa;
import persistencia.PessoaDAO;
import java.util.List;
public class Sistema {
    private PessoaDAO pessoaDAO;
    public void adicionarPessoa (Pessoa p){
            pessoaDAO.insert(p);
    }
    public void removerPessoa (Pessoa p){
        pessoaDAO.delete(p);
    }
    public List<Pessoa> getList(){
        return pessoaDAO.getAll();
    }

}

