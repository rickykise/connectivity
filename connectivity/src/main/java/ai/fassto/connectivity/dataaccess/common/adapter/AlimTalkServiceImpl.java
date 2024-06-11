package ai.fassto.connectivity.dataaccess.common.adapter;

import ai.fassto.connectivity.dataaccess.common.dto.CustomerInfoDTO;
import ai.fassto.connectivity.dataaccess.common.dto.NotificationInfoDTO;
import ai.fassto.connectivity.dataaccess.common.repository.mybatis.AlimTalkMapper;
import ai.fassto.connectivity.dataaccess.common.dto.ClientConfirmTalkDTO;
import ai.fassto.connectivity.dataaccess.common.AlimTalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AlimTalkServiceImpl implements AlimTalkService {

    private final AlimTalkMapper alimTalkMapper;

    @Override
    public List<CustomerInfoDTO> findCstHpAndEmail(String cstCd) {
        return alimTalkMapper.findCstHpAndEmail(cstCd);
    }

    @Override
    public List<NotificationInfoDTO> findInboundInfo(ClientConfirmTalkDTO clientConfirmTalkDTO) {
        return alimTalkMapper.findInboundInfo(clientConfirmTalkDTO);
    }

    @Override
    public List<NotificationInfoDTO> findReturnInfo(ClientConfirmTalkDTO clientConfirmTalkDTO) {
        return alimTalkMapper.findReturnInfo(clientConfirmTalkDTO);
    }
}
