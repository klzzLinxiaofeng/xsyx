package com.xunyunedu.mergin.util;

public class Order {
    private String field;
    private Boolean asc;
    private static final long serialVersionUID = -1694070611556906167L;

    public Order() {
    }

    public Order(String field, Boolean asc) {
        this.field = field;
        this.asc = asc;
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Boolean getAsc() {
        return this.asc;
    }

    public void setAsc(Boolean asc) {
        this.asc = asc;
    }
}
