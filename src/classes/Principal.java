package classes;

import classes.Estado.PosicaoAgente;
import classes.Estado.Situacao;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Principal {

	public static void main(String[] args) {
		
		Estado estadoInicial = new Estado();
		estadoInicial.setPosicaoAgente(PosicaoAgente.ESQUERDA);
		estadoInicial.setSituacaoEsquerda(Situacao.SUJO);
		estadoInicial.setSituacaoDireita(Situacao.SUJO);
		
		Agente agente = new Agente(estadoInicial);
		agente.aspirarUsandoBuscaEmProfundidade();

	}
	
}
