package ai.fassto.connectivity.domain.item.application.dto.solochain;

import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import ai.fassto.connectivity.application.common.validation.group.PatternCheckGroup;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class RequestItem {
    // 사용하는 데이터
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Goods code (상품 코드)", example = "12345TEST001")
    private String godCd;

    @ApiModelProperty(notes = "Goods code (상품명)", example = "STARBUCKS COFFEE - DARK ROAST")
    private String godNm; // 상품명

    @ApiModelProperty(notes = "Goods barcode (상품 바코드)")
    private String godBarcd; // 상품 바코드

    @ApiModelProperty(notes = "Distribution Term management Y/N (유통기간 관리 여부)", example = "Y")
    @Pattern(regexp = "^[YNyn]$", message = "{ai.fassto.connectivity.constraints.Pattern.yn.message}", groups = PatternCheckGroup.class)
    private String distTermMgtYn; // 유통기간 관리 여부

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @Pattern(regexp = "^[YNyn]$", message = "{ai.fassto.connectivity.constraints.Pattern.yn.message}", groups = PatternCheckGroup.class)
    @ApiModelProperty(required = true, notes = "USE Y/N (사용 여부)", example = "Y")
    private String useYn; // "Y",

    @ApiModelProperty(notes = "OUT STOP DAY (반출금지 - 유통기간 몇일 전 부터 출금 금지 해야 하는지)", example = "5")
    private String outStopDay; //"5" - 반출금지 - 유통기간 몇일 전 부터 출금 금지 해야 하는지

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Customer Code (고객사 코드)", example = "12345")
    private String cstCd; // ": "04233",

    @ApiModelProperty(notes = "Vendor Code (공급사 코드)", example = "08812345")
    private String supCd; // ": "04233001",

    @ApiModelProperty(notes = "Category Code For Depth 1 (카테고리 코드)", example = "1001")
    private String categoryCode1;

    @ApiModelProperty(notes = "Category Name For Depth 1 (카테고리 명)", example = "가구/인테리어")
    private String categoryName1;

    @ApiModelProperty(notes = "Category Code For Depth 2 (카테고리 코드)", example = "10012004")
    private String categoryCode2;

    @ApiModelProperty(notes = "Category Name For Depth 2 (카테고리 명)", example = "인테리어소품")
    private String categoryName2;

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Dealing Temperature (취급온도 - 01,02,03)", example = "02")
    private String dealTemp;
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Dealing Temperature Name (취급온도 - 냉장,냉동,상온)", example = "냉동")
    private String dealTempName;
    @ApiModelProperty(notes = "Minimum Store Temperature (최저보관온도 - 취급온도 냉동, 냉장인 경우 필수 값)", example = "-40")
    private String minStrTemp;
    @ApiModelProperty(notes = "Maximum Store Temperature (최고보관온도 - 취급온도 냉동, 냉장인 경우 필수 값)", example = "-10")
    private String maxStrTemp;
    @ApiModelProperty(notes = "Completed Box ((Shippable:사용함, null:사용하지않음))", example = "Shippable")
    private String completedBox;


    @ApiModelProperty(notes = "Gift Division (상품 구분 - 01:본품, 02:사은품, 03:부자재)", example = "03")
    private String giftDiv; // ": "01",
    @ApiModelProperty(notes = "Gift Division Name (상품 구분 명 - original product, free gift, subsidiary materials)", example = "subsidiary materials")
    private String giftDivName; // ": "01",
    @ApiModelProperty(notes = "Sub Mate (부자재 종류 - 01:홍보물,03:쇼핑백,04:포장지,05:고객사테이프,06:케이스,07:단상자,08:세트제작용 부자재,09:출고용 패킹박스,10:습자지,11:고객사인박스)", example = "10")
    private String subMate;
    @ApiModelProperty(notes = "Sub Mate Name (부자재 종류 명 - promotional material, shopping bag, wrapping papaer, customer tape, case, single box, subsidiary material for set production, packing box for shipment, wet paper, customer sign box)", example = "wet paper")
    private String subMateName;
    @ApiModelProperty(notes = "Buffer Goods Y/N (완충재포장 - N:필요없음,Y:기본완충재(무료),A:추가완충재(유료))", example = "N")
    private String bufGodYn; // ": "Y",
    @ApiModelProperty(notes = "Buffer Goods Name (완충재포장명 - 필요없음,기본완충재(무료),추가완충재(유료))", example = "필요없음")
    private String bufGodName; //
    @ApiModelProperty(notes = "makeDtYn (제조일자 년월일 관리 여부 - N: 관리안함, Y: 관리함)", example = "N")
    private String makeDtMgtYn; //

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Warehouse Code (창고 코드)", example = "DT01")
    private String whCd; //  "DT01",

    public void valid() {
        godCd = StringUtils.trim(godCd);
        godNm = StringUtils.trim(godNm);
        godBarcd = StringUtils.trim(godBarcd);
        distTermMgtYn = StringUtils.trim(distTermMgtYn);
        useYn = StringUtils.trim(useYn);
        outStopDay = StringUtils.trim(outStopDay);
        cstCd = StringUtils.trim(cstCd);
        supCd = StringUtils.trim(supCd);
        categoryCode1 = StringUtils.trim(categoryCode1);
        categoryName1 = StringUtils.trim(categoryName1);
        categoryCode2 = StringUtils.trim(categoryCode2);
        categoryName2 = StringUtils.trim(categoryName2);
        dealTemp = StringUtils.trim(dealTemp);
        dealTempName = StringUtils.trim(dealTempName);
        minStrTemp = StringUtils.trim(minStrTemp);
        maxStrTemp = StringUtils.trim(maxStrTemp);
        completedBox = StringUtils.trim(completedBox);
        giftDiv = StringUtils.trim(giftDiv);
        giftDivName = StringUtils.trim(giftDiv);
        subMate = StringUtils.trim(subMate);
        subMateName = StringUtils.trim(subMate);
        bufGodYn = StringUtils.trim(bufGodYn);
        bufGodName = StringUtils.trim(bufGodName);
        makeDtMgtYn = StringUtils.trim(makeDtMgtYn);
        whCd = StringUtils.trim(whCd);

        // 2023-03-29: make godCd to upper case (ERP 에서는 DB 저장시 대문자로 저장시킴)
        // 입력창에서 소문자를 받을 수 있기 때문에 대문자로 변환 시켜야 함
        godCd = godCd.toUpperCase();
    }
}
