package dados;

import enums.Situacao;
import enums.TipoDeVeiculo;
import enums.TipoDeVinculo;

import java.util.ArrayList;

public class Motorista extends PessoaFisica {
    private Cnh cnh;
    private String dataDeAdmissao;
    private TipoDeVinculo vinculo;
    private Situacao situacao;
    private ArrayList<TipoDeVeiculo> tipoDeVeiculosDisponiveis;

    public Motorista (String nome, String endereco, String telefone, String email, String cpf, Cnh cnh, String dataDeAdmissao, TipoDeVinculo vinculo, Situacao situacao, ArrayList<TipoDeVeiculo> tipoDeVeiculosDisponiveis){
        super(nome, endereco, telefone, email, cpf);
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
