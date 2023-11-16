package com.biter.jdbc.utils;

import org.junit.Test;

import java.sql.Connection;

/**
 * @author 小帅
 * @version 1.0
 * @date 2023/11/15 21:22
 */
public class ConnectTest {



    /**
     * <h2>测试连接数据库的方法</h2>
     *
     * Test how to connect to a database
     */
    @Test
    public void getConnectionTest() {

        Connection connection = ConnectUtil.getConnection();

        if (connection == null)
            System.out.println("连接为空:" + null);
        else
            System.out.println(connection);


    }


}
