package dados;

import java.time.*;
import java.util.*;

public class EventoRastreamento {
    private Evento evento;
    private LocalDateTime dataHora;
    private String localizacao;
    private String descricao;
    private String foto;

    public EventoRastreamento(Evento evento, LocalDateTime dataHora, String localizacao, String descricao, String foto) {
        this.evento = evento;
        this.dataHora = dataHora;
        this.localizacao = localizacao;
        this.descricao = descricao;
        this.foto = foto;
    }

    public Evento getEvento() { return evento; }
    public void setEvento(Evento evento) { this.evento = evento; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }

    @Override
    public String toString() { return dataHora + " | " + evento + " | " + localizacao + " | " + descricao; }
}
