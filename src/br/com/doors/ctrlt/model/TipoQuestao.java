package br.com.doors.ctrlt.model;

public enum TipoQuestao {
	UNICA(1), DISSERTATIVA(2), VERDADEIROFALSO(3), MULTIPLA(4);

	private final double _tipoQuestao;

	TipoQuestao(double tipoQuestao) {
		_tipoQuestao = tipoQuestao;
	}
	public double tipoQuestao() {
		return _tipoQuestao;
	}
}
