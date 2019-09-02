package cn.hsiangsun.result;

import org.springframework.stereotype.Component;

@Component
public class QueryResponse {
    private Integer status;
    private String msg;

    public QueryResponse() {
    }

    public QueryResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public QueryResponse OK(){
        return new QueryResponse(200,"ok");
    }

    public QueryResponse FAIL(String msg){
        if (msg == null){
            return new QueryResponse(400,"operate fail");
        }else {
            return new QueryResponse(400,msg);
        }
    }
}
