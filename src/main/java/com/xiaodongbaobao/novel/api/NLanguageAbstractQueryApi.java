package com.xiaodongbaobao.novel.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.aip.nlp.AipNlp;
import com.wenwenmao.core.entity.CApiBase;
import com.wenwenmao.core.exception.ApiProcessException;
import com.wenwenmao.core.service.IApiProcessService;
import com.wenwenmao.core.service.ParamContextHolder;

@Service("nLanguageAbstractQueryApi")
public class NLanguageAbstractQueryApi implements IApiProcessService{

	@Autowired
	public AipNlp client;
	@Override
	public Map<String, Object> apiProcess(ParamContextHolder paramContextHolder, CApiBase arg1) throws ApiProcessException {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		String content = paramContextHolder.getReq().getParameter("content");
		int maxSummaryLen = Integer.valueOf(paramContextHolder.getReq().getParameter("len"));
		String title = paramContextHolder.getReq().getParameter("title");
		HashMap<String, Object> options = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(title)){
			options.put("title", title);
		}
		System.out.println(content);
		System.out.println(maxSummaryLen);
		JSONObject res = client.newsSummary(content, maxSummaryLen, options);
		System.out.println(res);
		try{
		map.put("content", res.get("summary"));
		}catch(Exception e){
			throw new ApiProcessException("c0001","内容摘要获取异常");
		}
		return map;
	}

}
