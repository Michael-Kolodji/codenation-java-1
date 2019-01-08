package br.com.codenation.desafio.testes;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.codenation.DesafioMeuTimeApplication;

public class Testes {

	public static void main(String[] args) {
		DesafioMeuTimeApplication application = new DesafioMeuTimeApplication();
		
		//Inclui Time
		application.incluirTime(1l, "Barcelona", LocalDate.now(), "Vermelho e azul", "Laranja");
//		application.incluirTime(1l, "Barcelona", LocalDate.now(), "Vermelho e azul", "Laranja");
		application.incluirTime(2l, "Juventus", LocalDate.now(), "Branco", "Azul Escuro");
		application.incluirTime(3l, "Real Madrid", LocalDate.now(), "Branco", "Azul Escuro");
		application.incluirTime(4l, "PSG", LocalDate.now(), "Vermelho e azul", "Vermelho");
		
		//Inclui Jogador
		application.incluirJogador(1l, 1l, "Messi", LocalDate.of(1986, 10, 15), 98, new BigDecimal(1500000));
		//application.incluirJogador(1l, 1l, "Messi", LocalDate.of(1986, 10, 15), NivelHabilidade.EXCELENTE.getNivel(), new BigDecimal(1500000));
		application.incluirJogador(2l, 1l, "Coutinho", LocalDate.of(1986, 11, 25), 88, new BigDecimal(1000000));
		application.incluirJogador(3l, 1l, "Modric", LocalDate.of(1987, 11, 25), 85, new BigDecimal(1000000));
		application.incluirJogador(4l, 2l, "Cristiano Ronaldo", LocalDate.of(1985, 11, 10), 98, new BigDecimal(1000000));
		application.incluirJogador(5l, 2l, "Douglas Costa", LocalDate.of(1985, 11, 10), 93, new BigDecimal(1000000));
		application.incluirJogador(6l, 2l, "Dybala", LocalDate.of(1985, 11, 10), 93, new BigDecimal(500000));
		application.incluirJogador(7l, 4l, "Neymar Jr.", LocalDate.of(1994, 11, 15), 95, new BigDecimal(2500000));
		application.incluirJogador(8l, 4l, "Killian MBape", LocalDate.of(1999, 10, 15), 94, new BigDecimal(2000000));
		application.incluirJogador(9l, 3l, "Benzema", LocalDate.of(1990, 10, 25), 70, new BigDecimal(1500000));
		
		//Define Capitão
		application.definirCapitao(1L);
		//System.out.println("-----------------------------");
		//application.definirCapitao(3L);
		
		//Busca Capitão
		//System.out.println("Capitão: " + application.buscarCapitaoDoTime(1L));
		
		//Busca Nome Jogador
		//System.out.println("Jogador: " + application.buscarNomeJogador(3L));
		
		// Busca Nome Time
		//System.out.println("Time: " + application.buscarNomeTime(2L));
		
		// Buscar Jogadores do Time
		//System.out.println(application.buscarJogadoresDoTime(1L));

		/*long ini = System.currentTimeMillis();
		long fim = System.currentTimeMillis();
		System.out.println("Tempo: " + (fim - ini));*/
		
		// Buscar Melhor Jogador do Time
		//System.out.println("Melhor Jogador: " + application.buscarMelhorJogadorDoTime(1L));
		
		// Buscar Jogador Mais Velho
		//System.out.println(application.buscarJogadorMaisVelho(2L));
		
		// Buscar Times
		//System.out.println(application.buscarTimes());
		
		// Buscar Jogador Maior Salario
		//System.out.println(application.buscarJogadorMaiorSalario(2L));
		
		// Buscar Salario Do Jogador
		//System.out.println(application.buscarSalarioDoJogador(4l));
		
		// Buscar Top Jogadores
		//System.out.println(application.buscarTopJogadores(4));
		
		// Buscar Cor Camisa Time de Fora
		System.out.println("Uniforme visitante: " + application.buscarCorCamisaTimeDeFora(1L, 3L));
		
		//Imprime jogadores
		//application.imprimeJogadores();
		
		System.out.println();
		
		//Imprime times
		//application.imprimeTimes();
				
	}
}
