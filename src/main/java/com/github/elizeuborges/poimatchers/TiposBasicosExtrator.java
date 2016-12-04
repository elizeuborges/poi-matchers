package com.github.elizeuborges.poimatchers;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;

/**
 * Fornece as estratégias de extração de conteúdo da célula 
 * para os principais tipos
 *
 * @author Elizeu Borges
 *
 */
@SuppressWarnings("rawtypes")
public enum TiposBasicosExtrator implements ExtratorDeConteudoDaCelula {
	
	/**
	 * Define um {@link ExtratorDeConteudoDaCelula} retorna o equivalente à:
	 * <br>
	 * <code>
	 * <pre>
	 * cell.getNumericCellValue().intValue()
	 * </pre>
	 * </code>
	 * @see Cell#getNumericCellValue()
	 */
	INTEGER(Integer.class){
		public Object extrairConteudo(Cell cell) {
			Double valor = cell.getNumericCellValue();
			return valor.intValue();
		}
	},
	/**
	 * Define um {@link ExtratorDeConteudoDaCelula} retorna o equivalente à:
	 * <br>
	 * <code>
	 * <pre>
	 * cell.getNumericCellValue().floatValue()
	 * </pre>
	 * </code>
	 * @see Cell#getNumericCellValue()
	 */
	FLOAT(Float.class){
		public Object extrairConteudo(Cell cell) {
			Double valor = cell.getNumericCellValue();
			return valor.floatValue();
		}
	},
	/**
	 * Define um {@link ExtratorDeConteudoDaCelula} retorna o equivalente à:
	 * <br>
	 * <code>
	 * <pre>
	 * cell.getNumericCellValue()
	 * </pre>
	 * </code>
	 * @see Cell#getNumericCellValue()
	 */
	DOUBLE(Double.class){
		public Object extrairConteudo(Cell cell) {
			return cell.getNumericCellValue();
		}
	},
	/**
	 * Define um {@link ExtratorDeConteudoDaCelula} retorna o equivalente à:
	 * <br>
	 * <code>
	 * <pre>
	 * Double.valueOf(cell.getNumericCellValue()).longValue()
	 * </pre>
	 * </code>
	 * @see Cell#getNumericCellValue()
	 */
	LONG(Long.class){
		public Object extrairConteudo(Cell cell) {
			return Double.valueOf(cell.getNumericCellValue()).longValue();
		}
	},
	/**
	 * Define um {@link ExtratorDeConteudoDaCelula} retorna o equivalente à:
	 * <br>
	 * <code>
	 * <pre>
	 * cell.getBooleanCellValue()
	 * </pre>
	 * </code>
	 * @see Cell#getBooleanCellValue()
	 */
	BOOLEAN(Boolean.class){
		public Object extrairConteudo(Cell cell) {
			return cell.getBooleanCellValue();
		}
	},
	/**
	 * Define um {@link ExtratorDeConteudoDaCelula} retorna o equivalente à:
	 * <br>
	 * <code>
	 * <pre>
	 * BigDecimal.valueOf(cell.getNumericCellValue());
	 * </pre>
	 * </code>
	 * @see Cell#getNumericCellValue()
	 */
	BIG_DECIMAL(BigDecimal.class){
		public Object extrairConteudo(Cell cell) {
			return BigDecimal.valueOf(cell.getNumericCellValue());
		}
	},
	/**
	 * Define um {@link ExtratorDeConteudoDaCelula} retorna o equivalente à:
	 * <br>
	 * <code>
	 * <pre>
	 * cell.getDateCellValue()
	 * </pre>
	 * </code>
	 * @see Cell#getDateCellValue()
	 */
	DATE(Date.class){
		public Object extrairConteudo(Cell cell) {
			return cell.getDateCellValue();
		}
	},
	/**
	 * Define um {@link ExtratorDeConteudoDaCelula} retorna o conteudo em texto da célula
	 * @see Cell#getStringCellValue()
	 */
	STRING(String.class){
		public Object extrairConteudo(Cell cell) {
			return cell.getStringCellValue();
		}
	}
	;
	
	private Class clazz;
	
	private TiposBasicosExtrator(Class clazz){
		this.clazz = clazz;
	}
	
	/**
	 * Retorna uma estratégia de extração do conteúdo da celula para tipo informado 
	 * 
	 * @param clazz classe do tipo de dado inserido na {@link Cell}
	 * @return instancia de um {@link ExtratorDeConteudoDaCelula} para o tipo de dado fornecido
	 * @throws IllegalArgumentException se não possuir uma definição para a classe informada em {@link TiposBasicosExtrator}
	 */
	@SuppressWarnings("unchecked")
	public static <T> ExtratorDeConteudoDaCelula<T> paraClasse(Class<T> clazz){
		for (TiposBasicosExtrator tipoBasicoExtrator : values()) {
			if (tipoBasicoExtrator.clazz.equals(clazz)) {
				return tipoBasicoExtrator;
			}
		}
		throw new IllegalArgumentException("Tipo "+clazz.getCanonicalName()+" não esperado. "
				+ "Forneça o sua própria implementação de "+ExtratorDeConteudoDaCelula.class.getCanonicalName());
	}
	

}
