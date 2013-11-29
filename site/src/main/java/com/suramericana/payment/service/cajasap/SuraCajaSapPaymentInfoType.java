package com.suramericana.payment.service.cajasap;

import org.broadleafcommerce.core.payment.service.type.PaymentInfoType;

public class SuraCajaSapPaymentInfoType extends PaymentInfoType {
	
	private static final long serialVersionUID = 1L;
	
	public static SuraCajaSapPaymentInfoType CAJA_SAP = new SuraCajaSapPaymentInfoType("CAJA_SAP","Suramericana Caja SAP");

    public SuraCajaSapPaymentInfoType(String type, String friendlyType) {
        super(type, friendlyType);
    }
        
    public SuraCajaSapPaymentInfoType() {
        //do nothing
    }

}
