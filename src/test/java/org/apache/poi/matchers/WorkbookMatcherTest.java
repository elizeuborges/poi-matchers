package org.apache.poi.matchers;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.matchers.WorkbookMatcher;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WorkbookMatcherTest {

	@Mock
	private Workbook workbook;
	
	@Mock
	private Sheet sheet;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setUp() {
		Mockito.when(workbook.getNumberOfSheets()).thenReturn(1);
		Mockito.when(workbook.getSheetAt(Mockito.anyInt())).thenReturn(sheet);
		Row row = Mockito.mock(Row.class);
		Mockito.when(sheet.getRow(Mockito.anyInt())).thenReturn(row);
	}
	
	@Test
	public void deveLancarExcecaoSeMatcherIncompleto() throws Exception {
		WorkbookMatcher matcher = WorkbookMatcher.estaCom("textoEsperado");
		thrown.expect(IllegalStateException.class);
		thrown.expectMessage(containsString("não informou a célula"));
		matcher.matches(workbook);
	}

	@Test
	public void deveRetornarFalseSeNenhumaFolhaTiverSidoCriada() throws Exception {
		WorkbookMatcher matcher = WorkbookMatcher.estaCom("textoEsperado").naCelula("A1");
		boolean matches = matcher.matches(new HSSFWorkbook());
		assertThat(matches, is(false));
	}

	@Test
	public void deveRetornarFalseSeRowInformadaNaoTiverSidoCriada() throws Exception {
		WorkbookMatcher matcher = WorkbookMatcher.estaCom("textoEsperado").naCelula("A1");
		boolean matches = matcher.matches(new HSSFWorkbook());
		assertThat(matches, is(false));
	}
	
	@Test
	public void deveRetornarFalseSeCelulaInformadaNaoTiverSidoCriada() throws Exception {
		WorkbookMatcher matcher = WorkbookMatcher.estaCom("textoEsperado").naCelula("A1");
		boolean matches = matcher.matches(new HSSFWorkbook());
		assertThat(matches, is(false));
	}
	
	@Test
	public void deveVerificarTextoDaCelula() throws Exception {
		WorkbookMatcher matcher = WorkbookMatcher.estaCom("textoEsperado").naCelula("A1");
		Workbook workbook = new WorkbookBuilder()
				.comValorNaLinhaColuna("textoEsperado", 0, 0)
				.build();
		boolean matches = matcher.matches(workbook);
		assertThat(matches, is(true));
	}
	
	@Test
	public void deveVerificarBooleanDaCelula() throws Exception {
		WorkbookMatcher matcher = WorkbookMatcher.estaCom(true).naCelula("B2");
		Workbook workbook = new WorkbookBuilder()
				.comValorNaLinhaColuna(true, 1, 1)
				.build();
		boolean matches = matcher.matches(workbook);
		assertThat(matches, is(true));
	}
	
	@Test
	public void deveVerificarIntDaCelula() throws Exception {
		WorkbookMatcher matcher = WorkbookMatcher.estaCom(10).naCelula("B2");
		Workbook workbook = new WorkbookBuilder()
				.comValorNaLinhaColuna(10, 1, 1)
				.build();
		boolean matches = matcher.matches(workbook);
		assertThat(matches, is(true));
	}
	
	@Test
	public void deveVerificarLongDaCelula() throws Exception {
		WorkbookMatcher matcher = WorkbookMatcher.estaCom(10L).naCelula("B2");
		Workbook workbook = new WorkbookBuilder()
				.comValorNaLinhaColuna(10L, 1, 1)
				.build();
		boolean matches = matcher.matches(workbook);
		assertThat(matches, is(true));
	}
	
	@Test
	public void deveVerificarDoubleDaCelula() throws Exception {
		WorkbookMatcher matcher = WorkbookMatcher.estaCom(15D).naCelula("B2");
		Workbook workbook = new WorkbookBuilder()
				.comValorNaLinhaColuna(15D, 1, 1)
				.build();
		boolean matches = matcher.matches(workbook);
		assertThat(matches, is(true));
	}
	
	@Test
	public void deveVerificarFloatDaCelula() throws Exception {
		WorkbookMatcher matcher = WorkbookMatcher.estaCom(10F).naCelula("B2");
		Workbook workbook = new WorkbookBuilder()
				.comValorNaLinhaColuna(10F, 1, 1)
				.build();
		boolean matches = matcher.matches(workbook);
		assertThat(matches, is(true));
	}
	
	@Test
	public void deveVerificarBigDecimalDaCelula() throws Exception {
		WorkbookMatcher matcher = WorkbookMatcher.estaCom(BigDecimal.valueOf(34.7)).naCelula("B2");
		Workbook workbook = new WorkbookBuilder()
				.comValorNaLinhaColuna(BigDecimal.valueOf(34.7), 1, 1)
				.build();
		boolean matches = matcher.matches(workbook);
		assertThat(matches, is(true));
	}
	
	@Test
	public void deveVerificarDataDaCelula() throws Exception {
		Date data = new Date();
		WorkbookMatcher matcher = WorkbookMatcher.estaCom(data).naCelula("B2");
		Workbook workbook = new WorkbookBuilder()
				.comValorNaLinhaColuna(data, 1, 1)
				.build();
		boolean matches = matcher.matches(workbook);
		assertThat(matches, is(true));
	}
	
}
