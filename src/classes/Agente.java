package classes;

import java.util.*;

import classes.Estado.PosicaoAgente;
import classes.Estado.Situacao;

import static classes.Estado.Acao.IR_DIREITA;
import static classes.Estado.Acao.IR_ESQUERDA;
import static classes.Estado.Acao.LIMPAR;

public class Agente {
	
	private Estado estado;
	private Stack<Estado> pilha;
	private List<Estado> listaExplorada;
	private Estado.Acao[] acoes;
	private String resultado = "";
	private int aux = 0;

	public Agente(Estado estado) {
		iniciaAgente(estado);
	}
	
	public Agente() {
		iniciaAgente(criaEstado());
	}
	
	private void iniciaAgente(Estado estado) {
		this.estado = estado;
		this.pilha = new Stack<>();
		this.listaExplorada = new ArrayList<>();
		//this.acoes = new Estado.Acao[]{IR_ESQUERDA, IR_DIREITA, LIMPAR};
		this.acoes = new Estado.Acao[]{IR_DIREITA, LIMPAR, IR_ESQUERDA, LIMPAR};
	}

	private Estado criaEstado() {
		Estado estado = new Estado();
		estado.setPosicaoAgente(PosicaoAgente.ESQUERDA);
		estado.setSituacaoEsquerda(Situacao.SUJO);
		estado.setSituacaoDireita(Situacao.SUJO);
		return estado;
	}

	public void executaLimpeza() {
		aspirarBep(estado);
	}

	private int geraNumeroAleatorio() {
		Random r = new Random();
		return r.nextInt(3);
	}

//	private void aspirar(Estado estado) {
//		if (isObjetivo(estado)) {
//			pilha.push(estado);
//			fila.add(estado);
//			imprimeResultado(estado);
//			//imprimePilha();
//			//imprimeFila();
//		} else {
//			Estado e = new Estado();
//			//Estado.Acao acao = acoes[geraNumeroAleatorio()];
//			Estado.Acao acao = acoes[aux];
//			switch (estado.getPosicaoAgente()) {
//				case ESQUERDA:
//					if (acao.equals(Estado.Acao.IR_DIREITA)) {
//						e.setSituacaoEsquerda(estado.getSituacaoEsquerda());
//						e.setSituacaoDireita(estado.getSituacaoDireita());
//						e.setPosicaoAgente(PosicaoAgente.DIREITA);
//						e.setEstadoPai(estado);
//						//estado.setEstadoDireito(e);
//						pilha.push(estado);
//						fila.add(estado);
//					} else if (acao.equals(Estado.Acao.IR_ESQUERDA)) {
//						e = estado;
//						//estado.setEstadoEsquerdo(null);
//						pilha.push(estado);
//						fila.add(estado);
//					} else {
//						if (estado.getSituacaoEsquerda().equals(Situacao.SUJO)) {
//							e.setSituacaoEsquerda(Situacao.LIMPO);
//							e.setSituacaoDireita(estado.getSituacaoDireita());
//							e.setPosicaoAgente(estado.getPosicaoAgente());
//							e.setEstadoPai(estado);
//							//estado.setEstadoLimpo(e);
//							pilha.push(estado);
//							fila.add(estado);
//						} else {
//							e = estado;
//						}
//					}
//					break;
//
//				case DIREITA:
//					if (acao.equals(Estado.Acao.IR_DIREITA)) {
//						e = estado;
//						//estado.setEstadoDireito(null);
//						pilha.push(estado);
//						fila.add(estado);
//					} else if (acao.equals(Estado.Acao.IR_ESQUERDA)) {
//						e.setSituacaoDireita(estado.getSituacaoDireita());
//						e.setSituacaoEsquerda(estado.getSituacaoEsquerda());
//						e.setPosicaoAgente(PosicaoAgente.ESQUERDA);
//						e.setEstadoPai(estado);
//						//estado.setEstadoEsquerdo(e);
//						pilha.push(estado);
//						fila.add(estado);
//					} else {
//						if (estado.getSituacaoDireita().equals(Situacao.SUJO)) {
//							e.setSituacaoDireita(Situacao.LIMPO);
//							e.setSituacaoEsquerda(estado.getSituacaoEsquerda());
//							e.setPosicaoAgente(estado.getPosicaoAgente());
//							e.setEstadoPai(estado);
//							//estado.setEstadoLimpo(e);
//							pilha.push(estado);
//							fila.add(estado);
//						} else {
//							e = estado;
//						}
//					}
//					break;
//			}
//			aux++;
//			aspirar(e);
//		}
//	}

