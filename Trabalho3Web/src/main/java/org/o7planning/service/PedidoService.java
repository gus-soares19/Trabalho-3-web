package org.o7planning.service;

import java.util.List;
import java.util.Optional;

import org.o7planning.model.Pedido;
import org.o7planning.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public List<Pedido> findAll() {
		return repo.findAll();
	}

	public Optional<Pedido> findById(Integer id) {
		return repo.findById(id);
	}

	public void delete(Integer id) {
		repo.deleteById(id);
	}

}
