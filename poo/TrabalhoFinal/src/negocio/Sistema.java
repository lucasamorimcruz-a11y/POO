
package negocio;

import dados.*;
import dados.enums.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Sistema {

    private ArrayList<Cliente> clientes;
    private ArrayList<Veiculo> veiculos;
    private ArrayList<Motorista> motoristas;
    private ArrayList<Pedido> pedidos;
    private ArrayList<Rota> rotas;
    private ArrayList<ConhecimentoTransporte> conhecimentos;
    private TabelaPreco tabelaPadrao;

    public void cadastrarCliente (Cliente cliente){

    }
    public void cadastrarMotorista (Motorista motorista){

    }
    public void cadastrarVeiculo (Veiculo veiculo){

    }
    public Cliente buscarCliente (String documento){

    }
    public Motorista buscarMotorista (String cpf) {

    }
    public Veiculo buscarVeiculo (String placa ){

    }
    public Pedido buscarPedido (int numero){

    }
    public Pedido criarPedido (Cliente cliente, Endereco coleta, String contatoColeta, LocalDate dataColeta, Periodo periodo, String descricao, double valorDeclarado, Endereco entrega, String contatoDestino, TipoDeServico tipo, FormaDePagamento formaDePagamento){

    }
    public void adicionarItem (Pedido pedido, Item item){

    }
    public double calcularFrete (Pedido pedido, double distanciaKm, Regiao regiao){

    }
    public void cancelarPedido (Pedido pedido){

    }
    public ArrayList<Cliente> pedidosDoCliente (Cliente cliente){

    }
    public ConhecimentoTransporte registrarColeta (Pedido pedido, String local, String notaFiscal){

    }
    public ConhecimentoTransporte buscarConhecimento (Pedido pedido){

    }
    public Rota criarRota (LocalDate data, Veiculo veiculo, Motorista motorista){

    }
    public void adicionarPedidoARota (Rota rota, Pedido pedido, LocalDateTime horarioPrevisto){

    }
    public void iniciarRota (Rota rota, double kmInicial){

    }
    public void concluirRota (Rota rota, double kmFinal){

    }
    public void registrarDespesa (Rota rota, LocalDate data, TipoDeDespesa tipo, double valor, String comprovante){

    }
    public void registrarEvento (Pedido pedido, TipoDeEvento tipo, String local, String descricao, String foto){

    }
    public void registrarFalhaNaEntrega (Pedido pedido, String motivo, boolean novaTentativa){

    }
    public ArrayList<EventoDeRastreamento> rastrear (int numeroDoPedido){

    }
    public void registrarManutencao (Veiculo veiculo, TipoDeManutencao tipo,String servico, String pecas, String oficina, double valor, LocalDate proximaManutencao){

    }
    public ArrayList<Veiculo> veiculosComManutencaoPendente (){

    }
    public ArrayList<Veiculo> veiculosDisponiveis (TipoDeVeiculo tipo){

    }
    public ArrayList<Motorista> motoristasHabilitados (TipoDeVeiculo tipo){

    }
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(ArrayList<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public ArrayList<Motorista> getMotoristas() {
        return motoristas;
    }

    public void setMotoristas(ArrayList<Motorista> motoristas) {
        this.motoristas = motoristas;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public ArrayList<Rota> getRotas() {
        return rotas;
    }

    public void setRotas(ArrayList<Rota> rotas) {
        this.rotas = rotas;
    }

    public ArrayList<ConhecimentoTransporte> getConhecimentos() {
        return conhecimentos;
    }

    public void setConhecimentos(ArrayList<ConhecimentoTransporte> conhecimentos) {
        this.conhecimentos = conhecimentos;
    }

    public TabelaPreco getTabelaPadrao() {
        return tabelaPadrao;
    }

    public void setTabelaPadrao(TabelaPreco tabelaPadrao) {
        this.tabelaPadrao = tabelaPadrao;
    }
}
