package dados.dados;

import dados.Pessoa;
import dados.TabelaPreco;
import dados.enums.TipoCliente;

public class Cliente  {
    private dados.Pessoa pessoa;
    private TipoCliente tipoCliente;
    private dados.TabelaPreco tabelaDePreco;
    private double limiteDeCredito;

    public Cliente(dados.Pessoa pessoa, TipoCliente tipoCliente, dados.TabelaPreco tabelaDePreco, double limiteDeCredito) {
        this.pessoa = pessoa;
        this.tipoCliente = tipoCliente;
        this.tabelaDePreco = tabelaDePreco;
        this.limiteDeCredito = limiteDeCredito;
    }

    public dados.Pessoa getPessoa() {
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

    public dados.TabelaPreco getTabelaDePreco() {
        return tabelaDePreco;
    }

    public void setTabelaDePreco(TabelaPreco tabelaDePreco) {
        this.tabelaDePreco = tabelaDePreco;
    }

    public double getLimiteDeCredito() {
        return limiteDeCredito;
    }

    public void setLimiteDeCredito(double limiteDeCredito) {
        this.limiteDeCredito = limiteDeCredito;
    }

    @Override
    public String toString() {
        return pessoa.getNome() + " (" + pessoa.getDocumento() + ")";
    }
}
