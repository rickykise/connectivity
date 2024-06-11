package ai.fassto.connectivity.dataaccess.common;

import ai.fassto.connectivity.dataaccess.common.dto.CustomerInfoDTO;
import ai.fassto.connectivity.dataaccess.common.dto.NotificationInfoDTO;
import ai.fassto.connectivity.dataaccess.common.dto.ClientConfirmTalkDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AlimTalkService {
    List<CustomerInfoDTO> findCstHpAndEmail(String cstCd);
    List<NotificationInfoDTO> findInboundInfo(ClientConfirmTalkDTO clientConfirmTalkDTO);
    List<NotificationInfoDTO> findReturnInfo(ClientConfirmTalkDTO clientConfirmTalkDTO);
}
