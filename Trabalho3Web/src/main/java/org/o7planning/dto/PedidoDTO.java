package org.o7planning.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PedidoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date data;
	private List<ItemPedidoDTO> itensPedido;
	private Integer userId;

	public PedidoDTO() {
	}

	public PedidoDTO(Integer id, Date data, List<ItemPedidoDTO> itensPedido, Integer userId) {
		this.setId(id);
		this.setData(data);
		this.setItensPedido(itensPedido);
		this.setUserId(userId);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<ItemPedidoDTO> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedidoDTO> itensPedido) {
		this.itensPedido = itensPedido;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
