package com.github.elizeuborges.poimatchers;

import org.apache.poi.ss.usermodel.Cell;

public interface ExtratorDeConteudoDaCelula<T> {
	T extrairConteudo(Cell cell);
}
