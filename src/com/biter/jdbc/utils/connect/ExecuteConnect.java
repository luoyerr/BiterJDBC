/**
 * 该类只负责连接数据库和关闭连接数据库，不负责数据库的增删改查
 *
 * <p></p>
 *
 *
 * <h3>作者邮箱: ly2958534395@163.com </h3>
 * <p>
 * This class is only responsible for connecting to and closing connected databases,
 * and is not responsible for adding, deleting, modifying and checking databases
 * <p></p>
 *
 *
 * <h3>Author Email: ly2958534395@163.com</h3>
 */

package com.biter.jdbc.utils.connect;



import java.sql.*;

import static com.biter.jdbc.pool.Pool.*;


/**
 * <h2>连接数据库和关闭数据库连接的操作</h2>
 * The operation of connecting to the database
 *
 * <p></p>
 * @author 小帅
 * @version 1.0
 * @date 2023/11/15 10:02
 */
public class ExecuteConnect implements Connect {





    /**
     * <h2>创建 Connection 连接对象</h2>
     * <br></br>
     * 这个连接对象通过<b>DriverManager</b>类来获取
     * <br></br>
     * 例如 :  DriverManager.getConnection() , 也可以通过其它方式获取 , 德鲁伊连接池来获取连接对象
     * <br></br>
     * <p></p>
     * Create a Connection object
     * <br></br>
     * This connection object is obtained from the <b>DriverManager</b> class
     * <br></br>
     * For example: the DriverManager getConnection (), or by other means, the druids connection pool to get connection object
     */
    public static Connection conn = null;

