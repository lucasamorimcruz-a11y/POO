package dados;

public enum Vinculo {
    CLT ("CLT"), PJ ("PJ");
    private String vinculo;

    Vinculo (String vinculo){
        this.vinculo = vinculo;
    }

    public String getVinculo() {
        return vinculo;
    }
}
