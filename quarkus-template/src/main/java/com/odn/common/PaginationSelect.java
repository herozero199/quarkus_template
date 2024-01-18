package com.odn.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationSelect<T> {
    private long recordCount;
    private T records;

    public PaginationSelect() {
        this.recordCount = 0;
        this.records = null;
    }

    public PaginationSelect(long recordCount, T records) {
        this.recordCount = recordCount;
        this.records = records;
    }
}
