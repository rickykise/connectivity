package ai.fassto.connectivity.dataaccess.purchaseorder.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GodLotEntity {
    private String whCd;
    private String godCd;
    private String distTermDt;
    private String makeDt;
    private String lotNo;
    private String regGear;
}
