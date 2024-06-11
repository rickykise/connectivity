package ai.fassto.connectivity.dataaccess.purchaseorder.dto;

import ai.fassto.connectivity.dataaccess.purchaseorder.entity.ClientConfirmEntity;
import lombok.*;

import java.util.List;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClientConfirmBulkInsertDTO {
    private String whCd;                    //창고코드
    private String slipNo;                  //전표번호
    private String cstCd;                   //고객사코드
    private List<ClientConfirmEntity> clientConfirmEntityList;
}
