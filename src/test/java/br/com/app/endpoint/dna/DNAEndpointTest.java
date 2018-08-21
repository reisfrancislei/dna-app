package br.com.app.endpoint.dna;

import static java.net.URI.create;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.app.endpoint.dna.Dna;;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DNAEndpointTest {

	@Autowired
	private TestRestTemplate restTemplate;

	private String path = "/mutant/";
	private String path2 = "/mutant-second/";

	final Dna dnaOK = new Dna(new String[] { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" });
	final Dna dnaFail = new Dna(new String[] { "ATGCGA", "CBGTAC", "TTATGT", "AGAAGG", "CBCCCA", "TCACTG" });

	@Test
	public void mutant_Ok() {
		RequestEntity<Dna> request = RequestEntity.post(create(path)).body(dnaOK);
		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);
		assertTrue(response.getStatusCode().equals(OK));
	}

	@Test
	public void mutant_Forbidden() {
		RequestEntity<Dna> request = RequestEntity.post(create(path)).body(dnaFail);
		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);
		assertTrue(response.getStatusCode().equals(FORBIDDEN));
	}

	@Test
	public void mutant_second_Ok() {
		RequestEntity<Dna> request = RequestEntity.post(create(path2)).body(dnaOK);
		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);
		assertTrue(response.getStatusCode().equals(OK));
	}

	@Test
	public void mutant_second_Forbidden() {
		RequestEntity<Dna> request = RequestEntity.post(create(path2)).body(dnaFail);
		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);
		assertTrue(response.getStatusCode().equals(FORBIDDEN));
	}
}
