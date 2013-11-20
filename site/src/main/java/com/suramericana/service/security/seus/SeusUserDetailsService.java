package com.suramericana.service.security.seus;

import javax.annotation.Resource;

import org.broadleafcommerce.profile.core.domain.Customer;
import org.broadleafcommerce.profile.core.service.CustomerService;
import org.broadleafcommerce.profile.core.service.UserDetailsServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("blUserDetailsService")
public class SeusUserDetailsService extends UserDetailsServiceImpl {
	
	@Resource(name="blCustomerService")
    protected CustomerService customerService;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Customer customer = customerService.readCustomerByUsername(username);
		
		if(customer == null) {
			customer = customerService.createCustomer();
			customer.setEmailAddress(username);
			customer.setFirstName(username);
			//customer.setLastName(null);
			customer.setPassword("Desconocido");
			customer.setUsername(username);
			customerService.saveCustomer(customer);
		}
		return super.loadUserByUsername(username);
	}
	

}
