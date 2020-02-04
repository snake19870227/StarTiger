package com.snake19870227.stiger.cloud.base.stream;

import cn.hutool.core.util.StrUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Bu HuaYang
 */
public class AckPlayload {

    private String thatName;
    private String[] thatProfiles;

    private String helloDatetime;
    private String ackDatetime;

    public AckPlayload() {
        this.ackDatetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS"));
    }

    public AckPlayload(String thatName, String[] thatProfiles, String helloDatetime) {
        this.thatName = thatName;
        this.thatProfiles = thatProfiles;
        this.helloDatetime = helloDatetime;
    }

    public String getThatName() {
        return thatName;
    }

    public void setThatName(String thatName) {
        this.thatName = thatName;
    }

    public String[] getThatProfiles() {
        return thatProfiles;
    }

    public void setThatProfiles(String[] thatProfiles) {
        this.thatProfiles = thatProfiles;
    }

    public String getHelloDatetime() {
        return helloDatetime;
    }

    public void setHelloDatetime(String helloDatetime) {
        this.helloDatetime = helloDatetime;
    }

    public String getAckDatetime() {
        return ackDatetime;
    }

    public void setAckDatetime(String ackDatetime) {
        if (StrUtil.isBlank(this.ackDatetime)) {
            this.ackDatetime = ackDatetime;
        }
    }
}
