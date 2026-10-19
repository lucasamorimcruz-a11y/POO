package dados;
import java.time.*;
import java.util.*;

public class Motorista extends PessoaFisica {
    private Cnh cnh;
    private LocalDate dataDeAdmissao;
    private TipoDeVinculo vinculo;
    private Situacao situacao = Situacao.ATIVO;
    private List<TipoDeVeiculo> tiposDeVeiculosDisponiveis = new ArrayList<>();

    public Motorista(String nome, Endereco endereco, String telefone, String email, String cpf, Cnh cnh, LocalDate dataDeAdmissao, TipoDeVinculo vinculo) {
        super(nome, endereco, telefone, email, cpf);
        this.cnh = cnh;
        this.dataDeAdmissao = dataDeAdmissao;
        this.vinculo = vinculo;
    }

    public Cnh getCnh() { return cnh; }
    public void setCnh(Cnh cnh) { this.cnh = cnh; }
    public LocalDate getDataDeAdmissao() { return dataDeAdmissao; }
    public void setDataDeAdmissao(LocalDate dataDeAdmissao) { this.dataDeAdmissao = dataDeAdmissao; }
    public TipoDeVinculo getVinculo() { return vinculo; }
    public void setVinculo(TipoDeVinculo vinculo) { this.vinculo = vinculo; }
    public Situacao getSituacao() { return situacao; }
    public void setSituacao(Situacao situacao) { this.situacao = situacao; }
    public List<TipoDeVeiculo> getTiposDeVeiculosDisponiveis() { return tiposDeVeiculosDisponiveis; }
    public void setTiposDeVeiculosDisponiveis(List<TipoDeVeiculo> tiposDeVeiculosDisponiveis) { this.tiposDeVeiculosDisponiveis = tiposDeVeiculosDisponiveis; }

    public void habilitar(TipoDeVeiculo tipo) { if (!tiposDeVeiculosDisponiveis.contains(tipo)) tiposDeVeiculosDisponiveis.add(tipo); }

    public boolean podeDirigir(Veiculo v) {
        return situacao == Situacao.ATIVO && cnh.estaValida() && tiposDeVeiculosDisponiveis.contains(v.getTipoDeVeiculo());
    }

    @Override
    public String toString() { return getNome() + " (" + situacao + ")"; }
}
