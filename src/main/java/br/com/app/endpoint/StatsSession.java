package br.com.app.endpoint;

import org.springframework.stereotype.Component;

import br.com.app.endpoint.stat.Stats;

@Component
public class StatsSession {

	private Stats stats = new Stats();

	public void incrementMutant() {
		stats.incrementMutant();
	}

	public void incrementHuman() {
		stats.incrementHuman();
	}

	public Stats getStats() {
		return stats;
	}

	/**
	 * Somente para teste unitário.
	 * 
	 * @deprecated
	 */
	public void clean() {
		this.stats = new Stats();
	}
}
