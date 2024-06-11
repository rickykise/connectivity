package ai.fassto.connectivity.domain.vendor.application.dto;

import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class SoloChainVendorRequest {
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Supplier Code (공급사 코드)", example = "08812345")
    private String supCd;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Supplier Name (공급사명)", example = "WE ARE A STARBUCKS VENDOR")
    private String supNm;

    @ApiModelProperty(notes = "ZIP Number (우편번호)", example = "06158")
    private String zipNo;

    @ApiModelProperty(notes = "Address 1 (주소 1)", example = "서울 강남구 삼성로 507 (삼성동, JS타워)")
    private String addr1;

    @ApiModelProperty(notes = "Address 2 (주소 2)", example = "9층")
    private String addr2;

    @ApiModelProperty(notes = "Telephone number (전화번호)", example = "010-1111-2222")
    private String telNo;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "USE Y/N (사용 여부)", example = "Y")
    private String useYn;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Warehouse Code (창고 코드)", example = "DT01")
    private String whCd; //  "DT01",

    public SoloChainVendorRequest validate() {
        supCd = StringUtils.trim(supCd);
        supNm = StringUtils.trim(supNm);
        zipNo = StringUtils.trim(zipNo);
        addr1 = StringUtils.trim(addr1);
        addr2 = StringUtils.trim(addr2);
        telNo = StringUtils.trim(telNo);
        useYn = StringUtils.trim(useYn);
        whCd = StringUtils.trim(whCd);

        return this;
    }
}
