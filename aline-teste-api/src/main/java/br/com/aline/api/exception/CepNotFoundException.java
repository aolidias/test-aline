package br.com.aline.api.exception;

/**
 * Exception para cep não encontrado. 
 * 
 * @author aline.dias
 *
 */
public class CepNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -316018858132671255L;

	public CepNotFoundException() {
        super();
    }

    public CepNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CepNotFoundException(String message) {
        super(message);
    }

    public CepNotFoundException(Throwable cause) {
        super(cause);
    }
}
