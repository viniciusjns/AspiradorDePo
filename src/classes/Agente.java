package classes;

import java.util.*;

import classes.Estado.PosicaoAgente;
import classes.Estado.Situacao;

public class Agente {
	
	private Estado estado;
	private Stack<Estado> pilha;
	private Queue<Estado> fila;
	private List<Estado> listaExplorada;
	private String resultado = "";

	public Agente(Estado estado) {
		iniciaAgente(estado);
	}
	
	public Agente() {
		iniciaAgente(criaEstado());
	}
	
	private void iniciaAgente(Estado estado) {
		this.estado = estado;
		this.pilha = new Stack<>();
		this.fila = new LinkedList<>();
		this.listaExplorada = new ArrayList<>();
	}

	private Estado criaEstado() {
		Estado estado = new Estado();
		estado.setPosicaoAgente(PosicaoAgente.ESQUERDA);
		estado.setSituacaoEsquerda(Situacao.SUJO);
		estado.setSituacaoDireita(Situacao.SUJO);
		return estado;
	}

	public void aspirarUsandoBuscaEmProfundidade() {
		aspirarBep(this.estado);
	}

	public void aspirarUsandoBuscaEmLargura() {
		aspirarBel(this.estado);
	}

	private void aspirarBep(Estado estado) {
		//pilha.push(estado);

		if (isObjetivo(estado)) {
			listaExplorada.add(estado);
			imprimeResultado();
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
					pilha.push(estadoDir);
					pilha.push(estadoEsq);

				}
				listaExplorada.add(estado);
			}

			Estado estadoBorda = pilha.pop();
			aspirarBep(estadoBorda);

		}
	}

	private void aspirarBel(Estado estado) {
		if (isObjetivo(estado)) {
			listaExplorada.add(estado);
			imprimeResultado();
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

					fila.add(estadoEsq);
					fila.add(estadoDir);
					fila.add(estadoLim);

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

					fila.add(estadoEsq);
					fila.add(estadoDir);
					fila.add(estadoLim);

				}
				listaExplorada.add(estado);
			}

			Estado estadoBorda = fila.poll();
			aspirarBel(estadoBorda);

		}
	}
	
	private void imprimeResultado() {
		for (Estado estado : listaExplorada) {
			System.out.println("Esquerda: " + estado.getSituacaoEsquerda());
			System.out.println("Direita: " + estado.getSituacaoDireita());
			System.out.println("Posicao Agente: " + estado.getPosicaoAgente());
			System.out.println();
		}
	}

//	private void imprimeResultadoPeloEstadoPai(Estado estado) {
//		resultado += imprimeEstado(estado);
//		Estado e = estado;
//
//		while (e.getEstadoPai() != null) {
//			e = e.getEstadoPai();
//			resultado = imprimeEstado(e) + resultado;
//		}
//
//		System.out.println(resultado);
//
//	}
//
//	private String imprimeEstado(Estado estado) {
//		return  "|E = " + estado.getSituacaoEsquerda().name() + "|\n" +
//				"|D = " + estado.getSituacaoDireita().name() + "|\n" +
//				"|A = " + estado.getPosicaoAgente().name() + "|\n\n";
//	}

	private boolean isObjetivo(Estado estado) {
		return estado.getSituacaoDireita().equals(Situacao.LIMPO) &&
				estado.getSituacaoEsquerda().equals(Situacao.LIMPO);
	}

	private boolean estaNaListaExplorada(Estado estado) {
		return listaExplorada.indexOf(estado) != -1;
	}

}
