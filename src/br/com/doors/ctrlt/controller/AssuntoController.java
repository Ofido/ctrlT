package br.com.doors.ctrlt.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.doors.ctrlt.dao.AssuntoDAO;
import br.com.doors.ctrlt.dao.DisciplinaDAO;
import br.com.doors.ctrlt.model.Assunto;
import br.com.doors.ctrlt.model.Disciplina;

@Controller
public class AssuntoController {
	private final AssuntoDAO assuntoDAO;
	private final DisciplinaDAO disciplinasDAO;
	
	@Autowired
	public AssuntoController(AssuntoDAO daoP, DisciplinaDAO aP){
		this.assuntoDAO = daoP;
		this.disciplinasDAO = aP;
	}
	
	@RequestMapping("CadastrandoAssunto")
	public String caminhoCadastro(HttpSession session, Long id) {
		List<Disciplina>disciplinas = new ArrayList<Disciplina>();
		if (id != null) {			
			Assunto assunto = assuntoDAO.procurar(id);
			session.setAttribute("alterando", assunto);
			disciplinas.add(assunto.getDisciplinaAssunto());
			for (Disciplina disciplina : disciplinasDAO.listarTodos()) {
				if (disciplina.getIdDisciplina() != assunto.getDisciplinaAssunto().getIdDisciplina()) {
					disciplinas.add(disciplina);
				}
			}
		}
		session.setAttribute("disciplinas", disciplinas);
		return "CadastroAssunto";
	}
	
	@RequestMapping("ListandoAssunto")
	private String listar(HttpSession session) {
		session.setAttribute("lista", assuntoDAO.listarTodos());
		return "ListarAssunto";
	}
	
	@RequestMapping("ExcluirAssunto")
	private String excluir(Long id) {
		assuntoDAO.excluir(id);
		return "redirect:ListandoAssunto";
	}
	
	@RequestMapping("CadastroAssunto")
	public String cadastro(HttpSession session, Assunto assunto, Disciplina disciplina) {
		assunto.setDisciplinaAssunto(disciplina);
		if (assunto.getIdAssunto() == null) {			
			assuntoDAO.incluir(assunto);
		}else {
			assuntoDAO.alterar(assunto);
		}
		return "Index";
	}
}
