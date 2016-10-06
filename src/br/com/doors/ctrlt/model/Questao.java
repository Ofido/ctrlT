package br.com.doors.ctrlt.model;

import java.sql.Time;
import java.util.Arrays;
import java.util.Calendar;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class Questao {
	private Long idQuestao;	
	private TipoQuestao tipoQuestao;
	private Disciplina disciplinaQuestao;
	private Assunto assuntoQuestao;
	private Professor criadorQuestao;
	private Especialista validadorQuestao;
	private Resposta[] respostasQuestao;
	private Integer ratingAlunoQuestao;
	private Integer ratingProfessorQuestao;
	private Integer nivelQuestao;
	private String questao;
	private Time tempoQuestao;
	private Calendar ultimoUsoQuestao;
	private Boolean validadaQuestao;
	private byte[] complementoQuestao;

	public Long getIdQuestao() {
		return idQuestao;
	}

	public void setIdQuestao(Long idQuestao) {
		this.idQuestao = idQuestao;
	}

	public String getQuestao() {
		return questao;
	}

	public void setQuestao(String questao) {
		this.questao = questao;
	}

	public TipoQuestao getTipoQuestao() {
		return tipoQuestao;
	}

	public void setTipoQuestao(TipoQuestao tipoQuestao) {
		this.tipoQuestao = tipoQuestao;
	}

	public Disciplina getDisciplinaQuestao() {
		return disciplinaQuestao;
	}

	public void setDisciplinaQuestao(Disciplina disciplinaQuestao) {
		this.disciplinaQuestao = disciplinaQuestao;
	}

	public Assunto getAssuntoQuestao() {
		return assuntoQuestao;
	}

	public void setAssuntoQuestao(Assunto assunto) {
		this.assuntoQuestao = assunto;
	}

	public Professor getCriadorQuestao() {
		return criadorQuestao;
	}

	public void setCriadorQuestao(Professor criador) {
		this.criadorQuestao = criador;
	}

	public Especialista getValidadorQuestao() {
		return validadorQuestao;
	}

	public void setValidadorQuestao(Especialista validador) {
		this.validadorQuestao = validador;
	}

	public Integer getNivelQuestao() {
		return nivelQuestao;
	}

	public void setNivelQuestao(Integer nivelQuestao) {
		this.nivelQuestao = nivelQuestao;
	}

	public Boolean getValidadaQuestao() {
		return validadaQuestao;
	}

	public void setValidadaQuestao(Boolean validadaQuestao) {
		this.validadaQuestao = validadaQuestao;
	}

	public byte[] getComplementoQuestao() {
		return complementoQuestao;
	}

	public void setComplementoQuestao(byte[] complementoQuestao) {
		this.complementoQuestao = complementoQuestao;
	}

	public String getFoto64() {
		String encodedImage = Base64.encode(complementoQuestao);
		return encodedImage;
	}

	public Resposta[] getRespostasQuestao() {
		return respostasQuestao;
	}

	public void setRespostasQuestao(Resposta[] respostasQuestao) {
		this.respostasQuestao = respostasQuestao;
	}

	public Integer getRatingAlunoQuestao() {
		return ratingAlunoQuestao;
	}

	public void setRatingAlunoQuestao(Integer ratingAlunoQuestao) {
		this.ratingAlunoQuestao = ratingAlunoQuestao;
	}

	public Integer getRatingProfessorQuestao() {
		return ratingProfessorQuestao;
	}

	public void setRatingProfessorQuestao(Integer ratingProfessorQuestao) {
		this.ratingProfessorQuestao = ratingProfessorQuestao;
	}

	@Override
	public String toString() {
		return "Questao [idQuestao=" + idQuestao + ", tipoQuestao="
				+ tipoQuestao.name() + ", disciplinaQuestao=" + disciplinaQuestao
				+ ", assuntoQuestao=" + assuntoQuestao + ", criadorQuestao="
				+ criadorQuestao + ", validadorQuestao=" + validadorQuestao
				+ ", respostasQuestao=" + Arrays.toString(respostasQuestao)
				+ ", ratingAlunoQuestao=" + ratingAlunoQuestao
				+ ", ratingProfessorQuestao=" + ratingProfessorQuestao
				+ ", nivelQuestao=" + nivelQuestao + ", questao=" + questao
				+ ", tempoQuestao=" + tempoQuestao + ", ultimoUsoQuestao="
				+ ultimoUsoQuestao + ", validadaQuestao=" + validadaQuestao
				+ ", complementoQuestao=" + Arrays.toString(complementoQuestao)
				+ "]";
	}
}
