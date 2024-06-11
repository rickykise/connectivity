package ai.fassto.connectivity.dataaccess.item.mapper;

import ai.fassto.connectivity.dataaccess.item.valueobject.GoodsVolume;
import ai.fassto.connectivity.domain.item.core.valueobject.ItemVolume;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ItemDataAccessMapper {
    public GoodsVolume itemVolumeToGoodsVolume(ItemVolume itemVolume){
        return GoodsVolume.builder()
                .godCd(itemVolume.getItemCode())
                .godWidth(itemVolume.getWidth()) // width: 가로
                .godLength(itemVolume.getDepth()) // length: 세로
                .godHeight(itemVolume.getHeight()) // height: 높이
                .godWeight(itemVolume.getWeight()) // weight: 무게
                .godBulk(itemVolume.getWidth() * itemVolume.getDepth() * itemVolume.getHeight()) // 체적값
                .godSideSum(itemVolume.getWidth() + itemVolume.getDepth() + itemVolume.getHeight()) // 세변의합
                .build();
    }

    public Optional<ItemVolume> goodsVolumeToItemVolume(GoodsVolume goodsVolumeOrNull) {
        if (goodsVolumeOrNull == null) {
            return Optional.empty();
        }

        return Optional.of(ItemVolume.builder()
                .itemCode(goodsVolumeOrNull.getGodCd())
                .width(goodsVolumeOrNull.getGodWidth())
                .depth(goodsVolumeOrNull.getGodLength())
                .height(goodsVolumeOrNull.getGodHeight())
                .weight(goodsVolumeOrNull.getGodWeight())
                .build()
        );
    }
}
