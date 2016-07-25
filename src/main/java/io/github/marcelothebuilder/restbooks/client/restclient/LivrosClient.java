package io.github.marcelothebuilder.restbooks.client.restclient;

import java.net.URI;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;

import io.github.marcelothebuilder.restbooks.dto.LivroDTO;

public class LivrosClient extends AbstractClient<LivroDTO> {
	
	private URI resourceUri;
	
	public LivrosClient() {
		this.resourceUri = this.createUriByResource("livros");
	}
	
	public List<LivroDTO> listar() {
		return this.fetchListFromURI(resourceUri, new LivroDtoListTypeReference());
	}
	
	private class LivroDtoListTypeReference extends ParameterizedTypeReference<List<LivroDTO>> {};
	
	public String salvar(LivroDTO livro) {
		return save(resourceUri, livro);
	}
}
