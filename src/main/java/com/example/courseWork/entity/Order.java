package com.example.courseWork.entity;

import java.util.List;

public class Order {
    private String name;
    private String last;
    private long number;
    private String address;
    private String comment;
    private String email_to;
    private List<Integer> order_goods;

    public Order() {}

    public Order(String email_to, List<Integer> order_goods) {
        this.email_to = email_to;
        this.order_goods = order_goods;
    }

    public Order(String name, String last, int number, String address, String comment, String email_to, List<Integer> order_goods) {
        this.name = name;
        this.last = last;
        this.number = number;
        this.address = address;
        this.comment = comment;
        this.email_to = email_to;
        this.order_goods = order_goods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEmail_to() {
        return email_to;
    }

    public void setEmail_to(String email_to) {
        this.email_to = email_to;
    }

    public List<Integer> getOrder_goods() {
        return order_goods;
    }

    public void setOrder_goods(List<Integer> order_goods) {
        this.order_goods = order_goods;
    }
}
