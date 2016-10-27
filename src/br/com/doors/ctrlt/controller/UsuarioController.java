package br.com.doors.ctrlt.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.doors.ctrlt.dao.AdministradorDAO;
import br.com.doors.ctrlt.dao.AlunoDAO;
import br.com.doors.ctrlt.dao.EspecialistaDAO;
import br.com.doors.ctrlt.dao.ProfessorDAO;
import br.com.doors.ctrlt.model.Administrador;
import br.com.doors.ctrlt.model.Aluno;
import br.com.doors.ctrlt.model.Especialista;
import br.com.doors.ctrlt.model.Professor;

@Controller
public class UsuarioController {
	private final AdministradorDAO admDAO;
	private final EspecialistaDAO espDAO;
	private final ProfessorDAO profDAO;
	private final AlunoDAO alunoDAO;

	@Autowired
	public UsuarioController(AdministradorDAO daoA, EspecialistaDAO daoE, ProfessorDAO daoP, AlunoDAO daoS) {
		this.admDAO = daoA;
		this.espDAO = daoE;
		this.profDAO = daoP;
		this.alunoDAO = daoS;
	}
	
	@RequestMapping("efetuaLogin")
	public String efetuaLogin(String email, String senha, HttpSession session) {
		System.out.println(email + "     " + senha);
		Administrador adm = admDAO.logar(email, senha);
		Especialista esp = espDAO.logar(email, senha);
		Professor prof = profDAO.logar(email, senha);
		Aluno aluno = alunoDAO.logar(email, senha);
		if (adm != null) {
			session.setAttribute("usuarioLogado", adm);
			return "adm";
		}
		if (esp != null) {
			session.setAttribute("usuarioLogado", esp);
			session.setAttribute("esp", true);
			return "esp";
		}
		if (prof != null) {
			session.setAttribute("usuarioLogado", prof);
			return "prof";
		}
		if (aluno != null) {
			session.setAttribute("usuarioLogado", aluno);
			return "aluno";
		}
		return "falha";
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login.jsp";
	}
}