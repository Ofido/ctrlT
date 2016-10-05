package br.com.doors.ctrlt.model;

public class Assunto {
	private Long idAssunto;
	private String nomeAssunto;
	private Disciplina disciplinaAssunto;
	
	public Long getIdAssunto() {
		return idAssunto;
	}
	public void setIdAssunto(Long idAssunto) {
		this.idAssunto = idAssunto;
	}
	public String getNomeAssunto() {
		return nomeAssunto;
	}
	public void setNomeAssunto(String nomeAssunto) {
		this.nomeAssunto = nomeAssunto;
	}
	public Disciplina getDisciplinaAssunto() {
		return disciplinaAssunto;
	}
	public void setDisciplinaAssunto(Disciplina disciplinaAssunto) {
		this.disciplinaAssunto = disciplinaAssunto;
	}
	@Override
	public String toString() {
		return "Assunto [idAssunto=" + idAssunto + ", nomeAssunto="
				+ nomeAssunto + ", disciplinaAssunto=" + disciplinaAssunto
				+ "]";
	}
	
}
