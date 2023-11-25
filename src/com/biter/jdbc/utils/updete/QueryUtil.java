package com.biter.jdbc.utils.updete;

import com.biter.jdbc.utils.connect.ConnectUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 用List集和的方式实现查询功能
 *
 * @author 小帅
 * @version 1.0
 * @date 2023/11/25 14:57
 */
public class QueryUtil implements Execute, List {
    /**
     * 创建一个Connection对象 并且让其为 null 值
     * <p></p>
     * <p>
     * <p>
     * Create a Connection object and set it to null
     */

    public static Connection conn = null;

    /**
     * 创建一个 PreparedStatement 对象 并且让其为 null 值
     * <p></p>
     * <p>
     * <p>
     * Create a PreparedStatement object and set it to null
     */
    public static PreparedStatement pst = null;

    /**
     * 创建一个 ResultSet 对象 并且让其为 null 值
     * <p></p>
     * <p>
     * <p>
     * Create a ResultSet object and set it to null
     */
    public static ResultSet rs = null;


    /**
     * <h2>执行SQL查询并返回结果集</h2>
     * 这个方法接受两个参数：sql（SQL查询语句）和objs（可变参数列表，用于传入查询参数）
     * <div>
     *      Execute SQL query and return result set
     *  <p></p>
     *      This method takes two parameters: sql (SQL query statement) and objs (variable parameter list, used to pass query parameters).
     * </div>
     *
     * @param sql  传入的sql语句
     *             <p>Incoming sql statement</p>
     * @param objs 传入的sql语句参数
     *             <p>Incoming sql statement parameters</p>
     * @return ResultSet
     */
    public static ResultSet query(String sql, Object... objs) throws SQLException {
        /*
            这一行代码获取数据库连接对象，并将其赋值给变量conn。

            This line of code takes the database connection object and assigns it to the variable conn.
         */
        conn = ConnectUtil.getConnection();
        /*
            这一行代码调用getResultSet方法，传入连接对象conn，SQL查询语句sql和查询参数objs。

            This line of code calls the get result set method, incoming connection object connor SQL query statement SQL and query parameter objs view.
         */
        return getResultSet(conn, sql, objs);
    }

    /**
     * <h2>执行SQL查询并返回结果集</h2>
     * 这个方法接受三个参数：conn（数据库连接对象），sql（SQL查询语句）和objs（可变参数列表，用于传入查询参数）。
     * <div>
     *      Execute SQL query and return result set
     *  <p></p>
     *      This method accepts three parameters: conn (database connection object), sql (SQL query statement), and objs (variable parameter list, used to pass query parameters).
     * </div>
     * @param conn 传入的Connection对象
     *             <p>Incoming Connection object</p>
     * @param sql  传入的sql语句
     *             <p>Incoming sql statement</p>
     * @param objs 传入的sql语句参数
     *             <p>Incoming sql statement parameters</p>
     * @return ResultSet
     */

    public static ResultSet query(Connection conn, String sql, Object... objs) throws SQLException {
         /*
            这一行代码调用getResultSet方法，传入连接对象conn，SQL查询语句sql和查询参数objs。

            This line of code calls the get result set method, incoming connection object connor SQL query statement SQL and query parameter objs view.
         */
        return getResultSet(conn, sql, objs);
    }

    /**
     * <h2>执行SQL查询并返回结果集</h2>
     *
     * 这个方法接受三个参数：conn（数据库连接对象），sql（SQL查询语句）和objs（对象数组，用于传入查询参数）。
     * <dv>
     *     Execute SQL query and return result set
     *     <p>This method takes three parameters: conn (database connection object), sql (SQL query statement), and objs (array of objects to pass in query parameters).</p>
     * </dv>
     * @param conn 传入的Connection对象
     *
     *             <p>Incoming Connection object</p>
     * @param sql 传入的sql语句
     *
     *            <p>Incoming sql statement</p>
     * @param objs 传入的sql语句参数
     *               <p>Incoming sql statement parameters</p>
     *
     * @return ResultSet
     */

    /*
            这行代码使用prepareStatement方法预编译SQL查询语句，并将预编译语句对象赋值给pst变量。
            This line of code pre compiles the SQL query using the prepareStatement method and assigns the precompiled statement object to the pst variable.
         */
    private static ResultSet getResultSet(Connection conn, String sql, Object[] objs) throws SQLException {

         /*
            设置查询参数并执行SQL查询

            Set query parameters and execute SQL queries
         */
        pst = conn.prepareStatement(sql);

            /*
                这行代码检查objs对象数组是否为null，以及数组的长度是否大于0。如果条件为真，执行下面的代码块

                This line of code checks whether the array of objs objects is null and whether the length of the array is greater than 0. If the condition is true, execute the following code block
             */
        if (objs != null && objs.length > 0) {
           /*
                这行代码使用for循环遍历objs数组，并将数组中的每个对象设置为pst对象的参数。

                This line of code uses a for loop to iterate through the objs array and sets each object in the array as a parameter to the pst object.
            */
            for (int i = 0; i < objs.length; i++) {
                /*
                这行代码将objs数组中的第i个对象设置为pst对象的参数。i是循环变量，objs[i]是数组中的第i个对象。

                This line sets the I-th object in the objs array as a parameter to the pst object. i is the loop variable, and objs[i] is the i th object in the array.
                 */
                pst.setObject(i + 1, objs[i]);
            }
        }

        /*
        这一行代码调用executeQuery方法，传入预编译语句pst。executeQuery方法用于执行SQL查询并返回结果集。

        This line of code calls the executeQuery method, passing in the precompiled statement pst. The executeQuery method is used to execute SQL queries and return result sets.
         */
        ResultSet resultSet = pst.executeQuery();
        /*
         这一行代码的作用是释放数据库连接资源，防止资源泄漏。当程序不再需要该连接时，调用close()方法来关闭连接，以释放资源。

           This line of code is used to release the database connection resources to prevent resource leaks. When the program no longer needs to be connected, the close() method is called to close the connection to release the resource.
         */
        ConnectUtil.close();
        return resultSet;
    }


}
