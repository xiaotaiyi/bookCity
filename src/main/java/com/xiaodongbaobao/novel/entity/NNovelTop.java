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
 * @since 2019-03-15
 */
@TableName("n_novel_top")
public class NNovelTop extends Model<NNovelTop> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    /**
     * 书籍id
     */
    private String bookId;
    /**
     * 日期
     */
    private Date day;
    /**
     * 日点击数
     */
    private Integer daySum;
    /**
     * 月
     */
    private Date month;
    /**
     * 月点击数
     */
    private Integer monthSum;
    /**
     * 总点击数
     */
    private Integer countSum;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Integer getDaySum() {
        return daySum;
    }

    public void setDaySum(Integer daySum) {
        this.daySum = daySum;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    public Integer getMonthSum() {
        return monthSum;
    }

    public void setMonthSum(Integer monthSum) {
        this.monthSum = monthSum;
    }

    public Integer getCountSum() {
        return countSum;
    }

    public void setCountSum(Integer countSum) {
        this.countSum = countSum;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "NNovelTop{" +
        ", id=" + id +
        ", bookId=" + bookId +
        ", day=" + day +
        ", daySum=" + daySum +
        ", month=" + month +
        ", monthSum=" + monthSum +
        ", countSum=" + countSum +
        "}";
    }
}
