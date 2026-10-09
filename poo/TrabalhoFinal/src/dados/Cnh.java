package dados;
import java.util.Date;

public class Cnh {
    private String numeroDeCNH;
    private String categoria;
    private Date dataDeValidade;

    public Cnh(String numeroDeCNH, String categoria, Date dataDeValidade) {
        this.numeroDeCNH = numeroDeCNH;
        this.categoria = categoria;
        this.dataDeValidade = dataDeValidade;
    }

    public String getNumeroDeCNH() {
        return numeroDeCNH;
    }

    public void setNumeroDeCNH(String numeroDeCNH) {
        this.numeroDeCNH = numeroDeCNH;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Date getDataDeValidade() {
        return dataDeValidade;
    }

    public void setDataDeValidade(Date dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }
}
