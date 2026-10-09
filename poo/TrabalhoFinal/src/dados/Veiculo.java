package dados;

import enums.Status;
import enums.TipoDeVeiculo;

public class Veiculo {
    private String placa;
    private String marca;
    private String modelo;
    private String anoDeFabricacao;
    private float capacidadeEmQuilos;
    private float capacidadeEmMetroCubicos;
    private float quilometragemAtual;
    private String dataDeAquisicao;
    private Status status;
    private String dataProximaManuntencao;
    private TipoDeVeiculo tipoDeVeiculo;

    public Veiculo(String placa, String marca, String modelo, String anoDeFabricacao, float capacidadeEmQuilos, float capacidadeEmMetroCubicos, float quilometragemAtual, String dataDeAquisicao, Status status, String dataProximaManuntencao, TipoDeVeiculo tipoDeVeiculo) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anoDeFabricacao = anoDeFabricacao;
        this.capacidadeEmQuilos = capacidadeEmQuilos;
        this.capacidadeEmMetroCubicos = capacidadeEmMetroCubicos;
        this.quilometragemAtual = quilometragemAtual;
        this.dataDeAquisicao = dataDeAquisicao;
        this.status = status;
        this.dataProximaManuntencao = dataProximaManuntencao;
        this.tipoDeVeiculo = tipoDeVeiculo;
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

    public String getAnoDeFabricacao() {
        return anoDeFabricacao;
    }

    public void setAnoDeFabricacao(String anoDeFabricacao) {
        this.anoDeFabricacao = anoDeFabricacao;
    }

    public float getCapacidadeEmQuilos() {
        return capacidadeEmQuilos;
    }

    public void setCapacidadeEmQuilos(float capacidadeEmQuilos) {
        this.capacidadeEmQuilos = capacidadeEmQuilos;
    }

    public float getCapacidadeEmMetroCubicos() {
        return capacidadeEmMetroCubicos;
    }

    public void setCapacidadeEmMetroCubicos(float capacidadeEmMetroCubicos) {
        this.capacidadeEmMetroCubicos = capacidadeEmMetroCubicos;
    }

    public float getQuilometragemAtual() {
        return quilometragemAtual;
    }

    public void setQuilometragemAtual(float quilometragemAtual) {
        this.quilometragemAtual = quilometragemAtual;
    }

    public String getDataDeAquisicao() {
        return dataDeAquisicao;
    }

    public void setDataDeAquisicao(String dataDeAquisicao) {
        this.dataDeAquisicao = dataDeAquisicao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDataProximaManuntencao() {
        return dataProximaManuntencao;
    }

    public void setDataProximaManuntencao(String dataProximaManuntencao) {
        this.dataProximaManuntencao = dataProximaManuntencao;
    }

    public TipoDeVeiculo getTipoDeVeiculo() {
        return tipoDeVeiculo;
    }

    public void setTipoDeVeiculo(TipoDeVeiculo tipoDeVeiculo) {
        this.tipoDeVeiculo = tipoDeVeiculo;
    }
}
