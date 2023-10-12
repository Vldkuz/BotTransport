package Develop.ReqBuilder;

import java.util.List;

public class ReqBuilder {

    public ReqBuilder(String curl) {
        if (curl.length() == 0) {
            throw new RuntimeException("Указан пустой URL для запроса");
        }
        url = curl;
    }

    public void setBranch(String cBranch) {
        branch = cBranch;
        request = url + branch;
    }

    public String addParams(List<String> params) {
        for (int idx = 0; idx < params.size(); ++idx) {
            request += "&" + params.get(idx);
        }
        return request;
    }

    public String getRequest() {
        return request;
    }


    private String url;
    private String branch;
    private String request;
}
