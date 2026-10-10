package dados.enums;

public enum TipoDeServico {
    ECONOMICO ("Econômico"), EXPRESSO("Expresso"), AGENDADO("Agendado");
    String servico;
    TipoDeServico (String servico){
        this.servico = servico;
    }

    public String getServico() {
        return servico;
    }
}
