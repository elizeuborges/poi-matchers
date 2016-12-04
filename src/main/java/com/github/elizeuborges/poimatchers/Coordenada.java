package com.github.elizeuborges.poimatchers;

class Coordenada {

	private int linha;

	private int coluna;
	
	public Coordenada(int coluna, int linha) {
		this.coluna = coluna;
		this.linha = linha;
	}

	public int getLinha() {
		return linha;
	}
	
	public int getColuna() {
		return coluna;
	}

}
