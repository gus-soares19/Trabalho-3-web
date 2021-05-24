package org.o7planning.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.o7planning.dto.ItemPedidoDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();
	private Double quantidade;
	private Double precoTotal;

	public ItemPedido() {

	}

	public ItemPedido(Pedido pedido, Produto produto, Double quantidade, Double preco) {
		this.setQuantidade(quantidade);
		this.setPreco(preco);
	}

	public ItemPedido(ItemPedidoDTO itemPedidoDTO) {
		this.setQuantidade(itemPedidoDTO.getQuantidade());
	}

	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return precoTotal;
	}

	public void setPreco(Double preco) {
		this.precoTotal = preco * this.getQuantidade();
	}

	public Produto getProduto() {
		return this.id.getProduto();
	}

	public void setProduto(Produto produto) {
		this.id.setProduto(produto);
	}

	@JsonIgnore
	public Pedido getPedido() {
		return this.id.getPedido();
	}

	public void setPedido(Pedido pedido) {
		this.id.setPedido(pedido);
	}

}
