package com.suramericana.web.processor;

import java.text.NumberFormat;

import org.broadleafcommerce.common.money.Money;
import org.springframework.stereotype.Service;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.attr.AbstractTextChildModifierAttrProcessor;
import org.thymeleaf.standard.expression.StandardExpressionProcessor;

@Service("blPriceTextDisplayProcessor")
public class SuraPriceTextDisplayProcessor extends
		AbstractTextChildModifierAttrProcessor {
	
	public SuraPriceTextDisplayProcessor(){
		super("price");
	}

	@Override
	protected String getText(Arguments arguments, Element element, String attributeName) {
		
		Money price;
        
        try {
            price = (Money) StandardExpressionProcessor.processExpression(arguments, element.getAttributeValue(attributeName));
        } catch (ClassCastException e) {
            Number value = (Number) StandardExpressionProcessor.processExpression(arguments, element.getAttributeValue(attributeName));
            price = new Money(value.doubleValue());
        }

        if (price == null) {
            return "Not Available";
        }
        
        NumberFormat nf = NumberFormat.getInstance();  
        
        nf.setMaximumFractionDigits(0);
        
        return "$ " + nf.format(price.getAmount());

	}

	@Override
	public int getPrecedence() {
		return 1500;
	}

}
