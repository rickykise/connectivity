package ai.fassto.connectivity.dataaccess.item.entity;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GodEntity {
    private String godCd;           //상품코드
    private String godNm;           //상품명
    private String trimGodNm;       //상품명(공백제거)
    private String trimGodEnNm;     //상품영문명
    private String cstGodCd;        //고객사상품코드
    private String godOptCd1;       //상품옵션코드1
    private String godOptCd2;       //상품옵션코드2
    private String cstCd;           //고객사코드
    private String godType;         //1:단품상품, 2:모음상품, 3:묶음상품
    private String supCd;           //공급사코드
    private String cateCd;          //카테고리코드
    private String seasonCd;        //계절상품구분(STD18)
    private String genderCd;        //성별상품구분(STD19)
    private String makeYr;          //연식(YY)
    private Integer godPr;          //단가
    private Integer inPr;           //입고가
    private Integer salPr;          //판매가
    private String dealTemp;        //취급온도(STD10)
    private String pickFac;         //피킹설비(STD04)',
    private String giftDiv;         //사은품구분(STD05)',
    private Float godWidth;
    private Float godLength;
    private Float godHeight;
    private Float godBulk;          //상품체적
    private Integer godWeight;      //상품무게
    private Float godSideSum;       //상품규격(3면의합)
    private String godVolume;       //상품용량
    private String godBarcd;        //상품바코드
    private Integer boxWidth;       //내품BOX가로길이
    private Integer boxLength;      //내품BOX세로길이
    private Integer boxHeight;      //내품BOX높이길이
    private String loadingDirection;    //적재방향 (NONE, UP)
    private Integer boxBulk;        //내품BOX체적
    private Integer boxWeight;      //내품BOX무게
    private String boxBarcd;        //내품BOX바코드
    private Integer boxInCnt;       //내품BOX입수
    private Float inBoxWidth;
    private Float inBoxLength;
    private Float inBoxHeight;
    private Float inBoxBulk;
    private Integer inBoxWeight;    //입고BOX무게
    private Float inBoxSideSum;     //입고BOX규격(3면의합)
    private String inBoxBarcd;      //입고BOX바코드
    private Integer inBoxInCnt;     //입고BOX입수
    private Integer pltInCnt;       //파레트입수
    private String externalGodImgUrl;//상품이미지 URL
    private String cstGodImgUrl;    //상품판매 URL
    private Integer fileSeq;        //상품이미지파일순번
    private String origin;          //원산지
    private String distTermMgtYn;   //유통기한관리여부
    private Integer useTermDay;     //사용기한
    private Integer outCanDay;      //출고가능일수
    private Integer inCanDay;       //입고가능일수
    private String boxDiv;          //출고박스(1:파스토박스, 2:자사박스, 4: 파스토폴리백, 5: 자사폴리백, 7: 파스토스티로폼, 8: 자사스티로폼)
    private String bufGodYn;        //완충상품여부( Y:기본 완충재, A: 추가 완충재, N: 필요 없음 )
    private String firstInDt;       //최초입고일자
    private String subMate;         //부자재
    private String useYn;           //사용여부
    private String regId;           //등록자
    private String updId;           //수정자
    private String cubageChgYn;     //체적정보변경여부
    private String feeYn;           //요금적용여부
    private String invGodNm;        //송장출력용 상품명
    private String invGodNmUseYn;   //송장출력용 상품명 사용여부',
    private Integer saleUnitQty;    //판매단위수량
    private Integer safetyStock;    //안전재고수량
    private String godSerialNoYn;   //상품일련번호 사용여부(Y:사용, N:미사용)
    private String godInOutSameDayYn; //당일입출고 사용여부(Y:사용, N:미사용)
    private String godOutReservationYn; //출고예약 사용여부(Y:사용, N:미사용)
    private Float minStrTemp;       //최저보관온도
    private Float maxStrTemp;       //최고보관온도
    private String separateYn;      //별도배송 사용여부
    private Integer distNearDay;    //유통임박일수
    private Integer outStopDay;     //출고금지일수
    private Boolean mpckYn;         //합포장여부
    private Boolean completedBoxYn; //완박스여부
    private Boolean abroadDlvrYn;   //해외배송여부
    private String abroadNationCd;  //해외원산지코드
    private Float abroadSalDlrAmt;  //해외판매달러금액
    private Float abroadSalYenAmt;  //해외판매엔화금액
    private Integer abroadGdsHsCd;  //해외상품HS코드
    private Integer abroadGdsMsdsSeq; //해외상품MSDS파일순번
}
