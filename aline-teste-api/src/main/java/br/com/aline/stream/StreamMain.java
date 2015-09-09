package br.com.aline.stream;

import java.util.ArrayList;
import java.util.List;

import br.com.aline.api.exception.CharStreamException;

/**
 * Classe que contém a lógica para retornar o primeiro char de uma Stream que não se repete.
 * @author aline.dias
 * 
 */
public class StreamMain {
	
	public static char firstChar(Stream input) {
		
		List<Character> listDiff = new ArrayList<>();
		String palavra = "";
		while(input.hasNext()){
			char next = input.getNext();
			listDiff.add(next);
			palavra = palavra + next;
		}
		for(Character c : listDiff){
			int quantidade = palavra.length() - palavra.replaceAll(String.valueOf(c), "").length();
			if(quantidade == 1){
				return c;
			}
		}
		throw new CharStreamException("Todos os caracteres das palavras se repetem.");
	}
}
