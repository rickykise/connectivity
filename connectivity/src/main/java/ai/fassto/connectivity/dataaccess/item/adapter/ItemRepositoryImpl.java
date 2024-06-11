package ai.fassto.connectivity.dataaccess.item.adapter;

import ai.fassto.connectivity.dataaccess.item.mapper.ItemDataAccessMapper;
import ai.fassto.connectivity.dataaccess.item.repository.mybatis.ItemMapper;
import ai.fassto.connectivity.dataaccess.item.valueobject.GoodsVolume;
import ai.fassto.connectivity.domain.item.application.port.output.repository.ItemRepository;
import ai.fassto.connectivity.domain.item.core.valueobject.ItemVolume;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {
    private final ItemMapper itemMapper;
    private final ItemDataAccessMapper itemDataAccessMapper;


    @Override
    public Optional<ItemVolume> findItemOptionalBy(String itemCode) {
        return itemDataAccessMapper.goodsVolumeToItemVolume(findGoodsOrNullBy(itemCode));
    }

    @Override
    public ItemVolume updateItemVolume(ItemVolume itemVolume) {
        itemMapper.updateItemVolume(itemDataAccessMapper.itemVolumeToGoodsVolume(itemVolume));
        return itemVolume;
    }

    private GoodsVolume findGoodsOrNullBy(String godCd) {
        return itemMapper.findBy(godCd);
    }
}
