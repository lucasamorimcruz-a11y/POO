public class Fibonacci extends Gerador {
    @Override
    public void gerar(int quantidade) {
        this.lista.clear();
        lista.add(0);
        lista.add (1);
        for (int i = 0; i < quantidade - 2; i++){
            int n1 = lista.get(lista.size()-1);
            int n2 = lista.get(lista.size()-2);
            lista.add (n1 + n2);
        }
    }
}