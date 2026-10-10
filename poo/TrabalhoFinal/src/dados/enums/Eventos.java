package dados.enums;

public enum Eventos {
    COLETA_REALIZADA ("Coleta realizada"),
    CARGA_EM_TRANSITO ("Carga em transito"),
    SAIU_PARA_ENTREGA ("Saiu para entrega"),
    ENTREGUE ("Entregue"),
    DESTINATARIO_AUSENTE ("Destinatário ausente"),
    ENDERECO_NAO_LOCALIZADO ("Endereco não localizado"),
    RECUSADO_PELO_DESTINATARIO ("Recusado");
    String evento;
    Eventos (String evento){
        this.evento = evento;
    }
    public String getEvento() {
        return evento;
    }
}
