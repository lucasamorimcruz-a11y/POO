package gerador;


public class Perfeitos extends Gerador{
    @Override
    public void gerar(int quantidade) {
        this.sequencia.clear();
        int soma = 0;
        for (int i = 1; i <quantidade;i++){
            for (int j = 1; j < i; j++){
                if (i % j == 0){
                    soma+= j;
                }
            }
            if (soma == i){
                this.sequencia.add(i);
            }
            soma = 0;
        }
    }
}