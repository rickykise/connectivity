package ai.fassto.connectivity.dataaccess.common.adapter;

import ai.fassto.connectivity.dataaccess.common.dto.AlimTalkMultiPleDTO;
import ai.fassto.connectivity.dataaccess.common.dto.TalkDreamTmplDTO;
import ai.fassto.connectivity.dataaccess.common.repository.mybatis.TalkDreamMapper;
import ai.fassto.connectivity.dataaccess.common.TalkDreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TalkDreamDreamServiceImpl implements TalkDreamService {

    private final TalkDreamMapper talkDreamMapper;

    @Override
    public String findTalkDreamCompName(String cstCd) {
        return talkDreamMapper.findTalkDreamCompName(cstCd);
    }

    @Override
    public String findTalkDreamTemplate(TalkDreamTmplDTO talkDreamTmplDTO) {
        return talkDreamMapper.findTalkDreamTemplate(talkDreamTmplDTO);
    }

    @Override
    public int insertAlimTalkMultiple(AlimTalkMultiPleDTO alimTalkMultiPleDTO) {
        return talkDreamMapper.insertAlimTalkMultiple(alimTalkMultiPleDTO);
    }
}
