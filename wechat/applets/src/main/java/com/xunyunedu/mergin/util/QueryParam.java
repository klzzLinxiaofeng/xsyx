package com.xunyunedu.mergin.util;

public class QueryParam {
    private String relation;
    private String field;
    private Object value;

    public QueryParam(String relation, String field, Object value) {
        this.relation = relation;
        this.field = field;
        this.value = value;
    }

    public QueryParam() {
    }

    public String getRelation() {
        return this.relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
