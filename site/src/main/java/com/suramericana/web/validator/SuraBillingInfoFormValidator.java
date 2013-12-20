package com.suramericana.web.validator;


import org.broadleafcommerce.core.web.checkout.model.BillingInfoForm;
import org.broadleafcommerce.core.web.checkout.validator.BillingInfoFormValidator;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Service("blBillingInfoFormValidator")
public class SuraBillingInfoFormValidator extends BillingInfoFormValidator{

	@SuppressWarnings("rawtypes")
    public boolean supports(Class clazz) {
        return clazz.equals(BillingInfoForm.class);
    }

	@Override
    public void validate(Object obj, Errors errors) {
        BillingInfoForm billingInfoForm = (BillingInfoForm) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.firstName", "firstName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.lastName", "lastName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.addressLine1", "addressLine1.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.city", "city.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.postalCode", "postalCode.required");

        if (billingInfoForm.getAddress().getCountry() == null) {
            errors.rejectValue("address.country", "country.required", null, null);
        }
    }

}
