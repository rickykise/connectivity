package ai.fassto.connectivity.domain.parcel.application.dto.carrier.ylp;


import ai.fassto.connectivity.application.common.validation.group.NotEmptyGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class DownData {
    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Drop off area address (하차지역 주소)", example = "서울 용산구 독서당로 39 (한남동, 신성미소시티)")
    private String downAddr; // "서울 용산구 독서당로 39 (한남동, 신성미소시티)",

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Detailed address of Destination area (하차지역 상세 주소)", example = "110호 두발 히어로 김동욱 과장")
    private String downAddrDetail; // "110호 두발 히어로 김동욱 과장",

    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    @ApiModelProperty(required = true, notes = "Delivery date (하차 날짜)", example = "2022-12-30 12:00")
    private LocalDateTime downDate; // "2022-12-30 12:00",

    @NotNull(message = "{ai.fassto.connectivity.constraints.NotEmpty.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Name of drop off location (하차지명)", example = "두발 히어로")
    private String downName; // "두발 히어로",

    @NotBlank(message = "{ai.fassto.connectivity.constraints.NotBlank.message}", groups = {NotEmptyGroup.class})
    @ApiModelProperty(required = true, notes = "Dropf off destination phone number (하차지 전화번호)", example = "010-1111-2222")
    private String downPhone; // "010-1111-2222"
}
