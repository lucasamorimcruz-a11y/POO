package dados.enums;

public enum TipoCliente {
    EVENTUAL ("Eventual"), CONTRATADO("Contratado");
    String tipo;
    TipoCliente(String tipo){
        this.tipo = tipo;
    }
    public String getTipo() {
        return tipo;
    }
}
