		package br.com.doors.ctrlt.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import br.com.doors.ctrlt.dao.AssuntoDAO;
import br.com.doors.ctrlt.dao.DisciplinaDAO;
import br.com.doors.ctrlt.dao.EspecialistaDAO;
import br.com.doors.ctrlt.dao.ProfessorDAO;
import br.com.doors.ctrlt.dao.QuestaoDAO;
import br.com.doors.ctrlt.model.Assunto;
import br.com.doors.ctrlt.model.Disciplina;
import br.com.doors.ctrlt.model.Especialista;
import br.com.doors.ctrlt.model.Professor;
import br.com.doors.ctrlt.model.Questao;

@Controller
public class QuestaoController {
	private final DisciplinaDAO disciplinasDAO;
	private final QuestaoDAO questaoDAO;
	private final ProfessorDAO professorDAO;
	private final EspecialistaDAO especialistaDAO;
	private final AssuntoDAO assuntoDAO;
	
	@Autowired
	public QuestaoController(ProfessorDAO professorDAO, DisciplinaDAO disciplinaDAO, QuestaoDAO questaoDAO, EspecialistaDAO especialistaDAO, AssuntoDAO assuntoDAO){
		this.professorDAO = professorDAO;
		this.disciplinasDAO = disciplinaDAO;
		this.questaoDAO = questaoDAO;
		this.especialistaDAO = especialistaDAO;
		this.assuntoDAO = assuntoDAO;
	}
	
	@RequestMapping("CadastrandoQuestao")
	public String caminhoCadastro(Model modelo, Long id) {
		List<Disciplina>disciplinas = null;
		List<Assunto>assuntos = null;
		if (id != null) {			
			disciplinas = new ArrayList<Disciplina>();
			assuntos = new ArrayList<Assunto>();
			Questao questao = questaoDAO.procurar(id);
			modelo.addAttribute("alterando", questao);
			modelo.addAttribute("especialista", especialistaDAO.procurar(2L));//TODO TEIRAR APOS TESTE
			disciplinas.add(questao.getDisciplinaQuestao());
			assuntos.add(questao.getAssuntoQuestao());
			for (Disciplina disciplina : disciplinasDAO.listarTodos()) {
				if (disciplina.getIdDisciplina() != questao.getDisciplinaQuestao().getIdDisciplina()) {
					disciplinas.add(disciplina);
				}
			}
			for (Assunto assunto : assuntoDAO.procurarDisciplina(questao.getDisciplinaQuestao().getIdDisciplina())) {
				if (assunto.getIdAssunto() != questao.getAssuntoQuestao().getIdAssunto()) {
					assuntos.add(assunto);
				}
			}
		}
		if (disciplinas == null) {
			disciplinas = disciplinasDAO.listarTodos();
		}
		if (assuntos == null) {
			assuntos = assuntoDAO.listarTodos();
		}
		modelo.addAttribute("disciplinas", disciplinas);
		modelo.addAttribute("assunto", assuntos);
		return "CadastroQuestao";
	}
	
	@RequestMapping("ListandoQuestao")		
	private String listar(Model session) {
		session.addAttribute("lista", questaoDAO.listarTodos());
		return "ListarQuestao";
	}

	@RequestMapping("ExcluirQuestao")
	private String excluir(Long id) {
		questaoDAO.excluir(id);
		return "redirect:ListandoQuestao";
	}
	
	@RequestMapping("CadastroQuestao")
	public String cadastro(Model session, Assunto assunto, Questao questao, Disciplina disciplina, MultipartFile arquivo, Integer tempo, String tipoCadastro, Professor prof, Especialista especialista, HttpSession session2) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(0, 0, 0, 0, tempo);
		questao.setTempoQuestao(calendar);
		Calendar aqui = Calendar.getInstance();
		aqui.set(1995, 11, 20, 15, 55, 55);
		questao.setUltimoUsoQuestao(aqui);
		if (!arquivo.isEmpty()) {
			try {
				questao.setComplementoQuestao(arquivo.getBytes());
			} catch (IOException e) {
				throw new RuntimeException("ERRO NA FOTO"+ this.getClass() + e.toString());
			}
		}
		questao.setCriadorQuestao(professorDAO.procurar(((Professor) session2.getAttribute("usuarioLogado")).getIdProfessor()));
		questao.setValidadorQuestao(especialistaDAO.procurar(1L));
		questao.setDisciplinaQuestao(disciplina);
		questao.setAssuntoQuestao(assunto);
		if (questao.getIdQuestao() == null) {			
			questaoDAO.incluir(questao);
		}else {
			questao.setValidadorQuestao(especialistaDAO.procurar(especialista.getIdEspecialista()));//TODO arrumar
			questaoDAO.alterar(questao);
			return "index";
		}
		if (tipoCadastro.contains("CadastroComum")) {//TODO FAZER ISSO FUNCIONAR
			return "index";
		}else{
			return "redirect:CadastrandoResposta?id=&idQuestao=" + questao.getIdQuestao().toString()+"&tipoQuestao="+questao.getTipoQuestao();
		}
		
	}
}
