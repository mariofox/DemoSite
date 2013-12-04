package com.suramericana.service.security.seus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class SeusService {
	
	public JSONObject autenticarSeus(String username, String password) throws ClientProtocolException, IOException, JSONException {
        
		HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("https://soa.suramericana.com.co/SuraLNFWeb/http/autenticar");
        
        JSONObject jsonInput = new JSONObject();
		jsonInput.put("login", username);
		jsonInput.put("clave", password);
		jsonInput.put("ip", "10.10.10.10");
		
        StringEntity input = new StringEntity( jsonInput.toString());
        post.setEntity(input);
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        JSONObject jsonResponse = null;
        while ((line = rd.readLine()) != null) {
            jsonResponse = new JSONObject(line);
        }
        
        return jsonResponse;
    }
	
	public JSONObject getInfoFromToken(String tokenMus) throws IOException, JSONException {
		
		int usernameIndex = 0;
		int dniIndex = 1;
		int nameIndex = 7;
		int emailIndex = 8;
		
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet("http://apps02.suranet.com/seus-soa/pubs/SrvGetInfoUsuarioFromTokenMus?token=" + tokenMus);
		HttpResponse response = client.execute(request);
		
		JSONObject jsonResponseInfoFromToken = new JSONObject();
		
		HttpEntity entity = response.getEntity();
		String entityContents = EntityUtils.toString(entity);
		
		JSONObject jsonInfoSeusResponse = XML.toJSONObject(entityContents);

		JSONArray jsonArraySeusInfo = jsonInfoSeusResponse.getJSONObject("SERVICIO").getJSONObject("PARAMS").getJSONArray("PAR");
		
		String username = jsonArraySeusInfo.getJSONObject(usernameIndex).getString("content").toLowerCase();
		String dni = jsonArraySeusInfo.getJSONObject(dniIndex).getString("content");
		String name = URLDecoder.decode( jsonArraySeusInfo.getJSONObject(nameIndex).getString("content"), "UTF-8" );
		String email = URLDecoder.decode( jsonArraySeusInfo.getJSONObject(emailIndex).getString("content"), "UTF-8" );
		
		jsonResponseInfoFromToken.put("username", username);
		jsonResponseInfoFromToken.put("dni", dni);
		jsonResponseInfoFromToken.put("name", name);
		jsonResponseInfoFromToken.put("email", email);
		
		return jsonResponseInfoFromToken;
		
	}
	
	public String[] getFirstAndLastName(String name){
		
		String[] responseFirstLastName = new String[2];
		String firstname = "";
		String lastname = "";
		
		int countWordsName = StringUtils.countMatches(name.trim(), " ") + 1;
		
		String[] arrName = name.split(" ");
		
		int initPosLastname = 0;
		
		if ( countWordsName  >= 4 ) 
		{ 
			//Asumir que lastname empieza desde 2 si hay 4 palabras
			initPosLastname = 2; 
	    } else {
	    	initPosLastname = 1;
	    }
		
		for (int i = 0; i < arrName.length; i++) {
			if ( i < initPosLastname ){
				firstname = firstname + " " + arrName[i];
			} else {
				lastname = lastname + " " + arrName[i];
			}
				
		}
		responseFirstLastName[0] = firstname.trim();
		responseFirstLastName[1] = lastname.trim();
		return responseFirstLastName;
	}

}
