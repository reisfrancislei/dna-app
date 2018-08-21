package br.com.app.endpoint.dna;

import java.io.Serializable;

public class Dna implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String[] dna;

	public Dna() {}
	
	public Dna(String[] dna) {
		super();
		this.dna = dna;
	}
	public String[] getDna() {
		return dna;
	}
	public void setDna(String[] dna) {
		this.dna = dna;
	}	
}