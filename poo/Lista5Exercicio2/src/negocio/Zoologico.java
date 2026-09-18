package negocio;
import dados.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Zoologico {
    private ArrayList<Viveiro> viveiros;
    private ArrayList<Animal> animais;
    public Zoologico() {
        viveiros = new ArrayList<>();
        animais = new ArrayList<>();
    }
    public void cadastrarViveiro (Viveiro viveiro){
        this.viveiros.add(viveiro);
    }
    public void cadastrarAnimais (Animal animal){
        this.animais.add(animal);
    }
    public boolean alocarAnimal(Animal animal, Viveiro viveiro) {
        if (animal instanceof Peixe && !(viveiro instanceof Aquario)) {
            return false;
        }
        if (!(animal instanceof Peixe) && viveiro instanceof Aquario) {
            return false;
        }
        if (viveiro instanceof Aquario) {
            Aquario aquario = (Aquario) viveiro;
            Peixe peixe = (Peixe) animal;
            if (Math.abs(aquario.getTemperatura() - peixe.getTemperaturaIdeal()) >= 3) {
                return false;
            }
            if (aquario.espacoDisponivel() <= 0.7f * animal.calcularEspacoOcupado()) {
                return false;
            }
        }
        else {
            if (viveiro.espacoDisponivel() <= 0.7f * animal.calcularEspacoOcupado()) {
                return false;
            }
        }
        viveiro.adicionarAnimal(animal);
        return true;
    }
    public ArrayList<Aquario> getSoAquarios(){
        ArrayList<Aquario> aquarios = new ArrayList<>();
        for (var viveiro : viveiros){
            if (viveiro instanceof Aquario){
                aquarios.add((Aquario) viveiro);
            }
        }
        return aquarios;
    }
    public ArrayList<Viveiro> getSoViveiro(){
        return this.viveiros;
    }

    public ArrayList<Viveiro> getViveiros() {
        return viveiros;
    }

    public void setViveiros(ArrayList<Viveiro> viveiros) {
        this.viveiros = viveiros;
    }

    public ArrayList<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(ArrayList<Animal> animais) {
        this.animais = animais;
    }
}
