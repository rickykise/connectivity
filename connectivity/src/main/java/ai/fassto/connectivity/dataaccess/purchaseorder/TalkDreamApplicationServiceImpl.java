package ai.fassto.connectivity.dataaccess.purchaseorder;

import ai.fassto.connectivity.domain.common.valueobject.enums.OrderType;
import ai.fassto.connectivity.dataaccess.common.dto.*;
import ai.fassto.connectivity.dataaccess.common.CommonService;
import ai.fassto.connectivity.dataaccess.common.TalkDreamService;
import ai.fassto.connectivity.dataaccess.common.valueobject.AlimTalkType;
import ai.fassto.connectivity.dataaccess.common.valueobject.SlipNoDivType;
import ai.fassto.connectivity.domain.purchaseorder.core.valueobject.enums.ReleaseType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class TalkDreamApplicationServiceImpl implements TalkDreamApplicationService {

    public static final String FSS_CST_CD = "00000";	// FSS 자체 고객사 코드

    private final TalkDreamService talkDreamService;
    private final CommonService commonService;

    /* [센터도착]  */
    public synchronized void insertNotifyingCstOfCenArrival(String cstHp, CenArrivalTalkDTO cenArrivalTalk) {
        try {

            String cstCd = FSS_CST_CD;
            String compName = "";
            String tmplCd = "";
            String serverName = "CONNECTIVITY"; //request.getServerName();
            String siteType = "(CONNECTIVITY)";
            AlimTalkType alimTalkType = AlimTalkType.CEN_ARRIVED;

            /*
            int siteTypeIndex = serverName.indexOf("-");
            if (siteTypeIndex >= 0) {
                siteType = "(" + serverName.substring(0, siteTypeIndex).toUpperCase() + ")";
            }
             */

            /* 고객사명 조회 findTalkDreamCompName */
            compName = getTalkDreamCompName(cstCd);
            if (StringUtils.isEmpty(compName)) {
                log.debug("no talkDream cstCd=[{}]", cstCd);
                return;
            }

            /* 템플릿 조회 findTalkDreamTemplate */
            tmplCd = getTalkDreamTemplateCode(cstCd, alimTalkType.getCode());
            if (StringUtils.isEmpty(tmplCd)) {
                log.debug("no talkDream cstCd=[{}] bznType=[{}]", cstCd, alimTalkType);
                return;
            }

            if (!StringUtils.isEmpty(siteType)) {
                compName = siteType + compName;
            }//2022

            HashMap<String, String> valueMap = new HashMap<>();
            String ordDt = cenArrivalTalk.getOrdDt();  //2023-01-06 해당 형식으로 전송
            String slipNo = cenArrivalTalk.getSlipNo();
            String releaseDt = cenArrivalTalk.getReleaseDt();
            WorkDayCalcDTO workDayCalcDTO = WorkDayCalcDTO.builder()
                    .pDate(releaseDt)
                    .pSign("-")
                    .pDays(1)
                    .build();

            String inLimitDt = commonService.getWorkdayCalc(workDayCalcDTO);

            valueMap.put("자사명", compName);
            valueMap.put("입고요청번호", slipNo);
            valueMap.put("출고가능일 - 1영업일", inLimitDt);
            valueMap.put("출고가능일", releaseDt);
            valueMap.put("FmsUrl", serverName);
            valueMap.put("param1", "classic/cmn/main/main.do?initMenu=CINP03" + "&inOrdSlipNo=" + slipNo + "&ordDt=" + ordDt);
            valueMap.put("param2", "classic/cmn/main/main.do?initMenu=CTT01" + "&inOrdSlipNo=" + slipNo + "&ordDt=" + ordDt);

            AlimTalkMultiPleDTO alimTalkMultiPleDTO = AlimTalkMultiPleDTO.builder()
                    .cstCd(cstCd)
                    .linkTable("tb_in_ord")     // 관련 테이블
                    .linkKey(slipNo)            // 관련 테이블의 키
                    .mobile(cstHp)
                    .template(tmplCd)           // tmplCd는 고객사별로 다를 수 있음
                    .alimTalkType(alimTalkType.getCode())
                    .valueJson(new ObjectMapper().writeValueAsString(valueMap))
                    .build();

            log.debug("alimTalkMultiPleDTO=[{}]", alimTalkMultiPleDTO);

            insertAlimTalkMultiple(alimTalkMultiPleDTO);

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /* [센터도착시] 익일 출고건이 00~13시 도착 시, 당일 출고 변환 권유 알림톡 전송 */
    public synchronized void insertNotifyingCstOfConvertAdvice(String cstHp, ConvertAdviceTalkDTO convertAdviceTalk) {
        try {

            String cstCd = FSS_CST_CD;
            String compName = "";
            String tmplCd = "";
            String serverName = "CONNECTIVITY"; //request.getServerName();
            String siteType = "(CONNECTIVITY)";
            AlimTalkType alimTalkType = AlimTalkType.PLUS_DAY_1_TO_DAY_0;

            /*
            int siteTypeIndex = serverName.indexOf("-");
            if (siteTypeIndex >= 0) {
                siteType = "(" + serverName.substring(0, siteTypeIndex).toUpperCase() + ")";
            }*/

            /* 고객사명 조회 findTalkDreamCompName */
            compName = getTalkDreamCompName(cstCd);
            if (StringUtils.isEmpty(compName)) {
                log.debug("no talkDream cstCd=[{}]", cstCd);
                return;
            }

            /* 템플릿 조회 findTalkDreamTemplate */
            tmplCd = getTalkDreamTemplateCode(cstCd, alimTalkType.getCode());
            if (StringUtils.isEmpty(tmplCd)) {
                log.debug("no talkDream cstCd=[{}] bznType=[{}]", cstCd, alimTalkType);
                return;
            }

            if (!StringUtils.isEmpty(siteType)) {
                compName = siteType + compName;
            }

            String slipNo = convertAdviceTalk.getSlipNo();
            String ordDt = convertAdviceTalk.getOrdDt();
            String cenArvTime = convertAdviceTalk.getCenArvTime();
            String releaseDt = convertAdviceTalk.getReleaseDt();

            HashMap<String, String> valueMap = new HashMap<>();
            valueMap.put("자사명", compName);
            valueMap.put("입고요청번호", slipNo);
            valueMap.put("센터도착일", cenArvTime);      //2022-11-04 10:06:35
            valueMap.put("출고가능일", releaseDt);       //2022-11-08
            valueMap.put("FmsUrl", serverName);
            //valueMap.put("HomeUrl", getHomeUrl()); // FSS 사용법 페이지 링크
            valueMap.put("param", "classic/cmn/main/main.do?initMenu=CINP03" + "&inOrdSlipNo=" + slipNo + "&ordDt=" + ordDt);

            AlimTalkMultiPleDTO alimTalkMultiPleDTO = AlimTalkMultiPleDTO.builder()
                    .cstCd(cstCd)
                    .linkTable("tb_in_ord")     // 관련 테이블
                    .linkKey(slipNo)            // 관련 테이블의 키
                    .mobile(cstHp)
                    .template(tmplCd)           // tmplCd는 고객사별로 다를 수 있음
                    .alimTalkType(alimTalkType.getCode())
                    .valueJson(new ObjectMapper().writeValueAsString(valueMap))
                    .build();

            log.debug("alimTalkMultiPleDTO=[{}]", alimTalkMultiPleDTO);

            insertAlimTalkMultiple(alimTalkMultiPleDTO);

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /* [센터도착시] 지연도착 하는 경우 3가지 출고구분(당일, 익일, D+2)에 대해 알림톡 발송
     * 당일출고 지연 (13시 이후 도착)
     * 익일출고 지연 (16시 이후 도착)
     * D+2 출고 지연 (16시 이후 도착) */
    public synchronized void insertNotifyingCstOfDelayArrival(String cstHp, DelayArrivalTalkDTO delayArrivalTalk) {
        try {

            String cstCd = FSS_CST_CD;
            String compName = "";
            String tmplCd = "";
            String serverName = "CONNECTIVITY"; //request.getServerName();
            String siteType = "(CONNECTIVITY)";
            ReleaseType releaseType =  delayArrivalTalk.getReleaseType();

            // 당일 출고이면 [당일출고 지연 템플릿] 아니면 [익일, D+2 출고 지연 템플릿]
            AlimTalkType alimTalkType = (releaseType == ReleaseType.PLUS_DAY_0) ?
                                                AlimTalkType.PLUS_DAY_0_DELAY :
                                                AlimTalkType.PLUS_DAY_2_DELAY;

            /*
            int siteTypeIndex = serverName.indexOf("-");
            if (siteTypeIndex >= 0) {
                siteType = "(" + serverName.substring(0, siteTypeIndex).toUpperCase() + ")";
            }*/

            compName = getTalkDreamCompName(cstCd);
            if (StringUtils.isEmpty(compName)) {
                log.debug("no talkDream cstCd=[{}]", cstCd);
                return;
            }

            tmplCd = getTalkDreamTemplateCode(cstCd, alimTalkType.getCode());
            if (StringUtils.isEmpty(tmplCd)) {
                log.debug("no talkDream cstCd=[{}] bznType=[{}]", cstCd, alimTalkType);
                return;
            }

            if (!StringUtils.isEmpty(siteType)) {
                compName = siteType + compName;
            }

            String slipNo = delayArrivalTalk.getSlipNo();
            String ordDt = delayArrivalTalk.getOrdDt();
            String cenArvTime = delayArrivalTalk.getCenArvTime();
            String whName = getWhName(delayArrivalTalk.getWarehouseCode());

            Double normalFee = 0.0;
            Double lateFee = 0.0;
            // 출고가능구분 공통코드 내의 코드값은 [ 0: D+2 출고, 1: D+1 출고. 2: 당일 출고 ]
            // 당일
            if(releaseType == ReleaseType.PLUS_DAY_0) {
                //todo - 공통코드 map에 저장해놓고 가져오는 것 같음. CodeUtil에서 공통코드 목록 cache 동기화 메소드 호출되는 시점 확인하기
                //normalFee = NumberUtil.toDouble(CodeUtil.getNm("INP25", "2"));
                lateFee   = normalFee * 1.5;
                // 익일
            }else if(releaseType == ReleaseType.PLUS_DAY_1) {
                //normalFee = NumberUtil.toDouble(CodeUtil.getNm("INP25", "1"));
                lateFee   = normalFee * 1.5;
                // D+2
            }else if(releaseType == ReleaseType.PLUS_DAY_2) {
                //normalFee = NumberUtil.toDouble(CodeUtil.getNm("INP25", "0"));
                lateFee   = normalFee * 2.0;
            }

            HashMap<String, String> valueMap = new HashMap<>();
            valueMap.put("자사명", compName);
            valueMap.put("입고요청번호", slipNo);
            valueMap.put("센터도착일", cenArvTime);
            valueMap.put("센터명", whName);
            valueMap.put("정상판매단위개당", String.valueOf(normalFee.intValue())+"원/EA");
            valueMap.put("할증판매단위개당", String.valueOf(lateFee.intValue())+"원/EA");
            valueMap.put("FmsUrl", serverName);
            //valueMap.put("HomeUrl", getHomeUrl()); // FSS 사용법 페이지 링크
            valueMap.put("param", "classic/cmn/main/main.do?initMenu=CINP03" + "&inOrdSlipNo=" + slipNo + "&ordDt=" + ordDt);

            AlimTalkMultiPleDTO alimTalkMultiPleDTO = AlimTalkMultiPleDTO.builder()
                    .cstCd(cstCd)
                    .linkTable("tb_in_ord")     // 관련 테이블
                    .linkKey(slipNo)            // 관련 테이블의 키
                    .mobile(cstHp)
                    .template(tmplCd)           // tmplCd는 고객사별로 다를 수 있음
                    .alimTalkType(alimTalkType.getCode())
                    .valueJson(new ObjectMapper().writeValueAsString(valueMap))
                    .build();

            log.debug("alimTalkMultiPleDTO=[{}]", alimTalkMultiPleDTO);

            insertAlimTalkMultiple(alimTalkMultiPleDTO);

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /* [입고검수|반품검수] 검수확인용 알림톡 */
    @Override
    public synchronized void insertNotifyingCstOfClientConfirm(String cstHp, NotificationInfoDTO notificationInfo) {
        try {
            String cstCd = FSS_CST_CD;
            String compName = "";
            String tmplCd = "";
            String serverName = "CONNECTIVITY"; // request.getServerName();
            String siteType = "(CONNECTIVITY)";
            OrderType orderType = OrderType.findByType(notificationInfo.getInDiv());
            AlimTalkType alimTalkType = (orderType == OrderType.SALES) ? AlimTalkType.IN_CLIENT_CONFIRM : AlimTalkType.RETURN_CLIENT_CONFIRM;

            /*
            int siteTypeIndex = serverName.indexOf("-");
            if (siteTypeIndex >= 0) {
                siteType = "(" + serverName.substring(0, siteTypeIndex).toUpperCase() + ")";
            }*/

            /* 고객사명 조회 findTalkDreamCompName */
            compName = getTalkDreamCompName(cstCd);
            if (StringUtils.isEmpty(compName)) {
                log.debug("no talkDream cstCd=[{}]", cstCd);
                return;
            }

            /* 템플릿 조회 findTalkDreamTemplate */
            tmplCd = getTalkDreamTemplateCode(cstCd, alimTalkType.getCode());
            if (StringUtils.isEmpty(tmplCd)) {
                log.debug("no talkDream cstCd=[{}] alimTalkType=[{}]", cstCd, alimTalkType.getCode());
                return;
            }

            if (!StringUtils.isEmpty(siteType)) {
                compName = siteType + compName;
            }

            HashMap<String, String> valueMap = new HashMap<>();
            String slipNo = notificationInfo.getSlipNo();
            String memo = notificationInfo.getMemo();

            if(orderType == OrderType.SALES) {
                String ordDt = notificationInfo.getOrdDt();
                valueMap.put("자사명", compName);
                valueMap.put("입고요청번호", slipNo);
                valueMap.put("도착일", ordDt);
                valueMap.put("검수 및 입고 내용", memo);
            } else {
                valueMap.put("자사명", compName);
                valueMap.put("원출고요청번호", slipNo);
                valueMap.put("검수 및 반품 내용", memo);
            }

            AlimTalkMultiPleDTO alimTalkMultiPleDTO = AlimTalkMultiPleDTO.builder()
                    .cstCd(cstCd)
                    .linkTable("tb_in_ord")     // 관련 테이블
                    .linkKey(slipNo)            // 관련 테이블의 키
                    .mobile(cstHp)
                    .template(tmplCd)           // tmplCd는 고객사별로 다를 수 있음
                    .alimTalkType(alimTalkType.getCode())
                    .valueJson(new ObjectMapper().writeValueAsString(valueMap))
                    .build();
            log.debug("alimTalkMultiPleDTO=[{}]", alimTalkMultiPleDTO);

            insertAlimTalkMultiple(alimTalkMultiPleDTO);
        } catch (Exception e) {
            log.error("Exception Class = {}", e.getClass());
            log.error("Exception Message = {}", e.getMessage(), e);
        }
    }

    /**
     * 알림톡 요청 저장(bztType, linkTable, linkKey 기준으로 같은 내용 중복해서 보냄)
     * @exception Exception
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED, rollbackFor = Exception.class)
    void insertAlimTalkMultiple(AlimTalkMultiPleDTO alimTalkMultiPleDTO) throws Exception {
        // tb_biztalk_tmpl의 해당 cst_cd, tmpl_cd에 해당하는 tmpl_content의 변수와
        // tb_biztalk_tmpl_att의 cst_cd, tmpl_cd에 해당하는 url_mobile, url_pc의 변수를 값으로 변환해야 한다.
        try {
            /* SlipNo 채번 */
            String talkDreamSlipNo = getTalkDreamSlipNo(); //talkDreamSlipNo 채번
            alimTalkMultiPleDTO.setTalkDreamSlipNo(talkDreamSlipNo);

            /* 알림톡 정보 저장 insertAlimTalkMultiple */
            talkDreamService.insertAlimTalkMultiple(alimTalkMultiPleDTO);
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }

    }

    /* 입고 완료 */
    public synchronized void insertNotifyingCstOfInComplete(String cstHp, InCompleteTalkDTO inCompleteTalk) {
        try {

            String cstCd = FSS_CST_CD;
            AlimTalkType alimTalkType = AlimTalkType.IN_COMPLETED;
            String compName = "";
            String tmplCd = "";
            String serverName = "CONNECTIVITY"; // request.getServerName();
            String siteType = "(CONNECTIVITY)";

            /*
            int siteTypeIndex = serverName.indexOf("-");
            if (siteTypeIndex >= 0) {
                siteType = "(" + serverName.substring(0, siteTypeIndex).toUpperCase() + ")";
            }*/

            /* 고객사명 조회 findTalkDreamCompName */
            compName = getTalkDreamCompName(cstCd);
            if (StringUtils.isEmpty(compName)) {
                log.debug("no talkDream cstCd=[{}]", cstCd);
                return;
            }

            /* 템플릿 조회 findTalkDreamTemplate */
            tmplCd = getTalkDreamTemplateCode(cstCd, alimTalkType.getCode());
            if (StringUtils.isEmpty(tmplCd)) {
                log.debug("no talkDream cstCd=[{}] bznType=[{}]", cstCd, alimTalkType);
                return;
            }

            if (!StringUtils.isEmpty(siteType)) {
                compName = siteType + compName;
            }

            //상품종류수, 입고완료 수량 정보 조회
            InQtyInfoDTO inQtyInfo = commonService.getInQtyInfo(inCompleteTalk.getSlipNo());

            String ordDt = inCompleteTalk.getOrdDt();
            String releaseDt = inCompleteTalk.getReleaseDt();
            String slipNo = inCompleteTalk.getSlipNo();

            HashMap<String, String> valueMap = new HashMap<>();
            valueMap.put("자사명", compName);
            valueMap.put("입고요청번호", slipNo);
            valueMap.put("출고가능일", releaseDt);
            valueMap.put("상품종류수", inQtyInfo.getSku() + " SKU");
            valueMap.put("입고완료수량", inQtyInfo.getInQty() + " EA");

            valueMap.put("FmsUrl", serverName);
            valueMap.put("param1", "classic/cmn/main/main.do?initMenu=OUT101" + "&inOrdSlipNo=" + slipNo + "&ordDt=" + ordDt);
            valueMap.put("param2", "classic/cmn/main/main.do?initMenu=CTT01" + "&inOrdSlipNo=" + slipNo + "&ordDt=" + ordDt);

            AlimTalkMultiPleDTO alimTalkMultiPleDTO = AlimTalkMultiPleDTO.builder()
                    .cstCd(cstCd)
                    .linkTable("tb_in_ord")     // 관련 테이블
                    .linkKey(slipNo)            // 관련 테이블의 키
                    .mobile(cstHp)
                    .template(tmplCd)           // tmplCd는 고객사별로 다를 수 있음
                    .alimTalkType(alimTalkType.getCode())
                    .valueJson(new ObjectMapper().writeValueAsString(valueMap))
                    .build();
            log.debug("alimTalkMultiPleDTO=[{}]", alimTalkMultiPleDTO);

            insertAlimTalkMultiple(alimTalkMultiPleDTO);

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    // TalkDreamSlipNo 채번 트랜잭션 분리 (기존 FMS 1.0 쿼리에서 FN_SLIP_NO 함수를 사용하면 Rock이 많이 걸린다고함)
    private String getTalkDreamSlipNo() throws Exception {
        SlipNoDTO slipNo = SlipNoDTO.builder()
                            .div(SlipNoDivType.TALK_DREAM.getErpCode())
                            .dt(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE))
                            .whCd("ALL")    //todo - enum생성
                            .build();
        return commonService.getSlipNo(slipNo);
    }


    // 톡드림 고객사명 조회
    private String getTalkDreamCompName(String cstCd) {
        String compName = talkDreamService.findTalkDreamCompName(cstCd);
        if (cstCd == null) return null;
        return compName;
    }

    // 톡드림 템플릿코드 조회
    private String getTalkDreamTemplateCode(String cstCd, String alimTalkType){
        TalkDreamTmplDTO talkDreamTmplDTO = TalkDreamTmplDTO.builder()
                .cstCd(cstCd)
                .alimTalkType(alimTalkType)
                .build();
        String template = talkDreamService.findTalkDreamTemplate(talkDreamTmplDTO);
        if (template == null) return null;
        return template;
    }

    //센터명 조회
    private String getWhName(String whCd){
        return commonService.getWhName(whCd);
    }

}
