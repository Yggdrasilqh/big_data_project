package com.yggdrasil.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by yggdrasil on 2017/3/23.
 */
@Entity(name = "websites")
public class Website {
    @Id
    private String url;
    @Column
    private String wname;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }
}
