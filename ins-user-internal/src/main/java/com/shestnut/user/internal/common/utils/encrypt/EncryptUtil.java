package com.shestnut.user.internal.common.utils.encrypt;


import com.shestnut.user.internal.common.dao.entity.IEntity;

/**
 * 数据库字段加密解密工具
 */
public class EncryptUtil {

    /**
     * 实体数据库名称
     *
     * @param clazz
     * @return
     */
    private static String key(Class<? extends IEntity> clazz) {
        //获取类名
        String className = clazz.getName();
        return key(className);
    }

    /**
     * 实体数据库名称
     *
     * @param className
     * @return
     */
    private static String key(String className) {
        return "des_" + className + "_key";
    }

    /**
     * 加密
     *
     * @param value
     * @param clazz
     * @return
     */
    public static String encrypt(String value, Class<? extends IEntity> clazz) {
        try {
            return encrypt(value,clazz.getName());
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 加密
     *
     * @param value
     * @param className
     * @return
     */
    public static String encrypt(String value,String className) {
        try {
            return DesPlus.getInstance().encryptMaybeException(value,
                    className);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 解密
     *
     * @param value
     * @param clazz
     * @return
     */
    public static String decrypt(String value,Class<? extends IEntity> clazz) {
        try {
            return decrypt(value,clazz.getName());
        } catch (Exception e) {
            return value;
        }
    }

    /**
     * 解密
     *
     * @param value
     * @param className
     * @return
     */
    public static String decrypt(String value,String className) {
        try {
            return DesPlus.getInstance().decryptMaybeException(value,className);
        } catch (Exception e) {
            return null;
        }
    }
}
