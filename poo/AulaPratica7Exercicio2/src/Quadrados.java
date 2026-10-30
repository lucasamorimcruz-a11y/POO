public class Quadrados extends Gerador {
    @Override
    public void gerar(int quantidade) {
        lista.clear();
        for (int i = 1; i <= quantidade; i++) lista.add(i * i);
    }
}