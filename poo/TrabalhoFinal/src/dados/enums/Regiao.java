package dados.enums;

public enum Regiao {
    LOCAL ("Local"),
    ESTADUAL ("Estadual"),
    INTERESTADUAL ("Interestadual"),
    OUTRO ("Outro");
    String regiao;
    Regiao (String regiao){
        this.regiao = regiao;
    }
    public String getRegiao() {
        return regiao;
    }
}
