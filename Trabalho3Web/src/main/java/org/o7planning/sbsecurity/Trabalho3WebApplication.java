package org.o7planning.sbsecurity;

import java.util.Arrays;

import org.o7planning.model.Produto;
import org.o7planning.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Trabalho3WebApplication implements CommandLineRunner {

	@Autowired
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(Trabalho3WebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Produto p1 = new Produto();
		p1.setNome("calzone");
		p1.setPreco(35.0);

		Produto p2 = new Produto();
		p2.setNome("pizza enrolada");
		p2.setPreco(110.0);

		produtoRepository.saveAll(Arrays.asList(p1, p2));
	}

}
