package ai.fassto.connectivity.domain.customer.core.valueobject;

import ai.fassto.connectivity.domain.common.valueobject.EmployeeInCharge;
import ai.fassto.connectivity.domain.common.valueobject.StreetAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ReturnInfo {
    private boolean returnAddressUsing; // rtnAddrUseYn;
    private StreetAddress returnAddress;
    private EmployeeInCharge employee;
}
