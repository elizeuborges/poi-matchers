package com.github.elizeuborges.poimatchers;

import org.apache.poi.ss.usermodel.Cell;

/**
 * Define uma estratégia de extração do conteúdo de uma {@link Cell}
 * 
 * @author Elizeu Borges
 *
 * @param <T> tipo de dado a ser extraido ca céelula
 */
public interface ExtratorDeConteudoDaCelula<T> {
	
	/**
	 * Recebe uma {@link Cell} não nulla e deve retornar um valor extraido dela.
	 * 
	 * @param cell não <code>null</code>
	 * @return o conteúdo da {@link Cell}
	 */
	T extrairConteudo(Cell cell);

}
