package com.suramericana.checkout.service.workflow;

import org.broadleafcommerce.core.checkout.service.workflow.CheckoutContext;
import org.broadleafcommerce.core.workflow.BaseActivity;

public class SuraCheckoutCheckInventoryActivity extends BaseActivity<CheckoutContext> {

	@Override
	public CheckoutContext  execute(CheckoutContext context) throws Exception {
		

        System.out.println("en SuraCheckInventoryActivity");


        return context;
	}
	

	

}
