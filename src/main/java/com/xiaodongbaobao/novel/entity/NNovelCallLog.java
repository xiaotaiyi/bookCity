package com.xiaodongbaobao.novel.entity;

import java.util.Date;
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
@TableName("n_novel_call_log")
public class NNovelCallLog extends Model<NNovelCallLog> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    /**
     * ip地址
     */
    private String ip;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 访问次数 
     */
    private Integer num;
    /**
     * 最后访问时间
     */
	private Date lastTime;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}


    @Override
    public String toString() {
        return "NNovelCallLog{" +
        ", id=" + id +
        ", ip=" + ip +
        ", createTime=" + createTime +
        "}";
    }
}
