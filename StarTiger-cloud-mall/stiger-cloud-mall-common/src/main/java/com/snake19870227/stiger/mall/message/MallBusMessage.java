package com.snake19870227.stiger.mall.message;

/**
 * @author Bu HuaYang
 */
public class MallBusMessage {

    private String sourceService;

    private String datetime;

    private String type;

    private String bizInfo;

    @Override
    public String toString() {
        return "Bus Message [" + sourceService + "][" + datetime + "][" + type + "] ==>> '" + bizInfo + "'";
    }

    public String getSourceService() {
        return sourceService;
    }

    public void setSourceService(String sourceService) {
        this.sourceService = sourceService;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBizInfo() {
        return bizInfo;
    }

    public void setBizInfo(String bizInfo) {
        this.bizInfo = bizInfo;
    }
}
