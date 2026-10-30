package dados;

import java.time.LocalDateTime;

public class PedidoNaRota {
    int sequenciaEntrega;
    LocalDateTime horarioPrevistoDeEntrega;
    LocalDateTime horarioRealDeEntrega;

    public PedidoNaRota(int sequenciaEntrega, LocalDateTime horarioPrevistoDeEntrega, LocalDateTime horarioRealDeEntrega) {
        this.sequenciaEntrega = sequenciaEntrega;
        this.horarioPrevistoDeEntrega = horarioPrevistoDeEntrega;
        this.horarioRealDeEntrega = horarioRealDeEntrega;
    }

    public int getSequenciaEntrega() {
        return sequenciaEntrega;
    }

    public void setSequenciaEntrega(int sequenciaEntrega) {
        this.sequenciaEntrega = sequenciaEntrega;
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
