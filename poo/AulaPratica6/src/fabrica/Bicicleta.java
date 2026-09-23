package fabrica;

public class Bicicleta extends Veiculo {

    private int numeroMarchas;

    public int getNumeroMarchas() {
        return this.numeroMarchas;
    }

    public void setNumeroMarchas(int numeroMarchas) {
        this.numeroMarchas = numeroMarchas;
    }

    public String info() {
        return "Bicicleta com cor: " + this.getCor() + " - Numero de marchas: " + this.numeroMarchas + "\n";
    }

}