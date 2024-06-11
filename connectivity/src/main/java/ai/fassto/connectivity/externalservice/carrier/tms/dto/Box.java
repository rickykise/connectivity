package ai.fassto.connectivity.externalservice.carrier.tms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class Box {
    private String boxID; //  id1284179234712
    private String type; //
    private String category; //
    private String invoiceNo; // "invoice1",
    private Integer productQty; // 4,
    private Float boxWidth; //  100,
    private Float boxHeight; //  20,
    private Float boxDepth; //  10,
    private Integer boxWeight; //  100
    private List<Item> items;
}
