package com.suramericana.order.service.workflow;

import org.broadleafcommerce.core.order.service.workflow.CartOperationContext;
import org.broadleafcommerce.core.workflow.BaseActivity;

public class SuraAddItemOrderCheckInventory extends BaseActivity<CartOperationContext> {

	@Override
	public CartOperationContext execute(CartOperationContext context) throws Exception {
		System.out.println("En SuraAddItemOrderCheckInventory");
		return context;
	}

}
