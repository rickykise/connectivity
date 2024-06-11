package ai.fassto.connectivity.externalservice.wms.solochain.common.exception.handler;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.externalservice.common.exception.ExternalApiCallException;
import ai.fassto.connectivity.domain.customer.core.exception.CustomerExternalApiException;
import ai.fassto.connectivity.domain.item.core.exception.ItemExternalApiException;
import ai.fassto.connectivity.domain.parcel.core.exception.VehicleParcelExternalApiException;
import ai.fassto.connectivity.domain.purchaseorder.core.exception.PurchaseOrderExternalApiException;
import ai.fassto.connectivity.domain.salesorder.core.exception.SalesOrderExternalApiException;
import ai.fassto.connectivity.domain.vendor.core.exception.VendorExternalApiException;
import ai.fassto.connectivity.domain.workorder.core.exception.WorkOrderExternalApiException;
import ai.fassto.connectivity.externalservice.wms.solochain.common.valueobject.Message;
import feign.FeignException;
import org.apache.http.HttpStatus;

import java.util.List;

import static ai.fassto.connectivity.application.common.valueobject.ExceptionMessages.*;

public class FeignExceptionHandler {
    public static void externalApiCallExceptionHandler(List<ErrorDetail> errorList) throws ExternalApiCallException {
        throw new ExternalApiCallException(SOLOCHAIN_EXTERNAL_API_CALL_ERROR_MESSAGE, errorList);
    }

    public static void externalApiCallExceptionHandler(FeignException exception) throws ExternalApiCallException {
        if (exception.status() == HttpStatus.SC_UNAUTHORIZED) {
            throw new ExternalApiCallException(SOLOCHAIN_UNAUTHORIZED_ERROR_MESSAGE, exception);
        }

        throw new ExternalApiCallException(SOLOCHAIN_EXTERNAL_API_CALL_ERROR_MESSAGE, exception);
    }

    public static void customerExceptionHandler(List<Message> messages) throws CustomerExternalApiException {
        throw new CustomerExternalApiException(
                CUSTOMER_EXTERNAL_API_CALL_ERROR_MESSAGE,
                getErrorList(messages)
        );
    }

    public static void vendorExceptionHandler(List<Message> messages) throws VendorExternalApiException {
        throw new VendorExternalApiException(
                VENDOR_EXTERNAL_API_CALL_ERROR_MESSAGE,
                getErrorList(messages)
        );
    }

    public static void itemExceptionHandler(List<Message> messages) throws ItemExternalApiException {
        throw new ItemExternalApiException(
                ITEM_EXTERNAL_API_CALL_ERROR_MESSAGE,
                getErrorList(messages)
        );
    }

    public static void PurchaseOrderExceptionHandler(List<Message> messages) throws PurchaseOrderExternalApiException {
        throw new PurchaseOrderExternalApiException(
                PURCHASE_ORDER_EXTERNAL_API_CALL_ERROR_MESSAGE,
                getErrorList(messages)
        );
    }

    public static void SalesOrderExceptionHandler(List<Message> messages) throws SalesOrderExternalApiException {
        throw new SalesOrderExternalApiException(
                SALES_ORDER_EXTERNAL_API_CALL_ERROR_MESSAGE,
                getErrorList(messages)
        );
    }

    public static void WorkOrderExceptionHandler(List<Message> messages) throws WorkOrderExternalApiException {
        throw new WorkOrderExternalApiException(
                WORK_ORDER_EXTERNAL_API_CALL_ERROR_MESSAGE,
                getErrorList(messages)
        );
    }

    public static void VehicleParcelExceptionHandler(List<Message> messages) throws VehicleParcelExternalApiException {
        throw new VehicleParcelExternalApiException(
                PARCEL_EXTERNAL_API_CALL_ERROR_MESSAGE,
                getErrorList(messages)
        );
    }


    public static void exceptionHandler(FeignException exception) throws PurchaseOrderExternalApiException {
        if (exception.status() == HttpStatus.SC_UNAUTHORIZED) {
            throw new PurchaseOrderExternalApiException(SOLOCHAIN_UNAUTHORIZED_ERROR_MESSAGE, exception);
        }

        throw new PurchaseOrderExternalApiException(SOLOCHAIN_EXTERNAL_API_CALL_ERROR_MESSAGE, exception);
    }

    private static List<ErrorDetail> getErrorList(List<Message> messages) {
        return messages.stream().map(message -> new ErrorDetail(message.id(), message.message())).toList();
    }
}
