package dados;

import dados.enums.StatusVeiculo;
import dados.enums.TipoDeVeiculo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Veiculo {
    private String placa;
    private String marca;
    private String modelo;
    private int anoDeFabricacao;
    private double capacidadeEmQuilos;
    private double capacidadeEmMetroCubicos;
    private double quilometragemAtual;
    private LocalDate dataDeAquisicao;
    private LocalDate dataProximaManuntencao;
    private TipoDeVeiculo tipoDeVeiculo;
    private StatusVeiculo statusVeiculo;
    private ArrayList<Manutencao> manutencoes;

    public Veiculo(String placa, String marca, String modelo, int anoDeFabricacao, double capacidadeEmQuilos, double capacidadeEmMetroCubicos, double quilometragemAtual, LocalDate dataDeAquisicao, LocalDate dataProximaManuntencao, TipoDeVeiculo tipoDeVeiculo, StatusVeiculo statusVeiculo, ArrayList<Manutencao> manutencoes) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anoDeFabricacao = anoDeFabricacao;
        this.capacidadeEmQuilos = capacidadeEmQuilos;
        this.capacidadeEmMetroCubicos = capacidadeEmMetroCubicos;
        this.quilometragemAtual = quilometragemAtual;
        this.dataDeAquisicao = dataDeAquisicao;
        this.dataProximaManuntencao = dataProximaManuntencao;
        this.tipoDeVeiculo = tipoDeVeiculo;
        this.statusVeiculo = statusVeiculo;
        this.manutencoes = manutencoes;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoDeFabricacao() {
        return anoDeFabricacao;
    }

    public void setAnoDeFabricacao(int anoDeFabricacao) {
        this.anoDeFabricacao = anoDeFabricacao;
    }

    public double getCapacidadeEmQuilos() {
        return capacidadeEmQuilos;
    }

    public void setCapacidadeEmQuilos(double capacidadeEmQuilos) {
        this.capacidadeEmQuilos = capacidadeEmQuilos;
    }

    public double getCapacidadeEmMetroCubicos() {
        return capacidadeEmMetroCubicos;
    }

    public void setCapacidadeEmMetroCubicos(double capacidadeEmMetroCubicos) {
        this.capacidadeEmMetroCubicos = capacidadeEmMetroCubicos;
    }

    public double getQuilometragemAtual() {
        return quilometragemAtual;
    }

    public void setQuilometragemAtual(double quilometragemAtual) {
        this.quilometragemAtual = quilometragemAtual;
    }

    public LocalDate getDataDeAquisicao() {
        return dataDeAquisicao;
    }

    public void setDataDeAquisicao(LocalDate dataDeAquisicao) {
        this.dataDeAquisicao = dataDeAquisicao;
    }

    public LocalDate getDataProximaManuntencao() {
        return dataProximaManuntencao;
    }

    public void setDataProximaManuntencao(LocalDate dataProximaManuntencao) {
        this.dataProximaManuntencao = dataProximaManuntencao;
    }

    public TipoDeVeiculo getTipoDeVeiculo() {
        return tipoDeVeiculo;
    }

    public void setTipoDeVeiculo(TipoDeVeiculo tipoDeVeiculo) {
        this.tipoDeVeiculo = tipoDeVeiculo;
    }

    public StatusVeiculo getStatusVeiculo() {
        return statusVeiculo;
    }

    public void setStatusVeiculo(StatusVeiculo statusVeiculo) {
        this.statusVeiculo = statusVeiculo;
    }

    public ArrayList<Manutencao> getManutencoes() {
        return manutencoes;
    }

    public void setManutencoes(ArrayList<Manutencao> manutencoes) {
        this.manutencoes = manutencoes;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Veiculo veiculo)) return false;
        return anoDeFabricacao == veiculo.anoDeFabricacao && Double.compare(capacidadeEmQuilos, veiculo.capacidadeEmQuilos) == 0 && Double.compare(capacidadeEmMetroCubicos, veiculo.capacidadeEmMetroCubicos) == 0 && Double.compare(quilometragemAtual, veiculo.quilometragemAtual) == 0 && Objects.equals(placa, veiculo.placa) && Objects.equals(marca, veiculo.marca) && Objects.equals(modelo, veiculo.modelo) && Objects.equals(dataDeAquisicao, veiculo.dataDeAquisicao) && Objects.equals(dataProximaManuntencao, veiculo.dataProximaManuntencao) && tipoDeVeiculo == veiculo.tipoDeVeiculo && statusVeiculo == veiculo.statusVeiculo && Objects.equals(manutencoes, veiculo.manutencoes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placa, marca, modelo, anoDeFabricacao, capacidadeEmQuilos, capacidadeEmMetroCubicos, quilometragemAtual, dataDeAquisicao, dataProximaManuntencao, tipoDeVeiculo, statusVeiculo, manutencoes);
    }
}
