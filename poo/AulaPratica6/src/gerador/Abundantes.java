package gerador;

public class Abundantes extends Gerador {
    @Override
    public void gerar(int quantidade) {
        this.sequencia.clear();
        int soma = 0;
        for (int i = 1; i < quantidade; i++){
            for (int j =1; j < i; j++){
                if (i % j == 0){
                    soma += j;
                }

            }
            if (quantidade  < soma){
                this.sequencia.add(i);
            }
        }
        return;
    }
}
