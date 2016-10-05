package br.com.doors.ctrlt.model;

public class Administrador {
	private Long idAdm;
	private String nomeAdm;
	private String emailAdm;
	private String senhaAdm;
	private String telefoneAdm;
	private String cpfAdm;
	
	public Long getIdAdm() {
		return idAdm;
	}
	public void setIdAdm(Long idAdm) {
		this.idAdm = idAdm;
	}
	public String getNomeAdm() {
		return nomeAdm;
	}
	public void setNomeAdm(String nomeAdm) {
		this.nomeAdm = nomeAdm;
	}
	public String getEmailAdm() {
		return emailAdm;
	}
	public void setEmailAdm(String emailAdm) {
		this.emailAdm = emailAdm;
	}
	public String getSenhaAdm() {
		return senhaAdm;
	}
	public void setSenhaAdm(String senhaAdm) {
		this.senhaAdm = senhaAdm;
	}
	public String getTelefoneAdm() {
		return telefoneAdm;
	}
	public void setTelefoneAdm(String telefoneAdm) {
		this.telefoneAdm = telefoneAdm;
	}
	public String getCpfAdm() {
		return cpfAdm;
	}
	public void setCpfAdm(String cpfAdm) {
		this.cpfAdm = cpfAdm;
	}
	@Override
	public String toString() {
		return "Administrador [idAdm=" + idAdm + ", nomeAdm=" + nomeAdm
				+ ", emailAdm=" + emailAdm + ", senhaAdm=" + senhaAdm
				+ ", telefoneAdm=" + telefoneAdm + ", cpfAdm=" + cpfAdm + "]";
	}
	
}
