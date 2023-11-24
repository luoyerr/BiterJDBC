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


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


/**
 * <h2>连接数据库和关闭数据库连接的操作</h2>
 * The operation of connecting to the database
 *
 * <p></p>
 * @author 小帅
 * @version 1.0
 * @date 2023/11/15 10:02
 */
public class ConnectUtil implements Connect {

    /**
     *
     *
     * <h2>数据驱动名</h2>
     * 如 : mysql , oracle ...
     * <p></p>
     *
     *
     * This is the data-driven name,
     * for example mysql , oracle ...
     *
     *
     */
    public  static  String dataDriver;

    /**
     *
     *
     * <h2>这是你需要连接的服务器名</h2>
     * 如 : localhost 127.0.0.1
     * <p></p>
     *
     *
     * This is the name of the server you need to connect to ,
     * for example localhost and 127.0.0.1
     *
     *
     */

    public static String serverName;

    /**
     *
     *
     * <h2>这是服务器端口号</h2>
     * 如 : mysql 默认端口号是 3306
     * <p></p>
     *
     * This is the server port number ,
     * for example mysql , the default port number is 3306
     *
     */

    public static String portNumber;

    /**
     *
     * <h2>这是你需要连接数据库的数据库名</h2>
     * 如 : biter
     * <p></p>
     *
     *
     * This is the name of the database you need to connect to,
     * for example biter
     */

    public static String databaseName;

    /**
     *
     * <h2>这是你数据库的连接用户名</h2>
     * 例如 : root用户
     * <p></p>
     *
     * This is your database connection username ,
     * for example root user
     */

    public static String userName;

    /**
     *
     *
     * <h2>这是你需要连接的数据库密码</h2>
     * 例如: ****
     * <p></p>
     *
     * This is the database password you need to connect to,
     * for example password  is ****
     *
     */
    public static String password;


    /**
     *
     * <h2>这是你需要连接的数据库驱动类</h2>
     * driverClassName 这是你的驱动类的名字
     *
     *  <br></br>
     *  例如 : com.mysql.cj.jdbc.Driver
     * <br></br>
     *
     * <p></p>
     *  driverClassName This is the name of your driver class
     *  <br></br>
     *  For example com.mysql.cj.jdbc.Driver
     */
    public static String driverClassName;

    /**
     * <h2>获取文件内容</h2>
     * Get file content
     * <p></p>
     * 它提供了一个灵活的方式来存储和检索键值对。
     * <br></br>
     * 例如 : key=value
     *
     * <p></p>
     * It provides a flexible way to store and retrieve key-value pairs.
     * <br></br>
     * For example, key=value
     */
    public static  Properties properties = new Properties();


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


    /*
         这个代码块主要完成的功能是 :
     *      <br></br>
     *      1、 加载配置文件 resources/db.properties 文件 ;
     *      2、 获取配置文件中的配置信息 ; 比如 : jdbcUrl , dataDriver  等一些配置
     */
    static {
        try {
            /*

                在普通项目中这样使用 但是在web项目中就会报错

                从流中加载 指定目录下的文件 如加载 resources/db.properties 文件

                This is used in a normal project but will result in an error in a web project

                Load files from the stream in a specified directory such as the resources/db.properties file
             */
           try {
               properties.load(new FileInputStream("resources/db.properties"));
           }catch (IOException e) {
               /*
                    在web项目中加载如果加载 但一定要制源配置文件才能找到

                    Load in the web project if you load but be sure to make the source profile to find
                */
               properties.load(ConnectUtil.class.getClassLoader().getResourceAsStream("db.properties"));

           }

            /*
                 driverClassName 是驱动文件

                 driverClassName is the driver file
             */
            driverClassName = properties.getProperty("driverClassName");
            /*
                获取jdbc的url路径 如 jdbc:mysql://localhost:3306/qq_system?useUnicode=true&characterEncoding=utf-8

                For the JDBC url path such as jdbc:mysql://localhost:3306/qq_system?useUnicode=true&characterEncoding=utf-8
             */
            String jdbcUrl = properties.getProperty("url");
            /*
                获取的驱动名字 如 mysql

                Obtain the driver name, such as mysql
             */
            dataDriver = ConnectMethod.getDataDriver(jdbcUrl);
            /*
                获得服务器的名称 如 localhost

                Obtain the server name, such as localhost
             */
            serverName = ConnectMethod.getServerName(jdbcUrl);
            /*
                获取服务器名称的端口号 如 post

                Obtain the server name port number, such as post
             */
            portNumber = ConnectMethod.getPortNumber(jdbcUrl);
            /*
                 获取数据库的名字 如 qq_system

                 Obtain the database name, such as qq_system
             */
            databaseName = ConnectMethod.getDatabaseName(jdbcUrl);
            /*
                获取数据库的输入用户名

                Obtain the database input username
             */
            userName = properties.getProperty("username");
            /*
                获取数据库的输入密码

                Obtain the database input password
             */
            password = properties.getProperty("password");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


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
     *    Connection  conn = ConnectUtil.getConnection();
     *    <br></br>
     *     ConnectUtil.close(conn);
     *     <br></br>
     *     System.out.println(connection.isClosed());
     *     <br></br>
     *     如果为ture 就是关闭成功 否则关闭失败
     *
     * <P></P>
     * Method of closing a connection
     *     <br></br>
     *    Connection  conn = ConnectUtil.getConnection();
     *    <br></br>
     *     ConnectUtil.close(conn);
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
     *    Connection  conn = ConnectUtil.getConnection();
     *    <br></br>
     *     ConnectUtil.close(conn);
     *     <br></br>
     *     System.out.println(connection.isClosed());
     *     <br></br>
     *     如果为ture 就是关闭成功 否则关闭失败
     *
     * <P></P>
     * Method of closing a connection
     *     <br></br>
     *    Connection  conn = ConnectUtil.getConnection();
     *    <br></br>
     *     ConnectUtil.close(conn);
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
     *    Connection  conn = ConnectUtil.getConnection();
     *    <br></br>
     *     ConnectUtil.close(conn);
     *     <br></br>
     *     System.out.println(connection.isClosed());
     *     <br></br>
     *     如果为ture 就是关闭成功 否则关闭失败
     *
     * <P></P>
     * Method of closing a connection
     *     <br></br>
     *    Connection  conn = ConnectUtil.getConnection();
     *    <br></br>
     *     ConnectUtil.close(conn);
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
     *    Connection  conn = ConnectUtil.getConnection();
     *    <br></br>
     *     ConnectUtil.close(conn);
     *     <br></br>
     *     System.out.println(connection.isClosed());
     *     <br></br>
     *     如果为ture 就是关闭成功 否则关闭失败
     *
     * <P></P>
     * Method of closing a connection
     *     <br></br>
     *    Connection  conn = ConnectUtil.getConnection();
     *    <br></br>
     *     ConnectUtil.close(conn);
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
     *    Connection  conn = ConnectUtil.getConnection();
     *    <br></br>
     *     ConnectUtil.close(conn);
     *     <br></br>
     *     System.out.println(connection.isClosed());
     *     <br></br>
     *     如果为ture 就是关闭成功 否则关闭失败
     *
     * <P></P>
     * Method of closing a connection
     *     <br></br>
     *    Connection  conn = ConnectUtil.getConnection();
     *    <br></br>
     *     ConnectUtil.close(conn);
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
