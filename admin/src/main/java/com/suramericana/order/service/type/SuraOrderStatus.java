package com.suramericana.order.service.type;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.broadleafcommerce.common.BroadleafEnumerationType;

public class SuraOrderStatus implements Serializable, BroadleafEnumerationType {
	
	private static final long serialVersionUID = 1L;

    private static final Map<String, SuraOrderStatus> TYPES = new LinkedHashMap<String, SuraOrderStatus>();

    public static final SuraOrderStatus NAMED = new SuraOrderStatus("NAMED", "Named");
    public static final SuraOrderStatus QUOTE = new SuraOrderStatus("QUOTE", "Quote");
    public static final SuraOrderStatus IN_PROCESS = new SuraOrderStatus("IN_PROCESS", "1. En carrito");
    public static final SuraOrderStatus SUBMITTED = new SuraOrderStatus("SUBMITTED", "2. Enviado");
    public static final SuraOrderStatus INVOICEDSAP = new SuraOrderStatus("INVOICEDSAP", "3. Facturado en SAP");
    public static final SuraOrderStatus PAID = new SuraOrderStatus("PAID", "4. Pagado");
    public static final SuraOrderStatus CANCELLED = new SuraOrderStatus("CANCELLED", "5. Anulado");
    public static final SuraOrderStatus SHIPPED = new SuraOrderStatus("SHIPPED", "7. Despachado");
    public static final SuraOrderStatus DELIVERED = new SuraOrderStatus("DELIVERED", "8. Entregado");
    


    public static SuraOrderStatus getInstance(final String type) {
        return TYPES.get(type);
    }

    private String type;
    private String friendlyType;

    public SuraOrderStatus() {
        //do nothing
    }

    public SuraOrderStatus(final String type, final String friendlyType) {
        this.friendlyType = friendlyType;
        setType(type);
    }

    public String getType() {
        return type;
    }

    public String getFriendlyType() {
        return friendlyType;
    }

    private void setType(final String type) {
        this.type = type;
        if (!TYPES.containsKey(type)) {
            TYPES.put(type, this);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SuraOrderStatus other = (SuraOrderStatus) obj;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

}
