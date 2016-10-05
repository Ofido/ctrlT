package br.com.doors.ctrlt.model;

public class Disciplina {
	private Long idDisciplina;
	private String nomeDisciplina;
	
	public Long getIdDisciplina() {
		return idDisciplina;
	}
	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}
	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}
	@Override
	public String toString() {
		return "Disciplina [idDisciplina=" + idDisciplina + ", nomeDisciplina="
				+ nomeDisciplina + "]";
	}
	
}