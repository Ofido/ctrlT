package br.com.doors.ctrlt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.doors.ctrlt.dao.AdministradorDAO;
import br.com.doors.ctrlt.model.Administrador;

@Controller
public class AdministradorController {
	private final AdministradorDAO admDAO;
	
	@Autowired
	public AdministradorController(AdministradorDAO daoA){
		this.admDAO= daoA;
	}
	
	@RequestMapping("CadastrandoAdm")
	public String caminhoCadastro(Model model, Long id) {
		if (id != null) {			
			model.addAttribute("alterando", admDAO.procurar(id));
		}
		return "CadastroAdm";
	}
	
	@RequestMapping("ListandoAdm")
	private String listar(Model model) {
		model.addAttribute("lista", admDAO.listarTodos());
		return "ListarAdm";
	}
	@RequestMapping("ExcluirAdm")
	private String excluir(Long id) {
		admDAO.excluir(id);
		return "redirect:ListandoAdm";
	}
	
	@RequestMapping("CadastroAdm")
	public String cadastro(Model model, Administrador adm) {
		if (adm.getIdAdm() == null) {			
			admDAO.incluir(adm);
		}else {
			admDAO.alterar(adm);
		}
		return "index";
	}
	
	@RequestMapping("voltaInicioAdm")
	public String voltaInicioAdm(){
		return "index";
	}
}
