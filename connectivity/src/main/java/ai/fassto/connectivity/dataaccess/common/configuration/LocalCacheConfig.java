package ai.fassto.connectivity.dataaccess.common.configuration;

import ai.fassto.connectivity.dataaccess.common.valueobject.LocalCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
public class LocalCacheConfig {
    @Primary
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(
                Arrays.stream(LocalCache.values())
                        .map(localCache -> new CaffeineCache(
                                localCache.getName(),
                                Caffeine.newBuilder()
                                        .maximumSize(localCache.getMaximumSizeByKey())
                                        .expireAfterWrite(localCache.getExpireAfterWriteInHours(), TimeUnit.HOURS)
                                        .build()
                        )).toList()
        );
        return cacheManager;
    }
}
