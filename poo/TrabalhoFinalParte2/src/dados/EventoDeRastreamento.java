package dados;

import dados.enums.TipoDeEvento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class EventoDeRastreamento {
    private LocalDate data;
    private LocalTime hora;
    private TipoDeEvento tipo;
    private String localizacao;
    private String descricao;
    private String fotoComprobatoria;

    public EventoDeRastreamento(LocalDate data, LocalTime hora, TipoDeEvento tipo, String localizacao, String descricao, String fotoComprobatoria) {
        this.data = data;
        this.hora = hora;
        this.tipo = tipo;
        this.localizacao = localizacao;
        this.descricao = descricao;
        this.fotoComprobatoria = fotoComprobatoria;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public TipoDeEvento getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeEvento tipo) {
        this.tipo = tipo;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFotoComprobatoria() {
        return fotoComprobatoria;
    }

    public void setFotoComprobatoria(String fotoComprobatoria) {
        this.fotoComprobatoria = fotoComprobatoria;
    }
}
