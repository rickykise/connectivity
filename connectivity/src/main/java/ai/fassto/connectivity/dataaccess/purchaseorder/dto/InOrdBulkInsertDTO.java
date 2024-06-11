package ai.fassto.connectivity.dataaccess.purchaseorder.dto;

import ai.fassto.connectivity.dataaccess.purchaseorder.entity.ItemEntity;
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
public class InOrdBulkInsertDTO {

    private String ordDt;
    private String whCd;
    private String slipNo;
    private String cstCd;
    private String supCd;
    private String inDiv;
    private int inPr;
    private String inWay;
    private String parcelComp;
    private String parcelInvoiceNo;
    private String rtnGathYn;
    private String wrkStat;
    private String ordEndDiv;
    private String emgrYn;
    private String remark;
    private String preArv;
    private String releaseDt;
    private String releaseGbn;

    private List<ItemEntity> itemList;

}
