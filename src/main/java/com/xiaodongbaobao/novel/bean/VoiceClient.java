package com.xiaodongbaobao.novel.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baidu.aip.speech.AipSpeech;

@Configuration
public class VoiceClient {
	@Value("${app.baidu.voice.appid}")
	private String appid;
	@Value("${app.baidu.voice.key}")
	private String key;
	@Value("${app.baidu.voice.secret}")
	private String secret;
	
	@Bean
	public AipSpeech getVoiceClient(){
		return new AipSpeech(appid, key, secret);
	}
}
