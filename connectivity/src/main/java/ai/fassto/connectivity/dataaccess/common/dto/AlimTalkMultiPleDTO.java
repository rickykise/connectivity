package ai.fassto.connectivity.dataaccess.common.dto;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class AlimTalkMultiPleDTO {
    private String talkDreamSlipNo;
    private String alimTalkType;
    private String linkTable;       // 관련 테이블
    private String linkKey;         // 관련 테이블의 키
    private String cstCd;
    private String mobile;          // 수신자 번호 예)01012345678
    private String template;
    private String valueJson;
    private String sendTime;
    private String loginUserId;

    public void setTalkDreamSlipNo(String talkDreamSlipNo) {
        this.talkDreamSlipNo = talkDreamSlipNo;
    }
}
