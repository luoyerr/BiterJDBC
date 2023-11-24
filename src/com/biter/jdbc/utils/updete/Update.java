package com.biter.jdbc.utils.updete;

import com.biter.jdbc.utils.connect.ConnectUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author 小帅
 * @version 1.0
 * @date 2023/11/25 0:10
 */
public abstract class  Update implements Execute {


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
     * <h3>执行更新的sql语句</h3>
     *
     *  <div>
     * 此sql方法主要用于对数据库执行 增、删、改的操作
     *  <p></p>
     *      作者推荐 -- 例如 : <br></br>
     *      String sql =
     *          "update t_user set user_name = ?,user_password = ? where user_id = ?";
     *      <br></br>
     *      Object[] params = {"张三","12345",1};
     *      <br></br>
     *      update(sql,params);
     *  </div>
     *
     *  <div>
     *      This sql method is mainly used to add, delete, and modify the database
     * <p></p>
     * Author recommendations - for example:
     * <br></br>
     *      String sql =
     *          "update t_user set user_name = ?,user_password = ? where user_id = ?";
     *      <br></br>
     *      Object[] params = {"张三","12345",1};
     *      <br></br>
     *      update(sql,params);
     *  </div>
     *
     * @param sql  要执行的sql语句 <br></br>
     *             sql statement to execute
     * @param objs 要传入的参数 <br></br>
     *             Parameters to pass in
     * @return int 返回执行的结果条数 如果为0 执行就失败 <br></br>
     * Returns the number of results executed ,If the value is 0, execution fails
     */
    public static int update(String sql, Object... objs) {

        /*
            获取连接对象 传入 conn

            Gets the connection object passed to conn
         */
        conn = ConnectUtil.getConnection();
        /*
            定义一个 int 类型的值存执行结果返回的记录条数 如果为0 执行就失败

            Define a value of type int to store the number of records returned from the execution result. If 0, the execution will fail.
         */
        int count;
        try {
            /*
                这行代码表示使用SQL语句创建一个PreparedStatement对象，并将其赋值给变量pst。
                conn：数据库连接对象，用于连接到数据库。
                prepareStatement()：这是一个方法，用于创建一个PreparedStatement对象。
                sql：字符串，表示SQL语句。

                This line of code represents creating a PreparedStatement object using an SQL statement and assigning it to the variable pst.
                conn: Database connection object, used to connect to a database.
                prepareStatement(): This is a method, used to create a PreparedStatement object.
                sql: A character string that represents an SQL statement.
             */
           pst = conn.prepareStatement(sql);
           /*
            如果 接收的 objs 有值 那就执行下面遍历值的代码

            If the received objs has a value then execute the following code that traverses the value
            */
            if (objs != null) {
                /*
                 遍历
                 */
                for (int i = 0; i < objs.length; i++) {
                    /*
                    这行代码表示将objs数组中的第i个对象设置到pst的第i+1个位置。
                    pst：PrintableStream对象，用于输出数据。
                    i：数组索引，表示当前要设置的对象在数组中的位置。
                    objs：数组，其中包含要设置到pst中的对象。
                    setObject()：这是一个方法，用于将对象设置到PrintableStream对象中。


                    This line of code means to set the I-th object in the objs array to the i+1 position of the pst.
                    pst: A PrintableStream object used to output data.
                    i: Array index, indicating the position of the object to be set in the array.
                    objs: An array that contains the object to be set to the pst.
                    setObject() : this is a method that is used to set objects to the printableStream object.
                     */
                    pst.setObject(i + 1, objs[i]);
                }
            }
            count = pst.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            /*
               这行代码表示调用ConnectUtil类的close方法，关闭数据库连接。
                ConnectUtil：一个工具类，用于管理数据库连接。
                close()：这是一个方法，用于关闭数据库连接。

                This line of code means to close the database connection by calling the close method of the ConnectUtil class.
             */
            ConnectUtil.close();
        }
        /*
         返回成功的结果
         */
        return count;
    }
    /**
     * <h3>执行更新的sql语句</h3>
     *
     *  <div>
     * 此sql方法主要用于对数据库执行 增、删、改的操作
     *  <p></p>
     *      作者推荐 -- 例如 : <br></br>
     *      String sql =
     *          "update t_user set user_name = ?,user_password = ? where user_id = ?";
     *      <br></br>
     *      Object[] params = {"张三","12345",1};
     *      <br></br>
     *      update(conn,sql,params);
     *  </div>
     *
     *  <div>
     *      This sql method is mainly used to add, delete, and modify the database
     * <p></p>
     * Author recommendations - for example:
     * <br></br>
     *      String sql =
     *          "update t_user set user_name = ?,user_password = ? where user_id = ?";
     *      <br></br>
     *      Object[] params = {"张三","12345",1};
     *      <br></br>
     *      update(conn,sql,params);
     *  </div>
     * @param conn 要连接的对象  <br></br>
     *                  The object to be connected
     * @param sql  要执行的sql语句 <br></br>
     *             sql statement to execute
     * @param objs 要传入的参数 <br></br>
     *             Parameters to pass in

     * @return int 返回执行的结果条数 <br></br>
     * Returns the number of results executed
     */
    public static int update(Connection conn ,String sql, Object... objs) {

        /*
            定义一个 int 类型的值存执行结果返回的记录条数 如果为0 执行就失败

            Define a value of type int to store the number of records returned from the execution result. If 0, the execution will fail.
         */
        int count;
        try {
            /*
                这行代码表示使用SQL语句创建一个PreparedStatement对象，并将其赋值给变量pst。
                conn：数据库连接对象，用于连接到数据库。
                prepareStatement()：这是一个方法，用于创建一个PreparedStatement对象。
                sql：字符串，表示SQL语句。

                This line of code represents creating a PreparedStatement object using an SQL statement and assigning it to the variable pst.
                conn: Database connection object, used to connect to a database.
                prepareStatement(): This is a method, used to create a PreparedStatement object.
                sql: A character string that represents an SQL statement.
             */
            pst = conn.prepareStatement(sql);
           /*
            如果 接收的 objs 有值 那就执行下面遍历值的代码

            If the received objs has a value then execute the following code that traverses the value
            */
            if (objs != null) {
                /*
                 遍历
                 */
                for (int i = 0; i < objs.length; i++) {
                    /*
                    这行代码表示将objs数组中的第i个对象设置到pst的第i+1个位置。
                    pst：PrintableStream对象，用于输出数据。
                    i：数组索引，表示当前要设置的对象在数组中的位置。
                    objs：数组，其中包含要设置到pst中的对象。
                    setObject()：这是一个方法，用于将对象设置到PrintableStream对象中。


                    This line of code means to set the I-th object in the objs array to the i+1 position of the pst.
                    pst: A PrintableStream object used to output data.
                    i: Array index, indicating the position of the object to be set in the array.
                    objs: An array that contains the object to be set to the pst.
                    setObject() : this is a method that is used to set objects to the printableStream object.
                     */
                    pst.setObject(i + 1, objs[i]);
                }
            }
            count = pst.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            /*
               这行代码表示调用ConnectUtil类的close方法，关闭数据库连接。
                ConnectUtil：一个工具类，用于管理数据库连接。
                close()：这是一个方法，用于关闭数据库连接。

                This line of code means to close the database connection by calling the close method of the ConnectUtil class.
             */
            ConnectUtil.close();
        }
        /*
         返回成功的结果
         */
        return count;
    }
}
