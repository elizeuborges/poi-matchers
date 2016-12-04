package org.apache.poi.matchers

import org.apache.poi.matchers.ExtratorDeConteudoDaCelula
import org.apache.poi.ss.usermodel.Cell
import org.hamcrest.Description
import org.hamcrest.TypeSafeDiagnosingMatcher
import org.hamcrest.TypeSafeMatcher

class CellMatcher<T> extends TypeSafeMatcher<Cell> {

	private T esperado;

	private ExtratorDeConteudoDaCelula extrator

	private Description mismatchDescription

	public CellMatcher(T esperado, ExtratorDeConteudoDaCelula<T> extrator) {
		this.esperado = esperado;
		this.extrator = extrator;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(" era esperado uma celula contendo ")
				.appendValue(esperado);
	}

	@Override
	protected void describeMismatchSafely(Cell cell, Description mismatchDescription) {
		mismatchDescription
			.appendText(" uma celula com ")
			.appendValue(extrator.extrairValor(cell))
	}
	
	@Override
	protected boolean matchesSafely(Cell cell) {
		return esperado.equals(extrator.extrairValor(cell));
	}
}
