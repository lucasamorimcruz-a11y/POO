package exceptions;

public class InsertException extends Exception {

    public InsertException(String mensagem) {
        super(mensagem);
    }

    public InsertException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
