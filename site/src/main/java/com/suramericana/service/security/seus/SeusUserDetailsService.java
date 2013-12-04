package com.suramericana.service.security.seus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.broadleafcommerce.profile.core.domain.Customer;
import org.broadleafcommerce.profile.core.domain.CustomerAttribute;
import org.broadleafcommerce.profile.core.domain.CustomerAttributeImpl;
import org.broadleafcommerce.profile.core.service.CustomerService;
import org.broadleafcommerce.profile.core.service.UserDetailsServiceImpl;
import org.json.JSONObject;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("blUserDetailsService")
public class SeusUserDetailsService extends UserDetailsServiceImpl {
	
	@Resource(name="blCustomerService")
    protected CustomerService customerService;
	
	@Override
	public UserDetails loadUserByUsername(String usernameAndToken)
			throws UsernameNotFoundException {
		
		String[] arrUsernameAndToken = usernameAndToken.split(":.:");
		String username = arrUsernameAndToken[0].toLowerCase();
		String tokenMus = arrUsernameAndToken[1];
		
		Customer customer = customerService.readCustomerByUsername(username);
		
		if(customer == null) {
			
			SeusService seusService = new SeusService();
			JSONObject jsonInfoFromToken = null;
			
			try {
				
				jsonInfoFromToken = seusService.getInfoFromToken(tokenMus);
				
			} catch (Exception e) {
				
				jsonInfoFromToken = null;
				e.printStackTrace();
			}
			
			customer = customerService.createCustomer();
			
			if(jsonInfoFromToken != null){
				
				String firstname = seusService.getFirstAndLastName( jsonInfoFromToken.getString("name") )[0];
				String lastname = seusService.getFirstAndLastName( jsonInfoFromToken.getString("name") )[1];
				
				customer.setUsername( jsonInfoFromToken.getString("username") );
				customer.setFirstName( firstname );
				customer.setLastName( lastname );
				customer.setEmailAddress( jsonInfoFromToken.getString("email") );
				customer.setPassword("Desconocido");
				
				CustomerAttribute customerDniAttr = new CustomerAttributeImpl();
				customerDniAttr.setName("DNI");
				customerDniAttr.setValue( jsonInfoFromToken.getString("dni") );
				customerDniAttr.setCustomer(customer);
				
				Map<String, CustomerAttribute> mapCustomerAttribute = new HashMap<String, CustomerAttribute>();
				
				mapCustomerAttribute.put("DNI", customerDniAttr);
				
				customer.setCustomerAttributes(mapCustomerAttribute);
				
			}
			else {
				
				customer.setUsername(username);
				customer.setFirstName(username);
				customer.setEmailAddress( username );
				customer.setPassword("Desconocido");
				
			}
			
			customerService.saveCustomer(customer);
		}
		return super.loadUserByUsername(username);
	}
	
	public UserDetails loadUserByUsername(String usernameIn, String tokenMus)
			throws UsernameNotFoundException, IOException {
		
		String username = usernameIn.toLowerCase();
		Customer customer = customerService.readCustomerByUsername(username);
		
		if(customer == null) {
			
			SeusService seusService = new SeusService();
			
			JSONObject jsonInfoFromToken =  seusService.getInfoFromToken(tokenMus);
			
			customer = customerService.createCustomer();
			customer.setEmailAddress( jsonInfoFromToken.getString("email") );
			customer.setFirstName( jsonInfoFromToken.getString("name") );
			//customer.setLastName(null);
			customer.setUsername( jsonInfoFromToken.getString("username") );
			//TODO customer.setDni( jsonInfoFromToken.getString("dni") ); -> Extender entity
			//No importa password porque la autenticación se hace por SEUS
			customer.setPassword("Desconocido");
			customerService.saveCustomer(customer);
			
		}
		return super.loadUserByUsername(username);
	}
	

}
