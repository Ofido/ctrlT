package br.com.doors.ctrlt.dao;

import br.com.doors.ctrlt.model.Resposta;

public interface InterfaceRespostaDAO extends InterfaceManter<Resposta>{

	void incluir(Resposta t, Long idQuestao);

	void alterar(Resposta t, Long idQuestao);

	Resposta[] procurarQuestao(Long id);

}
