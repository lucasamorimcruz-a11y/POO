package dados.enums;

public enum TipoDeEvento {
    COLETA_REALIZADA("Coleta realizada"),
    CARGA_EM_TRANSITO("Carga em trânsito"),
    SAIU_PARA_ENTREGA("Saiu para entrega"),
    ENTREGUE("Entregue"),
    DESTINATARIO_AUSENTE("Destinatário ausente"),
    ENDERECO_NAO_LOCALIZADO("Endereço não localizado"),
    RECUSADO_PELO_DESTINATARIO("Recusado pelo destinatário");

    private final String descricao;

    TipoDeEvento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
