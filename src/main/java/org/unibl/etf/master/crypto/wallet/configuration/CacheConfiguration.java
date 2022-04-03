package org.unibl.etf.master.crypto.wallet.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfiguration {
    public static final String WALLETS_CACHE_NAME = "wallets";

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(WALLETS_CACHE_NAME);
    }
}