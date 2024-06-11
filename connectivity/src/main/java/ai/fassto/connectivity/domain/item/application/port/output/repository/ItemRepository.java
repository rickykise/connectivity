package ai.fassto.connectivity.domain.item.application.port.output.repository;


import ai.fassto.connectivity.domain.item.core.valueobject.ItemVolume;

import java.util.Optional;

public interface ItemRepository {
    Optional<ItemVolume> findItemOptionalBy(String itemCode);

    ItemVolume updateItemVolume(ItemVolume itemVolume);
}
