package ai.fassto.connectivity.domain.salesorder.application.mapper;

import ai.fassto.connectivity.dataaccess.salesorder.dto.TmsPreCallParcelDTO;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.Box;
import ai.fassto.connectivity.domain.parcel.application.dto.carrier.tms.TmsPreCallParcelRequest;
import ai.fassto.connectivity.domain.salesorder.application.dto.erp.update.UpdateErpSalesOrderRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PreCallWhenAllocatedDataMapper {
    private final ModelMapper modelMapper;

    public TmsPreCallParcelRequest toTmsPreCallParcelRequest(TmsPreCallParcelDTO preCallInfoDto, UpdateErpSalesOrderRequest request) {
        Integer quantity = request.getOrderLines().stream().map(orderLine -> (orderLine.getQuantity() == null) ? 0 : orderLine.getQuantity()).reduce(0, Integer::sum);
        String invoiceNo = StringUtils.isBlank(preCallInfoDto.getInvoiceNo()) ? "" : preCallInfoDto.getInvoiceNo();

        TmsPreCallParcelRequest preCallInfo = modelMapper.map(preCallInfoDto, TmsPreCallParcelRequest.class);
        preCallInfo.setChangingShippingOption("Y");
        preCallInfo.setPickDate(request.getWorkDate());
        preCallInfo.setBoxes(List.of(new Box(invoiceNo, quantity)));
        preCallInfo.setBoxQty(1);

        return preCallInfo;
    }
}
