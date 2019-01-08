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

	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade,
			BigDecimal salario) {
		Jogador jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);

		verificaTime(idTime);
		
		if (jogadores.contains(jogador)) {
			throw new IdentificadorUtilizadoException();
		}

		jogadores.add(jogador);
		
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		verificaJogador(idJogador);
		
		Time timeDoJogador = timeDojogador(idJogador);

		jogadores.forEach((jogador) -> {
			if (jogador.getTime().getId().equals(timeDoJogador.getId())) {
				if (!jogador.getId().equals(idJogador)) {
					jogador.setCapitao(false);
				}
				if (jogador.getId().equals(idJogador)) {
					jogador.setCapitao(true);
				}
			}
		});
		
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {

		verificaTime(idTime);

		Stream<Jogador> jogadoresDoTime = filtroJogadoresPorTime(idTime);

		return jogadoresDoTime.filter(time -> time.getId().equals(idTime)).findFirst().map(Jogador::getId)
				.orElseThrow(() -> new CapitaoNaoInformadoException());
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		return jogadores.stream().filter(jogador -> jogador.getId().equals(idJogador)).map(Jogador::getNome).findFirst()
				.orElseThrow(() -> new JogadorNaoEncontradoException());
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		return times.stream().filter(time -> time.getId().equals(idTime)).map(Time::getNome).findFirst()
				.orElseThrow(() -> new TimeNaoEncontradoException());
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		verificaTime(idTime);

		Stream<Jogador> jogadoresDoTime = filtroJogadoresPorTime(idTime);

		return jogadoresDoTime.sorted((j1, j2) -> Long.compare(j1.getId(), j2.getId())).map(Jogador::getId)
								.collect(Collectors.toList());
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		verificaTime(idTime);

		Stream<Jogador> jogadoresDoTime = filtroJogadoresPorTime(idTime);

		return jogadoresDoTime.max(Comparator.comparing(Jogador::getNivelHabilidade)).get().getId();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		verificaTime(idTime);

		Stream<Jogador> jogadoresDoTime = filtroJogadoresPorTime(idTime);

		return jogadoresDoTime.min(Comparator.comparing(Jogador::getDataNascimento)).get().getId();

	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {

		return times.stream().sorted((t1, t2) -> Long.compare(t1.getId(), t2.getId())).map(Time::getId)
				.collect(Collectors.toList());
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		verificaTime(idTime);

		Stream<Jogador> jogadoresDoTime = filtroJogadoresPorTime(idTime);

		return jogadoresDoTime.max(Comparator.comparing(Jogador::getSalario)).get().getId();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		Time timeDojogador = timeDojogador(idJogador);

		Stream<Jogador> jogadoresDoTime = filtroJogadoresPorTime(timeDojogador.getId());

		return jogadoresDoTime.filter(jogador -> jogador.getId().equals(idJogador)).findFirst().get().getSalario();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {

		return jogadores.stream().sorted((j1, j2) -> Integer.compare(j2.getNivelHabilidade(), j1.getNivelHabilidade()))
				.map(Jogador::getId).collect(Collectors.toList()).subList(0, top);
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {

		Time timeCasa = times.stream().filter(time -> time.getId().equals(timeDaCasa)).findFirst().get();
		Time visitante = times.stream().filter(time -> time.getId().equals(timeDeFora)).findFirst().get();

		String corUniforme = "";

		if (timeCasa.getCorUniformePrincipal().equals(visitante.getCorUniformePrincipal())) {
			corUniforme = visitante.getCorUniformeSecundario();
		} else {
			corUniforme = visitante.getCorUniformePrincipal();
		}

		return corUniforme;
	}

	private void verificaTime(Long idTime) {
		if (!times.contains(new Time(idTime))) {
			throw new TimeNaoEncontradoException();
		}
	}
	
	private void verificaJogador(Long idJogador) {
		if (jogadores.contains(new Jogador(idJogador))) {
			throw new JogadorNaoEncontradoException();
		}
	}

	public void imprimeJogadores() {
		System.out.println("---------JOGADORES----------");
		jogadores.forEach(System.out::println);
	}

	public void imprimeTimes() {
		System.out.println("---------TIMES----------");
		times.forEach(System.out::println);
	}

	private Stream<Jogador> filtroJogadoresPorTime(Long idTime) {
		return jogadores.stream().filter(jogador -> jogador.getTime().getId().equals(idTime));
	}

	private Time timeDojogador(Long idJogador) {
		return jogadores.stream().filter((jogador) -> jogador.getId().equals(idJogador))
				.map(jogador -> jogador.getTime()).findFirst().orElseThrow(() -> new JogadorNaoEncontradoException());
	}

}
