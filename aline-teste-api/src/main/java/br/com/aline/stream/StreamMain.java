package br.com.aline.stream;

public class StreamMain {
	
	public static char firstChar(Stream input) {
		char firstDiff = 0;
		char last = 0;
		while(input.hasNext()){
			last = input.getNext();
			if(firstDiff != last){
				firstDiff = last;
			}
		}
		return firstDiff;
	}
}
