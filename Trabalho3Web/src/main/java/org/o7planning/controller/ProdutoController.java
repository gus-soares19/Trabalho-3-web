package org.o7planning.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.o7planning.model.Produto;
import org.o7planning.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@GetMapping
	public ResponseEntity<List<Produto>> findAll() {
		List<Produto> list = service.findAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/findPrice")
	public ResponseEntity<List<Produto>> findByPrice(
			@RequestParam(name = "preco", defaultValue = "100.0") String preco) {
		List<Produto> list = service.findAllByPriceLessThanEqual(Double.valueOf(preco));
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/findProduct")
	public ResponseEntity<List<Produto>> findAllByNomeContaining(@RequestParam(name = "nome") String nome) {
		List<Produto> list = service.findAllByNomeContaining(nome);
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Integer id) {
		Produto produto = service.findById(id);
		return ResponseEntity.ok(produto);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Produto> create(@RequestBody Produto produto) {
		Produto produtoCriado = service.create(produto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produtoCriado.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable("id") Integer id, @Valid @RequestBody Produto produto) {
		service.update(id, produto);
		return ResponseEntity.noContent().build();
	}
}
