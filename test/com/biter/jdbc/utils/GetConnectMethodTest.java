package com.biter.jdbc.utils;

import org.junit.Test;

/**
 *   <h2> 测试字符串操作的方法 </h2>
 *   A way to test string operations
 * @author 小帅
 * @version 1.0
 * @date 2023/11/15 22:23
 */
public class GetConnectMethodTest {

    /**
     * <h2>测试获取数据库驱动的方法</h2>
     *
     * Test getting database-driven methods
     */
    @Test
    public void getDataDriverTest() {
        String jdbcUrl = "jdbc:oracle:thin:@//127.0.0.1:1521/OADB";
//        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/test3";
        String dataDriver = GetConnectMethod.getDataDriver(jdbcUrl);
        System.out.println("MySQL dataDriver: " + dataDriver);
    }
    /**
     * 测试 获取数据库服务的方法
     */
    @Test
    public void getServerNameTest() {
//        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/test3";
//        String jdbcUrl = "jdbc:oracle:thin:@//127.0.0.1:1521/OADB";
        String jdbcUrl = "jdbc:oracle:thin:@//localhost:1521/OADB";
        String databaseHost = GetConnectMethod.getServerName(jdbcUrl);
        System.out.println("MySQL Database host: " + databaseHost);
    }
    /**
     * 测试 获取端口的方法
     */
    @Test
    public void getPortNumberTest() {
//        String jdbcUrl = "jdbc:mysql://localhost:3306/test3";
                String jdbcUrl = "jdbc:oracle:thin:@//127.0.0.1:1521/OADB";

// 找到最后一个冒号的位置
        int lastColonIndex = jdbcUrl.lastIndexOf(":");
        if (lastColonIndex != -1) {
            // 提取端口号
            String portStr = jdbcUrl.substring(lastColonIndex + 1);
            // 找到斜杠的位置
            int slashIndex = portStr.indexOf("/");
            if (slashIndex != -1) {
                // 提取斜杠前面的部分
                String port = portStr.substring(0, slashIndex);
                System.out.println("Port: " + port);
            } else {
                System.out.println("在字符串中找不到斜杠。");
                System.out.println("Slash not found in the string.");
            }
        } else {
            System.out.println("在字符串中找不到冒号。");
            System.out.println("Colon not found in the URL.");
        }
    }

    @Test
    public void getDatabaseNameName() {
//        String url = "jdbc:mysql://127.0.0.1:3306/test3";
//        String jdbcUrl = "jdbc:oracle:thin:@//127.0.0.1:1521/OADB";
//
//// 找到最后一个斜杠的位置
//        int lastSlashIndex = jdbcUrl.lastIndexOf("/");
//        if (lastSlashIndex != -1) {
//            // 提取斜杠后面的部分
//            String database = jdbcUrl.substring(lastSlashIndex + 1);
//            System.out.println("Database: " + database);
//        } else {
//            System.out.println("Slash not found in the URL.");
//        }

        String url = "jdbc:mysql://localhost:3306/qq_system?useUnicode=true&characterEncoding=utf-8";
        int questionMarkIndex = url.indexOf('?');
        String fieldName;
        if (questionMarkIndex != -1) {
            fieldName = url.substring(url.lastIndexOf('/', questionMarkIndex) + 1, questionMarkIndex);
        } else {
            fieldName = url.substring(url.lastIndexOf('/') + 1);
        }
        System.out.println(fieldName); // 输出：qq_system
    }
}
