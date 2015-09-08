package br.com.aline.api.exception;

/**
 * Exception para cep inválido.
 * 
 * @author aline.dias
 *
 */
public class InvalidCepException  extends RuntimeException {

	private static final long serialVersionUID = -1528613998771751662L;

	public InvalidCepException() {
        super();
    }

    public InvalidCepException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCepException(String message) {
        super(message);
    }

    public InvalidCepException(Throwable cause) {
        super(cause);
    }

}
