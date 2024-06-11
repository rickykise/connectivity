package ai.fassto.connectivity.dataaccess.item.valueobject;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoodsVolume {
    private String godCd;
    private Float godWidth;
    private Float godLength;
    private Float godHeight;
    private Integer godWeight;
    private Float godBulk;
    private Float godSideSum;
}
