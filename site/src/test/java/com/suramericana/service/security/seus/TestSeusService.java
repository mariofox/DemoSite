package com.suramericana.service.security.seus;

import java.io.IOException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class TestSeusService {

	@Test
	public void testGetInfoFromToken() throws IOException {
		
		SeusService seusService = new SeusService();
		
		//Autenticar con usuario y clave para obtener token
		JSONObject jsonAutenticResponse = seusService.autenticarSeus("mariaral", "c4152mar");
		
		String tokenMusFromService = jsonAutenticResponse.getString("tokenMus");
		
		//Obtener info de usuario dado token
		JSONObject jsonInfoSeusResponse = seusService.getInfoFromToken(tokenMusFromService);
		
		Assert.assertEquals("Validar que username sea mariaral", jsonInfoSeusResponse.getString("username"), "mariaral");
		Assert.assertEquals("Validar que dni sea C1128448357", jsonInfoSeusResponse.getString("dni"), "C1128448357");
		Assert.assertEquals("Validar que name sea Mario Alejandro Aristizabal Alzate", jsonInfoSeusResponse.getString("name"), "Mario Alejandro Aristizabal Alzate");
		Assert.assertEquals("Validar que email sea maristizabala@sura.com.co", jsonInfoSeusResponse.getString("email"), "maristizabala@sura.com.co");
		

	}
	
	@Test
	public void testGetFirstAndLastName() {

		SeusService seusService = new SeusService();
		
		String[] firstLastName = seusService.getFirstAndLastName("Mario Alejandro Aristizabal Alzate");
		Assert.assertEquals("Validar que firstname sea Mario Alejandro", firstLastName[0], "Mario Alejandro");
		Assert.assertEquals("Validar que lastname sea Aristizabal Alzate", firstLastName[1], "Aristizabal Alzate");
		
		firstLastName = seusService.getFirstAndLastName("Jhon Mesa Bedoya");
		Assert.assertEquals("Validar que firstname sea Jhon", firstLastName[0], "Jhon");
		Assert.assertEquals("Validar que lastname sea Mesa Bedoya", firstLastName[1], "Mesa Bedoya");
		
		firstLastName = seusService.getFirstAndLastName("Ramiro Antonio Puerta Camacho Perez");
		Assert.assertEquals("Validar que firstname sea Ramiro Antonio", firstLastName[0], "Ramiro Antonio");
		Assert.assertEquals("Validar que lastname sea Puerta Camacho Perez", firstLastName[1], "Puerta Camacho Perez");
		
		firstLastName = seusService.getFirstAndLastName("Camilo Giraldo");
		Assert.assertEquals("Validar que firstname sea Camilo", firstLastName[0], "Camilo");
		Assert.assertEquals("Validar que lastname sea Puerta Giraldo", firstLastName[1], "Giraldo");
		
	}
	

}
