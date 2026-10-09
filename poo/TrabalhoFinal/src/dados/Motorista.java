package dados;

import enums.Situacao;
import enums.TipoDeVeiculo;
import enums.TipoDeVinculo;

import java.util.ArrayList;

public class Motorista  {
    private PessoaFisica pessoaFisica;
    private Cnh cnh;
    private String dataDeAdmissao;
    private TipoDeVinculo vinculo;
    private Situacao situacao;
    private ArrayList<TipoDeVeiculo> tipoDeVeiculosDisponiveis;

    public Motorista (PessoaFisica pessoaFisica, Cnh cnh, String dataDeAdmissao, TipoDeVinculo vinculo, Situacao situacao, ArrayList<TipoDeVeiculo> tipoDeVeiculosDisponiveis){
        this.pessoaFisica = pessoaFisica;
        this.cnh = cnh;
        this.dataDeAdmissao = dataDeAdmissao;
        this.vinculo = vinculo;
        this.situacao = situacao;
        this.tipoDeVeiculosDisponiveis = tipoDeVeiculosDisponiveis;
    }

    public boolean podeDirigir (Veiculo veiculo){
        for (var tipoDeVeiculo : tipoDeVeiculosDisponiveis){
            if (tipoDeVeiculo.equals(veiculo.getTipoDeVeiculo())){
                return true;
            }
        }
        return false;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    public Cnh getCnh() {
        return cnh;
    }

    public void setCnh(Cnh cnh) {
        this.cnh = cnh;
    }

    public String getDataDeAdmissao() {
        return dataDeAdmissao;
    }

    public void setDataDeAdmissao(String dataDeAdmissao) {
        this.dataDeAdmissao = dataDeAdmissao;
    }

    public TipoDeVinculo getVinculo() {
        return vinculo;
    }

    public void setVinculo(TipoDeVinculo vinculo) {
        this.vinculo = vinculo;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public ArrayList<TipoDeVeiculo> getTipoDeVeiculosDisponiveis() {
        return tipoDeVeiculosDisponiveis;
    }

    public void setTipoDeVeiculosDisponiveis(ArrayList<TipoDeVeiculo> tipoDeVeiculosDisponiveis) {
        this.tipoDeVeiculosDisponiveis = tipoDeVeiculosDisponiveis;
    }
}
