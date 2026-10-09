package dados;

import java.time.LocalDateTime;

public class TentativaEntrega {
    private LocalDateTime horaeData;
    private String motivoDaFalha;
    private boolean novaTentativa;

    public TentativaEntrega(LocalDateTime horaeData, String motivoDaFalha, boolean novaTentativa) {
        this.horaeData = horaeData;
        this.motivoDaFalha = motivoDaFalha;
        this.novaTentativa = novaTentativa;
    }

    public LocalDateTime getHoraeData() {
        return horaeData;
    }

    public void setHoraeData(LocalDateTime horaeData) {
        this.horaeData = horaeData;
    }

    public String getMotivoDaFalha() {
        return motivoDaFalha;
    }

    public void setMotivoDaFalha(String motivoDaFalha) {
        this.motivoDaFalha = motivoDaFalha;
    }

    public boolean isNovaTentativa() {
        return novaTentativa;
    }

    public void setNovaTentativa(boolean novaTentativa) {
        this.novaTentativa = novaTentativa;
    }
}
