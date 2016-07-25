package io.github.marcelothebuilder.restbooks.client.restclient;

import java.net.URI;

import io.github.marcelothebuilder.restbooks.dto.AutorDTO;

public class AutoresClient extends AbstractClient<AutorDTO> {
	private URI resourceUri;

	public AutoresClient() {
		this.resourceUri = this.createUriByResource("autores");
	}

	public AutorDTO porCodigo(long codigo) {
		return this.fetchById(resourceUri, codigo, AutorDTO.class);
	}
	
	
}
