package ai.fassto.connectivity.externalservice.carrier.tms.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class Package {
    private String boxID;  //  id1284179234712
    private String invoiceNumber;  // "KNT000002196",
    private Integer basicFare;  // 0,
    private Integer dealFare;  // 1000,
    private Integer airFare;  // 2000,
    private Integer shipFare;  // 0
    private String boxType;  // "01",
    private Float boxWidth;  //  100,
    private Float boxHeight;  //  20,
    private Float boxDepth;  //  10,
    private Integer boxWeight;  //  100
    private PrintedDocument printDocument;
}
