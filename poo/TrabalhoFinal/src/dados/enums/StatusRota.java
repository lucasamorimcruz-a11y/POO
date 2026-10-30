package dados.enums;

public enum StatusRota {
    DISPONIVEL("Disponível"),
    EM_ANDAMENTO("Em andamento"),
    CONCLUIDA ("Concluida");

    private final String status;

    StatusRota(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
