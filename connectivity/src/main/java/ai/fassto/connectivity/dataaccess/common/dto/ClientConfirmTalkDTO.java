package ai.fassto.connectivity.dataaccess.common.dto;

import ai.fassto.connectivity.domain.common.valueobject.enums.OrderType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ClientConfirmTalkDTO {
    private String whCd;
    private String cstCd;
    private String slipNo;
    private OrderType orderType;
    private List<String> godCdList;
}
