package dados;

public class Aquario extends Viveiro {
    private float altura;
    private float temperatura;
    public Aquario (){
        super();
    }
    public float getAltura() {
        return altura;
    }
    public void setAltura(float altura) {
        this.altura = altura;
    }
    public float getTemperatura() {
        return temperatura;
    }
    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }
    @Override
    public float calculaEspaco (){
         return this.largura * this.comprimento * this.altura;
    }

}
