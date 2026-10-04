package gerador;

public class Fatoriais extends Gerador {
    @Override
    public void gerar(int quantidade) {
        this.sequencia.clear();
        int resultado = 1;
        for (int i = 1; i <=quantidade; i++){
            resultado *= i;
            this.sequencia.add(resultado);
        }
        return;
    }
}