package br.com.doors.ctrlt.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import br.com.doors.ctrlt.dao.ProfessorDAO;
import br.com.doors.ctrlt.model.Professor;

@Controller
public class ProfessorController {
	private final ProfessorDAO profDAO;
	
	@Autowired
	public ProfessorController(ProfessorDAO daoP){
		this.profDAO = daoP;
	}
	
	@RequestMapping("CadastrandoProfessor")
	public String caminhoCadastro(HttpSession session, Long id) {
		if (id != null) {			
			session.setAttribute("alterando", profDAO.procurar(id));
		}
		return "CadastroProfessor";
	}
	
	@RequestMapping("ListandoProfessor")
	private String listar(HttpSession session) {
		session.setAttribute("lista", profDAO.listarTodos());
		return "ListarProfessor";
	}
	@RequestMapping("ExcluirProfessor")
	private String excluir(Long id) {
		profDAO.excluir(id);
		return "redirect:ListandoProfessor";
	}
	
	@RequestMapping("CadastroProfessor")
	public String cadastro(HttpSession session, Professor professor, MultipartFile arquivo, Boolean licenca) {
		if (!arquivo.isEmpty()) {
			try {
				professor.setFotoProfessor(arquivo.getBytes());
			} catch (IOException e) {
				throw new RuntimeException("ERRO NA FOTO"+ this.getClass() + e.toString());
			}
		}
		if (professor.getIdProfessor() == null) {			
			profDAO.incluir(professor);
		}else {
			profDAO.alterar(professor);
		}
		return "index";
	}
	
	@RequestMapping("voltaInicioProfessor")
	public String voltaInicioProf(){
		return "";
	}
}
