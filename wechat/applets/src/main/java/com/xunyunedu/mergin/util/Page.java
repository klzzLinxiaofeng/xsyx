package com.xunyunedu.mergin.util;

public class Page {
    private int pageSize = 10;
    private int currentPage = 1;
    private int totalPages = 0;
    private int totalRows = 0;
    private int pageStartRow = 0;
    private int pageEndRow = 0;
    private boolean next = false;
    private boolean previous = false;

    public Page(int rows, int pageSize) {
        this.init(rows, pageSize);
    }

    public Page() {
    }

    public void init(int rows, int pageSize) {
        this.pageSize = pageSize;
        this.totalRows = rows;
        if (this.totalRows % pageSize == 0) {
            this.totalPages = this.totalRows / pageSize;
        } else {
            this.totalPages = this.totalRows / pageSize + 1;
        }

    }

    public void init(int rows, int pageSize, int currentPage) {
        this.pageSize = pageSize;
        this.totalRows = rows;
        if (this.totalRows % pageSize == 0) {
            this.totalPages = this.totalRows / pageSize;
        } else {
            this.totalPages = this.totalRows / pageSize + 1;
        }

        if (currentPage != 0) {
            this.gotoPage(currentPage);
        }

    }

    private void calculatePage() {
        this.previous = this.currentPage - 1 > 0;
        this.next = this.currentPage < this.totalPages;
        if (this.currentPage * this.pageSize < this.totalRows) {
            this.pageEndRow = this.currentPage * this.pageSize;
            this.pageStartRow = this.pageEndRow - this.pageSize;
        } else {
            this.pageEndRow = this.totalRows;
            this.pageStartRow = this.pageSize * (this.totalPages - 1);
        }

    }

    public void gotoPage(int page) {
        this.currentPage = page;
        this.calculatePage();
    }

    public String debugString() {
        return "共有数据数:" + this.totalRows + "共有页数:" + this.totalPages + "当前页数为:" + this.currentPage + "是否有前一页:" + this.previous + "是否有下一页:" + this.next + "开始行数:" + this.pageStartRow + "终止行数:" + this.pageEndRow;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public boolean isNext() {
        return this.next;
    }

    public boolean isPrevious() {
        return this.previous;
    }

    public int getPageEndRow() {
        return this.pageEndRow;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getPageStartRow() {
        return this.pageStartRow;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public int getTotalRows() {
        return this.totalRows;
    }

    public void setTotalPages(int i) {
        this.totalPages = i;
    }

    public void setCurrentPage(int i) {
        this.currentPage = i;
    }

    public void setNext(boolean b) {
        this.next = b;
    }

    public void setPrevious(boolean b) {
        this.previous = b;
    }

    public void setPageEndRow(int i) {
        this.pageEndRow = i;
    }

    public void setPageSize(int i) {
        this.pageSize = i;
    }

    public void setPageStartRow(int i) {
        this.pageStartRow = i;
    }

    public void setTotalRows(int i) {
        this.totalRows = i;
    }
}

