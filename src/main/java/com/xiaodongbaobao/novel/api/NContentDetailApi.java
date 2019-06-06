package com.xiaodongbaobao.novel.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.baidu.aip.nlp.AipNlp;
import com.wenwenmao.core.entity.CApiBase;
import com.wenwenmao.core.exception.ApiProcessException;
import com.wenwenmao.core.service.IApiProcessService;
import com.wenwenmao.core.service.ParamContextHolder;
import com.xiaodongbaobao.novel.bean.CosProcess;
import com.xiaodongbaobao.novel.entity.NNovelContent;
import com.xiaodongbaobao.novel.service.INNovelContentService;
import com.xiaodongbaobao.novel.utils.FanYiUtil;
import com.xiaodongbaobao.novel.utils.RedisUtil;

@Service("nContentDetailApi")
public class NContentDetailApi implements IApiProcessService {
	@Autowired
	private INNovelContentService iNNovelContentService;
	@Autowired
	private CosProcess cosProcess;
	@Autowired
	private AipNlp client;
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public Map<String, Object> apiProcess(ParamContextHolder paramContextHolder, CApiBase cApiBase)
			throws ApiProcessException {
		String id = paramContextHolder.getReq().getParameter("id");
		String language = paramContextHolder.getReq().getParameter("language");
		String str = null;
		NNovelContent content = iNNovelContentService.selectById(id);
		if (content == null)
			throw new ApiProcessException("C0001", "没有该书籍");
		Map<String, Object> map = new HashMap<>();
		ArrayList<Map<String, Object>> list = iNNovelContentService.selectByContent(content);
		Map<String, Object> result = (Map)redisUtil.hmget(id + "_" + language);
		if (result.size() == 0) {
			result = list.get(0);
			str = cosProcess.getContent(content.getBookId() + "/" + content.getId());
			str = str.replaceAll("< br >|</br>|<br>", "");

			if (language.equals("zy")) {
				int maxSummaryLen = 300;
				HashMap<String, Object> options = new HashMap<String, Object>();
				JSONObject res = client.newsSummary(str, maxSummaryLen, options);
				try {
					result.put("content", "&emsp;&emsp;" + res.get("summary"));
					map.put("result", result);
				} catch (Exception e) {
					throw new ApiProcessException("c0001", "内容摘要获取异常");
				}
				return map;
			}
			str = str.replaceAll("      ", "<br>");
			if (!language.equals("zh")) {
				str = FanYiUtil.translate(str, language);
			}
			str = str.replaceAll("< br >|</br>|<br>", "<br>&emsp;&emsp;");
			result.put("content", str);
			redisUtil.hmset(id + "_" + language, result);
		}

		map.put("result", result);
		return map;
	}
}
