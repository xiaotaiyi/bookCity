package com.xiaodongbaobao.novel.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.aip.speech.AipSpeech;

@RestController
public class NVoiceToWord {
	@Autowired
	private AipSpeech client;
	@PostMapping("/aip/VoiceToWord")
    public Map<String,Object> list(MultipartFile voice) throws Exception {
		Map<String,Object> map = new HashMap<>();
		map.put("code", "ok");
		byte[] bytes = null;
		try {
			bytes = voice.getBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject asr = client.asr(bytes, "pcm", 16000, null);

		try{
			String str = ((JSONArray) asr.get("result")).getString(0);
			str = str.replaceAll("。", "");
			map.put("content", str);
		}catch(Exception e){
		}
		
        return map;
    }
}
