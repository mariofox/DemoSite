package com.suramericana.order.service.type;

import org.broadleafcommerce.core.order.service.type.OrderStatus;

public class SuraOrderStatus extends OrderStatus {
	
	private static final long serialVersionUID = 1L;
    public static final OrderStatus ABORTED= new OrderStatus("ABORTED", "Aborted");
    public static final OrderStatus PENDING= new OrderStatus("PENDING", "Pending");

}
