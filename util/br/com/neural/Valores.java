package br.com.neural;

import java.io.Serializable;
import java.util.List;

public class Valores implements Serializable {
	private List<float[]> pesos;
	private List<float[]> saidas;
	private List<float[]> amostras;

	public Valores() {

	}

	public Valores(List<float[]> pesos, List<float[]> saidas,
			List<float[]> amostras) {
		super();
		this.pesos = pesos;
		this.saidas = saidas;
		this.amostras = amostras;
	}

	public List<float[]> getPesos() {
		return pesos;
	}

	public void setPesos(List<float[]> pesos) {
		this.pesos = pesos;
	}

	public List<float[]> getSaidas() {
		return saidas;
	}

	public void setSaidas(List<float[]> saidas) {
		this.saidas = saidas;
	}

	public List<float[]> getAmostras() {
		return amostras;
	}

	public void setAmostras(List<float[]> amostras) {
		this.amostras = amostras;
	}

}
