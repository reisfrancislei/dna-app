package br.com.app.endpoint.stat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpStatus.OK;

import java.net.URI;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.app.endpoint.StatsSession;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StatsEndpointTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private StatsSession session;

	private String path = "/stats";

	private Stats expected;

	@PostConstruct
	public void init() {
		this.expected = new Stats();
		expected.incrementHuman();
		expected.incrementMutant();

		session.clean();
		session.incrementHuman();
		session.incrementMutant();
	}

	@Test
	public void get() {
		RequestEntity<Void> request = RequestEntity.get(URI.create(path)).build();
		ResponseEntity<Stats> response = restTemplate.exchange(request, Stats.class);
		assertTrue(response.getStatusCode().equals(OK));
		assertNotNull(response.getBody());
		
		final Stats actual = response.getBody();
		assertEquals(expected.getQtdHuman(), actual.getQtdHuman());
		assertEquals(expected.getQtdMutant(), actual.getQtdMutant());
		assertEquals(expected.getRatio(), actual.getRatio());
	}

}
