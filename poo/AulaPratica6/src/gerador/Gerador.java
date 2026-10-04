package gerador;

import java.util.ArrayList;
import java.util.List;

public abstract class Gerador {
    protected List<Integer> sequencia = new ArrayList<>();

    public  abstract void gerar (int quantidade);

    public List<Integer> getSequencia (){
        return this.sequencia;
    }
    public String toString() {
        return sequencia.toString();
    }

}