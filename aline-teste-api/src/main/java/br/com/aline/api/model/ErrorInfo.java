package br.com.aline.api.model;

/**
 * modelo para a classe de retorno de erro
 * 
 * @author aline.dias
 *
 */
public class ErrorInfo {
	private String errorMessage;
	
	public ErrorInfo(){
		super();
	}
	public ErrorInfo(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
