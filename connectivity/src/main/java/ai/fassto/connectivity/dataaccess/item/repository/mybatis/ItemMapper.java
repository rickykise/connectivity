package ai.fassto.connectivity.dataaccess.item.repository.mybatis;

import ai.fassto.connectivity.dataaccess.item.valueobject.GoodsVolume;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemMapper {
    GoodsVolume findBy(String godCd);

    int updateItemVolume(GoodsVolume goodsVolume);
}
