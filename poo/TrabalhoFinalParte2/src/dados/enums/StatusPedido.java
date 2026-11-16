
package dados.enums;

public enum StatusPedido {
    PENDENTE("Pendente"),
    COLETADO("Coletado"),
    EM_TRANSITO ("Em transito"),
    ENTREGUE("Entregue"),
    CANCELADO("Cancelado");
    private  String status;
    StatusPedido(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
