package dados;

import dados.enums.TipoCliente;

public class Cliente  {
    private Pessoa pessoa;
    private TipoCliente tipoCliente;
    private TabelaPreco tabelaDePreco;
    private float limiteDeCredito;

    public Cliente(Pessoa pessoa, TipoCliente tipoCliente, TabelaPreco tabelaDePreco, float limiteDeCredito) {
        this.pessoa = pessoa;
        this.tipoCliente = tipoCliente;
        this.tabelaDePreco = tabelaDePreco;
        this.limiteDeCredito = limiteDeCredito;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public TabelaPreco getTabelaDePreco() {
        return tabelaDePreco;
    }

    public void setTabelaDePreco(TabelaPreco tabelaDePreco) {
        this.tabelaDePreco = tabelaDePreco;
    }

    public float getLimiteDeCredito() {
        return limiteDeCredito;
    }

    public void setLimiteDeCredito(float limiteDeCredito) {
        this.limiteDeCredito = limiteDeCredito;
    }
}
