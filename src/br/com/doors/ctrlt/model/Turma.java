package br.com.doors.ctrlt.model;

import java.util.Arrays;

public class Turma {
	private Long idTurma;
	private Aluno[] alunosTurma;
	private Professor regenteTurma;
	private Professor[] professoresTurma;
	private String nomeTurma;
	public Long getIdTurma() {
		return idTurma;
	}
	public void setIdTurma(Long idTurma) {
		this.idTurma = idTurma;
	}
	public Aluno[] getAlunosTurma() {
		return alunosTurma;
	}
	public void setAlunosTurma(Aluno[] alunosTurma) {
		this.alunosTurma = alunosTurma;
	}
	public Professor getRegenteTurma() {
		return regenteTurma;
	}
	public void setRegenteTurma(Professor regenteTurma) {
		this.regenteTurma = regenteTurma;
	}
	public Professor[] getProfessoresTurma() {
		return professoresTurma;
	}
	public void setProfessoresTurma(Professor[] professoresTurma) {
		this.professoresTurma = professoresTurma;
	}
	public String getNomeTurma() {
		return nomeTurma;
	}
	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}
	@Override
	public String toString() {
		return "Turma [idTurma=" + idTurma + ", alunosTurma="
				+ Arrays.toString(alunosTurma) + ", regenteTurma="
				+ regenteTurma + ", professoresTurma="
				+ Arrays.toString(professoresTurma) + ", nomeTurma="
				+ nomeTurma + "]";
	}
}
