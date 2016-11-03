package br.com.doors.ctrlt.model;

import java.util.List;

public class Turma {
	private Long idTurma;
	private List<Aluno> alunosTurma;
	private Professor regenteTurma;
	private List<Professor> professoresTurma;
	private String nomeTurma;
	
	public Long getIdTurma() {
		return idTurma;
	}
	public void setIdTurma(Long idTurma) {
		this.idTurma = idTurma;
	}
	public Professor getRegenteTurma() {
		return regenteTurma;
	}
	public void setRegenteTurma(Professor regenteTurma) {
		this.regenteTurma = regenteTurma;
	}
	public String getNomeTurma() {
		return nomeTurma;
	}
	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}
	public List<Aluno> getAlunosTurma() {
		return alunosTurma;
	}
	public void setAlunosTurma(List<Aluno> alunosTurma) {
		this.alunosTurma = alunosTurma;
	}
	public List<Professor> getProfessoresTurma() {
		return professoresTurma;
	}
	public void setProfessoresTurma(List<Professor> professoresTurma) {
		this.professoresTurma = professoresTurma;
	}
	@Override
	public String toString() {
		return "Turma [idTurma=" + idTurma + ", alunosTurma=" + alunosTurma
				+ ", regenteTurma=" + regenteTurma + ", professoresTurma="
				+ professoresTurma + ", nomeTurma=" + nomeTurma + "]";
	}
}
