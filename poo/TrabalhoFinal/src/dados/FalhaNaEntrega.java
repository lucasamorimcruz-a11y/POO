package dados;

public class FalhaNaEntrega {
    private String motivo;
    private boolean novaTentativa;

    public FalhaNaEntrega(String motivo, boolean novaTentativa) {
        this.motivo = motivo;
        this.novaTentativa = novaTentativa;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public boolean isNovaTentativa() {
        return novaTentativa;
    }

    public void setNovaTentativa(boolean novaTentativa) {
        this.novaTentativa = novaTentativa;
    }
}
