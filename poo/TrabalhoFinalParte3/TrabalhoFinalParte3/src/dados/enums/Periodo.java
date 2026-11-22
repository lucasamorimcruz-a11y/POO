package dados.enums;

public enum Periodo {
    MANHA("Manhã"), TARDE ( "Tarde"), NOITE("Noite");
    String periodo;
    Periodo (String periodo){
        this.periodo = periodo;
    }

    public String getPeriodo() {
        return periodo;
    }
}
