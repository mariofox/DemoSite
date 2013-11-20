package com.suramericana.service.security.seus;

import java.io.IOException;
import java.util.Collection;

import javax.annotation.Resource;

import org.apache.http.client.ClientProtocolException;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service("seusCustomAuthenticationProvider")
public class SeusCustomAuthenticationProvider implements AuthenticationProvider {
	
	@Resource(name="blUserDetailsService")
    private UserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        
        JSONObject authSeusResponse = null;
        
        SeusService serviceSeus = new SeusService(); 
        
        try {
        	authSeusResponse = serviceSeus.autenticarSeus(username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ProviderNotFoundException("Seus not available");
		}
        
        if (!authSeusResponse.has("token")) {
            throw new BadCredentialsException("Wrong password.");
        }
        
        UserDetails user = userDetailsService.loadUserByUsername(username);
        
        if (user == null) {
            throw new BadCredentialsException("Username not found.");
        }

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(username, password, authorities);
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
