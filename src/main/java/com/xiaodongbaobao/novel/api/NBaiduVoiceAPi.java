package com.xiaodongbaobao.novel.api;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.xiaodongbaobao.novel.entity.NNovelContent;
import com.xiaodongbaobao.novel.service.INNovelContentService;
@Service("nBaiduVoiceApi")
public class NBaiduVoiceAPi implements IApiProcessService{

	@Autowired
	private AipSpeech client;
	@Autowired
	private CosProcess cosProcess;
	private static String folder = "voice/";
	private static String path = "https://novel.xiaodongbaobao.cn/";
	@Autowired
	private INNovelContentService iNNovelContentService;
	@Override
	public Map<String, Object> apiProcess(ParamContextHolder paramContextHolder, CApiBase arg1) throws ApiProcessException {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		String id = paramContextHolder.getReq().getParameter("id");
		if(!cosProcess.checkFileExist(folder+id+"_zh.mp3")){
			NNovelContent content = iNNovelContentService.selectById(id);
			if(content == null){
				throw new ApiProcessException("C0001","语音合成异常");
			}
			String str = cosProcess.getContent(content.getBookId()+"/"+content.getId());
			str = str.replaceAll("</br>", "");
			List<String> list = new ArrayList<>();
			str = str.trim();
			while(str.length()>0){
				if(str.length() > 2000){
					int index = str.indexOf("。", 2000);
					if(index == -1){
						index = str.indexOf(".", 2000);
					}
					if(index == -1){
						index = str.indexOf(",", 2000);
					}
					if(index == -1){
						index = str.indexOf("，", 2000);
					}
					list.add(str.substring(0, index));
					str = str.substring(index);
				}else{
					list.add(str);
					break;
				}
			}
			byte[] data = {};
			for(String s : list){
				TtsResponse res = client.synthesis(s,"zh",1,null);
				System.out.println(res.getResult());
				if(!(res.getResult()==null)) throw new ApiProcessException("C0001","语音合成异常");
				data = ArrayUtils.addAll(data,res.getData());
			}
			try {
				cosProcess.createMP3(folder+content.getId()+"_zh.mp3", new ByteArrayInputStream(data));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new ApiProcessException("C0001","语音合成异常");
			}
		}
		map.put("url",path+folder+id+"_zh.mp3");
		return map;
	}

}
