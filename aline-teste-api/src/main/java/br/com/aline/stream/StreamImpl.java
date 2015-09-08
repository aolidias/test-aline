package br.com.aline.stream;


public class StreamImpl implements Stream{
	
	private String input;
	
	public StreamImpl(String input){
		this.input = input;
	}
	
	public char getNext() {
		return 0;
	}

	@Override
	public boolean hasNext() {
		return false;
	}

}
