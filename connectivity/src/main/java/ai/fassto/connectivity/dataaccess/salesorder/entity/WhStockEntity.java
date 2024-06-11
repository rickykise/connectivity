package ai.fassto.connectivity.dataaccess.salesorder.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WhStockEntity {
    private String whCd;      //창고코드
    private String locNo;     //로케이션번호
    private String luNo;      //LU번호
    private String lotNo;     //LOT번호
    private String godCd;     //상품코드
    private Integer stockQty; //재고수량
    private Integer packQty;  //패킹수량
}
