package ai.fassto.connectivity.domain.salesorder.application.port.output.repository;

import ai.fassto.connectivity.dataaccess.salesorder.dto.TmsPreCallParcelDTO;

public interface PreCallWhenAllocatedRepository {
    TmsPreCallParcelDTO getPreCallInfo(String slipNo, String whCd, String cstCd);
}
