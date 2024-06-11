package ai.fassto.connectivity.dataaccess.purchaseorder.dto;

import ai.fassto.connectivity.dataaccess.purchaseorder.entity.InEntity;
import java.util.List;
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
public class InBulkInsertDTO {

    private String inDt;
    private String whCd;
    private String slipNo;
    private String cstCd;
    private String supCd;
    private String locNo;
    private String inDiv;
    private String inWay;
    private String carKind;
    private Integer pltCnt;
    private String inOrdSlipNo;
    private Integer inOrdSeq;
    private String regGear;
    private String rtnBoxTp;

    private List<InEntity> inEntityList;

}
