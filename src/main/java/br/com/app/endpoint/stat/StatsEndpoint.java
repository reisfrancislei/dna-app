package br.com.app.endpoint.stat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.endpoint.StatsSession;

@RestController
public class StatsEndpoint {

	@Autowired
	private StatsSession session;

	@GetMapping("/stats")
	public ResponseEntity<Stats> stats() {
		return ResponseEntity.ok(session.getStats());
	}
}