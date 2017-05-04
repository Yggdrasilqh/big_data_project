package com.yggdrasil.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by yggdrasil on 2017/5/3.
 */
public class RandomList {
    private List<Student> stuList;
    private List<Website> webList;
    private Date startTime;
    private Date endTime;

    public List<Student> getStuList() {
        return stuList;
    }

    public void setStuList(List<Student> stuList) {
        this.stuList = stuList;
    }

    public List<Website> getWebList() {
        return webList;
    }

    public void setWebList(List<Website> webList) {
        this.webList = webList;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
