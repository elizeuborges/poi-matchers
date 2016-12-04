package org.apache.poi.matchers;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;

@SuppressWarnings("rawtypes")
public enum TiposBasicosExtrator implements ExtratorDeConteudoDaCelula {
	
	INTEGER(Integer.class){
		public Object extrairValor(Cell cell) {
			Double valor = cell.getNumericCellValue();
			return valor.intValue();
		}
	},
	FLOAT(Float.class){
		public Object extrairValor(Cell cell) {
			Double valor = cell.getNumericCellValue();
			return valor.floatValue();
		}
	},
	DOUBLE(Double.class){
		public Object extrairValor(Cell cell) {
			return cell.getNumericCellValue();
		}
	},
	LONG(Long.class){
		public Object extrairValor(Cell cell) {
			return Double.valueOf(cell.getNumericCellValue()).longValue();
		}
	},
	BOOLEAN(Boolean.class){
		public Object extrairValor(Cell cell) {
			return cell.getBooleanCellValue();
		}
	},
	BIG_DECIMAL(BigDecimal.class){
		public Object extrairValor(Cell cell) {
			return BigDecimal.valueOf(cell.getNumericCellValue());
		}
	},
	DATE(Date.class){
		public Object extrairValor(Cell cell) {
			return cell.getDateCellValue();
		}
	},
	STRING(String.class){
		public Object extrairValor(Cell cell) {
			return cell.getStringCellValue() != null ? cell.getStringCellValue() 
					: cell.getRichStringCellValue() != null ? cell.getRichStringCellValue().getString() 
					: null;
		}
	}
	;
	
	private Class clazz;
	
	TiposBasicosExtrator(Class clazz){
		this.clazz = clazz;
	}
	
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
