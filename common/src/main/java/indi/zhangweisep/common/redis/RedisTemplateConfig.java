package indi.zhangweisep.common.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * <p>
 * 初始化redisTemplate
 * </p>
 *
 * @author ZhangWei
 * @since 2020/11/3 14:26
 */
@EnableCaching
@Configuration
@RequiredArgsConstructor
@AutoConfigureBefore(RedisAutoConfiguration.class)
public class RedisTemplateConfig {

    private final RedisConnectionFactory factory;

    /**
     * spring托管，初始化RedisTemplate
     * RedisTemplate：spring封装的redis操作类，支持各种redis原生的api
     *
     * @return {@link RedisTemplate<String, Object>}
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }

    /**
     * 散列操作
     *
     * @param redisTemplate redis操作类
     * @return {@link HashOperations<String, String, Object>}
     */
    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    /**
     * 值操作
     *
     * @param redisTemplate redis操作类
     * @return {@link ValueOperations<String, String>}
     */
    @Bean
    public ValueOperations<String, String> valueOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    /**
     * 列表操作
     *
     * @param redisTemplate redis操作类
     * @return {@link ListOperations<String, Object>}
     */
    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    /**
     * 集合操作
     *
     * @param redisTemplate redis操作类
     * @return {@link SetOperations<String, Object>}
     */
    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    /**
     * z集合操作
     *
     * @param redisTemplate redis操作类
     * @return {@link ZSetOperations<String, Object>}
     */
    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }

}
