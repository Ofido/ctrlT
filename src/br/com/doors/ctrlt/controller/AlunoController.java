package br.com.doors.ctrlt.controller;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import br.com.doors.ctrlt.dao.AlunoDAO;
import br.com.doors.ctrlt.dao.TurmaDAO;
import br.com.doors.ctrlt.model.Aluno;

@Controller
public class AlunoController {
	private final AlunoDAO alunoDAO;
	private final TurmaDAO turmaDAO;
	
	@Autowired
	public AlunoController(AlunoDAO daoS, TurmaDAO turmaDAO){
		this.alunoDAO = daoS;
		this.turmaDAO = turmaDAO;
	}
	
	@RequestMapping("CadastrandoAluno")
	public String caminhoCadastro(Model session, Long id) {
		if (id != null) {			
			session.addAttribute("alterando", alunoDAO.procurar(id));
		}
		return "CadastroAluno";
	}
	
	@RequestMapping("ListandoAluno")
	private String listar(Model session) {
		session.addAttribute("lista", alunoDAO.listarTodos());
		return "ListarAluno";
	}
	@RequestMapping("ExcluirAluno")
	private String excluir(Long id) {
		alunoDAO.excluir(id);
		return "redirect:ListandoAluno";
	}
	
	@RequestMapping("CadastroAluno")
	public String cadastro(Aluno aluno, MultipartFile ft, String aniversario, Long idTurma) {
		Calendar calendar = Calendar.getInstance();
		String[] strings = aniversario.split("-");
		calendar.set(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
		aluno.setAniversarioAluno(calendar);
		if (!ft.isEmpty()) {
			try {
				aluno.setFotoAluno(ft.getBytes());
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
