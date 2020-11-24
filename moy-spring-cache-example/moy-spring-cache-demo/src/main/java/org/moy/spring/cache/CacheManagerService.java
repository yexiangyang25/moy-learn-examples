package org.moy.spring.cache;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheManagerService {

    @Autowired
    CacheManager cacheManager;

    public void clearCacheByName(String cacheName) {
        Cache test = cacheManager.getCache(cacheName);
        if (null != test) {
            test.clear();
        }
    }
}
