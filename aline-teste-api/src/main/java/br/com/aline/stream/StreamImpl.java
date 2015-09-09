package br.com.aline.stream;


/**
 * Classe que implenta a interface Stream.
 * 
 * @author aline.dias
 *
 */
public class StreamImpl implements Stream{
	
	private String input;
	private int index = 0;
	
	public StreamImpl(String input){
		this.input = input;
	}
	
	/**
	 * Método que retorna o char de acordo com o index. 
	 * @author aline.dias
	 *
	 */
	public char getNext() {
		char nextChar =  input.charAt(index);
		index++;
		return nextChar;
	}
	
	/**
	 * Método que verifica se existe próximo char na String.
	 * @author aline.dias
	 *
	 */
	public boolean hasNext() {
		if(index < input.length()){
			return true;
		}
		return false;
	}
	
}
