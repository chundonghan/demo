/*
 * Copyright  辽宁爱伍佰  All rights reserved.
 *
 */
package com.demo.common.util;
import com.demo.entity.PageData;

/**
 * 
 * @see 分页工具类
 * @time 2016年9月30日 上午11:16:34
 * @author han
 *
 */
public class PageUtil {
    private static final int DEFAULT_PAGE_SIZE = 10; // 默认页面大小
    private int currentPage = 0;// 当前页
    private int rowCount = 0; // 总行数
    private int pageSize = 0; // 页大小
    private int pageCount = 0; // 总页数
    private int beginPosition = 0;// 页起始
    private int endPosition = 0;// 页终止
    private boolean hasNextPage = false;// 是否有下一页
    private boolean hasPreviousPage = false;// 是否上一页
 
    public PageUtil(int rowCount, String pageSize,
            String currentPage) {
        this.rowCount = rowCount;
 
        // 页面大小不能小与等于0, 否则设置为默认页面大小
        this.pageSize = Integer.parseInt(pageSize);
 
        if (this.pageSize <= 0) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        }
        // 计算出总页数
        this.getPageCount();
 
        // 当前页处理
        if (currentPage == null || currentPage.equals("")) {
            this.currentPage = 1;
        } else {
            try {
                this.currentPage = Integer.parseInt(currentPage);
            } catch (NumberFormatException nfe) {
                this.currentPage = 1;
            }
        }
        // 当前页范围处理
        this.getCurrentPage();
        // 页起始处理
        this.getBeginPosition();
        // 页结束处理
        this.getEndPosition();
    }
 
    public int getCurrentPage() {
        if (currentPage >= pageCount) {
            currentPage = pageCount;
        } else if (currentPage <= 1) {
            currentPage = 1;
        }
        return currentPage;
    }
 
    public int getBeginPosition() {
        // mysql计算出页起始
        beginPosition = (currentPage - 1) * pageSize;
        //+1
        return beginPosition;
    }
 
    public int getEndPosition() {
        // 计算出页终止
        if (currentPage >= pageCount) {
            endPosition = rowCount;
        } else {
            endPosition = currentPage * pageSize;
        }
        return endPosition;
    }
 
    public int getRowCount() {
        return rowCount;
    }
 
    public int getPageSize() {
        return pageSize;
    }
 
    public int getPageCount() {
        // 计算出总页数
        pageCount = (rowCount + pageSize - 1) / pageSize;
        return pageCount;
    }
 
    public boolean isHasNextPage() {
        // 计算出是否有下一页
        if (currentPage >= pageCount) {
            hasNextPage = false;
        } else {
            hasNextPage = true;
        }
        return hasNextPage;
    }
 
    public boolean isHasPreviousPage() {
        // 计算出是否有上一页
        if (currentPage <= 1) {
            hasPreviousPage = false;
        } else {
            hasPreviousPage = true;
        }
        return hasPreviousPage;
    }
 
    public int getPreviousPage() {
        if (this.isHasPreviousPage()) {
            return currentPage - 1;
        }
        return currentPage;
    }
 
    public int getNextPage() {
        if (this.isHasNextPage()) {
            return currentPage + 1;
        } else {
            return currentPage;
        }
    }
    private PageData pd = new PageData();

	public PageData getPd() {
		return pd;
	}

	public void setPd(PageData pd) {
		this.pd = pd;
	}
    
    
}