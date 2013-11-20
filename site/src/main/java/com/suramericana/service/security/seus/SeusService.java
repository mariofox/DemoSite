package com.suramericana.service.security.seus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

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

}
