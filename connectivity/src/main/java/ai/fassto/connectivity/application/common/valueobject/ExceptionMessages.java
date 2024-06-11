package ai.fassto.connectivity.application.common.valueobject;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum ExceptionMessages {
    INTERNAL_SERVER_ERROR_MESSAGE(INTERNAL_SERVER_ERROR, "Internal server error.", "에러가 발생했습니다. 이 증상이 계속될 경우 담당자에게 연락해주세요.", OK),
    PARAMETER_ERROR_MESSAGE(BAD_REQUEST, "Invalid parameter. Please check again.", "잘못된 요청 파라미터가 존재합니다. 다시 확인하고 요청해 주세요.", OK),
    DUPLICATE_KEY_ERROR_MESSAGE(BAD_REQUEST, "This task has already been processed. Please check again. ", "이미 처리가 완료된 작업입니다. 다시 확인하고 요청해 주세요.", OK),
    NO_SUCH_ENUM_ELEMENT_ERROR_MESSAGE(BAD_REQUEST, "ENUM type is not supported. Please check again. ", "지원되지 않는 ENUM 타입입니다. 다시 확인하고 요청해 주세요.", OK),
    NOT_BLANK_ERROR_MESSAGE(BAD_REQUEST, "Required value should not be blank ", "필수 입력값입니다.", OK),
    NOT_NULL_ERROR_MESSAGE(BAD_REQUEST, "Required value should not be empty ", "필수 입력값입니다.", OK),
    POSITIVE_ERROR_MESSAGE(BAD_REQUEST, "Required value must be greater than 0 ", "0보다 커야 합니다", OK),
    HTTP_METHOD_NOT_SUPPORTED(METHOD_NOT_ALLOWED, "Request method not allowed. ", "허용되지 않은 요청 메소드", OK),
    SIZE_LIMIT_ERROR_MESSAGE(BAD_REQUEST, "Invalid value size", "값의 크기가 유효하지 않습니다.", OK),

    /** Solochain Authorization */
    SOLOCHAIN_UNAUTHORIZED_ERROR_MESSAGE(UNAUTHORIZED, "[SOLOCHAIN] User not authenticated", "[SOLOCHAIN] User not authenticated", OK),
    /** External API Call */
    SOLOCHAIN_EXTERNAL_API_CALL_ERROR_MESSAGE(INTERNAL_SERVER_ERROR, "[SOLOCHAIN] Error with API", "[SOLOCHAIN] API 연동 오류", OK),
    CUSTOMER_EXTERNAL_API_CALL_ERROR_MESSAGE(BAD_REQUEST, "[Customer] Invalid parameter. Please check again.", "[고객사] 잘못된 요청 파라미터가 존재합니다. 다시 확인하고 요청해 주세요.", OK),
    VENDOR_EXTERNAL_API_CALL_ERROR_MESSAGE(BAD_REQUEST, "[Vendor] Invalid parameter. Please check again.", "[공급사] 잘못된 요청 파라미터가 존재합니다. 다시 확인하고 요청해 주세요.", OK),
    ITEM_EXTERNAL_API_CALL_ERROR_MESSAGE(BAD_REQUEST, "[Item] Invalid parameter. Please check again.", "[상품] 잘못된 요청 파라미터가 존재합니다. 다시 확인하고 요청해 주세요.", OK),
    PURCHASE_ORDER_EXTERNAL_API_CALL_ERROR_MESSAGE(BAD_REQUEST, "[Purchase Order] Invalid parameter. Please check again.", "[입고/입고 반품] 잘못된 요청 파라미터가 존재합니다. 다시 확인하고 요청해 주세요.", OK),
    SALES_ORDER_EXTERNAL_API_CALL_ERROR_MESSAGE(BAD_REQUEST, "[Sales Order] Invalid parameter. Please check again.", "[출고] 잘못된 요청 파라미터가 존재합니다. 다시 확인하고 요청해 주세요.", OK),
    WORK_ORDER_EXTERNAL_API_CALL_ERROR_MESSAGE(BAD_REQUEST, "[Work Order] Invalid parameter. Please check again.", "[유통가공] 잘못된 요청 파라미터가 존재합니다. 다시 확인하고 요청해 주세요.", OK),

    PARCEL_UNAUTHORIZED_ERROR_MESSAGE(UNAUTHORIZED, "[PARCEL] User not authenticated", "[PARCEL] User not authenticated", OK),
    PARCEL_EXTERNAL_API_CALL_ERROR_MESSAGE(INTERNAL_SERVER_ERROR, "[PARCEL] Error with API", "[PARCEL] API 연동 오류", OK),
    CARRIER_TMS_EXTERNAL_API_CALL_ERROR_MESSAGE(BAD_REQUEST, "[CARRIER_TMS] Invalid parameter. Please check again.", "[CARRIER_TMS] 잘못된 요청 파라미터가 존재합니다. 다시 확인하고 요청해 주세요.", OK),
    CARRIER_YLP_EXTERNAL_API_CALL_ERROR_MESSAGE(BAD_REQUEST, "[CARRIER_YLP] Invalid parameter. Please check again.", "[CARRIER_YLP] 잘못된 요청 파라미터가 존재합니다. 다시 확인하고 요청해 주세요.", OK),
    CARRIER_YLP_FAILED_ERROR_MESSAGE(BAD_REQUEST, "[CARRIER_YLP] Vehicle Allocation YLP failed", "[CARRIER_YLP] YLP 차량 배차 신청을 실패하였습니다.", OK),
    OMS_EXTERNAL_API_CALL_ERROR_MESSAGE(INTERNAL_SERVER_ERROR, "[OMS] Error with API", "[OMS] API 연동 오류", OK),

    /** ERP */
    ITEM_NOT_FOUND_ERROR_MESSAGE(BAD_REQUEST, "[Item] Item not found. Please check again.", "[상품] 상품을 찾을 수 없습니다. 다시 확인하고 요청해 주세요.", OK),
    ITEM_NOT_USED_ERROR_MESSAGE(BAD_REQUEST, "[Item] Item can not used. Please check again.", "[상품] 사용 할 수 없는 상품입니다. 다시 확인하고 요청해 주세요.", OK),

    PURCHASE_ORDER_REQUEST_NOT_FOUND_ERROR_MESSAGE(BAD_REQUEST, "[PurchaseOrder] PurchaseOrderRequest not found. Please check again.", "[입고] 입고 요청건을 찾을 수 없습니다. 다시 확인하고 요청해 주세요.", OK),
    PURCHASE_ORDER_ITEM_NOT_FOUND_ERROR_MESSAGE(BAD_REQUEST, "[PurchaseOrder] Item not found. Please check again.", "[입고] 대상 아이템을 찾을 수 없습니다. 다시 확인하고 요청해 주세요.", OK),
    PURCHASE_ORDER_ITEM_DISABLED_ERROR_MESSAGE(BAD_REQUEST, "[PurchaseOrder] Item is not available. Please check again.", "[입고] 대상 아이템은 사용가능하지 않습니다. 다시 확인하고 요청해 주세요.", OK),
    PURCHASE_ORDER_NOT_SUPPORT_ERROR_MESSAGE(BAD_REQUEST, "[PurchaseOrder] PurchaseOrderStatus not supported. Please check again.", "[입고] 지원되지 않는 입고상태 입니다. 다시 확인하고 요청해 주세요.", OK),
    PURCHASE_ORDER_NOT_CENTER_ARRIVAL_ERROR_MESSAGE(BAD_REQUEST, "[PurchaseOrder] PurchaseOrder centerArrival info not found. Please check again.", "[입고] 센터도착 정보를 찾을 수 없습니다. 센터도착을 먼저 진행해주세요.", OK),

    SALES_ORDER_REQUEST_NOT_FOUND_ERROR_MESSAGE(BAD_REQUEST, "[SalesOrder] SalesOrder is not found. Please check again.", "[출고] 출고 요청건을 찾을 수 없습니다. 다시 확인하고 요청해 주세요.", OK),
    SALES_ORDER_PICK_MAP_REQUEST_NOT_FOUND_ERROR_MESSAGE(BAD_REQUEST, "[SalesOrder] SalesOrderPickMap is not found. Please check again.", "[출고] 출고 지시 확정건을 찾을 수 없습니다. 다시 확인하고 요청해 주세요.", OK),
    SALES_ORDER_ALLOCATE_CANCELLED_ERROR_MESSAGE(BAD_REQUEST, "[SalesOrder] If it's not the allocated stage, can't not cancelled. Please check again.", "[출고] 출고지시확정 단계가 아니면 취소할 수 없습니다. 다시 확인하고 요청해 주세요.", OK),
    SALES_ORDER_NOT_SUPPORT_ERROR_MESSAGE(BAD_REQUEST, "[SalesOrder] SalesOrder not supported. Please check again.", "[출고] 지원되지 않는 출고상태 입니다. 다시 확인하고 요청해 주세요.", OK),
    SALES_ORDER_ITEM_NOT_FOUND_ERROR_MESSAGE(BAD_REQUEST, "[SalesOrder] Item not found. Please check again.", "[출고] 대상 아이템을 찾을 수 없습니다. 다시 확인하고 요청해 주세요.", OK),
    SALES_ORDER_ITEM_DISABLED_ERROR_MESSAGE(BAD_REQUEST, "[SalesOrder] Item is not available. Please check again.", "[출고] 대상 아이템은 사용가능하지 않습니다. 다시 확인하고 요청해 주세요.", OK),
    SALES_ORDER_LOT_MIS_MATCH_ERROR_MESSAGE(BAD_REQUEST, "[SalesOrder] Lot Number is mis match. Please check again.", "[출고] lot 정보가 일치하지 않습니다. 다시 확인하고 요청해 주세요.", OK),
    SALES_ORDER_WAREHOUSE_CONSUMABLE_NOT_FOUND_ERROR_MESSAGE(BAD_REQUEST, "[SalesOrder] warehouse consumable is not found. Please check again.", "[출고] 창고 부자재 정보를 찾을 수 없습니다. 다시 확인하고 요청해 주세요.", OK),


    WORK_ORDER_RESPONSE_NOT_FOUND_ERROR_MESSAGE(BAD_REQUEST, "[WorkOrder] WorkOrderResponse is not found. Please check again.", "[유통가공] 유통가공 작업 결과를 찾을 수 없습니다. 다시 확인하고 요청해 주세요.", OK),
    WORK_ORDER_NOT_SUPPORT_ERROR_MESSAGE(BAD_REQUEST, "[WorkOrder] WorkOrderStatus not supported. Please check again.", "[유통가공] 지원되지 않는 유통가공 작업상태 입니다. 다시 확인하고 요청해 주세요.", OK),
    WORK_ORDER_REQUEST_NOT_FOUND_ERROR_MESSAGE(BAD_REQUEST, "[WorkOrder] WorkOrderRequest is not found. Please check again.", "[유통가공] 유통가공 요청건을 찾을 수 않습니다. 다시 확인하고 요청해 주세요.", OK),
    SCHEDULER_NOT_FOUND_ERROR_MESSAGE(NOT_FOUND, "[Scheduler] Scheduler not found. Please check again.", "[스케줄] 스케줄을 찾을 수 없습니다. 다시 확인하고 요청해 주세요.", OK),
    SCHEDULER_STATUS_NOT_REGISTERD_ERROR_MESSAGE(BAD_REQUEST, "[Scheduler] Scheduler status not REGISTERD. Please check again.", "[스케줄] 삭제할 스케줄이 등록상태가 아니라 취소할 수 없습니다.다시 확인하고 요청해 주세요. ", OK),
    SCHEDULER_UNABLE_REGISTER_ERROR_MESSAGE(BAD_REQUEST, "[Scheduler] Scheduler status is REGISTERD. Please check again.", "[스케줄] 등록 후 실행되지 않은 작업이 존재합니다. 삭제 후 진행해 주세요\n ", OK);


    private final HttpStatus originalStatus;
    private final String message;
    private final String messageKo;
    private final HttpStatus returnStatusForOnlyKorean;

    ExceptionMessages(HttpStatus originalStatus, String message, String messageKo, HttpStatus returnStatusForOnlyKorean) {
        this.originalStatus = originalStatus;
        this.message = message;
        this.messageKo = messageKo;
        this.returnStatusForOnlyKorean = returnStatusForOnlyKorean;
    }
}
