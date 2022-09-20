package com.shestnut.user.internal.common.dao.sql;

import java.util.List;

public class SqlBean {

    private Long pk;

    private String dbName;

    private String tableName;

    private String sql;

    private List<Object> arrgs;

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<Object> getArrgs() {
        return arrgs;
    }

    public void setArrgs(List<Object> arrgs) {
        this.arrgs = arrgs;
    }
}
