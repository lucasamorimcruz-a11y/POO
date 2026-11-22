import java.util.ArrayList;

public class RegistroVenda {
    private String codigoDaVenda;
    private Cliente cliente;
    private Funcionario funcionario;
    private ArrayList<Livro> livrosVendidos;
    private double valorFinal;

    RegistroVenda(){

    }
    RegistroVenda (String codigoDaVenda, Cliente cliente){
        this.codigoDaVenda = codigoDaVenda;
        this.cliente = cliente;
    }
    RegistroVenda(Cliente cliente, Funcionario funcionario){
        this.cliente = cliente;
        this.funcionario = funcionario;
    }


    public String getCodigoDaVenda() {
        return codigoDaVenda;
    }

    public void setCodigoDaVenda(String codigoDaVenda) {
        this.codigoDaVenda = codigoDaVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public ArrayList<Livro> getLivrosVendidos() {
        return livrosVendidos;
    }

    public void setLivrosVendidos(ArrayList<Livro> livrosVendidos) {
        this.livrosVendidos = livrosVendidos;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }


    double calcularValorVenda(){
        double valorVenda = 0;
        for (var livro : livrosVendidos){
            valorVenda += livro.getPreco();
        }
        return valorVenda;
    }
    boolean possuiLivrosRepetidos(){
        for (int i = 0; i < this.livrosVendidos.size(); i++){
            for (int j = i + 1; j < this.livrosVendidos.size(); j++){
                if (livrosVendidos.get(j).equals(livrosVendidos.get(i))){
                    return true;
                }
            }
        }
        return false;
    }

    void calcularValorFinal(){
        if (possuiLivrosRepetidos()) {
            this.valorFinal = calcularValorVenda() * 80/100;
        }
        else{
            this.valorFinal = calcularValorVenda();
        }
    }

}
