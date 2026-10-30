package dados.enums;

public enum FormaDePagamento {
    DINHEIRO ("Dinheiro"),
    PIX ("Pix"),
    CARTAO ("Cartao"),
    BOLETO ("Boleto"),
    FATURADO ("Faturado");
    String forma;
    FormaDePagamento(String forma) {
        this.forma = forma;
    }
    public String getForma() {
        return forma;
    }
}
