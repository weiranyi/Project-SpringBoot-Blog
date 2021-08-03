package hello.entity;

import java.util.List;

public class BlogResult extends Result<List<Blog>> {
    private int total;
    private int page;
    private int totalPage;

    public static BlogResult newResults(List<Blog> data, int total, int page, int totalPage) {
        return new BlogResult("ok","获取成功",data, total, page, totalPage);
    }
    public static BlogResult failure(String msg){
        return new BlogResult("fail",msg,null,0,0,0);
    }

    protected BlogResult(String status,String msg,List<Blog> data, int total, int page, int totalPage) {
        super(status, msg, data);
        this.total = total;
        this.page = page;
        this.totalPage = totalPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
