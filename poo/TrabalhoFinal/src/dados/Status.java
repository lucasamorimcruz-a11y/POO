package dados;

public enum Status {
    DISPONIVEL("Disponível"),
    EM_ROTA("Em rota"),
    EM_MANUTENCAO("Em manutenção");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
