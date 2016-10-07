package br.com.doors.ctrlt.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.doors.ctrlt.dao.DisciplinaDAO;
import br.com.doors.ctrlt.dao.EspecialistaDAO;
import br.com.doors.ctrlt.dao.ProfessorDAO;
import br.com.doors.ctrlt.dao.QuestaoDAO;
import br.com.doors.ctrlt.model.Assunto;
import br.com.doors.ctrlt.model.Disciplina;
import br.com.doors.ctrlt.model.Questao;

@Controller
public class QuestaoController {
	private final DisciplinaDAO disciplinasDAO;
	private final QuestaoDAO questaoDAO;
	private final ProfessorDAO professorDAO;
	private final EspecialistaDAO especialistaDAO;
	
	@Autowired
	public QuestaoController(ProfessorDAO professorDAO, DisciplinaDAO disciplinaDAO, QuestaoDAO questaoDAO, EspecialistaDAO especialistaDAO){
		this.professorDAO = professorDAO;
		this.disciplinasDAO = disciplinaDAO;
		this.questaoDAO = questaoDAO;
		this.especialistaDAO = especialistaDAO;
	}
	
	@RequestMapping("CadastrandoQuestao")
	public String caminhoCadastro(HttpSession session, Long id) {
		List<Disciplina>disciplinas = null;
		session.setAttribute("usuarioLogado", professorDAO.procurar(1L));//TODO TEIRAR APOS TESTE
		if (id != null) {			
			
			disciplinas = new ArrayList<Disciplina>();
			Questao questao = questaoDAO.procurar(id);
			session.setAttribute("alterando", questao);
			disciplinas.add(questao.getDisciplinaQuestao());
			for (Disciplina disciplina : disciplinasDAO.listarTodos()) {
				if (disciplina.getIdDisciplina() != questao.getDisciplinaQuestao().getIdDisciplina()) {
					disciplinas.add(disciplina);
				}
			}
		}
		if (disciplinas == null) {
			disciplinas = disciplinasDAO.listarTodos();
		}
		session.setAttribute("disciplinas", disciplinas);
		return "CadastroQuestao";
	}
	
	@RequestMapping("ListandoQuestao")
	private String listar(HttpSession session) {
		return "ListarQuestao";
	}
	
	@RequestMapping("ExcluirQuestao")
	private String excluir(Long id) {
		return "redirect:ListandoQuestao";
	}
	
	@RequestMapping("CadastroQuestao")
	public String cadastro(HttpSession session, Questao questao, Disciplina disciplina) {
		return "Index";
	}
}
