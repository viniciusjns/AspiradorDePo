package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Estado implements Serializable {
	
	private Situacao situacaoEsquerda;
	private Situacao situacaoDireita;
	private PosicaoAgente posicaoAgente;
	private Estado estadoPai;

	public Situacao getSituacaoEsquerda() {
		return situacaoEsquerda;
	}

	public void setSituacaoEsquerda(Situacao situacaoEsquerda) {
		this.situacaoEsquerda = situacaoEsquerda;
	}

	public Situacao getSituacaoDireita() {
		return situacaoDireita;
	}

	public void setSituacaoDireita(Situacao situacaoDireita) {
		this.situacaoDireita = situacaoDireita;
	}

	public PosicaoAgente getPosicaoAgente() {
		return posicaoAgente;
	}

	public void setPosicaoAgente(PosicaoAgente posicao) {
		this.posicaoAgente = posicao;
	}

	public Estado getEstadoPai() {
		return estadoPai;
	}

	public void setEstadoPai(Estado estadoPai) {
		this.estadoPai = estadoPai;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Estado estado = (Estado) o;

		if (situacaoEsquerda != estado.situacaoEsquerda) return false;
		if (situacaoDireita != estado.situacaoDireita) return false;
		if (posicaoAgente != estado.posicaoAgente) return false;
		return estadoPai != null ? estadoPai.equals(estado.estadoPai) : estado.estadoPai == null;
	}

	@Override
	public int hashCode() {
		int result = situacaoEsquerda != null ? situacaoEsquerda.hashCode() : 0;
		result = 31 * result + (situacaoDireita != null ? situacaoDireita.hashCode() : 0);
		result = 31 * result + (posicaoAgente != null ? posicaoAgente.hashCode() : 0);
		result = 31 * result + (estadoPai != null ? estadoPai.hashCode() : 0);
		return result;
	}

	public enum Situacao {
		LIMPO, SUJO
	}
	
	public enum PosicaoAgente {
		ESQUERDA, DIREITA
	}

	public enum Acao {
		IR_ESQUERDA, IR_DIREITA, LIMPAR
	}

}
