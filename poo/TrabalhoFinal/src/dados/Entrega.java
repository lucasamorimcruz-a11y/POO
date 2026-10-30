package dados;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Entrega {
    private String nome;
    private String documento;
    private String parentesco;
    private LocalDate dia;
    private LocalDateTime hora;
    private String assinatura;
    private String foto;

    public Entrega(String nome, String documento, String parentesco, LocalDate dia, LocalDateTime hora, String assinatura, String foto) {
        this.nome = nome;
        this.documento = documento;
        this.parentesco = parentesco;
        this.dia = dia;
        this.hora = hora;
        this.assinatura = assinatura;
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public LocalDateTime getHora() {
        return hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    public String getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(String assinatura) {
        this.assinatura = assinatura;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
