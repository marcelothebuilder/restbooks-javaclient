package io.github.marcelothebuilder.restbooks.client.restclient;

import java.net.URI;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractClient<T> {

	private static final String BASE_URI = "http://localhost:8080";
	
	private static final String HEADER_AUTHORIZATION = "Basic cmVzdGJvb2tzOnBhc3M=";
	private static final String HEADER_CONTENT_TYPE = "application/json";
	
	protected T fetchById(URI uri, Long id, Class<T> responseClass) {
		RestTemplate restTemplate = new RestTemplate();
		
		URI parametrizedUri = URI.create(uri.toString() + "/" + id);
		
		RequestEntity<Void> requestEntity = RequestEntity.get(parametrizedUri)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.header("Authorization", HEADER_AUTHORIZATION)
				.header("Content-Type", HEADER_CONTENT_TYPE)
				.build();
		
		ResponseEntity<T> responseEntity = restTemplate.exchange(requestEntity, responseClass);
		
		return responseEntity.getBody();
	}
	
	protected String save(URI uri, T object) {
		RestTemplate restTemplate = new RestTemplate();
		RequestEntity<T> requestEntity = RequestEntity.post(uri)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.header("Authorization", HEADER_AUTHORIZATION)
				.header("Content-Type", HEADER_CONTENT_TYPE)
				.body(object, object.getClass());
		
		ResponseEntity<Void> responseEntity = restTemplate.exchange(requestEntity, Void.class);
		
		return responseEntity.getHeaders().getLocation().toString();
	}
	
	protected List<T> fetchListFromURI(URI uri, ParameterizedTypeReference<List<T>> type) {
		RestTemplate restTemplate = new RestTemplate();
		RequestEntity<Void> requestEntity = RequestEntity.get(uri)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.header("Authorization", HEADER_AUTHORIZATION)
				.header("Content-Type", HEADER_CONTENT_TYPE)
				.build();
		
		ResponseEntity<List<T>> response = restTemplate.exchange(requestEntity, type);
		
		return response.getBody();
	}
	
	protected URI createUriByResource(String resource) {
		return URI.create(getFullUriByResource(resource));
	}

	private static String getBaseUri() {
		return BASE_URI;
	}

	private static String getFullUriByResource(String resource) {
		if (isResourceBlank(resource)) {
			return getBaseUri();
		}
		
		if (!hasLeadingForwardSlash(resource)) {
			resource = prependWithForwardSlash(resource);
		}

		return String.format("%s%s", AbstractClient.getBaseUri(), resource);
	}

	private static boolean hasLeadingForwardSlash(String resource) {
		char firstCharacter = resource.charAt(0);
		if (firstCharacter == '/') {
			return true;
		}
		return false;
	}

	private static String prependWithForwardSlash(String resource) {
		return "/" + resource;
	}

	private static boolean isResourceBlank(String resource) {
		if (resource == null) {
			return true;
		}

		if ("".equals(resource.trim())) {
			return true;
		}

		return false;
	}
}