	public void aspirarUsandoBuscaEmProfundidade() {
		aspirarBep(this.estado);
	}

	private void aspirarBep(Estado estado) {
		//pilha.push(estado);

		if (isObjetivo(estado)) {
			listaExplorada.add(estado);
			imprimePilha();
		} else {

			if (!estaNaListaExplorada(estado)) {

				Estado estadoEsq = new Estado();
				Estado estadoDir = new Estado();
				Estado estadoLim = new Estado();

				if (estado.getPosicaoAgente().equals(PosicaoAgente.ESQUERDA)) {

					// estado esquerda
					//estadoEsq.setEstadoPai(estado);
					/*estadoEsq.setPosicaoAgente(estado.getPosicaoAgente());
					estadoEsq.setSituacaoDireita(estado.getSituacaoDireita());
					estadoEsq.setSituacaoEsquerda(estado.getSituacaoEsquerda());*/
					estadoEsq = estado;

					// estado direita
					//estadoDir.setEstadoPai(estado);
					estadoDir.setPosicaoAgente(PosicaoAgente.DIREITA);
					estadoDir.setSituacaoDireita(estado.getSituacaoDireita());
					estadoDir.setSituacaoEsquerda(estado.getSituacaoEsquerda());

					// estado limpo
					//estadoLim.setEstadoPai(estado);
					estadoLim.setPosicaoAgente(estado.getPosicaoAgente());
					estadoLim.setSituacaoDireita(estado.getSituacaoDireita());
					estadoLim.setSituacaoEsquerda(Situacao.LIMPO);

					pilha.push(estadoLim);
					pilha.push(estadoDir);
					pilha.push(estadoEsq);

				} else {

					// estado esquerda
					//estadoEsq.setEstadoPai(estado);
					estadoEsq.setPosicaoAgente(PosicaoAgente.ESQUERDA);
					estadoEsq.setSituacaoDireita(estado.getSituacaoDireita());
					estadoEsq.setSituacaoEsquerda(estado.getSituacaoEsquerda());

					// estado direita
					//estadoDir.setEstadoPai(estado);
					/*estadoDir.setPosicaoAgente(estado.getPosicaoAgente());
					estadoDir.setSituacaoDireita(estado.getSituacaoDireita());
					estadoDir.setSituacaoEsquerda(estado.getSituacaoEsquerda());*/
					estadoDir = estado;

					// estado limpo
					//estadoLim.setEstadoPai(estado);
					estadoLim.setPosicaoAgente(estado.getPosicaoAgente());
					estadoLim.setSituacaoDireita(Situacao.LIMPO);
					estadoLim.setSituacaoEsquerda(estado.getSituacaoEsquerda());

					pilha.push(estadoLim);
					pilha.push(estadoEsq);
					pilha.push(estadoDir);

				}
				listaExplorada.add(estado);
			}

			Estado estadoBorda = pilha.pop();
			aspirarBep(estadoBorda);

		}
	}

	private void aspirarBel(Estado estado) {

	}
	
	private void imprimePilha() {
		for (Estado estado : listaExplorada) {
			System.out.println("Esquerda: " + estado.getSituacaoEsquerda());
			System.out.println("Direita: " + estado.getSituacaoDireita());
			System.out.println("Posicao Agente: " + estado.getPosicaoAgente());
			System.out.println();
		}
	}

	private void imprimeFila() {
//		for (Estado estado : fila) {
//			System.out.println("Esquerda: " + estado.getSituacaoEsquerda());
//			System.out.println("Direita: " + estado.getSituacaoDireita());
//			System.out.println("Posicao Agente: " + estado.getPosicaoAgente());
//			System.out.println();
//		}
	}

	private void imprimeResultado(Estado estado) {
		resultado += imprimeEstado(estado);
		Estado e = estado;

		while (e.getEstadoPai() != null) {
			e = e.getEstadoPai();
			resultado = imprimeEstado(e) + resultado;
		}

		System.out.println(resultado);

	}

	private String imprimeEstado(Estado estado) {
		return  "|E = " + estado.getSituacaoEsquerda().name() + "|\n" +
				"|D = " + estado.getSituacaoDireita().name() + "|\n" +
				"|A = " + estado.getPosicaoAgente().name() + "|\n\n";
	}

	private boolean isObjetivo(Estado estado) {
		return estado.getSituacaoDireita().equals(Situacao.LIMPO) &&
				estado.getSituacaoEsquerda().equals(Situacao.LIMPO);
	}

	private boolean estaNaListaExplorada(Estado estado) {
		return listaExplorada.indexOf(estado) != -1;
	}

}
