package org.o7planning.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.o7planning.dto.ItemPedidoDTO;
import org.o7planning.dto.PedidoDTO;
import org.o7planning.model.ItemPedido;
import org.o7planning.model.Pedido;
import org.o7planning.model.Produto;
import org.o7planning.model.User;
import org.o7planning.model.enums.Perfil;
import org.o7planning.repository.ItemPedidoRepository;
import org.o7planning.repository.PedidoRepository;
import org.o7planning.security.UserSS;
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

		UserSS user = UserService.autenticado();

		if (user == null) {
			throw new NullPointerException("usuário não autenticado");
		}

		User u = userService.findById(user.getId());

		List<Pedido> pedidos;
		if (u.getPerfis().contains(Perfil.ADMIN)) {
			pedidos = pedidoRepo.findAll();
		} else {
			pedidos = new ArrayList<Pedido>();
			for (Pedido pedido : u.getPedidos()) {
				pedidos.add(findById(pedido.getId()));
			}
		}
		return pedidos;
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
			itemPedido.setPrecoTotal(produto.getPreco());
			itemPedidoRepository.save(itemPedido);
		}

		return pedido;
	}

	public Pedido update(Integer id, PedidoDTO pedidoDTO) {

		Pedido pedido = this.findById(id);
		User user = userService.findById(pedidoDTO.getUserId());

		pedido.setUser(user);
		pedido.setData(pedidoDTO.getData());

		ItemPedido itemPedido = null;
		Produto produto = null;

		itemPedidoRepository.deleteAll(pedido.getItensPedido());

		for (ItemPedidoDTO itemPedidoDTO : pedidoDTO.getItensPedido()) {
			produto = produtoService.findById(itemPedidoDTO.getProdutoId());
			itemPedido = new ItemPedido(itemPedidoDTO);
			itemPedido.setProduto(produto);
			itemPedido.setPedido(pedido);
			itemPedido.setPrecoTotal(produto.getPreco());
			itemPedidoRepository.save(itemPedido);
		}

		return pedido;
	}

}
