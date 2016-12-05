package com.github.elizeuborges.poimatchers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 * Matcher principal para verificações em {@link Workbook}
 * 
 * @author Elizeu
 *
 */
public class WorkbookMatcher extends TypeSafeDiagnosingMatcher<Workbook> {

	private Coordenada coordenada;
	
	private int sheetIndice = 0;
	
	private Matcher<Cell> cellMatcher;
	
	private WorkbookMatcher(Matcher<Cell> cellMatcher) {
		this.cellMatcher = cellMatcher;
	}

	private void garantaQueOMatcherEstaEmUmEstadoIntegro(){
		if (coordenada == null) {
			throw new IllegalStateException(
			"Matcher não finalizado corretamente. Você não informou a célula."+
			"Exemplo de chamada válida:"+
			"	assertThat(worbook, possuiTexto(\"Algum texto\").naCelula(\"A10\"));"); 
		}
	}
	
	/* (non-Javadoc)
	 * @see org.hamcrest.SelfDescribing#describeTo(org.hamcrest.Description)
	 */
	@Override
	public void describeTo(Description description) {
		description.appendDescriptionOf(cellMatcher)
		.appendText(" na folha ")
		.appendValue(sheetIndice)
		.appendText(", linha ")
		.appendValue(coordenada.getLinha())
		.appendText(" e coluna ")
		.appendValue(coordenada.getColuna())
		;
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
		if (cell != null) {
			boolean matches = cellMatcher.matches(cell);
			cellMatcher.describeMismatch(cell, mismatchDescription);
			return matches;
		}
		mismatchDescription.appendText("uma planilha com a celula informada não criada");
		return false;
	}

	private Cell getCellDaCoordenada(Sheet sheet) {
		Row row = sheet.getRow(coordenada.getLinha());
		Cell cell = null;
		if (row != null) {
			cell = row.getCell(coordenada.getColuna());
		}
		return cell;
	}

	/**
	 * Define a célula que será realizada a asserção.
	 * <br>
	 * <b>EX:</b>
	 * <br>
	 * <i>
	 * Verificando se na primeira coluna da primeira linha está preenchida com a String 
	 * "Um valor qualquer"</i>
	 * <code>
	 * <pre>
	 * Workbook workbook = ...
	 * 
	 * assertThat(workbook, estaCom("Um valor qualquer").naCelula("A1"));
	 * </pre>
	 * </code>
	 * 
	 * @param coordenada Coordenada XY da célula que se deseja 
	 * realizar a asserção.
	 * @return this WorkbookMatcher
	 */
	public WorkbookMatcher naCelula(String coordenada) {
		this.coordenada = ResolvedorDeCoordenada.resolver(coordenada);
		return this;
	}
	
	/**
	 * Define o valor que se espera obter da célula e a estratégia de extrair o conteudo da {@link Cell}
	 * 
	 * @param esperado valor que se espera obter
	 * @param extrator estratégia de extrair o conteúdo da {@link Cell}
	 * @return this WorkbookMatcher
	 */
	public static <T> WorkbookMatcher estaCom(T esperado, ExtratorDeConteudoDaCelula<T> extrator) {
		return new WorkbookMatcher((CellMatcher<T>) new CellMatcher<T>(esperado, extrator));
	}

	/**
	 * Define o {@link Matcher} que verificará a célula especificada se a mesma não for null
	 * 
	 * @param cellMatcher
	 * @return this WorkbookMatcher
	 */
	public static WorkbookMatcher estaCom(Matcher<Cell> cellMatcher) {
		return new WorkbookMatcher(cellMatcher);
	}

	/**
	 * Define valor que se espera obter da célula
	 * 
	 * @param valorEsperado
	 * @return this WorkbookMatcher
	 * @see TiposBasicosExtrator
	 * @throws IllegalArgumentException  conforme especificado em {@link TiposBasicosExtrator#paraClasse(Class)}
	 */
	@SuppressWarnings({ "unchecked" })
	public static <T> WorkbookMatcher estaCom(T valorEsperado) {
		return estaCom(valorEsperado, TiposBasicosExtrator.paraClasse((Class<T>)valorEsperado.getClass()));
	}
	
}
