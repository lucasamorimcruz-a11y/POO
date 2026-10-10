package dados.enums;

public enum TipoDeVinculo {
    CLT ("CLT"), PJ ("PJ");
    private String vinculo;

    TipoDeVinculo(String vinculo){
        this.vinculo = vinculo;
    }

    public String getVinculo() {
        return vinculo;
    }
}
