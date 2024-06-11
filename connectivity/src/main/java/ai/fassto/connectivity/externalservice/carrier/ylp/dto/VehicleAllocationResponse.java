package ai.fassto.connectivity.externalservice.carrier.ylp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleAllocationResponse {
    private String message;
    private String result;
    private Integer status;
    private String code;     //wmsId
}
