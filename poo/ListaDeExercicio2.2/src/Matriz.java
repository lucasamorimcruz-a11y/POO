import java.util.ArrayList;

public class Matriz<T> {
    private int linhas;
    private int colunas;
    private ArrayList<ArrayList<T>> matriz;

    public Matriz (int n, int m){
        this.linhas = n;
        this.colunas = m;
        matriz = new ArrayList<>();
        for (int i = 0; i < n; i++){
            ArrayList<T> linha = new ArrayList<>();
            for (int j = 0; j < m; j++){
                linha.add(null);
            }
            this.matriz.add(linha);
        }
    }
    public boolean set (T objeto, int i, int j){
        if ((i < 0 && this.linhas < i) || (j < 0 && this.colunas < j)){
            System.out.println("Erro na alocação");
            return false;
        }
        this.matriz.get(i).set(j, objeto);
        return true;
    }
    public T get (int i, int j){
        if (i > this.linhas || j > this.colunas){
            return null;
        }
        return this.matriz.get(i).get(j);
    }
    public ArrayList<T> getLinha (int linha){
        ArrayList<T> _linhas = new ArrayList<>();
        for (int i = 0; i < this.colunas; i++){
            _linhas.add(this.matriz.get(linha).get(i));
        }
        return _linhas;
    }
    public ArrayList<T> getColuna (int coluna){
        ArrayList<T> _colunas = new ArrayList<>();
        for (int i = 0; i < this.linhas; i++){
            _colunas.add(this.matriz.get(i).get(coluna));
        }
        return _colunas;
    }
    public ArrayList<T> getDiagonalPrincipal(){
        ArrayList<T> diagonal = new ArrayList<>();
        for (int i = 0; i < this.linhas; i++){
            for (int j = 0; j < this.colunas; j++){
                if (i == j){
                    diagonal.add(this.matriz.get(i).get(j));
                }
            }
        }
        return diagonal;
    }

    public int getLinhas() {
        return linhas;
    }

    public void setLinhas(int linhas) {
        this.linhas = linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public void setColunas(int colunas) {
        this.colunas = colunas;
    }

    public ArrayList<ArrayList<T>> getMatriz() {
        return matriz;
    }

    public void setMatriz(ArrayList<ArrayList<T>> matriz) {
        this.matriz = matriz;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < this.getLinhas(); i++) {
            s += "[";
            for (int j = 0; j < this.getColunas(); j++) {
                s += this.get(i, j) + ", ";
            }
            s += "]\n";
        }
        return s;
    }
}
