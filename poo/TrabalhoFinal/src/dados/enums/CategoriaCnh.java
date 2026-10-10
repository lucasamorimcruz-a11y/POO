package dados.enums;

public enum CategoriaCnh {
    A("A"), B("B"), C("C"), D("D"), E("E");
    private String categoria;
    CategoriaCnh (String categoria){
        this.categoria = categoria;
    }
    public String getCategoria() {
        return categoria;
    }
}
