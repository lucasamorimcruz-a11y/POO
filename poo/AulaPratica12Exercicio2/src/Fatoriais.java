public class Fatoriais extends Gerador {
    @Override
    public void gerar(int quantidade) {
        this.lista.clear();
        int resultado = 1;
        for (int i = 1; i <=quantidade; i++){
            resultado *= i;
            this.lista.add(resultado);
        }
        return;
    }
}