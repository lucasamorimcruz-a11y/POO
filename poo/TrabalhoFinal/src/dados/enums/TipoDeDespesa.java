package dados.enums;

public enum TipoDeDespesa {
    COMBUSTIVEL("Combustível"), PEDAGIO("Pedágio"), ALIMENTACAO("Alimentação");
    String tipo;
    TipoDeDespesa (String tipo){
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
