package com.shestnut.user.internal.common.utils;



import java.util.HashMap;
import java.util.Map;

/**
 * 平台线程共享类
 */
public class ThreadLocalUtil extends ThreadLocal<Map<String,Object>>{

    @Override
    protected Map<String, Object> initialValue() {
        return new HashMap<String, Object>(15);
    }

    private static final ThreadLocalUtil instance = new ThreadLocalUtil();

    private ThreadLocalUtil() {
    }

    public static ThreadLocalUtil getInstance() {
        return instance;
    }

    public void remove() {
        this.get().clear();
    }

    public Object get(String key) {
        return this.get().get(key);
    }

    public void set(String key, Object value) {
        this.get().put(key, value);
    }

    public Map<String, Object> getAll() {
        return this.get();
    }

    public void set(Map<String, Object> map) {
        this.get().putAll(map);
    }

}
