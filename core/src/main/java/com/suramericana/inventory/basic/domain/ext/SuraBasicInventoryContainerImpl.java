package com.suramericana.inventory.basic.domain.ext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.broadleafcommerce.common.currency.domain.BroadleafCurrency;
import org.broadleafcommerce.common.media.domain.Media;
import org.broadleafcommerce.common.money.Money;
import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.core.catalog.domain.Category;
import org.broadleafcommerce.core.catalog.domain.Dimension;
import org.broadleafcommerce.core.catalog.domain.Product;
import org.broadleafcommerce.core.catalog.domain.ProductImpl;
import org.broadleafcommerce.core.catalog.domain.ProductOptionValue;
import org.broadleafcommerce.core.catalog.domain.Sku;
import org.broadleafcommerce.core.catalog.domain.SkuAttribute;
import org.broadleafcommerce.core.catalog.domain.SkuFee;
import org.broadleafcommerce.core.catalog.domain.SkuImpl;
import org.broadleafcommerce.core.catalog.domain.Weight;
import org.broadleafcommerce.core.inventory.service.type.InventoryType;
import org.broadleafcommerce.core.order.domain.FulfillmentOption;
import org.broadleafcommerce.core.order.service.type.FulfillmentType;
import org.hibernate.annotations.Cache;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.InheritanceType;

//@Entity
////@Inheritance(strategy = InheritanceType.JOINED)
//@Table(name = "BLC_SKU_SURA")
////@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "blStandardElements")
//@AdminPresentationClass(friendlyName = "baseSku")
public class SuraBasicInventoryContainerImpl  extends SkuImpl  {
    
	private static final long serialVersionUID = 187898L;
	
//	@Column(name = "BASIC_INVENTORY_QTY_AVAIL")
//		@AdminPresentation(friendlyName = "SkuImpl_Sku_NameSura", order = ProductImpl.Presentation.FieldOrder.NAME,
//	    group = ProductImpl.Presentation.Group.Name.General, groupOrder = ProductImpl.Presentation.Group.Order.General,
//	    prominent = true, translatable = true)
    protected String basicQuantityAvailable;

}
