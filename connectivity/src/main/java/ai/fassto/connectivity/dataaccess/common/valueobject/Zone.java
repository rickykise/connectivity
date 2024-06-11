package ai.fassto.connectivity.dataaccess.common.valueobject;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Zone {

    IN("입고 구역"),
    RT("반품 구역");

    private final String description;
}
