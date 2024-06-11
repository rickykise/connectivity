package ai.fassto.connectivity.domain.parcel.application.dto.solochain;

import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class VehicleAllocationInfo {
    private String arrangeNo;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "WMS ID (코드)", example = "")
    private String code;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Warehouse Code (창고 코드)", example = "DT01")
    private String wareHouseCode;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Warehouse Code (차량 종류)", example = "WIN01")
    private String carType;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Warehouse Code (차량 무게)", example = "01")
    private String carWeight;

    private String carNumber;

    private String driverName;

    private String driverPhoneNum;
}
