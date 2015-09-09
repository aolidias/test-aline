package br.com.aline.stream.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.aline.api.exception.CharStreamException;
import br.com.aline.stream.StreamImpl;
import br.com.aline.stream.StreamMain;

/**
 * 
 * Classe que contém os testes da classe StreamMain.
 * @author aline.dias
 *
 */
public class StreamMainTest{
	
	public ExpectedException exception = ExpectedException.none();
	
	/**
	 * Método que testa diferentes casos da StreamMain com sucesso.
	 */
	@Test
	public void streamMainSuccessoTeste(){
		StreamImpl testeUm = new StreamImpl("aAbBABac");
		char testeUmChar = StreamMain.firstChar(testeUm);
		assertEquals('b', testeUmChar);
		
		StreamImpl testeDois = new StreamImpl("0000009001");
		char testeDoisChar = StreamMain.firstChar(testeDois);
		assertEquals('9', testeDoisChar);
		
		StreamImpl testeTres = new StreamImpl("aAbBbABc");
		char testeTresChar = StreamMain.firstChar(testeTres);
		assertEquals('a', testeTresChar);
		
		StreamImpl testeQuatro = new StreamImpl("!!#%#&%@");
		char testeQuatroChar = StreamMain.firstChar(testeQuatro);
		assertEquals('&', testeQuatroChar);
	}
	

	/**
	 * Método que testa a classe StreamMain quando todos os caracteres da Stream são repetidos. 
	 */
	@Test(expected=CharStreamException.class)
	public void streamMainComTodosCaracteresRepetidosTest(){
		StreamImpl testeUm = new StreamImpl("aABABa");
		StreamMain.firstChar(testeUm);
	}

}