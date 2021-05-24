package org.o7planning.service;

import java.util.List;
import java.util.Optional;

import org.o7planning.model.User;
import org.o7planning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(Integer id) {
		Optional<User> user = repo.findById(id);
		return user.orElseThrow(() -> new RuntimeException("pedido não encontrado."));
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

	public User create(User user) {
		return repo.save(user);
	}

	public User update(Integer id, User user) {
		User userEncontrado = this.findById(id);

		if (userEncontrado == null) {
			throw new NullPointerException("Usuário para atualizar não encontrado.");
		}

		user.setId(userEncontrado.getId());

		repo.save(user);

		return user;
	}

}
