package dados.dados.enums;

public enum StatusRota {
    PLANEJADA("Planejada"),
    EM_ANDAMENTO("Em andamento"),
    CONCLUIDA ("Concluida");

    private final String status;

    StatusRota(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return status;
    }
}
