package dados;

public class Item {
    private String tipoDeEmbalagem;
    private float peso;
    private float dimensao;
    private String descricao;

    public Item(String tipoDeEmbalagem, float peso, float dimensao, String descricao) {
        this.tipoDeEmbalagem = tipoDeEmbalagem;
        this.peso = peso;
        this.dimensao = dimensao;
        this.descricao = descricao;
    }

    public String getTipoDeEmbalagem() {
        return tipoDeEmbalagem;
    }

    public void setTipoDeEmbalagem(String tipoDeEmbalagem) {
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
