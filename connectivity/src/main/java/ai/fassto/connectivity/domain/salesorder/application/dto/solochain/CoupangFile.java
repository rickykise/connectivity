package ai.fassto.connectivity.domain.salesorder.application.dto.solochain;

import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class CoupangFile {
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Slip Number (출고요청번호)", example = "TESTIO221109007")
    private String slipNo; // DT01OO230215000001
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "File Type (파일 종류 - FORM, LABEL)", example = "LABEL")
    private String fileType; // FORM
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Original File Name (원본파일명)", example = "shipment_ManiFest_document(16490978)_2023_02_14\"")
    private String orgFileNm; // shipment_ManiFest_document(16490978)_2023_02_14
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "File Name (파일 명)", example = "513976609392_FORM")
    private String fileNm; // 513976609392_FORM
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "filePath (파일경로)", example = "/2023/02/14/")
    private String filePath; // /2023/02/14/
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "File Extension (파일 확장자)", example = "pdf")
    private String fileExt; // pdf
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "file volume (파일볼륨)", example = "2000")
    private String fileVol; // 2000

    public void validate() {
        slipNo = StringUtils.trim(slipNo); //  DT01OO230215000001",
        fileType = StringUtils.trim(fileType); //  FORM",
        orgFileNm = StringUtils.trim(orgFileNm); //  shipment_ManiFest_document(16490978)_2023_02_14",
        fileNm = StringUtils.trim(fileNm); //  513976609392_FORM",
        filePath = StringUtils.trim(filePath); //  /2023/02/14/",
        fileExt = StringUtils.trim(fileExt); //  pdf",
        fileVol = StringUtils.trim(fileVol); //  2000"
    }
}
