package ai.fassto.connectivity.dataaccess.purchaseorder.adapter;

import ai.fassto.connectivity.common.utility.StringUtil;
import ai.fassto.connectivity.dataaccess.common.AlimTalkService;
import ai.fassto.connectivity.dataaccess.common.CommonService;
import ai.fassto.connectivity.dataaccess.common.dto.*;
import ai.fassto.connectivity.dataaccess.purchaseorder.TalkDreamApplicationService;
import ai.fassto.connectivity.dataaccess.purchaseorder.mapper.PurchaseOrderAlimTalkDataMapper;
import ai.fassto.connectivity.dataaccess.common.dto.ClientConfirmTalkDTO;
import ai.fassto.connectivity.domain.purchaseorder.application.port.output.notification.alimtalk.PurchaseOrderAlimTalk;
import ai.fassto.connectivity.domain.purchaseorder.core.entity.PurchaseOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static ai.fassto.connectivity.domain.common.valueobject.enums.OrderType.SALES;

@Slf4j
@Service
@RequiredArgsConstructor
public class PurchaseOrderAlimTalkImpl implements PurchaseOrderAlimTalk {

    private final TalkDreamApplicationService talkDreamApplicationService;
    private final AlimTalkService alimTalkService;
    private final CommonService commonService;
    private final PurchaseOrderAlimTalkDataMapper purchaseOrderAlimTalkDataMapper;

    @Override
    public void sendClientConfirmTalk(PurchaseOrder purchaseOrder) {
        ClientConfirmTalkDTO clientConfirmTalk = purchaseOrderAlimTalkDataMapper.purchaseOrderToClientConfirmTalkDTO(purchaseOrder);

        //알림정보
        List<NotificationInfoDTO> notificationInfoList;
        if (clientConfirmTalk.getOrderType() == SALES) {
            /* 입고정보 확인 findInboundInfo */
            notificationInfoList = alimTalkService.findInboundInfo(clientConfirmTalk); //입고
        } else {
            /* 반품정보 확인 findInboundInfo */
            notificationInfoList = alimTalkService.findReturnInfo(clientConfirmTalk); //반품
        }

        /* 고객 정보 조회(휴대폰, 이메일) findCustomerHpEmail */
        List<CustomerInfoDTO> customerInfoList = alimTalkService.findCstHpAndEmail(clientConfirmTalk.getCstCd());
        log.info("customerInfoList: {}", customerInfoList);

        for (NotificationInfoDTO notificationInfo : notificationInfoList) {
            customerInfoList.stream()
                    .map(cstInfo -> cstInfo.getCstHp())
                    .distinct()
                    .filter(StringUtil::isHpNumberFormat)
                    .forEach(cstHp -> talkDreamApplicationService.insertNotifyingCstOfClientConfirm(cstHp, notificationInfo));
        }

    }

    // 1.센터도착 알림톡 전송
    @Override
    public void sendCenArrivalTalk(PurchaseOrder purchaseOrder) {
        CenArrivalTalkDTO cenArrivalTalk = purchaseOrderAlimTalkDataMapper.purchaseOrderToCenArrivalTalkDTO(purchaseOrder);

        /* 고객 정보 조회(휴대폰, 이메일) findCustomerHpEmail */
        List<CustomerInfoDTO> customerInfoList = alimTalkService.findCstHpAndEmail(cenArrivalTalk.getCstCd());
        log.info("customerInfoList: {}", customerInfoList);

        customerInfoList.stream()
                .map(cstInfo -> cstInfo.getCstHp())
                .distinct()
                .filter(StringUtil::isHpNumberFormat)
                .forEach(cstHp ->
                        talkDreamApplicationService.insertNotifyingCstOfCenArrival(cstHp, cenArrivalTalk)
                );

    }

    // 2.익일 출고 => 당일출고로 변환 권유 알림톡 전송
    @Override
    public void sendConvertAdviceTalk(PurchaseOrder purchaseOrder) {
        ConvertAdviceTalkDTO convertAdviceTalk =
                purchaseOrderAlimTalkDataMapper.purchaseOrderToConvertAdviceTalkDTO(purchaseOrder);

        /* 고객 정보 조회(휴대폰, 이메일) findCustomerHpEmail */
        List<CustomerInfoDTO> customerInfoList = alimTalkService.findCstHpAndEmail(convertAdviceTalk.getCstCd());
        log.info("customerInfoList: {}", customerInfoList);

        customerInfoList.stream()
                .map(cstInfo -> cstInfo.getCstHp())
                .distinct()
                .filter(StringUtil::isHpNumberFormat)
                .forEach(cstHp ->
                        talkDreamApplicationService.insertNotifyingCstOfConvertAdvice(cstHp, convertAdviceTalk)
                );

    }

    // 3.지연도착 하는 경우 3가지 출고구분(당일, 익일, D+2)에 대해 알림톡 전송
    @Override
    public void sendDelayArrivalTalk(PurchaseOrder purchaseOrder) {
        DelayArrivalTalkDTO delayArrivalTalk = purchaseOrderAlimTalkDataMapper.purchaseOrderToDelayArrivalTalkDTO(purchaseOrder);

        /* 고객 정보 조회(휴대폰, 이메일) findCustomerHpEmail */
        List<CustomerInfoDTO> customerInfoList = alimTalkService.findCstHpAndEmail(delayArrivalTalk.getCstCd());
        log.info("customerInfoList: {}", customerInfoList);

        customerInfoList.stream()
                .map(cstInfo -> cstInfo.getCstHp())
                .distinct()
                .filter(StringUtil::isHpNumberFormat)
                .forEach(cstHp ->
                        talkDreamApplicationService.insertNotifyingCstOfDelayArrival(cstHp, delayArrivalTalk)
                );

    }

    //입고완료 알림톡 전송
    @Override
    public void sendInCompleteTalk(PurchaseOrder purchaseOrder) {
        InCompleteTalkDTO inCompleteTalk = purchaseOrderAlimTalkDataMapper.purchaseOrderToInCompleteTalk(purchaseOrder);

        /* 고객 정보 조회(휴대폰, 이메일) findCustomerHpEmail */
        List<CustomerInfoDTO> customerInfoList = alimTalkService.findCstHpAndEmail(inCompleteTalk.getCstCd());
        log.info("customerInfoList: {}", customerInfoList);

        customerInfoList.stream()
                .map(cstInfo -> cstInfo.getCstHp())
                .distinct()
                .filter(StringUtil::isHpNumberFormat)
                .forEach(cstHp ->
                        talkDreamApplicationService.insertNotifyingCstOfInComplete(cstHp, inCompleteTalk)
                );
    }


}
