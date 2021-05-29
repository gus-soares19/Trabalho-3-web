package org.o7planning.dto;

import java.io.Serializable;

public class AutenticacaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String usuario;
	private String senha;

	public AutenticacaoDTO() {
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
