package dados;

import enums.Eventos;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class EventoDeRastreamento {
    private Date data;
    private LocalTime hora;
    private String localizacao;
    private String descricao;
    private String caminhoDaFoto;
    private ArrayList<Eventos> eventos;

    public EventoDeRastreamento(Date data, LocalTime hora, String localizacao, String descricao, String caminhoDaFoto, ArrayList<Eventos> eventos) {
        this.data = data;
        this.hora = hora;
        this.localizacao = localizacao;
        this.descricao = descricao;
        this.caminhoDaFoto = caminhoDaFoto;
        this.eventos = eventos;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
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

    public String getCaminhoDaFoto() {
        return caminhoDaFoto;
    }

    public void setCaminhoDaFoto(String caminhoDaFoto) {
        this.caminhoDaFoto = caminhoDaFoto;
    }

    public ArrayList<Eventos> getEventos() {
        return eventos;
    }

    public void setEventos(ArrayList<Eventos> eventos) {
        this.eventos = eventos;
    }
}
