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
public class QuestaoController {
	private final DisciplinaDAO disciplinasDAO;
	
	@Autowired
	public QuestaoController(AssuntoDAO daoP, DisciplinaDAO aP){
		this.disciplinasDAO = aP;
	}
	
	@RequestMapping("CadastrandoAssunto")
	public String caminhoCadastro(HttpSession session, Long id) {
		return "CadastroAssunto";
	}
	
	@RequestMapping("ListandoAssunto")
	private String listar(HttpSession session) {
		return "ListarAssunto";
	}
	
	@RequestMapping("ExcluirAssunto")
	private String excluir(Long id) {
		return "redirect:ListandoAssunto";
	}
	
	@RequestMapping("CadastroAssunto")
	public String cadastro(HttpSession session, Assunto assunto, Disciplina disciplina) {
		return "Index";
	}
}
