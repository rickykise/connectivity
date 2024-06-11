package ai.fassto.connectivity.dataaccess.common;

import ai.fassto.connectivity.dataaccess.common.dto.AlimTalkMultiPleDTO;
import ai.fassto.connectivity.dataaccess.common.dto.TalkDreamTmplDTO;
import org.springframework.stereotype.Service;

@Service
public interface TalkDreamService {
     String findTalkDreamCompName(String cstCd);
     String findTalkDreamTemplate(TalkDreamTmplDTO talkDreamTmplDTO);
     int insertAlimTalkMultiple(AlimTalkMultiPleDTO alimTalkMultiPleDTO);
}
