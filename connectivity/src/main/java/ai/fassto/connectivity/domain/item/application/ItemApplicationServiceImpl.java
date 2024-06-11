package ai.fassto.connectivity.domain.item.application;

import ai.fassto.connectivity.domain.item.application.dto.erp.update.volume.ErpItemVolumeRequest;
import ai.fassto.connectivity.domain.item.application.dto.erp.update.volume.ErpItemVolumeResponse;
import ai.fassto.connectivity.domain.item.application.dto.solochain.SoloChainItemRequest;
import ai.fassto.connectivity.domain.item.application.dto.solochain.SoloChainItemResponse;
import ai.fassto.connectivity.domain.item.application.port.input.service.ItemApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemApplicationServiceImpl implements ItemApplicationService {
    private final ItemCreateRequestHandler createRequestHandler;
    private final ItemUpdateRequestHandler updateRequestHandler;
    private final ItemDeleteRequestHandler deleteRequestHandler;
    private final ItemVolumeUpdateRequestHandler VolumeUpdateRequestHandler;

    @Override
    public SoloChainItemResponse create(SoloChainItemRequest request) {
        return createRequestHandler.createItem(request);
    }

    @Override
    public SoloChainItemResponse update(SoloChainItemRequest request) {
        return updateRequestHandler.updateItem(request);
    }

    @Override
    public SoloChainItemResponse delete(SoloChainItemRequest request) {
        return deleteRequestHandler.deleteItem(request);
    }

    @Override
    public ErpItemVolumeResponse updateVolume(ErpItemVolumeRequest request) {
        return VolumeUpdateRequestHandler.updateVolume(request);
    }

}
