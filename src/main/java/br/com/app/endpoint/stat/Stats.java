package br.com.app.endpoint.stat;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stats implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("count_mutant_dna")
	private int qtdMutant;
	@JsonProperty("count_human_dna")
	private int qtdHuman;
	private long ratio;

	public void incrementMutant() {
		this.qtdMutant++;

		if (qtdHuman > 0) {
			this.ratio = qtdMutant / qtdHuman;
		}
	}

	public void incrementHuman() {
		this.qtdHuman++;
	}

	public int getQtdMutant() {
		return qtdMutant;
	}

	public int getQtdHuman() {
		return qtdHuman;
	}

	public long getRatio() {
		return ratio;
	}
}