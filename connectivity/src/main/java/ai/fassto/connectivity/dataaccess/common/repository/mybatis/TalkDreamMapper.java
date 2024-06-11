package ai.fassto.connectivity.dataaccess.common.repository.mybatis;

import ai.fassto.connectivity.dataaccess.common.dto.AlimTalkMultiPleDTO;
import ai.fassto.connectivity.dataaccess.common.dto.TalkDreamTmplDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TalkDreamMapper {
    String findTalkDreamCompName(String cstCd);
    String findTalkDreamTemplate(TalkDreamTmplDTO talkDreamTmplDTO);
    int insertAlimTalkMultiple(AlimTalkMultiPleDTO alimTalkMultiPleDTO);
}
