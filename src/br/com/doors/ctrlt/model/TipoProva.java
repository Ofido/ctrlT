package br.com.doors.ctrlt.model;

public enum TipoProva {
	OBJETIVA(1), DISSERTATIVA(2), MISTA(3);

	private final double _tipoProva;

	TipoProva(double tipoProva) {
		_tipoProva = tipoProva;
	}
	public double tipoProva() {
		return _tipoProva;
	}
}
