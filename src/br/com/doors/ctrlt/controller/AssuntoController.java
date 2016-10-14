package br.com.doors.ctrlt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String caminhoCadastro(Model session, Long id) {
		List<Disciplina>disciplinas = null;
		if (id != null) {			
			disciplinas = new ArrayList<Disciplina>();
			Assunto assunto = assuntoDAO.procurar(id);
			session.addAttribute("alterando", assunto);
			disciplinas.add(assunto.getDisciplinaAssunto());
			for (Disciplina disciplina : disciplinasDAO.listarTodos()) {
				if (disciplina.getIdDisciplina() != assunto.getDisciplinaAssunto().getIdDisciplina()) {
					disciplinas.add(disciplina);
				}
			}
		}
		if (disciplinas == null) {
			disciplinas = disciplinasDAO.listarTodos();
		}
		session.addAttribute("disciplinas", disciplinas);
		return "CadastroAssunto";
	}
	
	@RequestMapping("ListandoAssunto")
	private String listar(Model session) {
		session.addAttribute("lista", assuntoDAO.listarTodos());
		return "ListarAssunto";
	}
	
	@RequestMapping("ExcluirAssunto")
	private String excluir(Long id) {
		assuntoDAO.excluir(id);
		return "redirect:ListandoAssunto";
	}
	
	@RequestMapping("CadastroAssunto")
	public String cadastro(Model session, Assunto assunto, Disciplina disciplina) {
		assunto.setDisciplinaAssunto(disciplina);
		if (assunto.getIdAssunto() == null) {			
			assuntoDAO.incluir(assunto);
		}else {
			assuntoDAO.alterar(assunto);
		}
		return "Index";
	}
}
