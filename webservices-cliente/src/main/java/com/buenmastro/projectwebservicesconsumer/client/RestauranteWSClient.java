package com.buenmastro.projectwebservicesconsumer.client;

import java.time.LocalDateTime;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.buenmastro.projectwebservicesconsumer.dto.RestauranteDTO;

public class RestauranteWSClient {

	public static void main(String[] args) {

		// :::::::::::::GET::::::::::::::

//		Client client = ClientBuilder.newClient();
//		WebTarget webTarget = client.target("http://localhost:8080/webservices/buenmaestro/restauranteWS").path("consultarRestaurantes");
//		System.out.println(webTarget);
//		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
//		Response response = invocationBuilder.get();

//		if (response.getStatus() == 204) {
//			System.out.println("No se encontro el restaurante con el numero de restaurante");
//		}
//		if (response.getStatus() == 200) {
//			
//			RestauranteDTO restauranteDTO = response.readEntity(RestauranteDTO.class);
//			
//			System.out.println("Nombre Restaurante: " + restauranteDTO.getNombre());
//			System.out.println("Fecha de Creacion: " + restauranteDTO.getFechaCreacion());
//			
//		}

		// :::::::::::::POST::::::::::::::

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/webservices/buenmaestro/restauranteWS")
				.path("guardarRestaurante");

		RestauranteDTO res = new RestauranteDTO();
		res.setIdRestaurante(1L);
		res.setDireccion("Calle 3ra Norte 708");
		res.setNombre("Restaurante El Ejemplo");
		res.setSlogan("Inserte Slogan aqui");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(res, MediaType.APPLICATION_JSON));

		if (response.getStatus() == 400) {
			String error = response.readEntity(String.class);
			System.out.println(error);
		}

		if (response.getStatus() == 200) {
			RestauranteDTO restauranteDTO = response.readEntity(RestauranteDTO.class);
			System.out.println(restauranteDTO.getNombre());
			System.out.println(restauranteDTO.getSlogan());
		}
	}

}
