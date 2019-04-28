package com.xiaodongbaobao.novel.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wenwenmao.core.entity.CApiBase;
import com.wenwenmao.core.exception.ApiProcessException;
import com.wenwenmao.core.service.IApiProcessService;
import com.wenwenmao.core.service.ParamContextHolder;
import com.wenwenmao.core.utils.BaseValueProcessUtil;
import com.xiaodongbaobao.novel.entity.NNovelCallLog;
import com.xiaodongbaobao.novel.service.INNovelCallLogService;

@Service("nCallLogInsertApi")
public class NCallLogInsertApi implements IApiProcessService {
	@Autowired
	private INNovelCallLogService iNNovelCallLogService;
	@Override
	public Map<String, Object> apiProcess(ParamContextHolder paramContextHolder, CApiBase arg1) throws ApiProcessException {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		String ip = paramContextHolder.getClientIp();
		EntityWrapper<NNovelCallLog> wrapper = new EntityWrapper<>();
		wrapper.eq("ip", ip);
		List<String> listOrder = new ArrayList<>();
		listOrder.add("createTime");
		wrapper.orderDesc(listOrder);
		List<NNovelCallLog> list = iNNovelCallLogService.selectList(wrapper);
		Date now  = new Date();
		if(list.size() > 0){ 
			NNovelCallLog lastLog = list.get(0);
			if(DateUtils.isSameDay(now, lastLog.getCreateTime())){
				if(now.getTime()-lastLog.getLastTime().getTime()<(60*1000)){//判断ip 60秒内不重复插入
					return map;
				}
				lastLog.setNum(lastLog.getNum()+1);
				lastLog.setLastTime(now);
				lastLog.updateById();
				map.put("ip", lastLog.getIp());
				return map;
			}
			
		}
		NNovelCallLog log = new NNovelCallLog();
		log.setId(BaseValueProcessUtil.uuid(null));
		log.setIp(ip);
		log.setCreateTime(new Date());
		log.setLastTime(now);
		log.setNum(1);
		log.insert();
		map.put("ip", ip);
		return map;
	}

}
