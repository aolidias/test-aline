package br.com.aline.stream;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.util.CollectionUtils;

public class StreamMain {
	
	

	public static char firstChar(Stream input) {
		Set<Character> listDiff = new HashSet<>();
		List<Character> notRepeatCharaters = new ArrayList<>();
		while(input.hasNext()){
			char next = input.getNext();
			boolean add = listDiff.add(input.getNext());
			if(!add){
				notRepeatCharaters.add(next);
			}
		}
		if(CollectionUtils.isEmpty(notRepeatCharaters)){
			throw new RuntimeException();
		}else{
			return listDiff.iterator().next();
		}
	}
}
