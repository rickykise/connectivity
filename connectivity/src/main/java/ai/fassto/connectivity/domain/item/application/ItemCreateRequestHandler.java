package ai.fassto.connectivity.domain.item.application;

import ai.fassto.connectivity.domain.common.valueobject.enums.ActionType;
import ai.fassto.connectivity.domain.item.application.dto.solochain.SoloChainItemRequest;
import ai.fassto.connectivity.domain.item.application.dto.solochain.SoloChainItemResponse;
import ai.fassto.connectivity.domain.item.application.mapper.ItemDataMapper;
import ai.fassto.connectivity.domain.item.application.port.output.external.api.wms.ItemWms;
import ai.fassto.connectivity.domain.item.core.entity.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemCreateRequestHandler {
    private final ItemDataMapper itemDataMapper;
    private final ItemWms itemWms;

    public SoloChainItemResponse createItem(SoloChainItemRequest request) {
        if (request.getItems().size() < 2) {
            return itemDataMapper.itemToSoloChainItemResponse(
                    itemWms.create(
                            itemDataMapper.soloChainItemRequestToItem(request, ActionType.CREATE).get(0)
                    )
            );
        } else {
            List<Item> items = itemDataMapper.soloChainItemRequestToItem(request, ActionType.CREATE);
            itemWms.createAsAsync(items);

            return itemDataMapper.itemToSoloChainItemResponse(itemDataMapper.itemsToSoloChainItemResponse(items));
        }
    }
}
