package dados;

import java.time.LocalDateTime;

public class PedidoNaRota {
    private int sequenciaEntrega;
    private Pedido pedido;
    private LocalDateTime horarioPrevistoDeEntrega;
    private LocalDateTime horarioRealDeEntrega; // preenchido só quando a entrega acontece

    public PedidoNaRota(int sequenciaEntrega, Pedido pedido, LocalDateTime horarioPrevistoDeEntrega) {
        this.sequenciaEntrega = sequenciaEntrega;
        this.pedido = pedido;
        this.horarioPrevistoDeEntrega = horarioPrevistoDeEntrega;
    }

    public int getSequenciaEntrega() {
        return sequenciaEntrega;
    }

    public void setSequenciaEntrega(int sequenciaEntrega) {
        this.sequenciaEntrega = sequenciaEntrega;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public LocalDateTime getHorarioPrevistoDeEntrega() {
        return horarioPrevistoDeEntrega;
    }

    public void setHorarioPrevistoDeEntrega(LocalDateTime horarioPrevistoDeEntrega) {
        this.horarioPrevistoDeEntrega = horarioPrevistoDeEntrega;
    }

    public LocalDateTime getHorarioRealDeEntrega() {
        return horarioRealDeEntrega;
    }

    public void setHorarioRealDeEntrega(LocalDateTime horarioRealDeEntrega) {
        this.horarioRealDeEntrega = horarioRealDeEntrega;
    }
}
