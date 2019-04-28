package com.xiaodongbaobao.novel.service.impl;

import com.xiaodongbaobao.novel.entity.NNovelContent;
import com.xiaodongbaobao.novel.mapper.NNovelContentMapper;
import com.xiaodongbaobao.novel.service.INNovelContentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-03-15
 */
@Service
public class NNovelContentServiceImpl extends ServiceImpl<NNovelContentMapper, NNovelContent> implements INNovelContentService {
	@Autowired
	NNovelContentMapper nNovelContentMapper;
	@Override
	public ArrayList<Map<String, Object>> selectByContent(NNovelContent content) {
		// TODO Auto-generated method stub
		return nNovelContentMapper.selectByContent(content);
	}

}
