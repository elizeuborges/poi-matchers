package com.github.elizeuborges.poimatchers;

import org.apache.poi.ss.usermodel.Cell;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class CellMatcher<T> extends TypeSafeMatcher<Cell> {

	private T esperado;

	private ExtratorDeConteudoDaCelula<T> extrator;

	public CellMatcher(T esperado, ExtratorDeConteudoDaCelula<T> extrator) {
		this.esperado = esperado;
		this.extrator = extrator;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(" uma celula contendo ")
				.appendValue(esperado);
	}

	@Override
	protected void describeMismatchSafely(Cell cell, Description mismatchDescription) {
		mismatchDescription
			.appendText(" uma celula com ")
			.appendValue(extrator.extrairConteudo(cell));
	}
	
	@Override
	protected boolean matchesSafely(Cell cell) {
		return esperado.equals(extrator.extrairConteudo(cell));
	}
}
