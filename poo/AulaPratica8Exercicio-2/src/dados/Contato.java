package dados;

import java.util.Objects;

public class Contato {
    private String nome;
    private int telefone;

    public Contato(String nome, int telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public Contato() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Contato){
            Contato c = (Contato) o;
            return this.nome.equals(c.getNome()) && this.telefone == c.getTelefone();
        }
        return false;
    }


    @Override
    public String toString() {
        return nome + " - " + telefone;
    }
}