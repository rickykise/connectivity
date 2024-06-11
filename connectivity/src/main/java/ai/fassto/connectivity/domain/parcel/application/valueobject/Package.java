package ai.fassto.connectivity.domain.parcel.application.valueobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record Package(
        String boxID, //  id1284179234712
        String invoiceNumber, // "KNT000002196",
        Integer basicFare, // 0,
        Integer dealFare, // 1000,
        Integer airFare, // 2000,
        Integer shipFare, // 0
        String boxType, // "01",
        Float boxWidth, //  100,
        Float boxHeight, //  20,
        Float boxDepth, //  10,
        Integer boxWeight, //  100
        PrintedDocument printedDocuments // {
) {
}
