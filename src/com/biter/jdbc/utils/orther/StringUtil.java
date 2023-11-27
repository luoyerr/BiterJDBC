package com.biter.jdbc.utils.orther;

/**
 * 封装一些常用的字符串操作
 *
 * @author 小帅
 * @version 1.0
 * @date 2023/11/15 23:54
 */
public class StringUtil {
    /**
     * 将字符串首字母小写
     *
     * @param str
     * @return String
     */
    public static String toLowerInFirst(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * 将字符串首字母大写
     *
     * @param str
     * @return String
     */
    public static String toUpperInFirst(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    // Goods_id

    /**
     * 将字符串首字母小写 且 _ 后第一个字母大写
     * Goods_id --> goods_Id
     *
     * @param string
     * @return
     */
    public static String toLowName(String string) {
        String[] words = string.split("_");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (i == 0) {
                // 将字符串转换为小写
                sb.append(words[i].toLowerCase());
            } else {
                // 将字符串转换为大写，并将其余字母转换为小写
                sb.append(words[i].substring(0, 1).toUpperCase());
                sb.append(words[i].substring(1).toLowerCase());
            }
            if (i < words.length - 1) {
                // 在每个单词之间添加下划线
                sb.append("_");
            }
        }
        return sb.toString();
    }

    /**
     * 把数据库中的字段变成entity中的类中的字段  Goods_id --> goodsId
     *
     * @param string
     * @return String
     */
    public static String toEntityName(String string) {
        String lowName = toLowName(string);
        return lowName.replaceAll("_", ""); // 替换
    }

    /**
     *
     * Filed 是 entity 类的 字段   goodsId -> 变大写
     * private int com.biko.supermarket.entity.Goods.goodsId
     */

    /**
     * 将字符串字母小写
     *
     * @param str
     * @return String
     */
    public static String toLower(String str) {
        return str.toLowerCase();
    }

    /**
     * 将字符串字母大写
     *
     * @param str
     * @return String
     */
    public static String toUpper(String str) {
        return  str.toUpperCase();
    }

    /**
     * 截取最后 的 字段名
     * private int com.biko.supermarket.entity.Goods.goodsId -> goodsId
     */
    public static String lastFieldName(String string) {
        int lastIndexOfPeriod = string.lastIndexOf('.');
        return string.substring(lastIndexOfPeriod + 1);
    }

}
