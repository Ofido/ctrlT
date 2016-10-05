package br.com.doors.ctrlt.model;

import java.util.Arrays;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class Professor {
	private Long idProfessor;
	private String nomeProfessor;
	private String emailProfessor;
	private String senhaProfessor;
	private String telefoneProfessor;
	private String cpfProfessor;
	private String escolaProfessor;
	private String tagProfessor;
	private Boolean licenca;
	private byte[] fotoProfessor;
	
	public Boolean getLicenca() {
		return licenca;
	}
	public void setLicenca(Boolean licenca) {
		this.licenca = licenca;
	}
	public String getEscolaProfessor() {
		return escolaProfessor;
	}
	public void setEscolaProfessor(String escolaProfessor) {
		this.escolaProfessor = escolaProfessor;
	}
	public String getTagProfessor() {
		return tagProfessor;
	}
	public void setTagProfessor(String tagProfessor) {
		this.tagProfessor = tagProfessor;
	}
	public Long getIdProfessor() {
		return idProfessor;
	}
	public void setIdProfessor(Long idProfessor) {
		this.idProfessor = idProfessor;
	}
	public String getNomeProfessor() {
		return nomeProfessor;
	}
	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}
	public String getEmailProfessor() {
		return emailProfessor;
	}
	public void setEmailProfessor(String emailProfessor) {
		this.emailProfessor = emailProfessor;
	}
	public String getSenhaProfessor() {
		return senhaProfessor;
	}
	public void setSenhaProfessor(String senhaProfessor) {
		this.senhaProfessor = senhaProfessor;
	}
	public String getTelefoneProfessor() {
		return telefoneProfessor;
	}
	public void setTelefoneProfessor(String telefoneProfessor) {
		this.telefoneProfessor = telefoneProfessor;
	}
	public String getCpfProfessor() {
		return cpfProfessor;
	}
	public void setCpfProfessor(String cpfProfessor) {
		this.cpfProfessor = cpfProfessor;
	}
	public byte[] getFotoProfessor() {
		return fotoProfessor;
	}
	public void setFotoProfessor(byte[] fotoProfessor) {
		this.fotoProfessor = fotoProfessor;
	}
	public String getFoto64(){
		String encodedImage = Base64.encode(fotoProfessor);
		return encodedImage;
		}
	@Override
	public String toString() {
		return "Professor [idProfessor=" + idProfessor
				+ ", nomeProfessor=" + nomeProfessor + ", emailProfessor="
				+ emailProfessor + ", senhaProfessor=" + senhaProfessor
				+ ", telefoneProfessor=" + telefoneProfessor
				+ ", cpfProfessor=" + cpfProfessor + ", escolaProfessor="
				+ escolaProfessor + ", tagProfessor=" + tagProfessor
				+ ", licenca=" + licenca + ", fotoProfessor="
				+ Arrays.toString(fotoProfessor) + "]";
	}
	
}