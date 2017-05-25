package com.yggdrasil.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by yggdrasil on 2017/5/25.
 */
@Entity(name = "records")
public class Record {
    @Id
    private String data_name;
    private int data_num;
    @Column(insertable = false)
    private Date time;
    private int status;

    public Record(String data_name, int data_num, Date time, int status) {
        this.data_name = data_name;
        this.data_num = data_num;
        this.time = time;
        this.status = status;
    }

    public Record() {
    }

    public String getData_name() {
        return data_name;
    }

    public void setData_name(String data_name) {
        this.data_name = data_name;
    }

    public int getData_num() {
        return data_num;
    }

    public void setData_num(int data_num) {
        this.data_num = data_num;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
