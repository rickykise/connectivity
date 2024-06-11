package ai.fassto.connectivity.domain.salesorder.application.dto;

import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleInfo {
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Code (코드)", example = "00953CHG-000001")
    private String code;
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Driver Name (운전자명)", example = "홍길동")
    private String driverName;
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Phone Number (전화번호)", example = "010-1234-5678")
    private String phoneNo;
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Plate Number (자동차 번호)", example = "서울러1234")
    private String plateNo;
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Arrange Number (할당번호)", example = "000001")
    private String arrangeNo;
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Weight (차량 중량)", example = "01")
    private String weight;
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Type (차량 종류)", example = "CAR01")
    private String type;

    public void validate() {
        code = StringUtils.trim(code);
        driverName = StringUtils.trim(driverName);
        phoneNo = StringUtils.trim(phoneNo);
        plateNo = StringUtils.trim(plateNo);
        arrangeNo = StringUtils.trim(arrangeNo);
        weight = StringUtils.trim(weight);
        type = StringUtils.trim(type);
    }
}