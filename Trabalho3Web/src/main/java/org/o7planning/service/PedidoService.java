package org.o7planning.service;

import java.util.List;

import org.o7planning.model.Pedido;
import org.o7planning.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public List<Pedido> findAll(){
		return repo.findAll();
	}
	
}
