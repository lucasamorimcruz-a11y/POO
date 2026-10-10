
package dados.enums;

public enum StatusEntrega {
    PENDENTE("Pendente"),
    EM_ANDAMENTO("Em andamento"),
    ENTREGUE("Entregue"),
    NAO_REALIZADA("Não realizada"),
    CANCELADA("Cancelada");
    private  String status;
    StatusEntrega(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
