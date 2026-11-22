package exceptions;

public class UpdateException extends Exception {

    public UpdateException(String mensagem) {
        super(mensagem);
    }

    public UpdateException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
