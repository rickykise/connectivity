package ai.fassto.connectivity.dataaccess.salesorder.dto;

import ai.fassto.connectivity.dataaccess.salesorder.entity.WhSubsidiaryEntity;
import lombok.Builder;

import java.util.List;

@Builder
public class WhSubsidiaryDTO {
    private String whCd;
    private List<WhSubsidiaryEntity> whSubsidiaryEntityList;
}
