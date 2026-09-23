package fabrica;

public abstract class Veiculo {

    public abstract String info();

    private Cor cor;

    public Cor getCor() {
        return this.cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }


}