package com.demo.config;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.demo.constant.RedisConstant;
import com.demo.domain.PmsProduct;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiWen
 * @Date: 2021-11-11
 * @description:  Spring cache采用redis作为缓存策略
 */
@Configuration
public class RedisCacheConfig {


    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        // 设置CacheManager的值序列化方式为Jackson2JsonRedisSerializer,默认就是使用StringRedisSerializer序列化key,JdkSerializationRedisSerializer序列化value

        //Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        //FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        // 配置value序列化方式为FastJsonRedisSerializer,key序列化方式采用默认的StringRedisSerializer
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(genericJackson2JsonRedisSerializer));
        //// 每一类信息进行缓存配置
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        cacheConfiguration.prefixCacheNameWith(RedisConstant.PRODUCT_CACHE_PREFIX);
        cacheConfiguration.entryTtl(Duration.ofSeconds(5));
        redisCacheConfigurationMap.put("product",cacheConfiguration);
        Map<String, RedisCacheConfiguration> expires = MapUtil.<String, RedisCacheConfiguration>builder()
                .put("15", RedisCacheConfiguration.defaultCacheConfig().entryTtl(
                        Duration.ofMillis(15)).serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(genericJackson2JsonRedisSerializer))
                )
                .put("30", RedisCacheConfiguration.defaultCacheConfig().entryTtl(
                        Duration.ofMillis(30)
                ))
                .put("60", RedisCacheConfiguration.defaultCacheConfig().entryTtl(
                        Duration.ofMillis(60)
                ))
                .put("120", RedisCacheConfiguration.defaultCacheConfig().entryTtl(
                        Duration.ofMillis(120)
                ))
                .build();

        // 初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);

        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(genericJackson2JsonRedisSerializer));

        // 设置默认超过期时间是1天(短时间的已经做了其他的处理,不会采用注解形式加入缓存)
        defaultCacheConfig.entryTtl(Duration.ofDays(1));

        // 初始化RedisCacheManager
        return RedisCacheManager.builder()
                .cacheWriter(redisCacheWriter)
                .cacheDefaults(defaultCacheConfig)
                .withInitialCacheConfigurations(redisCacheConfigurationMap).build();
       }




}
