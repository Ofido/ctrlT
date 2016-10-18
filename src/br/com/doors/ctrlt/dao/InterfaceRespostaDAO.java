package br.com.doors.ctrlt.dao;

import java.util.List;

import br.com.doors.ctrlt.model.Resposta;

public interface InterfaceRespostaDAO extends InterfaceManter<Resposta>{

	void incluir(Resposta t, Long idQuestao);

	void alterar(Resposta t, Long idQuestao);

	List<Resposta> procurarQuestao(Long id);

}
