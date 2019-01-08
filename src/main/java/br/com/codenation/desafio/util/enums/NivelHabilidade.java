package br.com.codenation.desafio.util.enums;

public enum NivelHabilidade {

	RUIM(1), RAZOAVEL(2), BOM(3), OTIMO(4), EXCELENTE(5);

	public final int nivel;

	NivelHabilidade(int nivel) {
		this.nivel = nivel;
	}

	public int getNivel() {
		return this.nivel;
	}

}
