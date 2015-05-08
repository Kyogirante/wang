package com.example.wang.myapplication.Bean;

import com.example.wang.myapplication.Utils.Bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by haha201 on 2015/5/4.
 */
public class ConsumeBean extends Bean implements Serializable{


    private double cost;
    private String category;
    private String describe_msg;
    private Date time;

    public ConsumeBean(){

    }

    public ConsumeBean(double cost, String category, String describe_msg, Date time) {
        this.cost = cost;
        this.category = category;
        this.describe_msg = describe_msg;
        this.time = time;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDescribe_msg() {
        return describe_msg;
    }

    public void setDescribe_msg(String describe_msg) {
        this.describe_msg = describe_msg;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ConsumeBean{" +
                "cost=" + cost +
                ", category='" + category + '\'' +
                ", describe_msg='" + describe_msg + '\'' +
                ", time=" + time +
                '}';
    }

}
