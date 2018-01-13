package cn.larry.search.controller;

/**
 * Created by fugz on 2016/6/15.
 */
public class Pager {
    int start;
    int end;
    int currentPage;
    int total;
    int pageSize = 20;
    int rows;
    String keyword;

    public int getStart() {
        return start;
    }

    public int getRows() {
        return rows;
    }

    public int getEnd() {
        return end;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotal() {
        return total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getKeyword() {
        return keyword;
    }

    public Pager(int total, int start, String keyword, int rows) {
        this.rows = rows;
        this.keyword = keyword;
        this.currentPage = start / pageSize;
        this.total = total;
        this.start = start;
        this.end = start + pageSize;
    }
}
