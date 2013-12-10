package com.suramericana.inventory.basic.domain.ext;

import java.math.BigDecimal;

public interface SuraBasicInventoryContainer {

    public void setQuantityAvailable(BigDecimal quantityAvailable);

    public BigDecimal getQuantityAvailable();
}
