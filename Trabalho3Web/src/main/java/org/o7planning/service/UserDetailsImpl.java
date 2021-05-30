package org.o7planning.service;

import org.o7planning.model.User;
import org.o7planning.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImpl implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
		User user = userService.findByNome(nome);

		if (user == null)
			throw new UsernameNotFoundException("Usuário \"" + nome + "\" inválido");

		return new UserSS(user.getId(), user.getNome(), user.getSenha(), user.getPerfis());
	}

}