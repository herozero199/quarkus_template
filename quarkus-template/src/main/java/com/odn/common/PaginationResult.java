package com.odn.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationResult<T> {
    private long recordCount;
    private int pageCount;
    private int currentPage;
    private int pageSize;
    private T records;

    public PaginationResult() {
        this.recordCount = 0;
        this.pageCount = 0;
        this.currentPage = 1;
        this.pageSize = 10;
        this.records = null;
    }

    public PaginationResult(long recordCount, int currentPage, int pageSize, T records) {
        double pageCount = (double)recordCount / (double)pageSize;
        int _pageCount = (int)Math.ceil(pageCount);

        this.recordCount = recordCount;
        this.pageCount = _pageCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.records = records;
    }
}
