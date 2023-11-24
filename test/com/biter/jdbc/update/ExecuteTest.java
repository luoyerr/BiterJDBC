package com.biter.jdbc.update;

import com.biter.jdbc.utils.connect.ConnectUtil;
import com.biter.jdbc.utils.updete.Update;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author 小帅
 * @version 1.0
 * @date 2023/11/25 0:32
 */
public class ExecuteTest {
    @Test
    public void UpdateTest(){
        String sql = "insert into user_info(user_name,user_pwd,email) values(?,?,?);";

        int update = Update.update(sql, "user4", "user4", "488@qq.com");
        System.out.println(update);

    }
}
