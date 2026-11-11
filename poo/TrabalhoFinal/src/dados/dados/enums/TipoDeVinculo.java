package dados.dados.enums;

public enum TipoDeVinculo {
    CLT ("CLT"), TERCEIRIZADO ("Terceirizado");
    private String vinculo;

    TipoDeVinculo(String vinculo){
        this.vinculo = vinculo;
    }

    public String getVinculo() {
        return vinculo;
    }

    @Override
    public String toString() {
        return vinculo;
    }
}
