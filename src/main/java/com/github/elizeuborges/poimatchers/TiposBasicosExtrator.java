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
	 * <pre>
	 * cell.getNumericCellValue().intValue()
	 * </pre>
	 * @see Cell#getNumericCellValue()
	 */
	INTEGER(Integer.class){
		@Override
		public Integer extrairConteudo(Cell cell) {
			Double valor = cell.getNumericCellValue();
			return valor.intValue();
		}
	},
	/**
	 * Define um {@link ExtratorDeConteudoDaCelula} retorna o equivalente à:
	 * <br>
	 * <pre>
	 * cell.getNumericCellValue().floatValue()
	 * </pre>
	 * @see Cell#getNumericCellValue()
	 */
	FLOAT(Float.class){
		@Override
		public Float extrairConteudo(Cell cell) {
			Double valor = cell.getNumericCellValue();
			return valor.floatValue();
		}
	},
	/**
	 * Define um {@link ExtratorDeConteudoDaCelula} retorna o equivalente à:
	 * <br>
	 * <pre>
	 * cell.getNumericCellValue()
	 * </pre>
	 * @see Cell#getNumericCellValue()
	 */
	DOUBLE(Double.class){
		@Override
		public Double extrairConteudo(Cell cell) {
			return cell.getNumericCellValue();
		}
	},
	/**
	 * Define um {@link ExtratorDeConteudoDaCelula} retorna o equivalente à:
	 * <br>
	 * <pre>
	 * Double.valueOf(cell.getNumericCellValue()).longValue()
	 * </pre>
	 * @see Cell#getNumericCellValue()
	 */
	LONG(Long.class){
		@Override
		public Long extrairConteudo(Cell cell) {
			Double valor = cell.getNumericCellValue();
			return valor.longValue();
		}
	},
	/**
	 * Define um {@link ExtratorDeConteudoDaCelula} retorna o equivalente à:
	 * <br>
	 * <pre>
	 * cell.getBooleanCellValue()
	 * </pre>
	 * @see Cell#getBooleanCellValue()
	 */
	BOOLEAN(Boolean.class){
		@Override
		public Boolean extrairConteudo(Cell cell) {
			return cell.getBooleanCellValue();
		}
	},
	/**
	 * Define um {@link ExtratorDeConteudoDaCelula} retorna o equivalente à:
	 * <br>
	 * <pre>
	 * BigDecimal.valueOf(cell.getNumericCellValue());
	 * </pre>
	 * @see Cell#getNumericCellValue()
	 */
	BIG_DECIMAL(BigDecimal.class){
		@Override
		public BigDecimal extrairConteudo(Cell cell) {
			return BigDecimal.valueOf(cell.getNumericCellValue());
		}
	},
	/**
	 * Define um {@link ExtratorDeConteudoDaCelula} retorna o equivalente à:
	 * <br>
	 * <pre>
	 * cell.getDateCellValue()
	 * </pre>
	 * @see Cell#getDateCellValue()
	 */
	DATE(Date.class){
		@Override
		public Date extrairConteudo(Cell cell) {
			return cell.getDateCellValue();
		}
	},
	/**
	 * Define um {@link ExtratorDeConteudoDaCelula} retorna o conteudo em texto da célula
	 * @see Cell#getStringCellValue()
	 */
	STRING(String.class){
		@Override
		public String extrairConteudo(Cell cell) {
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
	 * @param <T> tipo estático da classe que se deseja obter um extrator
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
