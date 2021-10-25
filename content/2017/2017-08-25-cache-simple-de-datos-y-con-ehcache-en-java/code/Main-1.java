package io.github.picodotdev.blogbitix.javacache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws Exception {
        {
            System.out.println("LinkedHashMap");
            Map<Integer, Integer> cache = Collections.synchronizedMap(new SimpleCache<Integer, Integer>(5));
            IntStream.rangeClosed(1, 15).forEach(i -> cache.put(i, i));

            System.out.println(cache);
        }

        ...
    }
}
