package com.xiaodongbaobao.novel.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2019-04-17
 */
@TableName("n_novel_content_translate")
public class NNovelContentTranslate extends Model<NNovelContentTranslate> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    /**
     * 内容id
     */
    private String contentId;
    /**
     * 语言种类
     */
    private String to;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 内容地址
     */
    private String contentUrl;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "NNovelContentTranslate{" +
        ", id=" + id +
        ", contentId=" + contentId +
        ", to=" + to +
        ", createTime=" + createTime +
        ", contentUrl=" + contentUrl +
        "}";
    }
}
