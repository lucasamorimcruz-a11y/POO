package gerador;

public class Quadrados extends Gerador {
    @Override
    public void gerar(int quantidade) {
        this.sequencia.clear();
        double numero = 0;
        for (int i = 0; i < quantidade; i++){
            numero = Math.sqrt(i);
            if (numero % 1 == 0){
                this.sequencia.add(i);
            }
        }
    }
}