package com.biter.jdbc.utils;


import com.biter.jdbc.utils.connect.ExecuteConnect;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

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
    Connection connection = null;
    @Test
    public void getConnectionTest() {




       try {
           connection = ExecuteConnect.getConnection();
           if (connection == null){
               System.out.println("连接为空:" + null);
           }else{
               System.out.println(connection);
           }
       }catch (Exception e) {
           e.printStackTrace();
       }finally {
           ExecuteConnect.close(connection);
       }

        System.out.println(connection + " oo");
        try {
            System.out.println(connection.isClosed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test(){
        Connection connection1 = ExecuteConnect.getConnection();
        System.out.println(connection1);
        ExecuteConnect.close(connection1);
        try {
            boolean closed = connection1.isClosed();
            System.out.println(closed);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
