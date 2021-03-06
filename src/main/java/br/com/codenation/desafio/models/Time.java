package br.com.codenation.desafio.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Time {

	private Long id;
	private String nome;
	private LocalDate dataCriacao;
	private String corUniformePrincipal;
	private String corUniformeSecundario;
	private Long capitao = 0L;
	private List<Jogador> jogadores = new ArrayList<>();

	public Time(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
			String corUniformeSecundario) {
		this.id = id;
		this.nome = nome;
		this.dataCriacao = dataCriacao;
		this.corUniformePrincipal = corUniformePrincipal;
		this.corUniformeSecundario = corUniformeSecundario;
	}

	public Time() {
	}

	public Time(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public String getCorUniformePrincipal() {
		return corUniformePrincipal;
	}

	public String getCorUniformeSecundario() {
		return corUniformeSecundario;
	}
	
	public List<Jogador> getJogadores() {
		return jogadores;
	}
	
	public void adicionaJogador(Jogador jogador) {
		this.jogadores.add(jogador);
	}

	public Long getCapitao() {
		return capitao;
	}
	
	public void setCapitao(Long capitao) {
		this.capitao = capitao;
	}
	
	@Override
	public String toString() {
		return "ID: " + this.id + "| Time: " + this.nome + "| Capit�o: " + this.capitao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Time other = (Time) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
