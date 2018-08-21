package br.com.app.endpoint;

public final class DNAFinder {

	public static boolean isMutant(String[] dna) {
		final int mutantTreshhold = 4;
		boolean mutant = false;
		for (int i = 0; (i < dna.length - mutantTreshhold && !mutant); i++) {
			for (int j = 0; (j < dna[i].length() - mutantTreshhold && !mutant); j++) {
				mutant |= findMutantSequence(dna, i, j, mutantTreshhold, 1, 1);
			}
		}
		for (int i = 0; (i < dna.length - mutantTreshhold && !mutant); i++) {
			for (int j = 0; (j < dna[i].length() && !mutant); j++) {
				mutant |= findMutantSequence(dna, i, j, mutantTreshhold, 1, 0);
			}
		}
		for (int i = 0; (i < dna.length && !mutant); i++) {
			for (int j = 0; (j < dna[i].length() - mutantTreshhold && !mutant); j++) {
				mutant |= findMutantSequence(dna, i, j, mutantTreshhold, 0, 1);
			}
		}
		return mutant;
	}

	private static boolean findMutantSequence(String[] dna, int i, int j, Integer mutantTreshhold, int lineIncrement,
			int columnIncrement) {
		char current = dna[i].charAt(j);
		int count = 1;
		boolean mutant = true;
		for (int k = i + lineIncrement, l = j + columnIncrement; k < dna.length && l < dna[k].length()
				&& mutant; k += lineIncrement, l += columnIncrement) {
			if (current != dna[k].charAt(l)) {
				mutant = false;
			} else {
				count++;
			}
		}
		return count >= mutantTreshhold;
	}

	public static boolean findMutantDNANeigborhood(String[] dna) {
		final int mutantTreshhold = 4;
		boolean res = false;
		char[][] dnaMatrix = new char[dna.length + 1][dna.length + 1];
		int[][][] dnaCountMatrix = new int[dna.length + 1][dna.length + 1][3];

		for (int i = 0; i < dna.length; i++) {
			for (int j = 0; j < dna.length; j++) {
				dnaMatrix[i + 1][j + 1] = dna[i].charAt(j);
			}
		}

		for (int i = 1; i < dnaMatrix.length; i++) {
			for (int j = 1; j < dnaMatrix[0].length; j++) {
				if (dnaMatrix[i][j] == dnaMatrix[i - 1][j - 1]) {
					dnaCountMatrix[i][j][0] = dnaCountMatrix[i - 1][j - 1][0] + 1;
				}

				if (dnaMatrix[i][j] == dnaMatrix[i][j - 1]) {
					dnaCountMatrix[i][j][1] = dnaCountMatrix[i][j - 1][1] + 1;
				}

				if (dnaMatrix[i][j] == dnaMatrix[i - 1][j]) {
					dnaCountMatrix[i][j][2] = dnaCountMatrix[i - 1][j][2] + 1;
				}

				if (dnaCountMatrix[i][j][0] == mutantTreshhold - 1 || dnaCountMatrix[i][j][1] == mutantTreshhold - 1
						|| dnaCountMatrix[i][j][2] == mutantTreshhold - 1) {
					res = true;
					break;
				}
			}
		}

		return res;
	}

}
