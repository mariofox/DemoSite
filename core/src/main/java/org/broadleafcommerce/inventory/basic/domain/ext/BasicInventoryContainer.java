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

/**
 * This interface and its implementation will get woven with the SkuImpl at runtime.
 * It provides a container for holding basic inventory availability data.
 * @author Kelly Tisdell
 *
 */
public interface BasicInventoryContainer {

    public void setQuantityAvailable(Integer quantityAvailable);

    public Integer getQuantityAvailable();
}
