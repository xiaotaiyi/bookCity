package com.xiaodongbaobao.novel.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baidu.aip.nlp.AipNlp;

@Configuration
public class LanguageClient {

	@Value("${app.baidu.language.appid}")
	public String APP_ID;
	@Value("${app.baidu.language.key}")
	public String API_KEY;
	@Value("${app.baidu.language.secret}")
	public String SECRET_KEY;
	@Bean
	public AipNlp getAipNlp(){
		return new AipNlp(APP_ID, API_KEY, SECRET_KEY);
	}
}
