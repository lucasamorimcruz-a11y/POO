package exceptions;

public class SelectException extends Exception {

    public SelectException(String mensagem) {
        super(mensagem);
    }

    public SelectException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
