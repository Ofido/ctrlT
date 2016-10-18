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
		case 1:
			return UNICA;
		case 2:
			return DISSERTATIVA;
		case 3:
			return VERDADEIROFALSO;
		case 4:
			return MULTIPLA;
		}
		return null;
	}
}
