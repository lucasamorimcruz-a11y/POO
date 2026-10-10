package dados;
import dados.enums.TipoDeManutencao;
import java.util.Date;

public class Manutencao {
    private Date dataDaManutencao;
    private TipoDeManutencao tipoDeManutencao;
    private float quilometragem;
    private String descricaoDosServicos;
    private String pecasTrocadas;
    private String oficina;
    private float valorTotal;
    private Date proximaManutencao;

    public Manutencao(Date dataDaManutencao, TipoDeManutencao tipoDeManutencao, float quilometragem, String descricaoDosServicos, String pecasTrocadas, String oficina, float valorTotal, Date proximaManutencao) {
        this.dataDaManutencao = dataDaManutencao;
        this.tipoDeManutencao = tipoDeManutencao;
        this.quilometragem = quilometragem;
        this.descricaoDosServicos = descricaoDosServicos;
        this.pecasTrocadas = pecasTrocadas;
        this.oficina = oficina;
        this.valorTotal = valorTotal;
        this.proximaManutencao = proximaManutencao;
    }

    public Date getDataDaManutencao() {
        return dataDaManutencao;
    }

    public void setDataDaManutencao(Date dataDaManutencao) {
        this.dataDaManutencao = dataDaManutencao;
    }

    public TipoDeManutencao getTipoDeManutencao() {
        return tipoDeManutencao;
    }

    public void setTipoDeManutencao(TipoDeManutencao tipoDeManutencao) {
        this.tipoDeManutencao = tipoDeManutencao;
    }

    public float getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(float quilometragem) {
        this.quilometragem = quilometragem;
    }

    public String getDescricaoDosServicos() {
        return descricaoDosServicos;
    }

    public void setDescricaoDosServicos(String descricaoDosServicos) {
        this.descricaoDosServicos = descricaoDosServicos;
    }

    public String getPecasTrocadas() {
        return pecasTrocadas;
    }

    public void setPecasTrocadas(String pecasTrocadas) {
        this.pecasTrocadas = pecasTrocadas;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getProximaManutencao() {
        return proximaManutencao;
    }

    public void setProximaManutencao(Date proximaManutencao) {
        this.proximaManutencao = proximaManutencao;
    }
}
