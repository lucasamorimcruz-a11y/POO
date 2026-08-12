package udesc.poo.aula_pratica2.petshop;

public class Animal {
    private String nome;
    private Dono dono;
    private String especie;
    private String descricao;
    Animal (){

    }
    public String getNome() {
        return nome;
    }

    public Dono getDono() {
        return dono;
    }

    public String getEspecie() {
        return especie;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDono(Dono dono) {
        this.dono = dono;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
