package org.o7planning.model.enums;

public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"), CLIENT(2, "ROLE_CLIENTE");

	private Integer codigo;
	private String descricao;

	private Perfil() {
	}

	private Perfil(Integer codigo, String descricao) {
		this.setCodigo(codigo);
		this.setDescricao(descricao);
	}

	public static Perfil toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (Perfil x : Perfil.values()) {
			if (cod.equals(x.getCodigo()))
				return x;
		}

		throw new IllegalArgumentException("Tipo com id " + cod + " não existente!");
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		if (codigo < 1 || codigo > 2) {
			throw new IllegalArgumentException("Código para perfil inválido.");
		}
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		if (descricao.isBlank()) {
			throw new IllegalArgumentException("Descrição para perfil inválida.");
		}
		this.descricao = descricao;
	}

}
