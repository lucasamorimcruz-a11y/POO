package dados.dados.enums;

public enum TipoDeManutencao {
    PREVENTIVA ("Preventiva"), CORRETIVA("Corretiva");
    String tipoDeManutencao;

    TipoDeManutencao(String tipoDeManutencao) {
        this.tipoDeManutencao = tipoDeManutencao;
    }

    public String getTipoDeManutencao() {
        return tipoDeManutencao;
    }

    @Override
    public String toString() {
        return tipoDeManutencao;
    }
}
