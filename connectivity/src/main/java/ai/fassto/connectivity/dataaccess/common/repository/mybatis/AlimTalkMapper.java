package ai.fassto.connectivity.dataaccess.common.repository.mybatis;

import ai.fassto.connectivity.dataaccess.common.dto.CustomerInfoDTO;
import ai.fassto.connectivity.dataaccess.common.dto.NotificationInfoDTO;
import ai.fassto.connectivity.dataaccess.common.dto.ClientConfirmTalkDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AlimTalkMapper {
    List<CustomerInfoDTO> findCstHpAndEmail(String cstCd);
    List<NotificationInfoDTO> findInboundInfo(ClientConfirmTalkDTO clientConfirmTalkDTO);
    List<NotificationInfoDTO> findReturnInfo(ClientConfirmTalkDTO clientConfirmTalkDTO);
}
