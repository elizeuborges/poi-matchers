package com.github.elizeuborges.poimatchers;

import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class WorkbookBuilder {

	private Workbook workbook;

	private int sheetIndex = 0;

	public WorkbookBuilder() {
		workbook = new HSSFWorkbook();
	}
	
	public Workbook build() {
		return workbook;
	}

	private Sheet getOrCreateSheet(Workbook workbook) {
		Sheet sheet;
		if(hasSheets(workbook)){
			sheet = workbook.getSheetAt(sheetIndex);
		} else {
			sheet = workbook.createSheet();
		}
		return sheet;
	}

	private boolean hasSheets(Workbook workbook) {
		return workbook.getNumberOfSheets() > 0;
	}
	
	private Cell getOrCreateCell(int coluna, int linha) {
		Sheet sheet = getOrCreateSheet(workbook);
		Row row = getOrCreateRow(sheet, linha);
		Cell cell = row.getCell(coluna);
		if (cell == null) {
			cell = row.createCell(coluna);
		}
		return cell;
	}

	private Row getOrCreateRow(Sheet sheet, int linha) {
		Row row = sheet.getRow(linha);
		if (row == null) {
			row = sheet.createRow(linha);
		}
		return row;
	}
	
	public WorkbookBuilder comValorNaLinhaColuna(String conteudo, int linha, int coluna) {
		Cell cell = getOrCreateCell(coluna, linha);
		cell.setCellValue(conteudo);
		return this;
	}

	public WorkbookBuilder comValorNaLinhaColuna(Boolean conteudo, int linha, int coluna) {
		Cell cell = getOrCreateCell(coluna, linha);
		cell.setCellValue(conteudo);
		return this;
	}
	
	public WorkbookBuilder comValorNaLinhaColuna(Number conteudo, int linha, int coluna) {
		Cell cell = getOrCreateCell(coluna, linha);
		cell.setCellValue(conteudo.doubleValue());
		return this;
	}
	
	public WorkbookBuilder comValorNaLinhaColuna(Date conteudo, int linha, int coluna) {
		Cell cell = getOrCreateCell(coluna, linha);
		cell.setCellValue(conteudo);
		return this;
	}
	
}
