package br.com.doors.ctrlt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.doors.ctrlt.dao.EspecialistaDAO;

@Controller
public class EspecialistaController {
	private final EspecialistaDAO dao;
	
	@Autowired
	public EspecialistaController(EspecialistaDAO daoE){
		this.dao = daoE;
	}
	
	@RequestMapping("voltaInicioEspecialista")
	public String voltaInicioAdm(){
		return "";
	}
}
