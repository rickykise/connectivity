package ai.fassto.connectivity.domain.item.core.valueobject;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
public class ItemVolume {
    private String itemCode;
    private Float width;
    private Float height;
    private Float depth;
    private String dimUnitCode;
    private Integer weight;
    private String weightUnitCode;
    private String itemUnitCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemVolume that = (ItemVolume) o;
        return itemCode.equals(that.itemCode) &&
                Objects.equals(width, that.width) &&
                Objects.equals(height, that.height) &&
                Objects.equals(depth, that.depth) &&
                Objects.equals(weight, that.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemCode, width, height, depth, weight);
    }
}
