package gerador;

import java.util.ArrayList;
import java.util.List;

public class Naturais extends Gerador {
    @Override
    public void gerar(int quantidade) {
        this.sequencia.clear();
        for (int i = 0; i < quantidade; i++){
            this.sequencia.add(i);
        }
        return;
    }

}