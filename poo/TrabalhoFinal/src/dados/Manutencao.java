package dados;

import java.time.*;
import java.util.*;

public class Manutencao {
    private LocalDate dataDaManutencao;
    private TipoManutencao tipoDeManutencao;
    private double quilometragem;
    private String descricaoDosServicos;
    private String pecasTrocadas;
    private String oficina;
    private double valorTotal;
    private LocalDate proximaManutencao;

    public Manutencao(LocalDate dataDaManutencao, TipoManutencao tipoDeManutencao, double quilometragem, String descricaoDosServicos, String pecasTrocadas, String oficina, double valorTotal, LocalDate proximaManutencao) {
        this.dataDaManutencao = dataDaManutencao;
        this.tipoDeManutencao = tipoDeManutencao;
        this.quilometragem = quilometragem;
        this.descricaoDosServicos = descricaoDosServicos;
        this.pecasTrocadas = pecasTrocadas;
        this.oficina = oficina;
        this.valorTotal = valorTotal;
        this.proximaManutencao = proximaManutencao;
    }

    public LocalDate getDataDaManutencao() { return dataDaManutencao; }
    public void setDataDaManutencao(LocalDate dataDaManutencao) { this.dataDaManutencao = dataDaManutencao; }
    public TipoManutencao getTipoDeManutencao() { return tipoDeManutencao; }
    public void setTipoDeManutencao(TipoManutencao tipoDeManutencao) { this.tipoDeManutencao = tipoDeManutencao; }
    public double getQuilometragem() { return quilometragem; }
    public void setQuilometragem(double quilometragem) { this.quilometragem = quilometragem; }
    public String getDescricaoDosServicos() { return descricaoDosServicos; }
    public void setDescricaoDosServicos(String descricaoDosServicos) { this.descricaoDosServicos = descricaoDosServicos; }
    public String getPecasTrocadas() { return pecasTrocadas; }
    public void setPecasTrocadas(String pecasTrocadas) { this.pecasTrocadas = pecasTrocadas; }
    public String getOficina() { return oficina; }
    public void setOficina(String oficina) { this.oficina = oficina; }
    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }
    public LocalDate getProximaManutencao() { return proximaManutencao; }
    public void setProximaManutencao(LocalDate proximaManutencao) { this.proximaManutencao = proximaManutencao; }
}
