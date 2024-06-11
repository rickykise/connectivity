package ai.fassto.connectivity.domain.parcel.application.valueobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record PrintedDocument(
        ProShipPrintOutputItem proShipPrintOutputItem
) {
}
