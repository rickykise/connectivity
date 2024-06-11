package ai.fassto.connectivity.domain.salesorder.core.valueobject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoxId {
    PALLET_DISP("PalletDisp");

    private final String solochainCode;
}
