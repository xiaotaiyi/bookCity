package com.xiaodongbaobao.novel.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import com.xiaodongbaobao.novel.utils.RedisUtil;

@Configuration
public class Common {

	
	@Bean
	public RedisUtil redisUtil(RedisTemplate redisTemplate){
		RedisUtil redisUtil = new RedisUtil();
		redisUtil.setRedisTemplate(redisTemplate);
		return redisUtil;
	}


}
