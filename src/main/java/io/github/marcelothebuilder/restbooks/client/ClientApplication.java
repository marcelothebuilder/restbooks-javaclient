package io.github.marcelothebuilder.restbooks.client;

import java.util.Date;

import io.github.marcelothebuilder.restbooks.client.restclient.AutoresClient;
import io.github.marcelothebuilder.restbooks.client.restclient.LivrosClient;
import io.github.marcelothebuilder.restbooks.dto.AutorDTO;
import io.github.marcelothebuilder.restbooks.dto.LivroDTO;

public class ClientApplication {
	public static void main(String[] args) {
		LivrosClient client = new LivrosClient();

		LivroDTO novoLivro = new LivroDTO();

		novoLivro.setEditora("Abril");
		novoLivro.setNome("Livro de Política");
		novoLivro.setPublicacao(new Date());
		novoLivro.setResumo("Um resumo pacífico");

		AutoresClient autoresClient = new AutoresClient();

		AutorDTO primeiroAutor = autoresClient.porCodigo(1L);

		novoLivro.setAutor(primeiroAutor);

		client.salvar(novoLivro);

		for (LivroDTO livroDTO : client.listar()) {
			System.out.println("Livro: " + livroDTO.getNome());
		}
	}

}
