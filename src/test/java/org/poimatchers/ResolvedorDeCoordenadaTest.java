package org.poimatchers;

import static org.poimatchers.CoordenadaMatcher.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.poimatchers.Coordenada;
import org.poimatchers.ResolvedorDeCoordenada;

public class ResolvedorDeCoordenadaTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void deveLancarExcecaoSeNaoForInformadoLetraDaColuna() throws Exception {
		thrown.expect(IllegalArgumentException.class);
		ResolvedorDeCoordenada.resolver("5");
		thrown.expectMessage("A célula deve-se iniciar com a(s) letra(s) referente a coluna");
	}
	
	@Test
	public void deveLancarExcecaoSeNaoForInformadoNumeroParaColuna() throws Exception {
		thrown.expect(IllegalArgumentException.class);
		ResolvedorDeCoordenada.resolver("AA");
		thrown.expectMessage("número da linha");
	}
	
	@Test
	public void deveResolverCoordenadaInicial() throws Exception {
		Coordenada coordenada = ResolvedorDeCoordenada.resolver("A1");
		assertThat(coordenada, is(0, 0));
	}

	@Test
	public void deveResolverCoordenadaComUmaLetraEUmNumero() throws Exception {
		Coordenada coordenada = ResolvedorDeCoordenada.resolver("K5");
		assertThat(coordenada, is(10, 4));
	}

	@Test
	public void deveResolverCoordenadaComDuasLetrasEUmNumero() throws Exception {
		Coordenada coordenada = ResolvedorDeCoordenada.resolver("AA8");
		assertThat(coordenada, is(26, 7));
	}

	@Test
	public void deveResolverCoordenadaComUmaLetraEDoisNumeros() throws Exception {
		Coordenada coordenada = ResolvedorDeCoordenada.resolver("C81");
		assertThat(coordenada, is(2, 80));
	}
	
	@Test
	public void deveResolverCoordenadaComDuasLetrasEDoisNumeros() throws Exception {
		Coordenada coordenada = ResolvedorDeCoordenada.resolver("AB90");
		assertThat(coordenada, is(27, 89));
	}

	@Test
	public void deveResolverCoordenadaComDuasLetrasEDoisNumerosComecandoComZero() throws Exception {
		Coordenada coordenada = ResolvedorDeCoordenada.resolver("AB05");
		assertThat(coordenada, is(27, 4));
	}
	
}
