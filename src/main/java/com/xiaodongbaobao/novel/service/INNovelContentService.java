package com.xiaodongbaobao.novel.service;

import com.xiaodongbaobao.novel.entity.NNovelContent;

import java.util.ArrayList;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-03-15
 */
public interface INNovelContentService extends IService<NNovelContent> {
	 ArrayList<Map<String, Object>> selectByContent(NNovelContent content);
}
