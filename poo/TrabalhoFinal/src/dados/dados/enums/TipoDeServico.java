package dados.dados.enums;

public enum TipoDeServico {
    ECONOMICO ("Econômico"), EXPRESSO("Expresso"), AGENDADO("Agendado");
    String servico;
    TipoDeServico (String servico){
        this.servico = servico;
    }

    public String getServico() {
        return servico;
    }

    @Override
    public String toString() {
        return servico;
    }
}
