package br.com.codenation.desafio.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Jogador {

	private Long id;
	private Time time;
	private String nome;
	private LocalDate dataNascimento;
	private Integer nivelHabilidade;
	private BigDecimal salario;
	private boolean capitao;

	public Jogador() {
	}

	public Jogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade,
			BigDecimal salario) {
		this.id = id;
		this.time = new Time();
		this.time.setId(idTime);
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.nivelHabilidade = nivelHabilidade;
		this.salario = salario;
	}

	public Jogador(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		//result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jogador other = (Jogador) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		/*if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;*/
		return true;
	}

	public Long getId() {
		return this.id;
	}

	public String getNome() {
		return nome;
	}

	public Time getTime() {
		return this.time;
	}

	public boolean isCapitao() {
		return capitao;
	}

	public void setCapitao(boolean capitao) {
		this.capitao = capitao;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public Integer getNivelHabilidade() {
		return nivelHabilidade;
	}

	@Override
	public String toString() {

		return "{ID: " + this.id + "| Jogador: " + this.nome + "| Time: " + this.time.getId() + "| Capitão: "
				+ this.capitao + "| Habilidade: " + this.nivelHabilidade + "| Salário: " + this.salario + "}\n";
	}

}
