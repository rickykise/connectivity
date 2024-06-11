package ai.fassto.connectivity.domain.customer.application.mapper;

import ai.fassto.connectivity.common.configuration.EnvironmentComponent;
import ai.fassto.connectivity.domain.common.valueobject.BusinessRegistration;
import ai.fassto.connectivity.domain.common.valueobject.EmployeeInCharge;
import ai.fassto.connectivity.domain.common.valueobject.enums.ActionType;
import ai.fassto.connectivity.domain.common.valueobject.enums.EStatus;
import ai.fassto.connectivity.domain.common.valueobject.id.CustomerId;
import ai.fassto.connectivity.domain.common.valueobject.id.WarehouseId;
import ai.fassto.connectivity.domain.customer.application.dto.SoloChainCustomerRequest;
import ai.fassto.connectivity.domain.customer.application.dto.SoloChainCustomerResponse;
import ai.fassto.connectivity.domain.customer.core.entity.Customer;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import static ai.fassto.connectivity.domain.common.valueobject.enums.EBooleanType.isY;
import static ai.fassto.connectivity.domain.common.valueobject.enums.EEmployeeType.MASTER;

@Component
@RequiredArgsConstructor
public class CustomerDataMapper {
    private final EnvironmentComponent environmentComponent;

    public Customer soloChainCustomerRequestToCustomer(SoloChainCustomerRequest request, ActionType actionType) {
        return Customer.Builder.builder()
                .customerId(new CustomerId(request.getCstCd()))
                .name(request.getCstNm())
                .businessRegistration(toBusinessRegistration(request))
                .employeeMap(toEmployeeInChargeMap(request))
                .status(isY(request.getUseYn()) ? EStatus.ACTIVE : EStatus.INACTIVE)
                .actionType(actionType)
                .warehouseId(new WarehouseId(request.getWhCd()))
                .build();
    }

    public SoloChainCustomerResponse customerToSoloChainCustomerResponse(Customer customer) {
        return new SoloChainCustomerResponse(
                customer.getId().getValue(),
                customer.getName()
        );
    }

    private BusinessRegistration toBusinessRegistration(SoloChainCustomerRequest request) {
        return BusinessRegistration.builder()
                .companyName(request.getCstNm())
                .zipCode(request.getZipNo())
                .address1(request.getAddr1())
                .address2(request.getAddr2())
                .phoneNo(request.getTelNo())
                .build();
    }

    private HashMap<Integer, EmployeeInCharge> toEmployeeInChargeMap(SoloChainCustomerRequest request) {
        HashMap<Integer, EmployeeInCharge> employees = new HashMap<>();


        if (environmentComponent.isProd()) {
            employees.put(MASTER.getNo(), new EmployeeInCharge(request.getMasterUserNm(), request.getMasterPosition(), null, null));
        } else {
            if (StringUtils.isNotBlank(request.getMasterUserNm())) {
                employees.put(MASTER.getNo(), new EmployeeInCharge(request.getMasterUserNm(), request.getMasterPosition(), null, null));
            } else {
                employees.put(MASTER.getNo(), new EmployeeInCharge(request.getEmpNm1(), null, null, null));
            }
        }

        return employees;
    }
}
