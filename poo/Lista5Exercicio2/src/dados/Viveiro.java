package dados;

import java.util.ArrayList;

public class Viveiro {
    protected String nome;
    protected float comprimento;
    protected float largura;
    private ArrayList<Animal> animais = new ArrayList<>();

    public Viveiro (){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getComprimento() {
        return comprimento;
    }

    public void setComprimento(float comprimento) {
        this.comprimento = comprimento;
    }

    public float getLargura() {
        return largura;
    }

    public void setLargura(float largura) {
        this.largura = largura;
    }

    public ArrayList<Animal> getAnimais() {
        return animais;
    }
    public void setAnimais (ArrayList<Animal>animais){
        this.animais = animais;
    }

    public float calculaEspaco (){
        return this.comprimento * this.largura;
    }
    private float EspacoOcupado(){
        float espaco = 0;
        for (var animal : animais){
            espaco += animal.calcularEspacoOcupado();
        }
        return espaco;
    }
    public float espacoDisponivel (){
        return this.calculaEspaco() - this.EspacoOcupado();
    }
    public boolean adicionarAnimal(Animal animal){
        this.animais.add(animal);
        return true;
    }
    public boolean equals (Object obj){
        if (obj instanceof Viveiro){
            Viveiro _v = (Viveiro) obj;
            return this.animais.equals(_v.animais) && this.comprimento == _v.comprimento && this.largura == _v.largura && this.nome.equals(_v.nome);
        }
        return false;
    }
}


