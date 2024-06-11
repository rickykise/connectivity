package ai.fassto.connectivity.externalservice.wms.solochain.workorder.mapper;

import ai.fassto.connectivity.common.utility.DateTimeUtil;
import ai.fassto.connectivity.domain.common.valueobject.enums.ActionType;
import ai.fassto.connectivity.domain.common.valueobject.enums.ItemUnit;
import ai.fassto.connectivity.domain.workorder.core.entity.OrderLine;
import ai.fassto.connectivity.domain.workorder.core.entity.WorkOrder;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.*;
import ai.fassto.connectivity.externalservice.wms.solochain.workorder.dto.WorkOrderWmsSoloChainRequest;
import ai.fassto.connectivity.externalservice.wms.solochain.workorder.valueobject.*;
import ai.fassto.connectivity.externalservice.wms.solochain.workorder.valueobject.Type;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static ai.fassto.connectivity.common.utility.DateTimeUtil.DATE_FORMATTER_yyyy_MM_dd;
import static ai.fassto.connectivity.common.utility.DateTimeUtil.ZERO_PADDED_FMT_HH_MM_SS_SSSS;
import static ai.fassto.connectivity.domain.common.valueobject.enums.ItemUnit.EA;
import static ai.fassto.connectivity.domain.workorder.enums.WorkType.*;
import static ai.fassto.connectivity.externalservice.wms.solochain.common.configuration.SoloChainConstant.*;

@Component
@Slf4j
public class WorkOrderWmsSoloChainMapper {
    public WorkOrderWmsSoloChainRequest workOrderToCreateUpdateWorkOrderWmsSoloChainRequest(WorkOrder workOrder) {
        List<Transaction> transactions = new ArrayList<>();
        List<OrderLine> toOrderLineList = workOrder.getToOrderLineList();
        List<OrderLine> fromOrderLineList = workOrder.getFromOrderLineList();

        if (KITTING.equals(workOrder.getType())) {
            /* kitting 인 경우 N to 1 */
            transactions.add(new Transaction(toWOHA(workOrder, toOrderLineList.get(0))));
            transactions.add(new Transaction(toWOHB(workOrder, fromOrderLineList, toOrderLineList.get(0))));
        } else if (STICKER.equals(workOrder.getType()) || TAG.equals(workOrder.getType()) || IRONING.equals(workOrder.getType())) {
            /* others 인 경우 1 to 1 */
            for (int i = 0; i < toOrderLineList.size(); ++i) {
                transactions.add(new Transaction(toWOHA(workOrder, toOrderLineList.get(i))));
                transactions.add(new Transaction(toWOHB(workOrder, List.of(toOrderLineList.get(i)), toOrderLineList.get(i))));
            }
        } else {
            log.info("[WorkOrderType: {}] - 지원되지 않는 Type 입니다. 오류방지를 위해서 log 만 남김 ", workOrder.getType());
        }

        return new WorkOrderWmsSoloChainRequest(transactions);
    }

    private WOHB toWOHB(WorkOrder workOrder, List<OrderLine> orderLineList, OrderLine toOrderLine) {
        return new WOHB(
                workOrder.getId().getValue(),
                Supplier.builder().accountNo_(workOrder.getWarehouseId().getValue()).build(),
                SOLH.builder()
                        .lifecycle_(INTERNAL)
                        .orderClass_(TRANSFER)
                        .orderNo_(workOrder.getId().getValue())
                        .orderType_(new OrderType("KittingWOTransfer"))
                        .supplier_(Supplier.builder().accountNo_(workOrder.getWarehouseId().getValue()).build())
                        .requester_(new Requester(workOrder.getCustomerId().getValue(), new SiteType(DOMAIN_CUSTOMER)))
                        .requiredDate_(requredDateToDateTimeMilliSecString(workOrder.getRequiredDate()))
                        .internalRequest_(InternalRequest.builder()
                                .referenceNo_(workOrder.getId().getValue())
                                .solh_(new SOLHSimple(INTERNAL, TRANSFER, workOrder.getId().getValue()))
                                .build()
                        )
                        .getSOLIByParent_(
                                new GetSOLIByParent(
                                        Attr.builder().action_(OVERRIDE).parameter_(PARENT).build(),
                                        toSoli(workOrder, orderLineList, toOrderLine)
                                )
                        )
                        .build()
        );
    }

