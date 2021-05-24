package org.o7planning.service;

import java.util.List;
import java.util.Optional;

import org.o7planning.model.Produto;
import org.o7planning.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;

	public List<Produto> findAll() {
		return repo.findAll();
	}

	public Produto findById(Integer id) {
		Optional<Produto> produto = repo.findById(id);
		return produto.orElseThrow(() -> new RuntimeException("produto não encontrado."));
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

	public Produto create(Produto produto) {
		return repo.save(produto);
	}

	public Produto update(Integer id, Produto produto) {
		Produto produtoEncontrado = this.findById(id);

		if (produtoEncontrado == null) {
			throw new NullPointerException("Produto para atualizar não encontrado.");
		}

		produto.setId(produtoEncontrado.getId());

		repo.save(produto);

		return produto;
	}

}
