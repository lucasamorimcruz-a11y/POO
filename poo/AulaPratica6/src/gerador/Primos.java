package gerador;

public class Primos extends Gerador {
    @Override
    public void gerar(int quantidade) {
        this.sequencia.clear();
        int curr = 2;
        for (int i = 1; i <= quantidade; i++){
            for (int j = 1; j <= i; j++){
                if (i % j == 0){
                    curr--;
                }
            }
            if (curr == 0){
                this.sequencia.add(i);
            }
            curr = 2;
        }
    }
}