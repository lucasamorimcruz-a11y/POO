package udesc.poo.mooc;

public class tryCatchExample {
    public void main (String[] args){
        try{
            int num1;
            int num2;
            num1 = 10;
            num2 = 0;
            int c = num1/num2;
        } catch (ArithmeticException e){
            System.err.println("Não pode dividir por 0");
        }
    }
}
