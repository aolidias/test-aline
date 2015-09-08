package br.com.aline.stream;


public class StreamImpl implements Stream{
	
	private String input;
	private int index = 0;
	
	public StreamImpl(String input){
		this.input = input;
	}
	
	public char getNext() {
		char nextChar =  input.charAt(index);
		index++;
		return nextChar;
	}

	public boolean hasNext() {
		if(index > input.length()){
			return false;
		}
		return true;
	}

}
