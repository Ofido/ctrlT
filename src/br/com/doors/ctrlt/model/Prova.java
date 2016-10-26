package br.com.doors.ctrlt.model;

import java.util.Calendar;
import java.util.List;

public class Prova {
	private Long id;
	private List<Questao> questoes;
	private Professor professor;
	private Turma turma;
	private TipoProva tipoProva;
	private Calendar tempo;
	private Calendar inicio;
	private Calendar fim;
	private Double nota;
	private Double acertos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public TipoProva getTipoProva() {
		return tipoProva;
	}

	public void setTipoProva(TipoProva tipoProva) {
		this.tipoProva = tipoProva;
	}

	public Calendar getTempo() {
		return tempo;
	}

	public void setTempo(Calendar tempo) {
		this.tempo = tempo;
	}

	public Calendar getInicio() {
		return inicio;
	}

	public void setInicio(Calendar inicio) {
		this.inicio = inicio;
	}

	public Calendar getFim() {
		return fim;
	}

	public void setFim(Calendar fim) {
		this.fim = fim;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public Double getAcertos() {
		return acertos;
	}

	public void setAcertos(Double acertos) {
		this.acertos = acertos;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	@Override
	public String toString() {
		return "Prova [id=" + id + ", questoes=" + questoes + ", professor="
				+ professor + ", turma=" + turma + ", tipoProva=" + tipoProva
				+ ", tempo=" + tempo + ", inicio=" + inicio + ", fim=" + fim
				+ ", nota=" + nota + ", acertos=" + acertos + "]";
	}

}
