package com.suramericana.payment.service.cajasap;

import org.broadleafcommerce.common.money.Money;
import org.broadleafcommerce.core.payment.domain.PaymentResponseItem;
import org.broadleafcommerce.core.payment.service.PaymentContext;
import org.broadleafcommerce.core.payment.service.exception.PaymentException;
import org.broadleafcommerce.core.payment.service.module.AbstractModule;
import org.broadleafcommerce.core.payment.service.module.DefaultModule;
import org.broadleafcommerce.core.payment.service.type.PaymentInfoType;

public class SuraCajaSapPaymentModule extends AbstractModule {
	
	@Override
    public PaymentResponseItem processAuthorize(PaymentContext paymentContext, Money amountToAuthorize, PaymentResponseItem responseItem) throws PaymentException {
        throw new PaymentException("authorize not implemented.");
    }

    @Override
    public PaymentResponseItem processReverseAuthorize(PaymentContext paymentContext, Money amountToReverseAuthorize, PaymentResponseItem responseItem) throws PaymentException {
        throw new PaymentException("reverse authorize not implemented.");
    }

    @Override
    public PaymentResponseItem processAuthorizeAndDebit(PaymentContext paymentContext, Money amountToDebit, PaymentResponseItem responseItem) throws PaymentException {
        responseItem.setTransactionSuccess(true);
        responseItem.setTransactionAmount(amountToDebit);
        return responseItem;
    }

    @Override
    public PaymentResponseItem processDebit(PaymentContext paymentContext, Money amountToDebit, PaymentResponseItem responseItem) throws PaymentException {
        throw new PaymentException("debit not implemented.");
    }

    @Override
    public PaymentResponseItem processCredit(PaymentContext paymentContext, Money amountToCredit, PaymentResponseItem responseItem) throws PaymentException {
        throw new PaymentException("credit not implemented.");
    }

    @Override
    public PaymentResponseItem processVoidPayment(PaymentContext paymentContext, Money amountToVoid, PaymentResponseItem responseItem) throws PaymentException {
        throw new PaymentException("voidPayment not implemented.");
    }

    @Override
    public PaymentResponseItem processBalance(PaymentContext paymentContext, PaymentResponseItem responseItem) throws PaymentException {
        throw new PaymentException("balance not implemented.");
    }

    @Override
    public PaymentResponseItem processPartialPayment(PaymentContext paymentContext, Money amountToDebit, PaymentResponseItem responseItem) throws PaymentException {
        throw new PaymentException("partial payment not implemented.");
    }
	
	@Override
    public Boolean isValidCandidate(PaymentInfoType paymentType) {
        if (SuraCajaSapPaymentInfoType.CAJA_SAP.equals(paymentType)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
