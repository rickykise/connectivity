package ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 입고 유형
 */
@Getter
@RequiredArgsConstructor
public enum InType {

    NORMAL("1","정상"),
    CST_STOCK_TRANSFER("2","고객사재고이관"),
    EXCEPTION("9","예외");

    private final String code;
    private final String name;
}
