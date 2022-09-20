package com.shestnut.user.internal.common.utils;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * 校验工具类
 */
public class ValidUtils {

    /**
     * Integer是否有效(非空且大于0)
     *
     * @param i
     */
    public static boolean isValid(Integer i) {
        return i != null && i > 0;
    }

    /**
     * Long是否有效(非空且大于0)
     *
     * @param i
     */
    public static boolean isValid(Long i) {
        return i != null && i > 0;
    }


    /**
     * Integer是否无效(为空或小于等于0)
     *
     * @param i
     */
    public static boolean isInvalid(Integer i) {
        return !isValid(i);
    }


    /**
     * 验证是否错误的Integer ，如果为空或小于0则视为无效
     *
     * @param intAry
     * @return
     */
    public static boolean isInvalid(Integer... intAry) {
        if (intAry == null || intAry.length == 0) {
            return false;
        }
        for (int i = 0; i < intAry.length; i++) {
            if (intAry[i] == null || intAry[i] <= 0)
                return true;
        }
        return false;
    }

    /**
     * 验证是否错误的Long ，如果为空或小于0则视为无效
     *
     * @param intAry
     * @return
     */
    public static boolean isInvalid(Long... intAry) {
        if (intAry == null || intAry.length == 0) {
            return false;
        }
        for (int i = 0; i < intAry.length; i++) {
            if (intAry[i] == null || intAry[i] <= 0)
                return true;
        }
        return false;
    }

    /**
     * 验证集合是否为空
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 验证集合是否非空
     *
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断字符串是否为空
     *
     * @param strArray
     * @return
     */
    public static boolean isBlank(String... strArray) {
        if (strArray == null || strArray.length == 0) {
            return false;
        }
        for (String str : strArray) {
            if (StringUtil.isBlank(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 校验 Map 是否为空
     *
     * @param map 待校验的Map
     * @return
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    /**
     * 检验 Map是不为空
     *
     * @param map 待校验的Map
     * @return
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    private static final String mailRegex = "^[a-zA-Z0-9][A-Za-z0-9!#$%&'*+/=?^_`{|}~-]*(?:\\.[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+([A-Za-z](?:[A-Za-z0-9-]*[A-Za-z0-9])?|[0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?[A-Za-z](?:[A-Za-z0-9-]*[A-Za-z0-9])?)$";
    private static final Pattern mailpattern = Pattern.compile(mailRegex);

    /**
     * 是否为有效邮箱
     *
     * @param mail
     * @return
     */
    public static boolean isValidEmail(String mail) {
        Matcher matcher = mailpattern.matcher(mail);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

    public static boolean check(Boolean bool) {
        return bool != null && bool.booleanValue();
    }
}
