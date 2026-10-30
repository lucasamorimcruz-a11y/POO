package dados;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Entrega {
    private String nomeDeQuemRecebeu;
    private String documentoDeIdentificacao;
    private String parentescoOuFuncao;
    private LocalDate data;
    private LocalTime hora;
    private String assinaturaOuFotoDeComprovante;

    public Entrega(String nomeDeQuemRecebeu, String documentoDeIdentificacao, String parentescoOuFuncao, LocalDate data, LocalTime hora, String assinaturaOuFotoDeComprovante) {
        this.nomeDeQuemRecebeu = nomeDeQuemRecebeu;
        this.documentoDeIdentificacao = documentoDeIdentificacao;
        this.parentescoOuFuncao = parentescoOuFuncao;
        this.data = data;
        this.hora = hora;
        this.assinaturaOuFotoDeComprovante = assinaturaOuFotoDeComprovante;
    }

    public String getNomeDeQuemRecebeu() {
        return nomeDeQuemRecebeu;
    }

    public void setNomeDeQuemRecebeu(String nomeDeQuemRecebeu) {
        this.nomeDeQuemRecebeu = nomeDeQuemRecebeu;
    }

    public String getDocumentoDeIdentificacao() {
        return documentoDeIdentificacao;
    }

    public void setDocumentoDeIdentificacao(String documentoDeIdentificacao) {
        this.documentoDeIdentificacao = documentoDeIdentificacao;
    }

    public String getParentescoOuFuncao() {
        return parentescoOuFuncao;
    }

    public void setParentescoOuFuncao(String parentescoOuFuncao) {
        this.parentescoOuFuncao = parentescoOuFuncao;
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

    public String getAssinaturaOuFotoDeComprovante() {
        return assinaturaOuFotoDeComprovante;
    }

    public void setAssinaturaOuFotoDeComprovante(String assinaturaOuFotoDeComprovante) {
        this.assinaturaOuFotoDeComprovante = assinaturaOuFotoDeComprovante;
    }
}


