package com.shestnut.user.internal.common.dao;


import com.shestnut.user.internal.common.dao.entity.IEntity;
import com.shestnut.user.internal.common.dao.sql.SqlBean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class IEntityDao<T extends IEntity> {

    public static String dbName = null;

    public static String tableName = null;

    public static SqlBean buildSqlTest(IEntity entity){
        SqlBean bean = new SqlBean();
        StringBuffer stringBuffer = new StringBuffer();
        List<Object> objects = new ArrayList<>();
        Class<?> clazz = entity.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if(field.get(entity) == null || field.getName().equals("id")){
                    continue;
                }else {
                    if(stringBuffer.length() == 0){
                        stringBuffer.append(field.getName() + "= ?");
                    }else {
                        stringBuffer.append("," + field.getName() + "= ?");
                    }

                    objects.add(field.get(entity));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        bean.setSql(stringBuffer.toString());
        bean.setArrgs(objects);
        return bean;
    }

    public static SqlBean buildSqlBean(IEntity entity){
        SqlBean bean = new SqlBean();
        StringBuilder stringBuffer = new StringBuilder();
        List<Object> objects = new ArrayList<>();
        Class<?> clazz = entity.getClass();
        for (EntityInfo entityInfo : clazz.getAnnotationsByType(EntityInfo.class)) {
            dbName = entityInfo.dbName();
            tableName = entityInfo.tableName();
        }
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(entity) != null) {
                    if(!field.getName().equals("id")){
                        if(stringBuffer.length() == 0){
                            stringBuffer.append(field.getName()).append("= ?");
                        }else {
                            stringBuffer.append(",").append(field.getName()).append("= ?");
                        }
                        objects.add(field.get(entity));
                    }else {
                        bean.setPk((Long) field.get(entity));
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        bean.setDbName(dbName);
        bean.setTableName(tableName);
        bean.setSql(stringBuffer.toString());
        bean.setArrgs(objects);
        return bean;
    }

}
