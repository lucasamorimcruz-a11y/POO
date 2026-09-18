package dados;

public class Peixe extends Animal {
    private float temperaturaIdeal;
    public Peixe(){
        super();
    }
    public float getTemperaturaIdeal() {
        return temperaturaIdeal;
    }
    public void setTemperaturaIdeal(float temperaturaIdeal) {
        this.temperaturaIdeal = temperaturaIdeal;
    }
    @Override
    public float calcularEspacoOcupado() {
        return this.largura * this.comprimento * this.altura;
    }
}