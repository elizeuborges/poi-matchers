package com.github.elizeuborges.poimatchers;

import org.apache.poi.ss.usermodel.Cell;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Matcher para verificar conteudo de uma {@link Cell}.
 * 
 * @author Elizeu Borges
 *
 * @param <T> tipo de dado que se espera obter da célula
 */
public class CellMatcher<T> extends TypeSafeMatcher<Cell> {

	private T esperado;

	private ExtratorDeConteudoDaCelula<T> extrator;

	/**
	 * @param esperado valor que se espera obter da célula
	 * @param extrator estratégia de extração do conteúdo da célula
	 */
	public CellMatcher(T esperado, ExtratorDeConteudoDaCelula<T> extrator) {
		this.esperado = esperado;
		this.extrator = extrator;
	}

	/* (non-Javadoc)
	 * @see org.hamcrest.SelfDescribing#describeTo(org.hamcrest.Description)
	 */
	@Override
	public void describeTo(Description description) {
		description.appendText(" uma celula contendo ")
				.appendValue(esperado);
	}

	/* (non-Javadoc)
	 * @see org.hamcrest.TypeSafeMatcher#describeMismatchSafely(java.lang.Object, org.hamcrest.Description)
	 */
	@Override
	protected void describeMismatchSafely(Cell cell, Description mismatchDescription) {
		mismatchDescription
			.appendText(" uma celula com ")
			.appendValue(extrator.extrairConteudo(cell));
	}
	
	/* (non-Javadoc)
	 * @see org.hamcrest.TypeSafeMatcher#matchesSafely(java.lang.Object)
	 */
	@Override
	protected boolean matchesSafely(Cell cell) {
		return esperado.equals(extrator.extrairConteudo(cell));
	}
}
