package br.com.doors.ctrlt.model;

public enum TipoQuestao {
	UNICA(0), DISSERTATIVA(1), VERDADEIROFALSO(2), MULTIPLA(3);

	private final double _tipoQuestao;

	TipoQuestao(double tipoQuestao) {
		_tipoQuestao = tipoQuestao;
	}

	public double tipoQuestao() {
		return _tipoQuestao;
	}

	public static TipoQuestao tipo(int teste) {
		switch (teste) {
		case 0:
			return UNICA;
		case 1:
			return DISSERTATIVA;
		case 2:
			return VERDADEIROFALSO;
		case 3:
			return MULTIPLA;
		}
		return null;
	}
}
