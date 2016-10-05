package br.com.doors.ctrlt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.doors.ctrlt.dao.AdministradorDAO;

@Controller
public class AdministradorController {
	private final AdministradorDAO dao;
	
	@Autowired
	public AdministradorController(AdministradorDAO daoA){
		this.dao = daoA;
	}
	
	@RequestMapping("voltaInicioAdm")
	public String voltaInicioAdm(){
		return "";
	}
}
