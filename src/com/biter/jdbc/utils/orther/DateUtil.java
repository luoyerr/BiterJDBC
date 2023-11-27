package com.biter.jdbc.utils.orther;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 小帅
 * @version 1.0
 * @date 2023/11/27 20:33
 */
public class DateUtil {
    /**
     * 获取 当前时间 并且 格式化 年月日
     * @return String 2023-11-13
     */
    public static String getCurrentTime() {
        // 获取当前时间
        Date now = new Date();
        // 创建一个SimpleDateFormat对象，用于格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 将当前时间转换为字符串格式
        String formattedNow = sdf.format(now);
        // 输出转换后的字符串
        return formattedNow;
    }

    /**
     * 获取 当前时间精确到秒 并且 格式化 年月日 时分秒
     * @return String 2023-11-13 09:17:31
     */
    public static String getCurrentTimeToSecond() {
        // 获取当前时间
        Date now = new Date();
        // 创建一个SimpleDateFormat对象，用于格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 将当前时间转换为字符串格式
        String formattedNow = sdf.format(now);
        // 输出转换后的字符串
        return formattedNow;
    }

    /**
     * 获取 当前时间精确到毫秒 并且 格式化 年月日时分秒 毫秒
     * @return String 2023-11-13 09:17:31 430
     */
    public static String getCurrentTimeToMillisecond() {
        // 获取当前时间
        Date now = new Date();
        // 创建一个SimpleDateFormat对象，用于格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        // 将当前时间转换为字符串格式
        String formattedNow = sdf.format(now);
        // 输出转换后的字符串
        return formattedNow;
    }
}
