package exceptions;

public class DeleteException extends Exception {

    public DeleteException(String mensagem) {
        super(mensagem);
    }

    public DeleteException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
