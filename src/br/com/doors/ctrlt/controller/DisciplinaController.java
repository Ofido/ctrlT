package br.com.doors.ctrlt.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.doors.ctrlt.dao.DisciplinaDAO;
import br.com.doors.ctrlt.model.Disciplina;

@Controller
public class DisciplinaController {
	private final DisciplinaDAO disciplinaDAO;
	
	@Autowired
	public DisciplinaController(DisciplinaDAO daoP){
		this.disciplinaDAO = daoP;
	}
	
	@RequestMapping("CadastrandoDisciplina")
	public String caminhoCadastro(HttpSession session, Long id) {
		if (id != null) {			
			session.setAttribute("alterando", disciplinaDAO.procurar(id));
		}
		return "CadastroDisciplina";
	}
	
	@RequestMapping("ListandoDisciplina")
	private String listar(HttpSession session) {
		session.setAttribute("lista", disciplinaDAO.listarTodos());
		return "ListarDisciplina";
	}
	@RequestMapping("ExcluirDisciplina")
	private String excluir(Long id) {
		disciplinaDAO.excluir(id);
		return "redirect:ListandoDisciplina";
	}
	
	@RequestMapping("CadastroDisciplina")
	public String cadastro(HttpSession session, Disciplina disciplina) {
		if (disciplina.getIdDisciplina() == null) {			
			disciplinaDAO.incluir(disciplina);
		}else {
			disciplinaDAO.alterar(disciplina);
		}
		return "index";
	}
}
