package br.com.doors.ctrlt.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import br.com.doors.ctrlt.dao.AlunoDAO;
import br.com.doors.ctrlt.model.Aluno;

@Controller
public class AlunoController {
	private final AlunoDAO alunoDAO;
	
	@Autowired
	public AlunoController(AlunoDAO daoS){
		this.alunoDAO = daoS;
	}
	
	@RequestMapping("CadastrandoAluno")
	public String caminhoCadastro(HttpSession session, Long id) {
		if (id != null) {			
			session.setAttribute("alterando", alunoDAO.procurar(id));
		}
		return "CadastroAluno";
	}
	
	@RequestMapping("ListandoAluno")
	private String listar(HttpSession session) {
		session.setAttribute("lista", alunoDAO.listarTodos());
		return "ListarAluno";
	}
	@RequestMapping("ExcluirAluno")
	private String excluir(Long id) {
		alunoDAO.excluir(id);
		return "redirect:ListandoAluno";
	}
	
	@RequestMapping("CadastroAluno")
	public String cadastro(HttpSession session, Aluno aluno, MultipartFile arquivo) {
		if (!arquivo.isEmpty()) {
			try {
				aluno.setFotoAluno(arquivo.getBytes());
			} catch (IOException e) {
				throw new RuntimeException("ERRO NA FOTO"+ this.getClass() + e.toString());
			}
		}
		if (aluno.getIdAluno() == null) {			
			alunoDAO.incluir(aluno);
		}else {
			alunoDAO.alterar(aluno);
		}
		return "index";
	}
	
	@RequestMapping("voltaInicioAluno")
	public String voltaInicioAluno(){
		return "";
	}
}
