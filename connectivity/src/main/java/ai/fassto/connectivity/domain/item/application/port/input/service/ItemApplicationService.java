package ai.fassto.connectivity.domain.item.application.port.input.service;


import ai.fassto.connectivity.domain.item.application.dto.erp.update.volume.ErpItemVolumeRequest;
import ai.fassto.connectivity.domain.item.application.dto.erp.update.volume.ErpItemVolumeResponse;
import ai.fassto.connectivity.domain.item.application.dto.solochain.SoloChainItemRequest;
import ai.fassto.connectivity.domain.item.application.dto.solochain.SoloChainItemResponse;

public interface ItemApplicationService {

    SoloChainItemResponse create(SoloChainItemRequest request);

    SoloChainItemResponse update(SoloChainItemRequest request);

    SoloChainItemResponse delete(SoloChainItemRequest request);

    ErpItemVolumeResponse updateVolume(ErpItemVolumeRequest request);

}
