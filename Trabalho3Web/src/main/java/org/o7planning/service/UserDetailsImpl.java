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
		User cli = userService.findByNome(nome);

		if (cli == null)
			throw new UsernameNotFoundException("Usuário \"" + nome + "\" inválido");

		return new UserSS(cli.getId(), cli.getNome(), cli.getSenha(), cli.getPerfis());
	}

}