package com.github.elizeuborges.poimatchers;

import java.util.regex.Pattern;

final class ResolvedorDeCoordenada {

	private static final int NUM_LETRAS_ALFABETO = 26;

	private static final int A = 65;
	
	private static final int ZERO = 48;

	private static Pattern iniciandoComLetraSeguidaDeNumero = Pattern.compile("^[A-Za-z]+[0-9]+");

	private ResolvedorDeCoordenada(){}
	
	public static Coordenada resolver(String coordenada) {
		if (!iniciandoComLetraSeguidaDeNumero.matcher(coordenada).find()) {
			throw new IllegalArgumentException("Formato de célula inválido. "
					+ "A célula deve-se iniciar com a(s) letra(s) referente a coluna seguida do número da linha");
		}
		String letras = coordenada.toUpperCase();
		int coluna = 0, linha = 0, iletras = 0, inumeros = 0;
		for (int i = 0; i < letras.length(); i++) {
			char letra = letras.charAt(i);
			if (isLetra(letra)) {
				int pesoDaLetra = letra - A;
				if (iletras>0) {
					coluna++;
					coluna *= NUM_LETRAS_ALFABETO;
				} 
				coluna += pesoDaLetra;
				iletras++;
			} else {
				int pesoDoNumero = letra - ZERO;
				if (inumeros > 0) {
					linha *= 10;
				}
				linha += pesoDoNumero;
				inumeros++;
			}
		}
		
		return new Coordenada(coluna, linha-1);
	}

	private static boolean isLetra(char letra) {
		return letra >= A;
	}

}
