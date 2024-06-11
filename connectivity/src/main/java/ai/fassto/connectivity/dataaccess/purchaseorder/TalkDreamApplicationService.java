package ai.fassto.connectivity.dataaccess.purchaseorder;

import ai.fassto.connectivity.dataaccess.common.dto.*;

public interface TalkDreamApplicationService {
    void insertNotifyingCstOfCenArrival(String cstHp, CenArrivalTalkDTO cenArrivalTalk);
    void insertNotifyingCstOfConvertAdvice(String cstHp, ConvertAdviceTalkDTO convertAdviceTalk);
    void insertNotifyingCstOfDelayArrival(String cstHp, DelayArrivalTalkDTO delayArrivalTalk);
    void insertNotifyingCstOfClientConfirm(String cstHp, NotificationInfoDTO notificationInfo);
    void insertNotifyingCstOfInComplete(String cstHp, InCompleteTalkDTO inCompleteDTO);
}
