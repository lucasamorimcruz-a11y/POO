package fabrica;

public class Carro extends Veiculo {

    private int numeroPortas;
    private Combustivel combustivel;

    public int getNumeroPortas() {
        return this.numeroPortas;
    }

    public void setNumeroPortas(int numeroPortas) {
        this.numeroPortas = numeroPortas;
    }

    public Combustivel getCombustivel() {
        return this.combustivel;
    }

    public void setCombustivel(Combustivel combustivel) {
        this.combustivel = combustivel;
    }

    public String info() {
        return "Carro comCor: " + this.getCor() + "- Numero de portas: " + numeroPortas + " e "
                + " - tipo de combustivel: " + combustivel;
    }

}