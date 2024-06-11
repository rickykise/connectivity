package ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums;

import ai.fassto.connectivity.domain.common.exception.NoSuchEnumElementException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum VehicleType {

    COURIER("01", "courier", "택배"),
    VEHICLE("02", "vehicle", "차량");

    private final String erpCode;
    private final String solochainName;
    private final String description;

    private static final Map<String, VehicleType> typeMapByErpCode =
            Arrays.stream(VehicleType.values())
                    .collect(Collectors.toMap(VehicleType::getErpCode, Function.identity()));

    public static VehicleType findByErpCode(String erpCode) {
        VehicleType type = typeMapByErpCode.get(erpCode);
        if (type == null) {
            throw new NoSuchEnumElementException(VehicleType.class.getSimpleName(), erpCode);
        }
        return type;
    }

    public static boolean isCourierByErpCode(String erpCode) {
        return COURIER.getErpCode().equalsIgnoreCase(erpCode);
    }
}
