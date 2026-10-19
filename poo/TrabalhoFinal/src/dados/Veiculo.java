package dados;

import java.time.*;
import java.util.*;

public class Veiculo {
    private String placa;
    private String marca;
    private String modelo;
    private int anoDeFabricacao;
    private TipoDeVeiculo tipoDeVeiculo;
    private double capacidadeEmQuilos;
    private double capacidadeEmMetroCubicos;
    private double quilometragemAtual;
    private LocalDate dataDeAquisicao;
    private StatusVeiculo status = StatusVeiculo.DISPONIVEL;
    private LocalDate dataProximaManutencao;
    private List<Manutencao> manutencoes = new ArrayList<>();

    public Veiculo(String placa, String marca, String modelo, int anoDeFabricacao, TipoDeVeiculo tipoDeVeiculo, double capacidadeEmQuilos, double capacidadeEmMetroCubicos, double quilometragemAtual, LocalDate dataDeAquisicao, LocalDate dataProximaManutencao) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anoDeFabricacao = anoDeFabricacao;
        this.tipoDeVeiculo = tipoDeVeiculo;
        this.capacidadeEmQuilos = capacidadeEmQuilos;
        this.capacidadeEmMetroCubicos = capacidadeEmMetroCubicos;
        this.quilometragemAtual = quilometragemAtual;
        this.dataDeAquisicao = dataDeAquisicao;
        this.dataProximaManutencao = dataProximaManutencao;
    }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public int getAnoDeFabricacao() { return anoDeFabricacao; }
    public void setAnoDeFabricacao(int anoDeFabricacao) { this.anoDeFabricacao = anoDeFabricacao; }
    public TipoDeVeiculo getTipoDeVeiculo() { return tipoDeVeiculo; }
    public void setTipoDeVeiculo(TipoDeVeiculo tipoDeVeiculo) { this.tipoDeVeiculo = tipoDeVeiculo; }
    public double getCapacidadeEmQuilos() { return capacidadeEmQuilos; }
    public void setCapacidadeEmQuilos(double capacidadeEmQuilos) { this.capacidadeEmQuilos = capacidadeEmQuilos; }
    public double getCapacidadeEmMetroCubicos() { return capacidadeEmMetroCubicos; }
    public void setCapacidadeEmMetroCubicos(double capacidadeEmMetroCubicos) { this.capacidadeEmMetroCubicos = capacidadeEmMetroCubicos; }
    public double getQuilometragemAtual() { return quilometragemAtual; }
    public void setQuilometragemAtual(double quilometragemAtual) { this.quilometragemAtual = quilometragemAtual; }
    public LocalDate getDataDeAquisicao() { return dataDeAquisicao; }
    public void setDataDeAquisicao(LocalDate dataDeAquisicao) { this.dataDeAquisicao = dataDeAquisicao; }
    public StatusVeiculo getStatus() { return status; }
    public void setStatus(StatusVeiculo status) { this.status = status; }
    public LocalDate getDataProximaManutencao() { return dataProximaManutencao; }
    public void setDataProximaManutencao(LocalDate dataProximaManutencao) { this.dataProximaManutencao = dataProximaManutencao; }
    public List<Manutencao> getManutencoes() { return manutencoes; }
    public void setManutencoes(List<Manutencao> manutencoes) { this.manutencoes = manutencoes; }

    @Override
    public String toString() { return placa + " (" + modelo + ", " + tipoDeVeiculo + ", " + status + ")"; }
}
