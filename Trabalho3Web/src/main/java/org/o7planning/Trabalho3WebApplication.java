package org.o7planning;

import java.util.Arrays;
import java.util.Date;

import org.o7planning.model.ItemPedido;
import org.o7planning.model.Pedido;
import org.o7planning.model.Produto;
import org.o7planning.model.User;
import org.o7planning.repository.ItemPedidoRepository;
import org.o7planning.repository.PedidoRepository;
import org.o7planning.repository.ProdutoRepository;
import org.o7planning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Trabalho3WebApplication implements CommandLineRunner {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(Trabalho3WebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		try {

			User user = new User();
			user.setNome("Gustavo Soares");

			user = userRepository.save(user);

			Produto p1 = new Produto();
			p1.setNome("calzone");
			p1.setPreco(35.0);

			Produto p2 = new Produto();
			p2.setNome("pizza enrolada");
			p2.setPreco(110.0);

			Pedido pedido = new Pedido();
			pedido.setData(new Date());
			pedido.setUser(user);

			ItemPedido item1 = new ItemPedido();
			item1.setPedido(pedido);
			item1.setProduto(p1);
			item1.setQuantidade(1.0);
			item1.setPreco(p1.getPreco());

			ItemPedido item2 = new ItemPedido();
			item2.setPedido(pedido);
			item2.setProduto(p2);
			item2.setQuantidade(1.0);
			item2.setPreco(p2.getPreco());

			pedido.setItensPedido(Arrays.asList(item1, item2));
			p1.getItens().add(item1);
			p2.getItens().add(item2);

			produtoRepository.saveAll(Arrays.asList(p1, p2));
			pedidoRepository.save(pedido);
			itemPedidoRepository.saveAll(Arrays.asList(item1, item2));

		} catch (Exception e) {
			System.out.println("Mensagem: " + e.getMessage() + "\ncausa: " + e.getCause());
		}

	}

}
