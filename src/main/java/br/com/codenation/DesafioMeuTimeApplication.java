package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.desafio.models.Jogador;
import br.com.codenation.desafio.models.Time;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private List<Time> times = new ArrayList<>();
	private List<Jogador> jogadores = new ArrayList<>();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
			String corUniformeSecundario) {
		
		Time time = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);

		if (times.contains(time)) {
			throw new IdentificadorUtilizadoException();
		}

		times.add(time);
//		throw new UnsupportedOperationException();
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade,
			BigDecimal salario) {
		Jogador jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);

		if (jogadores.contains(jogador)) {
			throw new IdentificadorUtilizadoException();
		}

		verificaTime(idTime);

		jogadores.add(jogador);
//		throw new UnsupportedOperationException();

	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		
		Time timeDoJogador = timeDojogador(idJogador);
		
		jogadores.forEach(jogador -> {
			if(jogador.getTime().getId().equals(timeDoJogador.getId())) {
				if(jogador.isCapitao() && !jogador.getId().equals(idJogador)) {
					jogador.setCapitao(false);
				}
				if(jogador.getId().equals(idJogador)) {
					jogador.setCapitao(true);
				}
			}
		});
		
//		throw new UnsupportedOperationException();
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {

		Time time = buscaTimePorID(idTime);
		Jogador capitao = new Jogador();
		
		for(Jogador jogador : jogadores) {
			if(jogador.getTime().getId().equals(time.getId())) {
				if(jogador.isCapitao()) {
					capitao = jogador;
				}
			}
		}
		
		if(!capitao.isCapitao()) {
			throw new CapitaoNaoInformadoException();
		}
		
		return capitao.getId();
		
//		throw new UnsupportedOperationException();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		return jogadores.stream().filter(jogador -> jogador.getId().equals(idJogador)).map(Jogador::getNome).findFirst()
				.orElseThrow(() -> new JogadorNaoEncontradoException());
//		throw new UnsupportedOperationException();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		
		Time time = buscaTimePorID(idTime);
		
		return time.getNome();
//		throw new UnsupportedOperationException();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		verificaTime(idTime);

		Stream<Jogador> jogadoresDoTime = filtroJogadoresPorTime(idTime);

		return jogadoresDoTime.sorted(Comparator.comparing(Jogador::getId)).map(Jogador::getId)
				.collect(Collectors.toList());
//		throw new UnsupportedOperationException();
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		verificaTime(idTime);

		Stream<Jogador> jogadoresDoTime = filtroJogadoresPorTime(idTime);

		return jogadoresDoTime.max(Comparator.comparing(Jogador::getNivelHabilidade)).get().getId();
//		throw new UnsupportedOperationException();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		
		verificaTime(idTime);

		List<Jogador> jogadoresDoTime = filtroJogadoresPorTime(idTime).collect(Collectors.toList());

		jogadoresDoTime.sort(Comparator.comparing(Jogador::getDataNascimento)
				.thenComparing(Comparator.comparing(Jogador::getId)));
		
		return jogadoresDoTime.get(0).getId();
		
//		throw new UnsupportedOperationException();

	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		if(times.isEmpty()) {
			return new ArrayList<Long>();
		}
		
		List<Long> listaTimes = times.stream().sorted(Comparator.comparing(Time::getId)).map(Time::getId)
				.collect(Collectors.toList());
		
		return listaTimes;
//		throw new UnsupportedOperationException();
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		verificaTime(idTime);

		List<Jogador> jogadoresDoTime = filtroJogadoresPorTime(idTime).collect(Collectors.toList());
		
		jogadoresDoTime.
			sort(Comparator.comparing(Jogador::getSalario).reversed()
					.thenComparing(Comparator.comparing(Jogador::getId)));
		
		return jogadoresDoTime.get(0).getId();
		
//		throw new UnsupportedOperationException();
	}

	/**/
	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		Time timeDojogador = timeDojogador(idJogador);

		Stream<Jogador> jogadoresDoTime = filtroJogadoresPorTime(timeDojogador.getId());

		return jogadoresDoTime.filter(jogador -> jogador.getId().equals(idJogador)).findFirst().get().getSalario();
//		throw new UnsupportedOperationException();
	}
	

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		
		if(jogadores.isEmpty()) {
			return new ArrayList<Long>();
		}
		
		if(top > jogadores.size()) {
			top = jogadores.size();
		}
		
		List<Long> jogadoresId = new ArrayList<>();
		
		jogadores.sort(Comparator.comparing(Jogador::getNivelHabilidade).reversed()
									.thenComparing(Comparator.comparing(Jogador::getId)));
		jogadores.subList(0, top).forEach(jogador -> jogadoresId.add(jogador.getId()));
		
		return jogadoresId;
//		throw new UnsupportedOperationException();
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {

		String corUniforme = "";
		Time timeCasa = buscaTimePorID(timeDaCasa);
		Time visitante = buscaTimePorID(timeDeFora);


		if (timeCasa.getCorUniformePrincipal().equals(visitante.getCorUniformePrincipal())) {
			corUniforme = visitante.getCorUniformeSecundario();
		} else {
			corUniforme = visitante.getCorUniformePrincipal();
		}

		return corUniforme;
//		throw new UnsupportedOperationException();
	}

	private Time buscaTimePorID(Long idTime) {
		return times.stream().filter(time -> time.getId().equals(idTime)).findFirst().orElseThrow(() -> new TimeNaoEncontradoException());
	}

	private void verificaTime(Long idTime) {
		if (!times.contains(new Time(idTime))) {
			throw new TimeNaoEncontradoException();
		}
	}

	private Stream<Jogador> filtroJogadoresPorTime(Long idTime) {
		return jogadores.stream().filter(jogador -> jogador.getTime().getId().equals(idTime));
	}

	private Time timeDojogador(Long idJogador) {
		return jogadores.stream().filter((jogador) -> jogador.getId().equals(idJogador))
				.map(jogador -> jogador.getTime()).findFirst().orElseThrow(() -> new JogadorNaoEncontradoException());
	}
	
	public void imprimeJogadores() {
		System.out.println("---------JOGADORES----------");
		jogadores.forEach(System.out::println);
	}

	public void imprimeTimes() {
		System.out.println("---------TIMES----------");
		times.forEach(System.out::println);
	}

}
