package com.shestnut.user.internal.common.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static boolean isBlank(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotBlank(String str) {
        if (str != null && !"".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    public static String trim(String str) {
        if (str == null) {
            return str;
        }
        str = str.trim();
        for (int i = 0; i < str.length(); i++) {
            int tmp = str.charAt(i);
            if (tmp == 9 || tmp == 13 || tmp == 10 || tmp == 32 || tmp == 12288) {
                continue;
            } else {
                str = str.substring(i);
                break;
            }
        }
        for (int i = str.length() - 1; i >= 0; i--) {
            int tmp = str.charAt(i);
            if (tmp == 9 || tmp == 13 || tmp == 10 || tmp == 32 || tmp == 12288) {
                continue;
            } else {
                str = str.substring(0, i + 1);
                break;
            }
        }
        return str;
    }

    public static String return2Br(String arg) {
        if (arg == null || "".equals(arg)) {
            return "";
        } else {
            arg = arg.replaceAll("\r\n", "<br/>");
            arg = arg.replaceAll("\n", "<br/>");
            return arg;
        }
    }

    public static String br2Return(String arg) {
        if (arg == null || "".equals(arg)) {
            return "";
        } else {
            arg = arg.replaceAll("<br>", "\r\n");
            arg = arg.replaceAll("<br/>", "\r\n");
            arg = arg.replaceAll("</br>", "\r\n");
            return arg;
        }
    }

    public static String whiteSpace2nbsp(String arg) {
        if (arg == null || "".equals(arg)) {
            return "";
        } else {
            arg = arg.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
            arg = arg.replaceAll(" ", "&nbsp;");
            return arg;
        }
    }

    public static String format(float d, int scale) {
        return format(new Double(d), scale);
    }

    public static String format(double d, int scale) {
        StringBuffer sb = new StringBuffer("0");
        if (scale > 0) {
            sb.append(".");
        }
        for (int i = 0; i < scale; i++) {
            sb.append("0");
        }
        DecimalFormat df = new DecimalFormat(sb.toString());
        return df.format(d);
    }

    public static String supplyChar(String str, int len, char c) {
        String ret = str;
        if (ret != null && !"".equals(ret)) {
            for (int i = 0; i < len - str.length(); i++) {
                ret = c + ret;
            }
        }
        return ret;
    }

    public static String supply8Zero(Object str) {
        return supplyChar(String.valueOf(str), 8, '0');
    }

    public static String supply8Zero(String str) {
        return supplyChar(str, 8, '0');
    }

    public static String supply8Zero(Integer str) {
        if (str != null) {
            return supplyChar(String.valueOf(str), 8, '0');
        } else {
            return null;
        }
    }

    public static String supply8Zero(int str) {
        return supplyChar(String.valueOf(str), 8, '0');
    }

    public static String highlight(String text, String target) {
        if (text == null) {
            return null;
        }
        if (target == null || "".equals(target)) {
            return text;
        }
        StringBuffer sb = new StringBuffer();
        target = target.toLowerCase();
        int len = target.length();
        for (int i = 0; i < text.length(); i++) {
            boolean flag = false;
            for (int j = 0; j < target.length(); j++) {
                if (i + j >= text.length()) {
                    flag = true;
                    break;
                }
                if (Character.toLowerCase(text.charAt(i + j)) != target.charAt(j)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                sb.append(text.charAt(i));
            } else {
                sb.append("<font color='red'>");
                sb.append(text.substring(i, i + len));
                sb.append("</font>");
                i = i + len - 1;
            }
        }
        return sb.toString();
    }

    public static String delSpace(String str) {
        if (str == null) {
            return null;
        }
        str = str.trim();
        StringBuffer tmp = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isSpaceChar(str.charAt(i)) && str.charAt(i) != '\t') {
                tmp.append(str.charAt(i));
            }

        }
        return tmp.toString();
    }

    public static String filterDollarStr(String str) {
        if (str == null || "".equals(str)) {
            return str;
        }
        String sReturn = "";

        if (str.indexOf('$', 0) > -1) {
            while (str.length() > 0) {
                if (str.indexOf('$', 0) > -1) {
                    sReturn += str.subSequence(0, str.indexOf('$', 0));
                    sReturn += "\\$";
                    str = str.substring(str.indexOf('$', 0) + 1, str.length());
                } else {
                    sReturn += str;
                    str = "";
                }
            }
        } else {

            sReturn = str;
        }

        return sReturn;

    }

    public static String escape(String src) {
        if (src == null) {
            return src;
        }
        try {
            return URLEncoder.encode(src, "UTF-8");
        } catch (Exception e) {
            return null;
        }
    }

    public static String unescape(String src) {
        if (src == null) {
            return null;
        }
        try {
            return URLDecoder.decode(src, "UTF-8");
        } catch (IllegalArgumentException e) {
            return unescape2(src);
        } catch (Exception e) {
            return null;
        }
    }

    @Deprecated
    public static String unescape2(String src) {
        if (src == null) {
            return null;
        }
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    public static String native2ascii(String str) {
        String tmp;
        StringBuffer sb = new StringBuffer();
        char c;
        int i, j;
        sb.setLength(0);
        for (i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (c > 255) {
                sb.append("\\u");
                j = (c >>> 8);
                tmp = Integer.toHexString(j);
                if (tmp.length() == 1) {
                    sb.append("0");
                }
                sb.append(tmp);
                j = (c & 0xFF);
                tmp = Integer.toHexString(j);
                if (tmp.length() == 1) {
                    sb.append("0");
                }
                sb.append(tmp);
            } else {
                sb.append(c);
            }

        }
        return sb.toString();
    }

    public static String native2ascii(char c) {
        String tmp;
        StringBuffer sb = new StringBuffer();

        if (c > 255) {
            sb.append("\\u");

            int j = (c >>> 8);
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
            j = (c & 0xFF);
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
        } else {
            sb.append(c);
        }

        return sb.toString();
    }

    public static String getLine(String context, int lineNum) {
        String tmp = context.trim();
        int start = 0;
        int end = tmp.indexOf("\r\n", start);
        int cnt = 0;
        while (end >= 0) {
            String line = tmp.substring(start, end).trim();
            if (line != null && !"".equals(line)) {
                cnt++;
            }
            if (cnt == lineNum) {
                return line;
            }
            start = end + 1;

            end = tmp.indexOf("\r\n", start);
        }

        return null;
    }

    /**
     *
     * @param str ?????????
     * @param len ????????????
     * @param elide ???????????????
     * @return
     */
    public static String omit(String str, int len, String elide) {
        if (str == null || len <= 0) {
            return "";
        }
        try {
            int elideLen = (elide.trim().length() == 0) ? 0 : elide.getBytes().length;
            if (len - elideLen > 0) {
                len = len - elideLen;
            }
            boolean isSub = false;
            List<Byte> list = new ArrayList<Byte>();
            for (char c : str.toCharArray()) {
                byte[] single = String.valueOf(c).getBytes("gbk");
                if ((list.size() + single.length) > len) {
                    isSub = true;
                    break;
                }
                for (byte b : single) {
                    list.add(b);
                }
            }
            byte[] arr = new byte[list.size()];
            for (int i = 0; i < list.size(); i++) {
                arr[i] = list.get(i).byteValue();
            }
            return new String(arr, "gbk") + ((isSub) ? elide.trim() : "");
        } catch (Throwable e) {
            return str;
        }
    }

    public static String initCap(String str) {
        if (str != null && str.length() > 0) {
            str = Character.toUpperCase(str.charAt(0)) + str.substring(1);
        }
        return str;
    }

    /**
     * ?????????array????????????separator??????????????????????????????
     *
     * @author fengpeng May 17, 2011 10:19:41 AM
     * @param array ??????
     * @param separator ?????????
     * @return
     */
    public static String array2str(String[] array, String separator) {
        if (array == null || array.length <= 0) {
            return "";
        }
        if (separator == null || "".equals(separator)) {
            return "";
        }

        String ret = "";
        separator = separator.trim();
        for (int i = 0; i < array.length; i++) {
            ret = ret + separator + array[i].trim();
        }
        if (ret.startsWith(separator)) {
            ret = ret.substring(1);
        }
        if (ret.endsWith(separator)) {
            ret = ret.substring(0, ret.length() - 1);
        }

        return ret;
    }

    /**
     * ????????????????????????
     *
     * @author fengpeng May 5, 2011 2:54:54 PM
     * @return
     */
    public static String getDomainName(String email) {
        if (email == null || "".equals(email) || email.indexOf("@") < 0) {
            return "";
        }
        String emailSuffix = email.substring(email.indexOf("@") + 1).trim();

        String domainName = "";
        if ("163.com".equals(emailSuffix)) {
            domainName = "mail.163.com";
        } else if ("126.com".equals(emailSuffix)) {
            domainName = "mail.126.com";
        } else if ("yeah.net".equals(emailSuffix)) {
            domainName = "mail.yeah.net";
        } else if ("gmail.com".equals(emailSuffix)) {
            domainName = "www.gmail.com";
        } else if ("10086.com".equals(emailSuffix)) {
            domainName = "mail.10086.cn";
        } else if ("139.com".equals(emailSuffix)) {
            domainName = "mail.10086.cn";
        } else if ("yahoo.com.cn".equals(emailSuffix)) {
            domainName = "mail.yahoo.cn";
        } else if ("yahoo.cn".equals(emailSuffix)) {
            domainName = "mail.yahoo.cn";
        } else if ("yahoo.com".equals(emailSuffix)) {
            domainName = "mail.yahoo.com";
        } else if ("foxmail.com".equals(emailSuffix)) {
            domainName = "www.foxmail.com";
        } else if ("sohu.com".equals(emailSuffix)) {
            domainName = "mail.sohu.com";
        } else if ("vip.sohu.com".equals(emailSuffix)) {
            domainName = "vip.sohu.com";
        } else if ("sina.com".equals(emailSuffix)) {
            domainName = "mail.sina.com.cn";
        } else if ("vip.sina.com".equals(emailSuffix)) {
            domainName = "mail.sina.com.cn";
        } else if ("eyou.com".equals(emailSuffix)) {
            domainName = "www.eyou.com";
        } else if ("vip.eyou.com".equals(emailSuffix)) {
            domainName = "vip.eyou.com";
        } else if ("wo.com.cn".equals(emailSuffix)) {
            domainName = "mail.wo.com.cn";
        } else if ("tom.com".equals(emailSuffix)) {
            domainName = "mail.tom.com";
        } else if ("hotmail.com".equals(emailSuffix)) {
            domainName = "login.live.com";
        } else if ("live.cn".equals(emailSuffix)) {
            domainName = "login.live.com";
        } else if ("live.com".equals(emailSuffix)) {
            domainName = "login.live.com";
        } else if ("msn.com".equals(emailSuffix)) {
            domainName = "login.live.com";
        } else if ("21.cn".equals(emailSuffix)) {
            domainName = "mail.21cn.com";
        } else if ("263.net".equals(emailSuffix)) {
            domainName = "www.263.net";
        } else if ("263.net.cn".equals(emailSuffix)) {
            domainName = "www.263.net";
        } else if ("x263.net".equals(emailSuffix)) {
            domainName = "www.263.net";
        } else if ("sogou.com".equals(emailSuffix)) {
            domainName = "mail.sogou.com";
        } else if ("189.cn".equals(emailSuffix)) {
            domainName = "www.189.cn";
        } else if ("188.com".equals(emailSuffix)) {
            domainName = "www.188.com";
        } else if ("vip.188.com".equals(emailSuffix)) {
            domainName = "www.188.com";
        }
        if (!"".equals(domainName) && !domainName.startsWith("http://")) {
            domainName = "http://" + domainName;
        }
        return domainName;
    }

    /**
     * ??????????????????????????? < &lt; > &gt; & &amp; " &quot; ????????????????????????
     */
    public static String FilterSpecialCharacter(String content) {
        if (content == null || "".equals(content)) {
            return content;
        }
        // <
        Pattern pattern1 = Pattern.compile("<");
        Matcher matcher1 = pattern1.matcher(content);
        content = matcher1.replaceAll("&lt;");
        // >
        Pattern pattern2 = Pattern.compile(">");
        Matcher matcher2 = pattern2.matcher(content);
        content = matcher2.replaceAll("&gt;");
        // &
        Pattern pattern3 = Pattern.compile("&");
        Matcher matcher3 = pattern3.matcher(content);
        content = matcher3.replaceAll("&amp;");
        // "
        Pattern pattern4 = Pattern.compile("\"");
        Matcher matcher4 = pattern4.matcher(content);
        content = matcher4.replaceAll("&quot;");
        return content;
    }

    /**
     * ??????list?????????????????????????????????
     *
     * @param list
     * @return
     */
    public static String getCollectionStr(List<?> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        StringBuilder objs = new StringBuilder();
        for (Object obj : list) {
            objs.append(obj).append(",");
        }
        if (objs.length() > 0) {
            objs.deleteCharAt(objs.length() - 1);
        }
        return objs.toString();
    }

    /**
     * ?????????????????????????????????????????????
     *
     * @param array
     * @return
     */
    public static String getArrayStr(String array[]) {
        if (array == null || array.length == 0) {
            return null;
        }
        StringBuilder objs = new StringBuilder();
        for (String obj : array) {
            if (!"".equals(obj)) {
                objs.append(obj).append(",");
            }
        }
        if (objs.length() > 0) {
            objs.deleteCharAt(objs.length() - 1);
        }
        return "".equals(objs.toString()) ? null : objs.toString();
    }

    /**
     * ?????????????????????????????????????????????
     *
     * @param array
     * @return
     */
    public static String getArrayStr(Integer array[]) {
        if (array == null || array.length == 0) {
            return null;
        }
        StringBuilder objs = new StringBuilder();
        for (Object obj : array) {
            if (!"".equals(String.valueOf(obj))) {
                objs.append(String.valueOf(obj)).append(",");
            }
        }
        if (objs.length() > 0) {
            objs.deleteCharAt(objs.length() - 1);
        }
        return "".equals(objs.toString()) ? null : objs.toString();
    }

    public static int string2int(String str, int defaultValue) {
        if (str == null || str.trim().length() == 0) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static boolean isNull(String value) {
        return (value == null || value.trim().length() == 0) ? true : false;
    }

    public static String byteArr2HexStr(byte[] arrB) {
        int iLen = arrB.length;
        // ??????byte??????????????????????????????????????????????????????????????????????????????
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // ????????????????????????
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // ??????0F????????????????????????0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    public static byte[] hexStr2ByteArr(String strIn) {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;

        // ?????????????????????????????????????????????????????????????????????????????????2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }
}
