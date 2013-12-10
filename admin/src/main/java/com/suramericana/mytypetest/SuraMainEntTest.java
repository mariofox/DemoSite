package com.suramericana.mytypetest;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.common.presentation.client.SupportedFieldType;
import org.broadleafcommerce.core.catalog.domain.ProductImpl;


@Entity
@Table(name = "BLC_SURA_TEST4")
@AdminPresentationClass(friendlyName = "SuraAdmPresentTest")
public class SuraMainEntTest implements Serializable {

    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "MY_TYPE")
    @AdminPresentation(friendlyName="My Type")
    protected String customType;
	
	@Column(name = "SALE_PRICE_SURA", precision = 19, scale = 5)
    @AdminPresentation(friendlyName = "SkuImpl_Sku_Sale_Price_Sura", order = 2000, 
        group = ProductImpl.Presentation.Group.Name.Price, groupOrder = ProductImpl.Presentation.Group.Order.Price,
        prominent = true, gridOrder = 9, 
        fieldType = SupportedFieldType.MONEY)
    protected BigDecimal salePriceSura;
	
	@Column(name = "AVAILABLE_FLAG")
    @AdminPresentation(friendlyName = "SkuImpl_Sku_Available_Sura", order = 2200,
        tab = ProductImpl.Presentation.Tab.Name.Inventory, tabOrder = ProductImpl.Presentation.Tab.Order.Inventory,
        group = ProductImpl.Presentation.Group.Name.Inventory, groupOrder = ProductImpl.Presentation.Group.Order.Inventory)
    protected Character availableSura;

}
