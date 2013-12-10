/*
 * Copyright 2008-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.broadleafcommerce.inventory.basic.domain.ext;

import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.core.catalog.domain.ProductImpl;
import org.broadleafcommerce.core.catalog.domain.SkuImpl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.InheritanceType;


@Entity
@Table(name = "BLC_SURA_BASIC_INVENTORY")
public class BasicInventoryContainerImpl extends SkuImpl implements BasicInventoryContainer {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "BASIC_INVENTORY_QTY_AVAIL")
    @AdminPresentation(friendlyName = "SkuImpl_Sku_BasicInventoryAvailable",
            order = 1010,
            tab = ProductImpl.Presentation.Tab.Name.Inventory,
            tabOrder = ProductImpl.Presentation.Tab.Order.Inventory,
            group = ProductImpl.Presentation.Group.Name.Inventory,
            groupOrder = ProductImpl.Presentation.Group.Order.Inventory,
            prominent = true)
    protected Integer basicQuantityAvailable = 0;

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.basicQuantityAvailable = quantityAvailable;
    }

    public Integer getQuantityAvailable() {
        return this.basicQuantityAvailable;
    }

}
