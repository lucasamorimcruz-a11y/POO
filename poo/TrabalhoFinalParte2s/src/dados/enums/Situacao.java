package dados.enums;

public enum Situacao {
    ATIVO("Ativo"), AFASTADO("Afastado"), DESLIGADO("Desligado");
    String situacao;

    Situacao (String situacao){
        this.situacao = situacao;
    }
    public String getSituacao() {
        return situacao;
    }
}
