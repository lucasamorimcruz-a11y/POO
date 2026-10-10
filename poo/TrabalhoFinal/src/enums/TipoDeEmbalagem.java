
package enums;

public enum TipoDeEmbalagem {
    CAIXA("Caixa"),
    ENVELOPE ("Envelope"),
    PACOTE ("Pacote"),
    SACO ("Saco"),
    PALETE ("Palete"),
    ENGRADADO ("Engradado"),
    OUTROS ("Outros");
    private String tipoDeEmbalagem;
    TipoDeEmbalagem (String tipo){
        this.tipoDeEmbalagem = tipo;
    }
    public String getTipoDeEmbalagem() {
        return tipoDeEmbalagem;
    }
}
