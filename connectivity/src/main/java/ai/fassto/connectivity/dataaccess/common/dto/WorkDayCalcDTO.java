package ai.fassto.connectivity.dataaccess.common.dto;

import lombok.Builder;

@Builder
public class WorkDayCalcDTO {
    private String pDate;
    private String pSign;
    private Integer pDays;
}
