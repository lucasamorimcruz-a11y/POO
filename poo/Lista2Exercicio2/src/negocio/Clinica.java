package negocio;

import dados.*;
import java.time.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Clinica {
    private ArrayList<Veterinario> veterinarios = new ArrayList<>();
    private ArrayList<Dono> donos = new ArrayList<>();
    private ArrayList<Consulta> consultas = new ArrayList<>();
    private ArrayList<Animal> animais = new ArrayList<>();

    public void cadastrarAnimal(Animal animal) {
        this.animais.add(animal);
    }

    public void cadastrarVeterinario(Veterinario veterinario) {
        this.veterinarios.add(veterinario);
    }

    public void cadastrarDono(Dono dono) {
        this.donos.add(dono);
    }

    public void marcarConsulta(Consulta consulta) {
        this.consultas.add(consulta);
    }

    public void desmarcarConsulta(Consulta consulta) {
        this.consultas.remove(consulta);
    }

    public ArrayList<Consulta> buscarConsultasPorData(LocalDate data) {
        ArrayList<Consulta> ordenada = new ArrayList<>();
        for (Consulta consulta : consultas) {
            if (consulta.getData().equals(data)) {
                ordenada.add(consulta);
            }
        }
        ordenada.sort(Comparator.comparing(Consulta::getHorario));
        return ordenada;
    }
}