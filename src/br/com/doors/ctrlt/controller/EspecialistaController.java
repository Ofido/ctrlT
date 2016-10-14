package br.com.doors.ctrlt.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import br.com.doors.ctrlt.dao.EspecialistaDAO;
import br.com.doors.ctrlt.model.Especialista;

@Controller
public class EspecialistaController {
	private final EspecialistaDAO espDAO;
	
	@Autowired
	public EspecialistaController(EspecialistaDAO daoE){
		this.espDAO = daoE;
	}
	
	@RequestMapping("CadastrandoEspecialista")
	public String caminhoCadastro(Model session, Long id) {
		if (id != null) {			
			session.addAttribute("alterando", espDAO.procurar(id));
		}
		return "CadastroEspecialista";
	}
	
	@RequestMapping("ListandoEspecialista")
	private String listar(Model session) {
		session.addAttribute("lista", espDAO.listarTodos());
		return "ListarEspecialista";
	}
	@RequestMapping("ExcluirEspecialista")
	private String excluir(Long id) {
		espDAO.excluir(id);
		return "redirect:ListandoEspecialista";
	}
	
	@RequestMapping("CadastroEspecialista")
	public String cadastro(Model session, Especialista especialista, MultipartFile arquivo, Boolean licenca) {
		if (!arquivo.isEmpty()) {
			try {
				especialista.setFotoEspecialista(arquivo.getBytes());
			} catch (IOException e) {
				throw new RuntimeException("ERRO NA FOTO"+ this.getClass() + e.toString());
			}
		}
		if (especialista.getIdEspecialista() == null) {			
			espDAO.incluir(especialista);
		}else {
			espDAO.alterar(especialista);
		}
		return "index";
	}
	
	@RequestMapping("voltaInicioEspecialista")
	public String voltaInicioEsp(){
		return "";
	}
}
