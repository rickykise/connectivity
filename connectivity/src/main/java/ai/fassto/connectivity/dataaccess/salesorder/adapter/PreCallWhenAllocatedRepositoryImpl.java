package ai.fassto.connectivity.dataaccess.salesorder.adapter;

import ai.fassto.connectivity.dataaccess.salesorder.dto.TmsPreCallParcelDTO;
import ai.fassto.connectivity.dataaccess.salesorder.repository.mybatis.PreCallWhenAllocatedMapper;
import ai.fassto.connectivity.domain.salesorder.application.port.output.repository.PreCallWhenAllocatedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PreCallWhenAllocatedRepositoryImpl implements PreCallWhenAllocatedRepository {
    private final PreCallWhenAllocatedMapper preCallWhenAllocatedMapper;

    @Transactional(readOnly = true)
    @Override
    public TmsPreCallParcelDTO getPreCallInfo(String slipNo, String whCd, String cstCd) {
        return preCallWhenAllocatedMapper.getPreCallInfo(slipNo, whCd, cstCd);
    }

}
