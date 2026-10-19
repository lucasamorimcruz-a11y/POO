package dados;
import java.time.*;
import java.util.*;

public class Item {
    private String tipoDeEmbalagem;
    private double peso;
    private double dimensao;
    private String descricao;

    public Item(String tipoDeEmbalagem, double peso, double dimensao, String descricao) {
        this.tipoDeEmbalagem = tipoDeEmbalagem;
        this.peso = peso;
        this.dimensao = dimensao;
        this.descricao = descricao;
    }

    public String getTipoDeEmbalagem() { return tipoDeEmbalagem; }
    public void setTipoDeEmbalagem(String tipoDeEmbalagem) { this.tipoDeEmbalagem = tipoDeEmbalagem; }
    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }
    public double getDimensao() { return dimensao; }
    public void setDimensao(double dimensao) { this.dimensao = dimensao; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
