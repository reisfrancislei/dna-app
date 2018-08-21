package br.com.app.endpoint.dna;

import static br.com.app.endpoint.DNAFinder.findMutantDNANeigborhood;
import static br.com.app.endpoint.DNAFinder.isMutant;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.endpoint.StatsSession;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class DNAEndpoint {

	@Autowired
	private StatsSession session;

	@PostMapping("/mutant/")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 403, message = "Forbidden") })
	public ResponseEntity<?> mutante(@RequestBody Dna dna) {
		if (isMutant(dna.getDna())) {
			session.incrementMutant();
			return ok().build();
		}

		session.incrementHuman();
		return status(FORBIDDEN).build();
	}

	@PostMapping("/mutant-second/")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 403, message = "Forbidden") })
	public ResponseEntity<?> mutante2(@RequestBody Dna dna) {
		if (findMutantDNANeigborhood(dna.getDna())) {
			session.incrementMutant();
			return ok().build();
		}

		session.incrementHuman();
		return status(FORBIDDEN).build();
	}
}