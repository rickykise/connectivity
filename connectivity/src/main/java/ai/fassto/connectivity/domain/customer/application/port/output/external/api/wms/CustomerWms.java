package ai.fassto.connectivity.domain.customer.application.port.output.external.api.wms;

import ai.fassto.connectivity.domain.customer.core.entity.Customer;

public interface CustomerWms {
    Customer create(Customer customer);

    Customer update(Customer customer);
}
