package br.com.doors.ctrlt.model;

import java.util.Arrays;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class Resposta {
	private Long idResposta;
	private String rsposta;
	private byte[] complementoResposta;
	private Boolean corretaResposta;
	
	public Long getIdResposta() {
		return idResposta;
	}
	public void setIdResposta(Long idResposta) {
		this.idResposta = idResposta;
	}
	public String getRsposta() {
		return rsposta;
	}
	public void setRsposta(String rsposta) {
		this.rsposta = rsposta;
	}
	public byte[] getComplementoResposta() {
		return complementoResposta;
	}
	public void setComplementoResposta(byte[] complementoResposta) {
		this.complementoResposta = complementoResposta;
	}
	public String getFoto64(){
		String encodedImage = Base64.encode(complementoResposta);
		return encodedImage;
		}
	public Boolean getCorretaResposta() {
		return corretaResposta;
	}
	public void setCorretaResposta(Boolean corretaResposta) {
		this.corretaResposta = corretaResposta;
	}
	
	@Override
	public String toString() {
		return "Resposta [idResposta=" + idResposta + ", rsposta=" + rsposta
				+ ", complementoResposta="
				+ Arrays.toString(complementoResposta) + ", corretaResposta="
				+ corretaResposta + "]";
	}
	
}
