package ai.fassto.connectivity.domain.item.application;

import ai.fassto.connectivity.application.common.valueobject.ErrorDetail;
import ai.fassto.connectivity.domain.item.application.dto.erp.update.volume.ErpItemVolumeRequest;
import ai.fassto.connectivity.domain.item.application.dto.erp.update.volume.ErpItemVolumeResponse;
import ai.fassto.connectivity.domain.item.application.mapper.ItemDataMapper;
import ai.fassto.connectivity.domain.item.application.port.output.repository.ItemRepository;
import ai.fassto.connectivity.domain.item.core.exception.ItemNotFoundException;
import ai.fassto.connectivity.domain.item.core.valueobject.ItemVolume;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemVolumeUpdateRequestHandler {
    private final ItemDataMapper itemDataMapper;
    private final ItemRepository itemRepository;

    /**
     * 상품 체적 수정 ItemVolumeUpdate
     */
    @Transactional
    public ErpItemVolumeResponse updateVolume(ErpItemVolumeRequest request) {
        ItemVolume itemVolume = itemDataMapper.erpItemVolumeRequestToItemVolume(request);
        ItemVolume foundItemVolume = checkItemVolume(itemVolume);

        if (isNotTheSameBeforeValue(itemVolume, foundItemVolume)) {
            itemRepository.updateItemVolume(itemVolume);
        }

        return itemDataMapper.itemVolumeToErpItemVolumeResponse(itemVolume);
    }

    private ItemVolume checkItemVolume(ItemVolume itemVolumeRequest) {
        Optional<ItemVolume> itemVolumeOptional = itemRepository.findItemOptionalBy(itemVolumeRequest.getItemCode());
        if (itemVolumeOptional.isEmpty()) {
            throw new ItemNotFoundException(new ErrorDetail("itemCode", itemVolumeRequest.getItemCode()));
        }

        return itemVolumeOptional.get();
    }

    private boolean isNotTheSameBeforeValue(ItemVolume request, ItemVolume found) {
        return !request.equals(found);
    }

}
