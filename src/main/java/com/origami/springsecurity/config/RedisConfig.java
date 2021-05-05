// package com.origami.springsecurity.config;
//
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.redis.connection.RedisConnectionFactory;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//
// /**
//  * @author origami1203
//  * @date 2021-5-3 18:59
//  * @description redis的配置类
//  */
// @Configuration
// public class RedisConfig {
//
//     @Bean
//     public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory) {
//         RedisTemplate<String, Object> template = new RedisTemplate<>();
//         template.setConnectionFactory(factory);
//
//         Jackson2JsonRedisSerializer<Object> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//         ObjectMapper objectMapper = new ObjectMapper();
//         jsonRedisSerializer.setObjectMapper(objectMapper);
//
//         template.setKeySerializer(jsonRedisSerializer);
//         template.setValueSerializer(jsonRedisSerializer);
//         template.setHashKeySerializer(jsonRedisSerializer);
//         template.setHashValueSerializer(jsonRedisSerializer);
//
//         template.afterPropertiesSet();
//         return template;
//     }
// }
