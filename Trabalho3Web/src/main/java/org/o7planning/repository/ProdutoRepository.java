package org.o7planning.repository;

import java.util.List;

import org.o7planning.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
	@Transactional(readOnly = true)
	public List<Produto> findAllByPrecoLessThanEqual(Double preco);
	
	@Transactional(readOnly = true)
	public List<Produto> findAllByNomeContaining(String nome);

}
