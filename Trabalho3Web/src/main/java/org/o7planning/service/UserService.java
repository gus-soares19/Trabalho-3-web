package org.o7planning.service;

import java.util.List;
import java.util.Optional;

import org.o7planning.model.User;
import org.o7planning.repository.UserRepository;
import org.o7planning.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
		return user.orElseThrow(() -> new NullPointerException("ERRO 404 - usuário não encontrado."));
	}

	public User findByNome(String nome) {
		User user = repo.findByNome(nome);

		if (user == null) {
			throw new NullPointerException("ERRO 404 - usuário não encontrado.");
		}
		return user;
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

	public User create(User user) {
		return repo.save(user);
	}

	public User update(Integer id, User user) {
		User userEncontrado = getUserAutenticado();
//		User userEncontrado = this.findById(id);

		user.setId(userEncontrado.getId());

		repo.save(user);

		return user;
	}

	public static UserSS autenticado() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

	public User getUserAutenticado() {
		UserSS user = UserService.autenticado();

		if (user == null) {
			throw new NullPointerException("ERRO 401 - usuário não autenticado");
		}

		return findById(user.getId());
	}

}
