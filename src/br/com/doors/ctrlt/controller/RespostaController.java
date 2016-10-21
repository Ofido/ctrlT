package br.com.doors.ctrlt.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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
		session.addAttribute("questao", idQuestao);
		return "ListarResposta";
	}

	@RequestMapping("ExcluirResposta")
	private String excluir(Long id, Long idQuestao) {
		respostaDAO.excluir(id);
		return "redirect:ListandoResposta?idQuestao=" + idQuestao.toString();
	}

	@RequestMapping("CadastroResposta")
	public String cadastro(Model session, Resposta resposta, Long idQuestao,
			Boolean alterandoCorreta, Boolean alterandoImg, MultipartFile arquivo) {
		if (alterandoCorreta != null && alterandoCorreta) {
			resposta.setCorretaResposta((resposta.getCorretaResposta() == null ? false
					: resposta.getCorretaResposta()));
			respostaDAO.alterar(resposta, idQuestao);
		} else if (alterandoImg != null && alterandoImg) {
				try {
					resposta.setComplementoResposta(arquivo.getBytes());
				} catch (IOException e) {
					throw new RuntimeException("ERRO NA FOTO"+ this.getClass() + e.toString());
				}
			respostaDAO.alterar(resposta, idQuestao);
		}else if (resposta.getCorretaResposta() == null) {
			String[] respostas = resposta.getResposta().trim().split(",");
			for (int i = 0; i < respostas.length; i++) {
				Resposta a = new Resposta();
				a.setResposta(respostas[i]);
				respostaDAO.incluir(a, idQuestao);
			}
		} else if (resposta.getIdResposta() == null) {
			respostaDAO.incluir(resposta, idQuestao);
		} else {
			respostaDAO.alterar(resposta, idQuestao);
		}	
		return "redirect:ListandoResposta?idQuestao=" + idQuestao.toString();// TODO
																				// FAZER
																				// RESPOSTA
																				// COMO
		// PENSAMOS
	}
}