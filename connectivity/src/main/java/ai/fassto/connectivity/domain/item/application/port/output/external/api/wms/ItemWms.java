package ai.fassto.connectivity.domain.item.application.port.output.external.api.wms;

import ai.fassto.connectivity.domain.item.core.entity.Item;
import ai.fassto.connectivity.externalservice.wms.solochain.item.valueobject.Result;

import java.util.List;

public interface ItemWms {
    List<Result> create(Item item);

    List<Result> update(Item item);

    List<Result> delete(Item item);

    void createAsAsync(List<Item> itemList);

    void updateAsAsync(List<Item> itemList);

    void deleteAsAsync(List<Item> itemList);
}
