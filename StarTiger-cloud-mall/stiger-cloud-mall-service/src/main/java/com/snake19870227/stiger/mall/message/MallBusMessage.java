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

    public MallBusMessage setSourceService(String sourceService) {
        this.sourceService = sourceService;
        return this;
    }

    public String getDatetime() {
        return datetime;
    }

    public MallBusMessage setDatetime(String datetime) {
        this.datetime = datetime;
        return this;
    }

    public String getType() {
        return type;
    }

    public MallBusMessage setType(String type) {
        this.type = type;
        return this;
    }

    public String getBizInfo() {
        return bizInfo;
    }

    public MallBusMessage setBizInfo(String bizInfo) {
        this.bizInfo = bizInfo;
        return this;
    }
}
