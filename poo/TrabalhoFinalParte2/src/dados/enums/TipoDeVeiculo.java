package dados.enums;

public enum TipoDeVeiculo {
    CARRO("Carro"), VAN("Van"), CAMINHAO_GRANDE("Caminhão grande"), CAMINHAO_PEQUENO("Caminhão pequeno");

    private String tipoDeVeiculo;

    TipoDeVeiculo(String tipoDeVeiculo){
        this.tipoDeVeiculo = tipoDeVeiculo;
    }
    public String getTipoDeVeiculo() {
        return tipoDeVeiculo;
    }
}
