package com.biter.jdbc.utils.orther;

import java.util.Random;

/**
 * @author 小帅
 * @version 1.0
 * @date 2023/11/21 0:04
 */
public class RandomUtil {
    /**
     * 默认长度
     */
    private static final int DEFAULT_LENGTH = 4;
    /**
     * 随机字符串
     */
    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String CHARACTERS_LOWER_UPPER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String CHARACTERS_LOWER= "abcdefghijklmnopqrstuvwxyz";
    private static final String CHARACTERS_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String CHARACTERS_NUMBER_LOWER = "0123456789abcdefghijklmnopqrstuvwxyz";
    private static final String CHARACTERS_NUMBER_UPPER = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String CHARACTERS_NUMBER = "0123456789";
    // 创建一个随机数对象
    private static final Random RANDOM = new Random();

    /**
     * 根据时间生成id
     * @return String
     */
    public static String generateTimeId() {
        return System.currentTimeMillis() +"";
    }

    /**
     * 生成长度的小写自字符串加数字
     * @param length 输入长度
     * @return String
     */
    public static String generateNumberUpper(int length) {
        StringBuilder idBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS_NUMBER_UPPER.length());
            idBuilder.append(CHARACTERS_NUMBER_UPPER.charAt(randomIndex));
        }
        return idBuilder.toString();
    }
    /**
     * 生成默认长度为 4 的小写字符串加数字
     * @return String
     */
    public static String generateNumberUpper() {
        StringBuilder idBuilder = new StringBuilder();
        for (int i = 0; i < DEFAULT_LENGTH; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS_NUMBER_UPPER.length());
            idBuilder.append(CHARACTERS_NUMBER_UPPER.charAt(randomIndex));
        }
        return idBuilder.toString();
    }


    /**
     * 生成长度的小写自字符串加数字
     * @param length 输入长度
     * @return String
     */
    public static String generateNumberLower(int length) {
        StringBuilder idBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS_NUMBER_LOWER.length());
            idBuilder.append(CHARACTERS_NUMBER_LOWER.charAt(randomIndex));
        }
        return idBuilder.toString();
    }
    /**
     * 生成默认长度为4的小写字符串加数字
     * @return String
     */
    public static String generateNumberLower() {
        StringBuilder idBuilder = new StringBuilder();
        for (int i = 0; i < DEFAULT_LENGTH; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS_NUMBER_LOWER.length());
            idBuilder.append(CHARACTERS_NUMBER_LOWER.charAt(randomIndex));
        }
        return idBuilder.toString();
    }



    /**
     * 生成指定长度的随机唯一ID 大小写字母和数字
     *
     * @param length 随机ID的长度
     * @return 随机唯一ID
     */
    public static String generateId(int length) {
        StringBuilder idBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            idBuilder.append(CHARACTERS.charAt(randomIndex));
        }
        return idBuilder.toString();
    }
    /**
     * 生成指定长度的随机唯一ID 小写字母加数字 默认为 4 长度
     * @return String
     */
    public static String generateId() {
        StringBuilder idBuilder = new StringBuilder();
        for (int i = 0; i < DEFAULT_LENGTH; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            idBuilder.append(CHARACTERS.charAt(randomIndex));
        }
        return idBuilder.toString();
    }


    /**
     * 生成长度的大写自字符串
     * @param length 输入长度
     * @return String
     */
    public static String generateStringUpper(int length) {
        StringBuilder idBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS_UPPER.length());
            idBuilder.append(CHARACTERS_UPPER.charAt(randomIndex));
        }
        return idBuilder.toString();
    }
    /**
     * 生成默认长度为4大写字符串
     * @return String
     */
    public static String generateStringUpper() {
        StringBuilder idBuilder = new StringBuilder();
        for (int i = 0; i < DEFAULT_LENGTH; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS_LOWER.length());
            idBuilder.append(CHARACTERS_LOWER.charAt(randomIndex));
        }
        return idBuilder.toString();
    }

    /**
     * 生成长度的大小写自字符串
     * @param length 输入长度
     * @return String
     */
    public static String generateStringLowerUpper(int length) {
        StringBuilder idBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS_LOWER_UPPER.length());
            idBuilder.append(CHARACTERS_LOWER_UPPER.charAt(randomIndex));
        }
        return idBuilder.toString();
    }
    /**
     * 生成默认长度为4的大小写字符串
     * @return String
     */
    public static String generateStringLowerUpper() {
        StringBuilder idBuilder = new StringBuilder();
        for (int i = 0; i < DEFAULT_LENGTH; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS_LOWER_UPPER.length());
            idBuilder.append(CHARACTERS_LOWER_UPPER.charAt(randomIndex));
        }
        return idBuilder.toString();
    }


    /**
     * 生成长度的小写自字符串
     * @param length 输入长度
     * @return String
     */
    public static String generateStringLower(int length) {
        StringBuilder idBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS_LOWER.length());
            idBuilder.append(CHARACTERS_LOWER.charAt(randomIndex));
        }
        return idBuilder.toString();
    }
    /**
     * 生成默认长度为4的小写字符串
     * @return String
     */
    public static String generateStringLower() {
        StringBuilder idBuilder = new StringBuilder();
        for (int i = 0; i < DEFAULT_LENGTH; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS_LOWER.length());
            idBuilder.append(CHARACTERS_LOWER.charAt(randomIndex));
        }
        return idBuilder.toString();
    }


    /**
     * 生成4位数字随机数
     * @return String
     */
    public static String generateNumber() {
        StringBuilder idBuilder = new StringBuilder();
        for (int i = 0; i < DEFAULT_LENGTH; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS_NUMBER.length());
            idBuilder.append(CHARACTERS_NUMBER.charAt(randomIndex));
        }
        return idBuilder.toString();
    }
    /**
     * 随机生成指定长度的随机 ID 数字
     * @param length 想要的长度
     * @return
     */
    public static String generateNumber(int length) {
        StringBuilder idBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS_NUMBER.length());
            idBuilder.append(CHARACTERS_NUMBER.charAt(randomIndex));
        }
        return idBuilder.toString();
    }

    /**
     * 生成指定范围的随机数
     * @param min
     * @param max
     * @return Integer
     */
    public static Integer generateNumber(int min, int max) {
       return RANDOM.nextInt(max - min + 1) + min;
    }
    /**
     * 随机生成指定长度指定范围的随机唯一ID 数字
     * 一般生成的都是 min 是 0 max 是 9
     * @param length 想要的长度
     * @param min 最小值
     * @param max 最大值
     * @param length int length
     * @return String
     */
    public static String generateNumber(int min, int max,int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomDigit = RANDOM.nextInt(max - min + 1) + min;
            sb.append(randomDigit);
        }
        return sb.toString();
    }

}
