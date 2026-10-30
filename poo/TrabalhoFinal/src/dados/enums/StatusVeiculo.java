package dados.enums;

public enum StatusVeiculo {
    DISPONIVEL ("Disponivel"),
    EM_ROTA ("Em rota"),
    EM_MANUTENCAO ("Em manutencao");
    String status;

    StatusVeiculo(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