    /**
     * <h2>获取连接对象的方法</h2>
     *
     * 此方法可以自动判断是什么数据库，然后返回连接对象,目前只有两种数据库的连接方式,分别是mysql还有oracle
     *
     *<br></br>
     * <p></p>
     * This method can automatically determine what database is, and then return the connection object, there are only two database connection methods, respectively mysql and oracle
     *
     * @return conn
     *  <br></br>
     *  <br></br>
     *  返回的是一个或多个连接对象 或者返回 null
     *  <br></br>
     *  Return one or more connection objects or return null
     */
    public static Connection getConnection() {

        try {
            /*
                用Class获取Driver类 , 加载驱动

                Use the Class to get the Driver class, load the driver
             */
            Class.forName(driverClassName);

        } catch (ClassNotFoundException e) {

            throw new RuntimeException(e);

        }




        /*
            jdbc:mysql://127.0.0.1:3306/test3
            jdbc:mysql://<host>:<port>/<databaseName>

            如果驱动是mysql的情况下 就会执行下面的代码

            If the driver is mysql, the following code will be executed
         */
        if ("mysql".equals(dataDriver)) {

            try {

                /*
                    如果conn地址还有的话就不从连接池中获取连接对象
                    如果conn地址没有了，就从连接池中获取连接对象

                    If the conn address is still there, you don't get a connection object from the connection pool
                    If the conn address is gone, the connection object is fetched from the connection pool
                 */
                if (conn == null) {
                    /*
                        尝试与数据库连接获取连接对象

                        Try to connect to the database to get the connection object
                     */
                    conn = DriverManager.getConnection("jdbc:" +
                                    dataDriver + "://" +
                                    serverName + ":" +
                                    portNumber + "/" +
                                    databaseName,
                            userName, password);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        /*
            jdbc:oracle:thin:@//127.0.0.1:1521/OADB
            jdbc:oracle:thin:@//<host>:<port>/<SERVICE_NAME>
            如果驱动是oracle的情况下 就会执行下面的代码

            If the driver is oracle, the following code is executed
         */

        if ("oracle".equals(dataDriver)) {

            try {

                /*
                    如果conn地址还有的话就不从连接池中获取连接对象
                    如果conn地址没有了，就从连接池中获取连接对象

                    If the conn address is still there, you don't get a connection object from the connection pool
                    If the conn address is gone, the connection object is fetched from the connection pool
                 */

               if (conn == null) {
                    /*
                        尝试与数据库连接获取连接对象

                        Try to connect to the database to get the connection object
                     */
                   conn = DriverManager.getConnection("jdbc:" +
                                   dataDriver + ":" +
                                   "thin:@//" +
                                   serverName + ":" +
                                   portNumber + "/" +
                                   databaseName,
                           userName, password);
               }
            } catch (SQLException e) {

                throw new RuntimeException(e);
            }
        }
        return conn;
    }



    /**
     * <h2>关闭 AutoCloseable 对象的方法</h2>
     *
     * 可以传多个参数 , 如 Connection , PreparedStatement , ResultSet 等一些对象的方法
     * 案例 :
     *  <br></br>
     * <p></p>
     * close() 或 close(Connection conn) 或 close(PreparedStatement pst) 或
     * close(ResultSet rs) 或 close(Connection conn, PreparedStatement pst)
     * close(Connection conn, PreparedStatement pst ,ResultSet rs)...
     * <p></p>
     *
     *     关闭连接的方法
     *     <br></br>
     *    Connection  conn = ExecuteConnect.getConnection();
     *    <br></br>
     *     ExecuteConnect.close(conn);
     *     <br></br>
     *     System.out.println(connection.isClosed());
     *     <br></br>
     *     如果为ture 就是关闭成功 否则关闭失败
     *
     * <P></P>
     * Method of closing a connection
     *     <br></br>
     *    Connection  conn = ExecuteConnect.getConnection();
     *    <br></br>
     *     ExecuteConnect.close(conn);
     *     <br></br>
     *     System.out.println(connection.isClosed());
     *     <br></br>
     *     If true, the shutdown succeeds. Otherwise, the shutdown fails
     * <p></p>
     * For example :
     *  <br></br>
     * <p></p>
     * close() or close(Connection conn) or close(PreparedStatement pst) or
     * close(ResultSet rs) or close(Connection conn, PreparedStatement pst) or
     * close(Connection conn, PreparedStatement pst ,ResultSet rs)...
     *
     * <br></br>
     * <p></p>
     * The method to close the AutoCloseable object can pass multiple parameters, such as Connection, PreparedStatement, ResultSet and other object methods
     *
     * <p></p>
     * @param objs  AutoCloseable 对象
     *              <p></p>
     *              AutoCloseable object
     */
    public static void close(AutoCloseable... objs) {

        /*
            循环关闭AutoCloseable对象

            Loop through the Auto Closeable object
         */
        for (AutoCloseable obj : objs) {

            /*
                判断obj是否是Connection类型

                如果是Connection就不做任何操作

                如果不是Connection就关闭obj对象


                Check whether obj is of the connection type

                If it's connection, you don't do anything

                If it's not a connection, you close the obj object

             */
            if (obj instanceof  Connection) {

            }else {

                try {
                    /*
                        关闭obj

                        Close the obj
                     */
                    obj.close();

                } catch (Exception e) {

                    // throw error
                    throw new RuntimeException(e);
                }
            }

        }
    } ;


    /**
     * <h2>关闭 Connection 对象的方法 </h2>
     *
     * 此方法传入的是 Connection 并且关闭
     * 例如 :
     * close(Connection conn)
     * <p></p>
     *This method passes Connection and closes
     * <br></br>
     * For example :
     * close(Connection conn)
     *     关闭连接的方法
     *     <br></br>
     *    Connection  conn = ExecuteConnect.getConnection();
     *    <br></br>
     *     ExecuteConnect.close(conn);
     *     <br></br>
     *     System.out.println(connection.isClosed());
     *     <br></br>
     *     如果为ture 就是关闭成功 否则关闭失败
     *
     * <P></P>
     * Method of closing a connection
     *     <br></br>
     *    Connection  conn = ExecuteConnect.getConnection();
     *    <br></br>
     *     ExecuteConnect.close(conn);
     *     <br></br>
     *     System.out.println(connection.isClosed());
     *     <br></br>
     *     If true, the shutdown succeeds. Otherwise, the shutdown fails
     *
     * @param conn 连接对象
     *                    connecting object
     */
    public  static void close (Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    };

    /**
     * <h2>关闭 PreparedStatement 对象的方法 </h2>
     *
     * 此方法传入的是 PreparedStatement 并且关闭
     * 例如 :
     * close(PreparedStatement pst)
     * <p></p>
     *This method passes PreparedStatement and closes
     * <br></br>
     * For example :
     * close(PreparedStatement pst)
     *     关闭连接的方法
     *     <br></br>
     *    Connection  conn = ExecuteConnect.getConnection();
     *    <br></br>
     *     ExecuteConnect.close(conn);
     *     <br></br>
     *     System.out.println(connection.isClosed());
     *     <br></br>
     *     如果为ture 就是关闭成功 否则关闭失败
     *
     * <P></P>
     * Method of closing a connection
     *     <br></br>
     *    Connection  conn = ExecuteConnect.getConnection();
     *    <br></br>
     *     ExecuteConnect.close(conn);
     *     <br></br>
     *     System.out.println(connection.isClosed());
     *     <br></br>
     *     If true, the shutdown succeeds. Otherwise, the shutdown fails
     *
     * @param pst 连接对象
     *                    connecting object
     */
    public static void close (PreparedStatement pst) {
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    };


    /**
     * <h2>关闭 Statement 对象的方法 </h2>
     *
     * 此方法传入的是 Statement 并且关闭
     * 例如 :
     * close(Statement st)
     * <p></p>
     *This method passes Statement and closes
     * <br></br>
     * For example :
     * close(Statement st)
     *
     *     关闭连接的方法
     *     <br></br>
     *    Connection  conn = ExecuteConnect.getConnection();
     *    <br></br>
     *     ExecuteConnect.close(conn);
     *     <br></br>
     *     System.out.println(connection.isClosed());
     *     <br></br>
     *     如果为ture 就是关闭成功 否则关闭失败
     *
     * <P></P>
     * Method of closing a connection
     *     <br></br>
     *    Connection  conn = ExecuteConnect.getConnection();
     *    <br></br>
     *     ExecuteConnect.close(conn);
     *     <br></br>
     *     System.out.println(connection.isClosed());
     *     <br></br>
     *     If true, the shutdown succeeds. Otherwise, the shutdown fails
     * @param st 连接对象
     *                    connecting object
     */
    public static void close (Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    };

    /**
     * <h2>关闭 ResultSet 对象的方法 </h2>
     *
     * 此方法传入的是 ResultSet 并且关闭
     * 例如 :
     * close(ResultSet rs)
     * <p></p>
     *This method passes ResultSet and closes
     * <br></br>
     * For example :
     * close(ResultSet rs)
     *     关闭连接的方法
     *     <br></br>
     *    Connection  conn = ExecuteConnect.getConnection();
     *    <br></br>
     *     ExecuteConnect.close(conn);
     *     <br></br>
     *     System.out.println(connection.isClosed());
     *     <br></br>
     *     如果为ture 就是关闭成功 否则关闭失败
     *
     * <P></P>
     * Method of closing a connection
     *     <br></br>
     *    Connection  conn = ExecuteConnect.getConnection();
     *    <br></br>
     *     ExecuteConnect.close(conn);
     *     <br></br>
     *     System.out.println(connection.isClosed());
     *     <br></br>
     *     If true, the shutdown succeeds. Otherwise, the shutdown fails
     *
     * @param rs 连接对象
     *                    connecting object
     */
    public  static void close (ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    };
}
