package dados;

import java.time.*;
import java.util.*;

public class Entrega {
    private boolean sucesso;
    private String nomeRecebedor;
    private String documento;
    private String parentesco;
    private LocalDateTime dataHora;
    private String assinatura;
    private String motivoFalha;
    private boolean novaTentativa;

    public Entrega(boolean sucesso, String nomeRecebedor, String documento, String parentesco, LocalDateTime dataHora, String assinatura, String motivoFalha, boolean novaTentativa) {
        this.sucesso = sucesso;
        this.nomeRecebedor = nomeRecebedor;
        this.documento = documento;
        this.parentesco = parentesco;
        this.dataHora = dataHora;
        this.assinatura = assinatura;
        this.motivoFalha = motivoFalha;
        this.novaTentativa = novaTentativa;
    }

    public boolean getSucesso() { return sucesso; }
    public void setSucesso(boolean sucesso) { this.sucesso = sucesso; }
    public String getNomeRecebedor() { return nomeRecebedor; }
    public void setNomeRecebedor(String nomeRecebedor) { this.nomeRecebedor = nomeRecebedor; }
    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }
    public String getParentesco() { return parentesco; }
    public void setParentesco(String parentesco) { this.parentesco = parentesco; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public String getAssinatura() { return assinatura; }
    public void setAssinatura(String assinatura) { this.assinatura = assinatura; }
    public String getMotivoFalha() { return motivoFalha; }
    public void setMotivoFalha(String motivoFalha) { this.motivoFalha = motivoFalha; }
    public boolean getNovaTentativa() { return novaTentativa; }
    public void setNovaTentativa(boolean novaTentativa) { this.novaTentativa = novaTentativa; }
}
