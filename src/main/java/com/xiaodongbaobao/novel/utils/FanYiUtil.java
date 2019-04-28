package com.xiaodongbaobao.novel.utils;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.wenwenmao.core.utils.BaseValueProcessUtil;
@Component
public class FanYiUtil {

	private static RestTemplate restTemplate = new RestTemplate();
	
	private static String APPID;
	
	private static String KEY;

	private static String URL;

	public static String translate(String q,String to){
		String result = null;
		int salt = (int) (1 + Math.random() * 10);
		String md5Str = BaseValueProcessUtil.md5(APPID + q + salt + KEY);
		//对传值进行封装
		MultiValueMap<String,Object> param = new LinkedMultiValueMap<>();
		param.add("appid", APPID);
		param.add("from", "zh");
		param.add("q", q);
		param.add("sign",md5Str );
		param.add("salt",salt );
		param.add("to", to);
		//post请求
		Map<String, Object> translate = (Map<String, Object>) JSONObject
				.parse(restTemplate.postForObject(URL, param, String.class));
		try {
			System.out.println(translate);
			List<Map<String, Object>> trans_result = (List<Map<String, Object>>) translate.get("trans_result");
			result = "&emsp;&emsp;"+(String) trans_result.get(0).get("dst");
		} catch (Exception e) {
			
		}
		return result;
	}
	@Value("${app.baidu.fanyi.appid}")
	public void setAPPID(String aPPID) {
		APPID = aPPID;
	}
	@Value("${app.baidu.fanyi.key}")
	public void setKEY(String kEY) {
		KEY = kEY;
	}
	@Value("${app.baidu.fanyi.url}")
	public void setURL(String uRL) {
		URL = uRL;
	}
}
