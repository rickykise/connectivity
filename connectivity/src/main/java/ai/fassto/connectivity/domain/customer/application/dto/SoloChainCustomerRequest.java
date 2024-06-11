package ai.fassto.connectivity.domain.customer.application.dto;

import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import ai.fassto.connectivity.application.common.validation.group.PatternCheckGroup;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@Getter
@Setter
@NoArgsConstructor
public class SoloChainCustomerRequest {

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Customer code (고객사 코드)", example = "12345")
    private String cstCd;


    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Customer name (고객사명)", example = "STARBUCKS")
    private String cstNm;


    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @Pattern(regexp = "^[YNyn]$", message = "{ai.fassto.connectivity.constraints.Pattern.yn.message}", groups = PatternCheckGroup.class)
    @ApiModelProperty(required = true, notes = "USE Y/N (사용 여부)", example = "Y")
    private String useYn;

    @ApiModelProperty(notes = "ZIP Number (우편번호)", example = "06158")
    private String zipNo;

    @ApiModelProperty(notes = "Address 1 (주소 1)", example = "서울 강남구 삼성로 507 (삼성동, JS타워)")
    private String addr1;

    @ApiModelProperty(notes = "Address 2 (주소 2)", example = "9층")
    private String addr2;

    @ApiModelProperty(notes = "Telephone number (전화번호)", example = "010-1111-2222")
    private String telNo;

    @ApiModelProperty(notes = "Master user name (마스터 유저명)", example = "KEVIN")
    private String masterUserNm;

    @ApiModelProperty(notes = "Master position (마스터 직급)", example = "Manager")
    private String masterPosition;
    private String empNm1;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Warehouse Code (창고 코드)", example = "DT01")
    private String whCd; //  "DT01",


    public SoloChainCustomerRequest validate() {
        cstCd = StringUtils.trim(cstCd);
        cstNm = StringUtils.trim(cstNm);
        zipNo = StringUtils.trim(zipNo);
        addr1 = StringUtils.trim(addr1);
        addr2 = StringUtils.trim(addr2);
        telNo = StringUtils.trim(telNo);
        masterUserNm = StringUtils.trim(masterUserNm);
        masterPosition = StringUtils.trim(masterPosition);
        empNm1 = StringUtils.trim(empNm1);
        whCd = StringUtils.trim(whCd);

        return this;
    }
}
