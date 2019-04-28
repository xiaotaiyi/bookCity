package com.xiaodongbaobao.novel.api;

import java.util.Calendar;
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
import com.xiaodongbaobao.novel.entity.NNovelTop;
import com.xiaodongbaobao.novel.service.INNovelBookService;
import com.xiaodongbaobao.novel.service.INNovelTopService;
@Service("nTopAddApi")
public class NTopAddApi implements IApiProcessService{
	@Autowired
	INNovelTopService INNovelTopService;
	@Autowired
	INNovelBookService iNNovelBookService;
	@Override
	public Map<String, Object> apiProcess(ParamContextHolder paramContextHolder, CApiBase cApiBase) throws ApiProcessException {
		String id = paramContextHolder.getReq().getParameter("id");
		//判断是否有这本书
		if(iNNovelBookService.selectById(id) == null){	
			throw new ApiProcessException("C0001", "没有该书籍");
		}
		Map<String, Object> map = new HashMap<>();
		EntityWrapper<NNovelTop> wrapper = new EntityWrapper<>();
		wrapper.eq("bookId",id);
		List<NNovelTop> list = INNovelTopService.selectList(wrapper);
		if(list.size() > 0){
			NNovelTop top = list.get(0);
			//日点击量
			if(DateUtils.isSameDay(top.getDay(), new Date())){
				top.setDaySum(top.getDaySum()+1);
			}else{
				top.setDay(new Date());
				top.setDaySum(1);
			}
			//月点击量
			if(DateUtils.truncatedEquals(top.getMonth(), new Date(), Calendar.MONTH)){
				top.setMonthSum(top.getMonthSum()+1);
			}else{
				top.setMonth(new Date());
				top.setMonthSum(1);
			}
			//总点击量
			top.setCountSum(top.getCountSum()+1);
			INNovelTopService.updateById(top);
		}else{
			NNovelTop top = new NNovelTop();
			top.setId(BaseValueProcessUtil.uuid(null));
			top.setBookId(id);
			top.setDay(new Date());
			top.setDaySum(1);
			top.setMonth(new Date());
			top.setMonthSum(1);
			top.setCountSum(1);
			INNovelTopService.insert(top);
		}
		return map;
	}

}
