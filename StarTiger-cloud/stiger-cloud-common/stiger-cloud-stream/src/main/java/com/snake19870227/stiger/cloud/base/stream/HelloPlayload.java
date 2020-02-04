package com.snake19870227.stiger.cloud.base.stream;

/**
 * @author Bu HuaYang
 */
public class HelloPlayload {

    private String thisName;
    private String[] thisProfiles;

    private String datetime;

    public HelloPlayload() {
    }

    public HelloPlayload(String thisName, String[] thisProfiles, String datetime) {
        this.thisName = thisName;
        this.thisProfiles = thisProfiles;
        this.datetime = datetime;
    }

    public String getThisName() {
        return thisName;
    }

    public void setThisName(String thisName) {
        this.thisName = thisName;
    }

    public String[] getThisProfiles() {
        return thisProfiles;
    }

    public void setThisProfiles(String[] thisProfiles) {
        this.thisProfiles = thisProfiles;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
