package com.xiaodongbaobao.novel.mapper;

import com.xiaodongbaobao.novel.entity.NNovelContent;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-03-15
 */
@Mapper
public interface NNovelContentMapper extends BaseMapper<NNovelContent> {
	
	 @Select("select c1.id,c1.title,c2.id up,c3.id down,b.id bookId,b.bookName,type.id typeId,type.typeName,b.author from n_novel_content c1\n" +
	            "LEFT JOIN n_novel_content c2 on c1.sort=c2.sort+1 and c1.bookId=c2.bookId\n" +
	            "LEFT JOIN n_novel_content c3 on c1.sort=c3.sort-1 and c1.bookId=c3.bookId\n" +
	            "LEFT JOIN n_novel_book b on c1.bookId=b.id\n" +
	            "LEFT JOIN n_novel_type type on b.type=type.id\n" +
	            "where \n" +
	            "c1.id=#{content.id}\n" +
	            "and\n" +
	            "c1.bookId =#{content.bookId}\n" +
	            "and\n" +
	            "c1.sort =#{content.sort}\n")
	    ArrayList<Map<String, Object>> selectByContent(@Param("content") NNovelContent content);
}
