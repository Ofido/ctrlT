package br.com.doors.ctrlt.model;

import java.util.Arrays;
import java.util.Calendar;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class Aluno {
	private Long idAluno;
	private Calendar aniversarioAluno;
	private String nomeAluno;
	private String emailAluno;
	private String senhaAluno;
	private String telefoneAluno;
	private String cepAluno;
	private String cpfAluno;
	private String ruaAluno;
	private String numeroAluno;
	private String bairroAluno;
	private String complementoAluno;
	private String cidadeAluno;
	private String estadoAluno;
	private byte[] fotoAluno;
	
	public Long getIdAluno() {
		return idAluno;
	}
	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}
	public Calendar getAniversarioAluno() {
		return aniversarioAluno;
	}
	public void setAniversarioAluno(Calendar aniversarioAluno) {
		this.aniversarioAluno = aniversarioAluno;
	}
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public String getEmailAluno() {
		return emailAluno;
	}
	public void setEmailAluno(String emailAluno) {
		this.emailAluno = emailAluno;
	}
	public String getSenhaAluno() {
		return senhaAluno;
	}
	public void setSenhaAluno(String senhaAluno) {
		this.senhaAluno = senhaAluno;
	}
	public String getTelefoneAluno() {
		return telefoneAluno;
	}
	public void setTelefoneAluno(String telefoneAluno) {
		this.telefoneAluno = telefoneAluno;
	}
	public String getCepAluno() {
		return cepAluno;
	}
	public void setCepAluno(String cepAluno) {
		this.cepAluno = cepAluno;
	}
	public String getCpfAluno() {
		return cpfAluno;
	}
	public void setCpfAluno(String cpfAluno) {
		this.cpfAluno = cpfAluno;
	}
	public String getRuaAluno() {
		return ruaAluno;
	}
	public void setRuaAluno(String ruaAluno) {
		this.ruaAluno = ruaAluno;
	}
	public String getNumeroAluno() {
		return numeroAluno;
	}
	public void setNumeroAluno(String numeroAluno) {
		this.numeroAluno = numeroAluno;
	}
	public String getBairroAluno() {
		return bairroAluno;
	}
	public void setBairroAluno(String bairroAluno) {
		this.bairroAluno = bairroAluno;
	}
	public String getComplementoAluno() {
		return complementoAluno;
	}
	public void setComplementoAluno(String complementoAluno) {
		this.complementoAluno = complementoAluno;
	}
	public String getCidadeAluno() {
		return cidadeAluno;
	}
	public void setCidadeAluno(String cidadeAluno) {
		this.cidadeAluno = cidadeAluno;
	}
	public String getEstadoAluno() {
		return estadoAluno;
	}
	public void setEstadoAluno(String estadoAluno) {
		this.estadoAluno = estadoAluno;
	}
	public byte[] getFotoAluno() {
		return fotoAluno;
	}
	public void setFotoAluno(byte[] fotoAluno) {
		this.fotoAluno = fotoAluno;
	}
	public String getFoto64(){
		String encodedImage = Base64.encode(fotoAluno);
		return encodedImage;
	}
	@Override
	public String toString() {
		return "Aluno [idAluno=" + idAluno + ", aniversarioAluno="
				+ aniversarioAluno + ", nomeAluno=" + nomeAluno
				+ ", emailAluno=" + emailAluno + ", senhaAluno=" + senhaAluno
				+ ", telefoneAluno=" + telefoneAluno + ", cepAluno=" + cepAluno
				+ ", cpfAluno=" + cpfAluno + ", ruaAluno=" + ruaAluno
				+ ", numeroAluno=" + numeroAluno + ", bairroAluno="
				+ bairroAluno + ", complementoAluno=" + complementoAluno
				+ ", cidadeAluno=" + cidadeAluno + ", estadoAluno="
				+ estadoAluno + ", fotoAluno=" + Arrays.toString(fotoAluno)
				+ "]";
	}
}