import java.util.ArrayList;
import java.util.Random;

public abstract class Gerador implements ISequencia{
    ArrayList<Integer> lista = new ArrayList<>();

    public abstract void gerar (int n );

    @Override
    public int sortear() {
        int index = 0;
        Random r = new Random();
        index = r.nextInt(lista.size());
        return lista.get(index);
    }

    @Override
    public long somatorio(){
        long soma = 0;
        for (int i = 0; i < lista.size(); i++){
            soma += lista.get(i);
        }
        return soma;
    }

    @Override
    public long produtorio(){
        long p = 1;
        for (var i : lista){
            p *= i;
        }
        return p;
    }

    @Override
    public double mediaAritmetica() {
        return (double)somatorio() / lista.size();
    }

    @Override
    public double mediaGeometrica() {
        return Math.pow(produtorio(), 1.0 / (double) (lista.size()));
    }

    @Override
    public double variancia() {
        final double media = mediaAritmetica();
        double soma = 0;
        for (var i : lista) {
            soma += Math.pow(i - media, 2);
        }
        return soma / ((double) (lista.size()) - 1);
    }

    @Override
    public double desvioPadrao() {
        return Math.sqrt(variancia());
    }

    @Override
    public long amplitude() {
        long mn = lista.get(0), mx = lista.get(0);
        for (int i : lista) {
            if (i < mn) mn = i;
            if (i > mx) mx = i;
        }
        return mx - mn;
    }

    public ArrayList<Integer> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Integer> lista) {
        this.lista = lista;
    }
}
