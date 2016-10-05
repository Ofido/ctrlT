package br.com.doors.ctrlt.model;

import java.util.Arrays;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class Especialista {
	private Long idEspecialista;
	private Disciplina disciplinaEspecialista;
	private String nomeEspecialista;
	private String emailEspecialista;
	private String senhaEspecialista;
	private String telefoneEspecialista;
	private String cpfEspecialista;
	private byte[] fotoEspecialista;
	
	public Long getIdEspecialista() {
		return idEspecialista;
	}
	public void setIdEspecialista(Long idEspecialista) {
		this.idEspecialista = idEspecialista;
	}
	public String getNomeEspecialista() {
		return nomeEspecialista;
	}
	public void setNomeEspecialista(String nomeEspecialista) {
		this.nomeEspecialista = nomeEspecialista;
	}
	public String getEmailEspecialista() {
		return emailEspecialista;
	}
	public void setEmailEspecialista(String emailEspecialista) {
		this.emailEspecialista = emailEspecialista;
	}
	public String getSenhaEspecialista() {
		return senhaEspecialista;
	}
	public void setSenhaEspecialista(String senhaEspecialista) {
		this.senhaEspecialista = senhaEspecialista;
	}
	public String getTelefoneEspecialista() {
		return telefoneEspecialista;
	}
	public void setTelefoneEspecialista(String telefoneEspecialista) {
		this.telefoneEspecialista = telefoneEspecialista;
	}
	public String getCpfEspecialista() {
		return cpfEspecialista;
	}
	public void setCpfEspecialista(String cpfEspecialista) {
		this.cpfEspecialista = cpfEspecialista;
	}
	public Disciplina getDisciplinaEspecialista() {
		return disciplinaEspecialista;
	}
	public void setDisciplinaEspecialista(Disciplina disciplinaEspecialista) {
		this.disciplinaEspecialista = disciplinaEspecialista;
	}
	public byte[] getFotoEspecialista() {
		return fotoEspecialista;
	}
	public void setFotoEspecialista(byte[] fotoEspecialista) {
		this.fotoEspecialista = fotoEspecialista;
	}
	public String getFoto64(){
		String encodedImage = Base64.encode(fotoEspecialista);
		return encodedImage;
		}
	@Override
	public String toString() {
		return "Especialista [idEspecialista=" + idEspecialista
				+ ", disciplinaEspecialista=" + disciplinaEspecialista
				+ ", nomeEspecialista=" + nomeEspecialista
				+ ", emailEspecialista=" + emailEspecialista
				+ ", senhaEspecialista=" + senhaEspecialista
				+ ", telefoneEspecialista=" + telefoneEspecialista
				+ ", cpfEspecialista=" + cpfEspecialista
				+ ", fotoEspecialista=" + Arrays.toString(fotoEspecialista)
				+ "]";
	}
	
}
