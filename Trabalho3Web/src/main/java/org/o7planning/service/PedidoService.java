package org.o7planning.service;

import java.util.List;
import java.util.Optional;

import org.o7planning.dto.ItemPedidoDTO;
import org.o7planning.dto.PedidoDTO;
import org.o7planning.model.ItemPedido;
import org.o7planning.model.Pedido;
import org.o7planning.model.Produto;
import org.o7planning.model.User;
import org.o7planning.repository.ItemPedidoRepository;
import org.o7planning.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepo;

	@Autowired
	private ItemPedidoRepository itemPedidoRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public List<Pedido> findAll() {
		return pedidoRepo.findAll();
	}

	public Pedido findById(Integer id) {
		Optional<Pedido> pedido = pedidoRepo.findById(id);
		return pedido.orElseThrow(() -> new RuntimeException("pedido não encontrado."));
	}

	public void deleteById(Integer id) {
		Pedido pedido = findById(id);

		itemPedidoRepo.deleteAll(pedido.getItensPedido());

		pedidoRepo.deleteById(id);
	}

	public Pedido create(PedidoDTO pedidoDTO) {
		User user = userService.findById(pedidoDTO.getUserId());
		Pedido pedido = new Pedido(pedidoDTO);

		pedido.setUser(user);
		pedido = pedidoRepo.save(pedido);

		ItemPedido itemPedido = null;
		Produto produto = null;
		for (ItemPedidoDTO itemPedidoDTO : pedidoDTO.getItensPedido()) {
			produto = produtoService.findById(itemPedidoDTO.getProdutoId());
			itemPedido = new ItemPedido(itemPedidoDTO);
			itemPedido.setProduto(produto);
			itemPedido.setPedido(pedido);
			itemPedido.setPreco(produto.getPreco());
			itemPedidoRepository.save(itemPedido);
		}

		return pedidoRepo.save(pedido);
	}

	// REVER
	public Pedido update(Integer id, PedidoDTO newPedidoDTO) {
		Pedido pedidoEncontrado = this.findById(id);

		if (pedidoEncontrado == null) {
			throw new NullPointerException("Pedido para atualizar não encontrado.");
		}

		Pedido pedido = create(newPedidoDTO);
		pedido.setId(pedidoEncontrado.getId());

		pedidoRepo.save(pedido);

		return pedido;
	}

}
