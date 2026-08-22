package dados;

public class Animal {
    private String nome;
    private Dono dono;
    private String especie;
    private String descricao;

    public Animal(String nome, Dono dono, String especie, String descricao) {
        this.nome = nome;
        this.dono = dono;
        this.especie = especie;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "nome='" + nome + '\'' +
                ", dono=" + dono +
                ", especie='" + especie + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public Animal (String nome, String especie, String descricao){
        this.nome = nome;
        this.especie = especie;
        this.descricao = descricao;
    }
    public Animal (){

    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Dono getDono() {
        return dono;
    }

    public void setDono(Dono dono) {
        this.dono = dono;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}
