package com.biter.jdbc.utils.updete;


import com.biter.jdbc.utils.connect.ExecuteConnect;
import com.biter.jdbc.utils.orther.StringUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

/**
 * 用List集和的方式实现查询功能
 *
 * @author 小帅
 * @version 1.0
 * @date 2023/11/25 14:57
 */
public class ExecuteQuery implements Execute, List {
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
    public static ResultSet queryR(String sql, Object... objs) throws SQLException {
        /*
            这一行代码获取数据库连接对象，并将其赋值给变量conn。

            This line of code takes the database connection object and assigns it to the variable conn.
         */
        conn = ExecuteConnect.getConnection();
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
     *
     * @param conn 传入的Connection对象
     *             <p>Incoming Connection object</p>
     * @param sql  传入的sql语句
     *             <p>Incoming sql statement</p>
     * @param objs 传入的sql语句参数
     *             <p>Incoming sql statement parameters</p>
     * @return ResultSet
     */

    public static ResultSet queryR(Connection conn, String sql, Object... objs) throws SQLException {
         /*
            这一行代码调用getResultSet方法，传入连接对象conn，SQL查询语句sql和查询参数objs。

            This line of code calls the get result set method, incoming connection object connor SQL query statement SQL and query parameter objs view.
         */
        return getResultSet(conn, sql, objs);
    }

    /**
     * <h2>执行SQL查询并返回结果集</h2>
     * <p>
     * 这个方法接受三个参数：conn（数据库连接对象），sql（SQL查询语句）和objs（对象数组，用于传入查询参数）。
     * <dv>
     * Execute SQL query and return result set
     * <p>This method takes three parameters: conn (database connection object), sql (SQL query statement), and objs (array of objects to pass in query parameters).</p>
     * </dv>
     *
     * @param conn 传入的Connection对象
     *
     *             <p>Incoming Connection object</p>
     * @param sql  传入的sql语句
     *
     *             <p>Incoming sql statement</p>
     * @param objs 传入的sql语句参数
     *             <p>Incoming sql statement parameters</p>
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

                This line sets the I-th object in the objs array as a parameter to the pst object. I am the loop variable, and objs[i] is the i th object in the array.
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
        ExecuteConnect.close();
        return resultSet;
    }


    /**
     * <h2>查询数据据中所有的数据 以list集合形式返回</h2>
     * 该方法接受一个SQL查询语句、一个实体类的Class对象和一个可变参数列表。这些参数将作为SQL语句中的参数，用于替换SQL语句中的占位符。
     *
     * <div>
     * All the data in the query data is returned as a list collection
     * <p>
     * This method takes an SQL query statement, a Class object of an entity class, and a list of variable arguments. These parameters are used as parameters in the SQL statement to replace placeholders in the SQL statement.
     * </div>
     *
     * @param sql   String类型，表示要查询的SQL语句
     *              <p>String indicates the SQL statement to be queried</p>
     * @param clazz Class<T>类型，表示要查询的实体类的Class对象。
     *              <p>The Class<T> type represents the Class object of the entity class to be queried.</p>
     * @param objs  Object...类型，表示查询参数的可变参数列表。这些参数将作为SQL语句中的参数，用于替换SQL语句中的占位符。
     *              <p>Object... Type: indicates the variable parameter list of query parameters. These parameters are used as parameters in the SQL statement to replace placeholders in the SQL statement.</p>
     * @return java.util.List<T>
     */

    public static <T> java.util.List<T> queryList(String sql, Class<T> clazz, Object... objs) throws Exception {
        /*
            这实际上是创建了一个空的列表，该列表可以用来存储和操作实体对象。在后续的代码中，可以通过向列表中添加实体对象来填充列表。

            This essentially creates an empty list that can be used to store and manipulate entity objects. In subsequent code, you can populate the list by adding entity objects to it.
         */
        java.util.List<T> list = new ArrayList<>();

//        conn = ExecuteConnect.getConnection();

        /*
            执行一个数据库查询操作，并将查询结果存储在名为"rs"的ResultSet对象中。


         */
        rs = queryR(sql, objs);
        // 从结果集中获取表中的信息
        /*
            获取一个ResultSet对象中查询结果的元数据，并将该元数据存储在名为"mateDate"的ResultSetMetaData对象中。

         */
        ResultSetMetaData mateDate = rs.getMetaData();

        /*
            获取ResultSet对象中查询结果的列数。

         */
        int cols = mateDate.getColumnCount(); // 获取列数

        /*
            使用一个while循环遍历每一行查询结果。
         */
        while (rs.next()) {
            /*
                创建一个名为"t"的Java对象，该对象由名为"clazz"的类创建。

                clazz: 是一个类名，表示要创建的类的对象。
                t: 是一个变量名，表示创建的类的对象。
                newInstance(): 方法名，表示创建类的实例。
             */
            T t = clazz.newInstance();
            for (int i = 0; i < cols; i++) {
                /*
                    获取一个名为colLabel的字符串变量，该字符串变量表示第i + 1列的标签。

                    mateDate: 是一个对象，可能是一个日期对象或者一个包含日期对象或其他属性的对象。
                    i: 是一个整数变量，表示当前遍历的列的索引。
                    getColumnLabel(): 方法名，表示获取指定列的标签。
                    i + 1: 表示第i + 1列，因为数组的索引从0开始，而列的索引从1开始。
                 */
                String colLabel = mateDate.getColumnLabel(i + 1); // 获取列名
                String colNewName = StringUtil.toEntityName(colLabel); // 获取表名
                //获取类中的指定字段
                Field field = clazz.getDeclaredField(colNewName);


                String lower = StringUtil.toLower(colNewName);
                String lastFieldName = StringUtil.lastFieldName(StringUtil.toLower(field.toString()));
                //设置该字段可以被访问
                try {
                    if (lower.equals(lastFieldName)) {
                        field.setAccessible(true);
                        Object colValue = rs.getObject(colLabel);
                        //设置该字段的值
                        field.set(t, colValue);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("表中没有字段 或者数据类型不对 " + colLabel + " \n" + e);
                }
            }
            list.add(t);
        }
        return list;

    }

    public static <T> T query(String sql, Class<T> clazz, Object... objs) throws Exception {
        java.util.List<T> list = queryList(sql, clazz, objs);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }


}
