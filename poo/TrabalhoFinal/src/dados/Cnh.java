package dados;
import java.util.Date;

public class Cnh {
    private String numeroCnh;
    private String categoria;
    private LocalDate dataDeValidade;

    public Cnh(String numeroCnh, String categoria, LocalDate dataDeValidade) {
        this.numeroCnh = numeroCnh;
        this.categoria = categoria;
        this.dataDeValidade = dataDeValidade;
    }

    public String getNumeroCnh() { return numeroCnh; }
    public void setNumeroCnh(String numeroCnh) { this.numeroCnh = numeroCnh; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public LocalDate getDataDeValidade() { return dataDeValidade; }
    public void setDataDeValidade(LocalDate dataDeValidade) { this.dataDeValidade = dataDeValidade; }

    public boolean estaValida() { return !dataDeValidade.isBefore(LocalDate.now()); }
}
}
