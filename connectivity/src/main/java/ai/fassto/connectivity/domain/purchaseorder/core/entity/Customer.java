package ai.fassto.connectivity.domain.purchaseorder.core.entity;

import ai.fassto.connectivity.domain.common.entity.BaseEntity;
import ai.fassto.connectivity.domain.common.valueobject.id.CustomerId;
import lombok.Getter;

@Getter
public class Customer extends BaseEntity<CustomerId> {

    public Customer(CustomerId customerId) {
        setId(customerId);
    }
}
