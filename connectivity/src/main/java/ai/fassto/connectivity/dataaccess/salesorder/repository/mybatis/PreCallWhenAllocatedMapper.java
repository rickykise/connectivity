package ai.fassto.connectivity.dataaccess.salesorder.repository.mybatis;

import ai.fassto.connectivity.dataaccess.salesorder.dto.TmsPreCallParcelDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PreCallWhenAllocatedMapper {
    TmsPreCallParcelDTO getPreCallInfo(String slipNo, String whCd, String cstCd);
}
