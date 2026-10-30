package dados;
import dados.enums.CategoriaCnh;

import java.util.Date;

public class Cnh {
    private String numeroDeCNH;
    private CategoriaCnh categoria;
    private Date dataDeValidade;

    public Cnh(String numeroDeCNH, CategoriaCnh categoria, Date dataDeValidade) {
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

    public CategoriaCnh getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaCnh categoria) {
        this.categoria = categoria;
    }

    public Date getDataDeValidade() {
        return dataDeValidade;
    }

    public void setDataDeValidade(Date dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }
}
