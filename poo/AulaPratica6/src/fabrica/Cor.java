package fabrica;

public enum Cor {
    Roxo ("roxo"),
    Azul ("azul"),
    Verde ("verde"),
    Vermelho ("vermelho"),
    Branco ("branco"),
    Preto ("preto");
    private Cor (String cor){
        this.cor = cor;
    }
    private String cor;

    public String getCor() {
        return cor;
    }
}
