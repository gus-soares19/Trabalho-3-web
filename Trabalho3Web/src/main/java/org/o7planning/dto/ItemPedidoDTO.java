package org.o7planning.dto;

import java.io.Serializable;

public class ItemPedidoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer produtoId;
	private Double quantidade;

	public ItemPedidoDTO() {
	}

	public ItemPedidoDTO(Integer produtoId, Double quantidade) {
		this.setProdutoId(produtoId);
		this.setQuantidade(quantidade);
	}

	public Integer getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Integer produtoId) {
		this.produtoId = produtoId;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

}
