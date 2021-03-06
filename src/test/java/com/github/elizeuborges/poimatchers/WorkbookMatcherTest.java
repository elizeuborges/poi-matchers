package com.github.elizeuborges.poimatchers;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import com.github.elizeuborges.poimatchers.WorkbookMatcher;

public class WorkbookMatcherTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void deveLancarExcecaoSeMatcherIncompleto() throws Exception {
		WorkbookMatcher matcher = WorkbookMatcher.estaCom("textoEsperado");
		thrown.expect(IllegalStateException.class);
		thrown.expectMessage(containsString("não informou a célula"));
		matcher.matches(new HSSFWorkbook());
	}
	
	@Test
	public void deveLancarExcecaoTipoInformadoNaoForConhecido() throws Exception {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(containsString("Tipo java.lang.Object não esperado."));
		WorkbookMatcher matcher = WorkbookMatcher.estaCom(new Object()).naCelula("A1");
		matcher.matches(new HSSFWorkbook());
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
		Workbook workbook = new HSSFWorkbook();
		workbook.createSheet();
		boolean matches = matcher.matches(workbook);
		assertThat(matches, is(false));
	}
	
	@Test
	public void deveRetornarFalseSeCelulaInformadaNaoTiverSidoCriada() throws Exception {
		WorkbookMatcher matcher = WorkbookMatcher.estaCom("textoEsperado").naCelula("A1");
		Workbook workbook = new HSSFWorkbook();
		workbook.createSheet().createRow(0);
		boolean matches = matcher.matches(workbook);
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
	public void deveVerificarTextoInseridoComoRichTextString() throws Exception {
		WorkbookMatcher matcher = WorkbookMatcher.estaCom("textoEsperado").naCelula("A1");
		Workbook workbook = new HSSFWorkbook();
		workbook.createSheet().createRow(0).createCell(0).setCellValue(new HSSFRichTextString("textoEsperado"));
		boolean matches = matcher.matches(workbook);
		assertThat(matches, is(true));
	}
	
	@Test
	public void deveRetornarFalseSeValorDaCelulaForDiferente() throws Exception {
		thrown.expect(AssertionError.class);
		WorkbookMatcher matcher = WorkbookMatcher.estaCom("Um texto").naCelula("A1");
		Workbook workbook = new WorkbookBuilder()
				.comValorNaLinhaColuna("Outro texto", 0, 0)
				.build();
		assertThat(workbook, is(matcher));
		thrown.expectMessage(containsString("uma celula contendo \"Um texto\" na folha <0>, linha <0> e coluna <0>"));
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
	
	@Test
	public void deveSerPossivelFornecerMatcherCustomizado() throws Exception {
		@SuppressWarnings("unchecked")
		Matcher<Cell> cellMatcher = Mockito.mock(Matcher.class);
		WorkbookMatcher matcher = WorkbookMatcher.estaCom(cellMatcher).naCelula("A1");
		Workbook workbook = new WorkbookBuilder()
				.comValorNaLinhaColuna("Oias", 0, 0)
				.build();
		matcher.matches(workbook);
		Mockito.verify(cellMatcher, Mockito.times(1)).matches(Mockito.any());
	}
	
	@Test
	public void deveSerPossivelFornecerUmExtratorDeConteudoDaCelulaCustomizado() throws Exception {
		@SuppressWarnings("unchecked")
		ExtratorDeConteudoDaCelula<BigInteger> extrator = Mockito.mock(ExtratorDeConteudoDaCelula.class);
		Mockito.when(extrator.extrairConteudo(Mockito.any(Cell.class))).thenReturn(BigInteger.valueOf(100));
		WorkbookMatcher matcher = WorkbookMatcher.estaCom(BigInteger.valueOf(100), extrator).naCelula("A1");
		Workbook workbook = new WorkbookBuilder()
				.comValorNaLinhaColuna(100, 0, 0)
				.build();
		boolean matches = matcher.matches(workbook);
		assertThat(matches, is(true));
	}
	
}
