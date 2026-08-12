package udesc.poo.aula_pratica2.exercicio1;

public class Grupo {
    private Pessoa []grupo = new Pessoa[10];
    private int quantidade_de_pessoas = 0;

    public Grupo (){

    }

    void adicionarPessoa (){
        Pessoa p = new Pessoa();
        this.grupo[this.quantidade_de_pessoas] = p;
        this.quantidade_de_pessoas++;
    }
    void ordenar (){
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if (this.grupo[j].calcularIMC() > this.grupo[i].calcularIMC()){
                    Pessoa aux = this.grupo[j];
                    this.grupo[j] = this.grupo[i];
                    this.grupo[i] = aux;
                }
            }
        }
    }
    void exibir (){
        for (int i = 0; i < this.quantidade_de_pessoas; i++){
            this.grupo[i].toString();
        }
    }
}
