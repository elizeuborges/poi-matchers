package org.apache.poi.matchers;

import org.apache.poi.matchers.Coordenada
import org.apache.poi.matchers.ResolvedorDeCoordenada
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.hamcrest.Description;
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeDiagnosingMatcher;

class WorkbookMatcher extends TypeSafeDiagnosingMatcher<Workbook> {

	private Coordenada coordenada;
	
	private int sheetIndice = 0;
	
	private Matcher<Cell> cellMatcher;
	
	private WorkbookMatcher(Matcher<Cell> cellMatcher) {
		this.cellMatcher = cellMatcher;
	}

	private void garantaQueOMatcherEstaEmUmEstadoIntegro(){
		if (!coordenada) {
			throw new IllegalStateException('''
			Matcher não finalizado corretamente. Você não informou a célula.
			Exemplo de chamada válida:				
				assertThat(worbook, possuiTexto("Algum texto").naCelula("A10"));''') 
		}
	}
	
	@Override
	void describeTo(Description description) {
		description.appendDescriptionOf(cellMatcher);
	}

	@Override
	protected boolean matchesSafely(Workbook workbook, Description mismatchDescription) {
		garantaQueOMatcherEstaEmUmEstadoIntegro();
		int numeroDeFolhas = workbook.getNumberOfSheets();
		if (numeroDeFolhas < sheetIndice+1) {
			mismatchDescription
				.appendText("uma planilha com ")
				.appendValue(numeroDeFolhas)
				.appendText(" folhas");
			return false;
		}
		Sheet sheet = workbook.getSheetAt(sheetIndice);
		Cell cell = getCellDaCoordenada(sheet);
		if (cell) {
			boolean matches = cellMatcher.matches(cell);
			cellMatcher.describeMismatch(cell, mismatchDescription)
			return matches;
		}
		mismatchDescription.appendText("uma planilha com a celula informada não criada");
		return false;
	}

	private Cell getCellDaCoordenada(Sheet sheet) {
		Row row = sheet.getRow(coordenada.getLinha());
		Cell cell = null;
		if (row) {
			cell = row.getCell(coordenada.getColuna());
		}
		return cell;
	}

	WorkbookMatcher naCelula(String celula) {
		coordenada = ResolvedorDeCoordenada.resolver(celula);
		return this;
	}
	
	static WorkbookMatcher estaCom(Matcher<Cell> cellMatcher) {
		return new WorkbookMatcher(cellMatcher);
	}

	static WorkbookMatcher estaCom(valorEsperado) {
		return new WorkbookMatcher(new CellMatcher(valorEsperado, TiposBasicosExtrator.paraClasse(valorEsperado.class)));
	}
	
}
