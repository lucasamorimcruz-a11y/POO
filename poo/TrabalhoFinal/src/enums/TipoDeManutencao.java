package enums;

public enum TipoDeManutencao {
    PREVENTINVA ("Preventiva"), CORRETIVA("Corretiva");
    String tipoDeManutencao;

    TipoDeManutencao(String tipoDeManutencao) {
        this.tipoDeManutencao = tipoDeManutencao;
    }

    public String getTipoDeManutencao() {
        return tipoDeManutencao;
    }
}