    private List<SOLI> toSoli(WorkOrder workOrder, List<OrderLine> orderLineList, OrderLine toOrderLine) {
        List<SOLI> soliList = new ArrayList<>();

        for (OrderLine orderLine : orderLineList) {
            soliList.add(
                    SOLI.builder()
                            .sequence_(orderLine.getId().getValue())
                            .status_(getStatusBy(workOrder.getActionType()))
                            .materialMaster_(new MaterialMaster(orderLine.getItemId().getValue()))
                            .quantity_(orderLine.getQuantityInfo().orderQuantity())
                            .releasedQty_(orderLine.getQuantityInfo().orderQuantity())
                            .uoi_(UOI.builder().name_(EA.name()).build())
                            .requester_(new Requester(workOrder.getCustomerId().getValue(), new SiteType(DOMAIN_CUSTOMER)))
                            .supplier_(Supplier.builder().accountNo_(workOrder.getWarehouseId().getValue()).build())
                            .owner_(Owner.builder().accountNo_(workOrder.getCustomerId().getValue()).build())
                            .shipTo_(ShipTo.builder().accountNo_(workOrder.getWarehouseId().getValue()).build())
                            .soliDimension_(toSOLIDimension(orderLine))
                            .shipmentRequest_(new ShipmentRequest(
                                    Attr.builder().className_(INTERNAL_REQUEST).index_(BY_REFERENCE_AND_REFERENCE_NO).build(),
                                    workOrder.getId().getValue(),
                                    SOLHSimple.builder().lifecycle_(INTERNAL).orderClass_(TRANSFER).orderNo_(workOrder.getId().getValue()).build()
                            ))
                            .wos_(WOSSimple.builder()
                                    .sequence_(toOrderLine.getId().getValue())
                                    .woh_(WOHSimple.builder()
                                            .referenceNo_(workOrder.getId().getValue())
                                            .supplier_(Supplier.builder().accountNo_(workOrder.getWarehouseId().getValue()).build())
                                            .build()
                                    )
                                    .build()
                            )
                            .build()
            );
        }


        return soliList;
    }

    private WOHA toWOHA(WorkOrder workOrder, OrderLine orderLine) {
        return WOHA.builder()
                .orderClass_(MANUAL)
                .referenceNo_(workOrder.getId().getValue())
                .type_(new Type(workOrder.getType().name()))
                .supplier_(Supplier.builder().accountNo_(workOrder.getWarehouseId().getValue()).build())
                .requester_(new Requester(workOrder.getCustomerId().getValue(), new SiteType(DOMAIN_CUSTOMER)))
                .shipTo_(ShipTo.builder().accountNo_(workOrder.getWarehouseId().getValue()).build())
                .requiredDate_(requredDateToDateTimeMilliSecString(workOrder.getRequiredDate()))
                .materialMaster_(new MaterialMaster(orderLine.getItemId().getValue()))
                .quantity_(orderLine.getQuantityInfo().orderQuantity())
                .status_(getStatusBy(workOrder.getActionType()))
                .uoi_(UOI.builder().name_(EA.name()).build())
                .wos_(toWOS(workOrder, orderLine))
                .build();
    }

    private WOS toWOS(WorkOrder workOrder, OrderLine orderLine) {
        return WOS.builder()
                .sequence_(String.valueOf(orderLine.getId().getValue()))
                .wOH_(new WOHSimple(
                        workOrder.getId().getValue(),
                        Supplier.builder().accountNo_(workOrder.getWarehouseId().getValue()).build()
                ))
                .status_(getStatusBy(workOrder.getActionType()))
                .quantity_(orderLine.getQuantityInfo().orderQuantity())
                .requiredDate_(requredDateToDateTimeMilliSecString(workOrder.getRequiredDate()))
                .materialMaster_(new MaterialMaster(orderLine.getItemId().getValue()))
                .uoi_(UOI.builder().name_(EA.name()).build())
                .orderInstruction_(toOrderInstructions(workOrder.getMemo()))
                .build();
    }

    private List<OrderInstruction> toOrderInstructions(String instruction) {
        if (StringUtils.isBlank(instruction)) {
            return null;
        }

        return List.of(new OrderInstruction("Kitting", instruction));
    }

    private String requredDateToDateTimeMilliSecString(LocalDate requiredDate) {
        return DateTimeUtil.localDateToString(requiredDate, DATE_FORMATTER_yyyy_MM_dd) + ZERO_PADDED_FMT_HH_MM_SS_SSSS;
    }

    private String getStatusBy(ActionType actionType) {
        return actionType.isDelete() ? CANCELLED : ACTIVE;
    }

    private SOLIDimension toSOLIDimension(OrderLine orderLine) {
        if (StringUtils.isBlank(orderLine.getLotNo())) {
            return null;
        }

        return SOLIDimension.builder()
                .dimension_(toDimension(orderLine))
                .quantity_(String.valueOf(orderLine.getQuantityInfo().orderQuantity()))
                .uoi_(toUOI(orderLine))
                .mode_(HARD)
                .build();
    }

    private UOI toUOI(OrderLine orderLine) {
        return UOI.builder()
                .materialMaster_(MaterialMaster.builder().partNo_(orderLine.getItemId().getValue()).build())
                .name_(ItemUnit.EA.name())
                .build();
    }

    private Dimension toDimension(OrderLine orderLine) {
        return Dimension.builder()
                .attr_(Attr.builder().className_(MMLOT_NUMBER).index_(BY_MATERIAL_MASTER_VALUE).build())
                .materialMaster_(MaterialMaster.builder().partNo_(orderLine.getItemId().getValue()).build())
                .value_(orderLine.getLotNo())
                .build();
    }
}
