package org.o7planning.service;

import java.util.List;

import org.o7planning.model.Produto;
import org.o7planning.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	public List<Produto> findAll(){
		return repo.findAll();
	}
	
}
