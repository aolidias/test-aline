package br.com.aline.api.exception;

/**
 * Exception para endereço não encontrado. 
 * 
 * @author aline.dias
 *
 */
public class EnderecoNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -316018858132671255L;

	public EnderecoNotFoundException() {
        super();
    }

    public EnderecoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EnderecoNotFoundException(String message) {
        super(message);
    }

    public EnderecoNotFoundException(Throwable cause) {
        super(cause);
    }
}
