package ai.fassto.connectivity.dataaccess.salesorder.entity;


import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OutOrdSeperatedGodQtyEntity {
    private String godCd;
    private int mainGodOrdQty;
    private int addGodOrdQty;
    private int promotionGodOrdQty;
}
