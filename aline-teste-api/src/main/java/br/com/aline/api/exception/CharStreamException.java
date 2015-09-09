package br.com.aline.api.exception;

/**
 * Exception para quando todas os caracteres são repetidos da Stream.
 * 
 * @author aline.dias
 *
 */
public class CharStreamException  extends RuntimeException {

	private static final long serialVersionUID = -1528613998771751662L;

	public CharStreamException() {
        super();
    }

    public CharStreamException(String message, Throwable cause) {
        super(message, cause);
    }

    public CharStreamException(String message) {
        super(message);
    }

    public CharStreamException(Throwable cause) {
        super(cause);
    }

}
