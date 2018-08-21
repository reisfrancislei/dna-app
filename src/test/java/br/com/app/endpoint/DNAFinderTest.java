package br.com.app.endpoint;

import static br.com.app.endpoint.DNAFinder.findMutantDNANeigborhood;
import static br.com.app.endpoint.DNAFinder.isMutant;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Classe testadora de {@link DNAFinder}
 * 
 * @author francislei
 *
 */
@RunWith(Parameterized.class)
public class DNAFinderTest {

	@Parameters(name = "{index} = {1}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(
				new Object[][] { { new String[] { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" }, true },
						{ new String[] { "ATGCGA", "CBGTAC", "TTATGT", "AGAAGG", "CBCCCA", "TCACTG" }, false },
						{ new String[] { "GCGCGA", "CBGTAC", "CCATGT", "AGCCTG", "CBCGGA", "TCACTG" }, false },
						{ new String[] { "ATGCGA", "CBGTGC", "TTATGT", "AGAAGG", "CBCCCA", "TCACTG" }, true } });
	}

	public DNAFinderTest(String[] dna, boolean isMutante) {
		super();
		this.dna = dna;
		this.isMutante = isMutante;
	}

	private String[] dna;
	private boolean isMutante;

	@Test
	public void applyTest() {
		assertThat(isMutant(dna)).isEqualTo(isMutante);
		assertThat(findMutantDNANeigborhood(dna)).isEqualTo(isMutante);
	}
}