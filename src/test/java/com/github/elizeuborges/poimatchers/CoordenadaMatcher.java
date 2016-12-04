package com.github.elizeuborges.poimatchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import com.github.elizeuborges.poimatchers.Coordenada;

public class CoordenadaMatcher extends TypeSafeMatcher<Coordenada> {
	
	private int coluna;

	private int linha;

	public CoordenadaMatcher(int coluna, int linha) {
		this.coluna = coluna;
		this.linha = linha;
	}
	
	public static Matcher<Coordenada> is(int coluna, int linha) {
		return new CoordenadaMatcher(coluna, linha);
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("era esperado");
		descreverEstaCoordenada(description, new Coordenada(coluna, linha));
	}

	private Description descreverEstaCoordenada(Description description, Coordenada coordenada) {
		return description.appendText(" uma Coordenada com coluna ")
		.appendValue(coordenada.getColuna())
		.appendText(" e com linha ")
		.appendValue(coordenada.getLinha());
	}

	@Override
	protected void describeMismatchSafely(Coordenada coordenada, Description mismatchDescription) {
		mismatchDescription.appendText("foi recebido");
		descreverEstaCoordenada(mismatchDescription, coordenada);
	}
	
	@Override
	protected boolean matchesSafely(Coordenada coordenada) {
		return coordenada.getLinha() == linha
				&&
			   coordenada.getColuna() == coluna;
	}
}