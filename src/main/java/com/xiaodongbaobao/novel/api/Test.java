package com.xiaodongbaobao.novel.api;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.wenwenmao.core.entity.CApiBase;
import com.wenwenmao.core.exception.ApiProcessException;
import com.wenwenmao.core.service.IApiProcessService;
import com.wenwenmao.core.service.ParamContextHolder;
import com.xiaodongbaobao.novel.bean.CosProcess;

@Service("test")
public class Test implements IApiProcessService{


	@Autowired
	private AipSpeech client;
	@Autowired
	private CosProcess cosProcess;
	private static String folder = "voice/";
	@Override
	public Map<String, Object> apiProcess(ParamContextHolder paramContextHolder, CApiBase arg1) throws ApiProcessException {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = new HashMap<>();
		TtsResponse res = client.synthesis("栋哥最帅","zh",1,null);
		TtsResponse res1 = client.synthesis("栋哥最有钱","zh",1,null);
		System.out.println(res.getResult());
		byte[] data = ArrayUtils.addAll(res.getData(), res1.getData());
		try {
			cosProcess.createMP3(folder+"test1"+"_zh.mp3", new ByteArrayInputStream(data));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ApiProcessException("C0001","语音合成异常");
		}
		return map;
	}
	
}
