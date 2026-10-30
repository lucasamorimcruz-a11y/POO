package dados;

import java.time.LocalDate;
import java.util.Date;

public class Cnh {
    private String numeroDeCNH;
    private String categoria;
    private LocalDate dataDeValidade;

    public Cnh(String numeroDeCNH, String categoria, LocalDate dataDeValidade) {
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

    public LocalDate getDataDeValidade() {
        return dataDeValidade;
    }

    public void setDataDeValidade(LocalDate dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }
}
