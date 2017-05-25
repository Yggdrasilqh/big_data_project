package com.yggdrasil.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.codehaus.jackson.annotate.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yggdrasil on 2017/5/3.
 */

public class RandomList {
    @JsonProperty
    private List<String> stuList;
    @JsonProperty
    private List<String> webList;
    @JsonProperty
    private Date startTime;
    @JsonProperty
    private Date endTime;
    @JsonProperty
    private int length;

    public List<String> getStuList() {
        return stuList;
    }

    public void setStuList(List<String> stuList) {
        this.stuList = stuList;
    }

    public List<String> getWebList() {
        return webList;
    }

    public void setWebList(List<String> webList) {
        this.webList = webList;
    }

    public Date getStartTime() {
        return startTime;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setStartTime(String startTime) throws Exception{
        startTime = startTime.replaceAll("T"," ");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        this.startTime = simpleDateFormat.parse(startTime);
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) throws Exception{
        endTime = endTime.replaceAll("T"," ");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        this.endTime = simpleDateFormat.parse(endTime);
    }
}
