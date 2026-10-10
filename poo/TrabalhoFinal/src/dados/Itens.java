package dados;
import enums.TipoDeEmbalagem;

public class Itens {
    private TipoDeEmbalagem tipoDeEmbalagem;
    private float peso;
    private float dimensao;
    private String descricao;

    public Itens(TipoDeEmbalagem tipoDeEmbalagem, float peso, float dimensao, String descricao) {
        this.tipoDeEmbalagem = tipoDeEmbalagem;
        this.peso = peso;
        this.dimensao = dimensao;
        this.descricao = descricao;
    }

    public TipoDeEmbalagem getTipoDeEmbalagem() {
        return tipoDeEmbalagem;
    }

    public void setTipoDeEmbalagem(TipoDeEmbalagem tipoDeEmbalagem) {
        this.tipoDeEmbalagem = tipoDeEmbalagem;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getDimensao() {
        return dimensao;
    }

    public void setDimensao(float dimensao) {
        this.dimensao = dimensao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
