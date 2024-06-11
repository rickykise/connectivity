package ai.fassto.connectivity.dataaccess.common;

import ai.fassto.connectivity.dataaccess.common.dto.ConnectivityPropertyDTO;
import ai.fassto.connectivity.dataaccess.common.repository.mybatis.ConnectivityPropertyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConnectivityPropertyComponent {

    private final ConnectivityPropertyMapper connectivityPropertyMapper;

    public static String getCacheKey(String mainType, String subType) {
        return String.format("%s:%s", mainType, subType);
    }

    @Cacheable(cacheNames = "$(caffeine-cache.connectivity-property-list)", key = "#cacheKey")
    public List<ConnectivityPropertyDTO> getConnectivityPropertyList(String mainType, String cacheKey) {
        return connectivityPropertyMapper.findByMainTypeAndActive(mainType, true);
    }

    @Cacheable(cacheNames = "$(caffeine-cache.connectivity-property)", key = "#cacheKey")
    public ConnectivityPropertyDTO getConnectivityPropertyOrNull(String mainType, String subType, String cacheKey) {
        return connectivityPropertyMapper.findOrNullByMainTypeAndSubTypeAndActive(mainType, subType, true);
    }

    @CacheEvict(cacheNames = "$(caffeine-cache.connectivity-property-list)", allEntries = true)
    public void refreshCacheForConnectivityPropertyList() {
    }

    @CacheEvict(cacheNames = "$(caffeine-cache.connectivity-property)", allEntries = true)
    public void refreshCacheForConnectivityProperty() {
    }

    @CacheEvict(cacheNames = "$(caffeine-cache.connectivity-property)", key = "#cacheKey")
    public void refreshCacheAtConnectivityProperty(String cacheKey) {
    }

    public List<ConnectivityPropertyDTO> getConnectivityPropertyList(String mainType) {
        return connectivityPropertyMapper.findByMainTypeAndActive(mainType, true);
    }

    public ConnectivityPropertyDTO getConnectivityPropertyOrNull(String mainType, String subType) {
        return connectivityPropertyMapper.findOrNullByMainTypeAndSubTypeAndActive(mainType, subType, true);
    }




}
