package org.o7planning.repository;

import org.o7planning.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Transactional(readOnly = true)
	public User findByNome(String nome);

}