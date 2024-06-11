package ai.fassto.connectivity.domain.common.valueobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class InboundBox {
    private String countInBox; // inBoxInCnt; // ": "40",
    private Money price; // private String inPr; // ": "9000",
    private Size size;
}
