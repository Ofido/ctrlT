package br.com.doors.ctrlt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.doors.ctrlt.dao.RespostaDAO;
import br.com.doors.ctrlt.model.Resposta;

@Controller
public class RespostaController {
	private final RespostaDAO respostaDAO;

	@Autowired
	public RespostaController(RespostaDAO daoP) {
		this.respostaDAO = daoP;
	}

	@RequestMapping("CadastrandoResposta")
	public String caminhoCadastro(Model session, Long id, Long idQuestao) {
		session.addAttribute("questao", idQuestao);
		if (id != null) {
			session.addAttribute("lista",
					respostaDAO.procurarQuestao(idQuestao));
		}
		return "CadastroResposta";
	}

	@RequestMapping("ListandoResposta")
	private String listar(Model session, Long idQuestao) {
		session.addAttribute("lista", respostaDAO.procurarQuestao(idQuestao));
		return "ListarResposta";
	}

	@RequestMapping("ExcluirResposta")
	private String excluir(Long id) {
		respostaDAO.excluir(id);
		return "redirect:ListandoResposta";
	}

	@RequestMapping("CadastroResposta")
	public String cadastro(Model session, Resposta resposta, Long idQuestao) {
		if (resposta.getCorretaResposta() == null) {
			String[] respostas = resposta.getResposta().trim().split(",");
			for (int i = 0; i < respostas.length; i++) {
				Resposta a = new Resposta();
				a.setResposta(respostas[i]);
				respostaDAO.incluir(resposta, idQuestao);
			}
		} else if (resposta.getIdResposta() == null) {
			respostaDAO.incluir(resposta, idQuestao);
		} else {
			respostaDAO.alterar(resposta, idQuestao);
		}
		return "redrect:ListandoResposta";// TODO FAZER RESPOSTA COMO
												// PENSAMOS
	}
}
