package Develop.API;

import java.util.ArrayList;
import java.util.List;

public class ReqBuilder {

    public ReqBuilder(String curl) {
        if (curl.length() == 0) {
            throw new RuntimeException("Указан пустой URL для запроса");
        }
        url = new String(curl);
    }

    public void setBranch(String cBranch) {
        branch = cBranch;
        request = url + branch;
    }

    public String addParams(ParamBuilder param) {

        ArrayList<String> params = new ArrayList<String>();

        if (!param.to.isEmpty()) {
            params.add("to=" + param.to);
        }
        if (!param.from.isEmpty()){
            params.add("from=" + param.from);
        }
        if (!param.date.isEmpty()){
            params.add("date=" + param.date);
        }
        if (!param.transportType.isEmpty()) {
            params.add("transport_types=" + param.transportType);
        }
        if (!param.limit.isEmpty()) {
            params.add("limit=" + param.limit);
        }
        if (!param.resultTimezone.isEmpty()) {
            params.add("result_timezone=" + param.resultTimezone);
        }
        if (!param.withTransfers.isEmpty()){
            params.add("transfers=" + param.withTransfers);
        }
        if (!param.station.isEmpty()) {
            params.add("station=" + param.station);
        }
        if (!param.direction.isEmpty()){
            params.add("direction=" + param.direction);
        }
        if (!param.event.isEmpty()){
            params.add("event=" + param.event);
        }
        if (!param.uid.isEmpty()){
            params.add("uid=" + param.uid);
        }
        if (!param.latitude.isEmpty()){
            params.add("lat=" + param.latitude);
        }
        if (!param.longtitude.isEmpty()) {
            params.add("lng=" + param.longtitude);
        }
        if (!param.distance.isEmpty()){
            params.add("distance=" + param.distance);
        }
        if (!param.code.isEmpty()){
            params.add("code=" + param.code);
        }
        if (!param.stationType.isEmpty()){
            params.add("station_types=" + param.stationType);
        }

        request += params.get(0); // Поскольку первый должен идти без &
        for (int idx = 1; idx < params.size(); ++idx) {
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
