package ai.fassto.connectivity.dataaccess.salesorder.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WhSubsidiaryEntity {
    private int whIdx;        //일련번호
    private String subNo;     //부자재 번호
    private String whCd;      //창고번호
    private String subNm;     //부자재명
    private String subDiv;    //부자재 구분 (STD49)
    private String subSizeCd; //부자재 사이즈 코드 (STD50)
    private int subSizeX;     //가로사이즈
    private int subSizeY;     //세로사이즈
    private int subSizeZ;     //높이
    private String subUseYn;  //사용여부
}
