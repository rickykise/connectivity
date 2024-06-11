package ai.fassto.connectivity.dataaccess.common.repository.mybatis;

import ai.fassto.connectivity.dataaccess.common.dto.ConnectivityPropertyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConnectivityPropertyMapper {
    ConnectivityPropertyDTO findOrNullByMainTypeAndSubTypeAndActive(String mainType, String subType, boolean active);

    List<ConnectivityPropertyDTO> findByMainTypeAndActive(String mainType, boolean active);
}
