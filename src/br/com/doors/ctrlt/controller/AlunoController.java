package br.com.doors.ctrlt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.doors.ctrlt.dao.AlunoDAO;

@Controller
public class AlunoController {
	private final AlunoDAO dao;
	
	@Autowired
	public AlunoController(AlunoDAO daoS){
		this.dao = daoS;
	}
	
	@RequestMapping("voltaInicioAluno")
	public String voltaInicioAdm(){
		return "";
	}
}
