/*
 * Copyright 2008-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.suramericana.controller.checkout;

import org.apache.commons.collections.CollectionUtils;
import org.broadleafcommerce.common.exception.ServiceException;
import org.broadleafcommerce.core.checkout.service.exception.CheckoutException;
import org.broadleafcommerce.core.checkout.service.workflow.CheckoutResponse;
import org.broadleafcommerce.core.order.domain.FulfillmentGroup;
import org.broadleafcommerce.core.order.domain.Order;
import org.broadleafcommerce.core.order.service.exception.RequiredAttributeNotProvidedException;
import org.broadleafcommerce.core.payment.domain.PaymentInfo;
import org.broadleafcommerce.core.payment.domain.Referenced;
import org.broadleafcommerce.core.payment.service.type.PaymentInfoType;
import org.broadleafcommerce.core.payment.service.PaymentInfoService;
import org.broadleafcommerce.core.payment.service.PaymentInfoServiceImpl;
import org.broadleafcommerce.core.pricing.service.exception.PricingException;
import org.broadleafcommerce.core.web.checkout.model.BillingInfoForm;
import org.broadleafcommerce.core.web.checkout.model.OrderInfoForm;
import org.broadleafcommerce.core.web.checkout.model.OrderMultishipOptionForm;
import org.broadleafcommerce.core.web.checkout.model.ShippingInfoForm;
import org.broadleafcommerce.core.web.checkout.validator.BillingInfoFormValidator;
import org.broadleafcommerce.core.web.controller.checkout.BroadleafCheckoutController;
import org.broadleafcommerce.core.web.order.CartState;
import org.broadleafcommerce.inventory.basic.service.BasicInventoryUnavailableException;
import org.broadleafcommerce.profile.core.domain.CustomerAddress;
import org.broadleafcommerce.profile.web.core.CustomerState;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.suramericana.payment.service.cajasap.SuraCajaSapPaymentInfoType;
import com.suramericana.web.validator.SuraBillingInfoFormValidator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/checkout")
public class CheckoutController extends BroadleafCheckoutController {
	
	@Resource(name = "blPaymentInfoService")
    protected PaymentInfoService paymentInfoService;
	
	@Resource(name = "suraBillingInfoFormValidator")
    protected SuraBillingInfoFormValidator suraBillingInfoFormValidator;

    /*
    * The Checkout page for Heat Clinic will have the shipping information pre-populated 
    * with an address if the fulfillment group has an address and fulfillment option 
    * associated with it. It also assumes that there is only one payment info of type 
    * credit card on the order. If so, then the billing address will be pre-populated.
    */
    @RequestMapping("")
    public String checkout(HttpServletRequest request, HttpServletResponse response, Model model,
            @ModelAttribute("orderInfoForm") OrderInfoForm orderInfoForm,
            @ModelAttribute("shippingInfoForm") ShippingInfoForm shippingForm,
            @ModelAttribute("billingInfoForm") BillingInfoForm billingForm, RedirectAttributes redirectAttributes) {
        prepopulateCheckoutForms(CartState.getCart(), orderInfoForm, shippingForm, billingForm);
        return super.checkout(request, response, model, redirectAttributes);
    }
    
    @RequestMapping(value = "/savedetails", method = RequestMethod.POST)
    public String saveGlobalOrderDetails(HttpServletRequest request, Model model, 
            @ModelAttribute("orderInfoForm") OrderInfoForm orderInfoForm, BindingResult result) throws ServiceException {
        return super.saveGlobalOrderDetails(request, model, orderInfoForm, result);
    }
    
    @RequestMapping(value="/singleship", method = RequestMethod.GET)
    public String convertToSingleship(HttpServletRequest request, HttpServletResponse response, Model model) throws PricingException {
        return super.convertToSingleship(request, response, model);
    }

    @RequestMapping(value="/singleship", method = RequestMethod.POST)
    public String saveSingleShip(HttpServletRequest request, HttpServletResponse response, Model model,
            @ModelAttribute("orderInfoForm") OrderInfoForm orderInfoForm,
            @ModelAttribute("billingInfoForm") BillingInfoForm billingForm,
            @ModelAttribute("shippingInfoForm") ShippingInfoForm shippingForm, 
            BindingResult result) throws PricingException, ServiceException {
        prepopulateOrderInfoForm(CartState.getCart(), orderInfoForm);
        return super.saveSingleShip(request, response, model, shippingForm, result);
    }

    @RequestMapping(value = "/multiship", method = RequestMethod.GET)
    public String showMultiship(HttpServletRequest request, HttpServletResponse response, Model model,
            @ModelAttribute("orderMultishipOptionForm") OrderMultishipOptionForm orderMultishipOptionForm, 
            BindingResult result) throws PricingException {
        return super.showMultiship(request, response, model);
    }
    
    @RequestMapping(value = "/multiship", method = RequestMethod.POST)
    public String saveMultiship(HttpServletRequest request, HttpServletResponse response, Model model,
            @ModelAttribute("orderMultishipOptionForm") OrderMultishipOptionForm orderMultishipOptionForm, 
            BindingResult result) throws PricingException, ServiceException {
        return super.saveMultiship(request, response, model, orderMultishipOptionForm, result);
    }
    
    @RequestMapping(value = "/add-address", method = RequestMethod.GET)
    public String showMultishipAddAddress(HttpServletRequest request, HttpServletResponse response, Model model,
            @ModelAttribute("addressForm") ShippingInfoForm addressForm, BindingResult result) {
        return super.showMultishipAddAddress(request, response, model);
    }
    
    @RequestMapping(value = "/add-address", method = RequestMethod.POST)
    public String saveMultishipAddAddress(HttpServletRequest request, HttpServletResponse response, Model model,
            @ModelAttribute("addressForm") ShippingInfoForm addressForm, BindingResult result) throws ServiceException {
        return super.saveMultishipAddAddress(request, response, model, addressForm, result);
    }

    /* Se comenta para implementar pago de caja sura sap
    @RequestMapping(value = "/complete", method = RequestMethod.POST)
    public String completeSecureCreditCardCheckout(HttpServletRequest request, HttpServletResponse response, Model model,
            @ModelAttribute("orderInfoForm") OrderInfoForm orderInfoForm,
            @ModelAttribute("shippingInfoForm") ShippingInfoForm shippingForm,
            @ModelAttribute("billingInfoForm") BillingInfoForm billingForm,
            BindingResult result) throws CheckoutException, PricingException, ServiceException {
        prepopulateCheckoutForms(CartState.getCart(), null, shippingForm, billingForm);
        return super.completeSecureCreditCardCheckout(request, response, model, billingForm, result);
    }*/
    
    @RequestMapping(value = "/complete", method = RequestMethod.POST)
    public String completeCheckout(HttpServletRequest request, HttpServletResponse response, Model model,
            @ModelAttribute("orderInfoForm") OrderInfoForm orderInfoForm,
            @ModelAttribute("shippingInfoForm") ShippingInfoForm shippingForm,
            @ModelAttribute("billingInfoForm") BillingInfoForm billingForm,
            BindingResult result) throws CheckoutException, PricingException, ServiceException {
    	
    	prepopulateCheckoutForms(CartState.getCart(), null, shippingForm, billingForm);
    	Order cart=CartState.getCart();
    	if (cart != null)
    	{
    		
	    	Map<PaymentInfo, Referenced> payments = new HashMap<PaymentInfo, Referenced>();
	    	
	    	orderService.removePaymentsFromOrder(cart, SuraCajaSapPaymentInfoType.CAJA_SAP);
	    	
	    	if (billingForm.isUseShippingAddress()){
                copyShippingAddressToBillingAddress(cart, billingForm);
            }
	    	
	    	suraBillingInfoFormValidator.validate(billingForm, result);
            if (result.hasErrors()) {
                populateModelWithShippingReferenceData(request, model);
                return getCheckoutView();
            }
	    	
	    	String uniqueReferenceNumber=String.valueOf(cart.getId());
	    	
	    	PaymentInfo paymentInfoCajaSap = (PaymentInfo) paymentInfoService.create();
	    	paymentInfoCajaSap.setAddress(billingForm.getAddress());
	    	cart.getPaymentInfos().add(paymentInfoCajaSap);
	    	
	    	paymentInfoCajaSap.setType(SuraCajaSapPaymentInfoType.CAJA_SAP);
	    	paymentInfoCajaSap.setOrder(cart);
	    	paymentInfoCajaSap.setAmount(cart.getTotal());
	    	paymentInfoCajaSap.setReferenceNumber(uniqueReferenceNumber);
	    	
	    	payments.put(paymentInfoCajaSap, paymentInfoCajaSap.createEmptyReferenced());
	    	
	    	cart.getPaymentInfos().add(paymentInfoCajaSap);
	    	
	    	
	    	try {
				CheckoutResponse checkoutResponse = checkoutService.performCheckout(cart,payments);
			} catch (Exception e) {
				if (e.getCause() instanceof BasicInventoryUnavailableException) {
					BasicInventoryUnavailableException inventoryException = (BasicInventoryUnavailableException) e.getCause();
					model.addAttribute("errorInventorySkuId", inventoryException.getSkuId().toString());
					model.addAttribute("errorInventoryAvail", inventoryException.getQuantityAvailable());
					return "redirect:/checkout";
	            }
			}
	    	
	    	
	    	return getConfirmationView(cart.getOrderNumber());
    	}

    	return getCartPageRedirect();
    }
    

    protected void prepopulateOrderInfoForm(Order cart, OrderInfoForm orderInfoForm) {
        if (orderInfoForm != null) {
            orderInfoForm.setEmailAddress(cart.getEmailAddress());
        }
    }
            
    protected void prepopulateCheckoutForms(Order cart, OrderInfoForm orderInfoForm, ShippingInfoForm shippingForm, 
            BillingInfoForm billingForm) {
        List<FulfillmentGroup> groups = cart.getFulfillmentGroups();
        
        prepopulateOrderInfoForm(cart, orderInfoForm);
        
        if (CollectionUtils.isNotEmpty(groups) && groups.get(0).getFulfillmentOption() != null) {
            //if the cart has already has fulfillment information
            shippingForm.setAddress(groups.get(0).getAddress());
            shippingForm.setFulfillmentOption(groups.get(0).getFulfillmentOption());
            shippingForm.setFulfillmentOptionId(groups.get(0).getFulfillmentOption().getId());
        } else {
            //check for a default address for the customer
            CustomerAddress defaultAddress = customerAddressService.findDefaultCustomerAddress(CustomerState.getCustomer().getId());
            if (defaultAddress != null) {
                shippingForm.setAddress(defaultAddress.getAddress());
                shippingForm.setAddressName(defaultAddress.getAddressName());
            }
        }

        if (cart.getPaymentInfos() != null) {
            for (PaymentInfo paymentInfo : cart.getPaymentInfos()) {
                if (PaymentInfoType.CREDIT_CARD.equals(paymentInfo.getType())) {
                    billingForm.setAddress(paymentInfo.getAddress());
                }
            }
        }
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
    }
    
}
