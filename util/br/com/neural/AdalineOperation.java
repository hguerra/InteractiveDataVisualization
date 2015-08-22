package br.com.neural;

public class AdalineOperation {
	public static void main(String[] args) {
		double[] pesos = { 0.01222577719907951, 0.004762759645191947,
				-0.009727417287218335, 0.0020898259147021994 };

		double[][] amostras = new double[][] { { -1.0, -1.0, -1.0, -1.0 },
				{ 0.1, 0.3, 0.6, 0.5 }, { 0.4, 0.7, 0.9, 0.7 },
				{ 0.7, 0.2, 0.8, 0.1 } };

		double[] saidas = new double[] { 1.0, -1.0, -1.0, 1.0 };

		double eqmMedio = Eqm.calculateEQM(amostras, saidas, pesos);

		System.out.println("EQM medio " + eqmMedio);

		// 3. Executar as seguintes instrucoes.
		// 3.1. Multiplicar os pesos pela amostra e resultar com a soma (u).
		double u = 0.0;
		for (int k = 0; k < amostras.length; k++) {
			double[] temp = amostras[k];
			u += pesos[k] * temp[k];
		}

		// 3.2. Verificar o sinal.
		int y = sinal(u);

		// 3.3. Verificando a classe da amostra.
		if (y == -1) {
			System.out.println("Amostra da classe A (-1).");
		} else if (y == 1) {
			System.out.println("Amostra da classe B (1).");
		}
	}

	private static int sinal(double u) {
		return (u >= 0.0) ? 1 : -1;
	}
}
