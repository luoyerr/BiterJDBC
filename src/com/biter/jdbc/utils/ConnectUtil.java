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

package com.biter.jdbc.utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
public class ConnectUtil {

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
                从流中加载 指定目录下的文件 如加载 resources/db.properties 文件

                Load files from the stream in a specified directory such as the resources/db.properties file
             */
            properties.load(new FileInputStream("resources/db.properties"));
            driverClassName = properties.getProperty("driverClassName");
            String jdbcUrl = properties.getProperty("url");
            dataDriver = GetConnectMethod.getDataDriver(jdbcUrl);
            serverName = GetConnectMethod.getServerName(jdbcUrl);
            portNumber = GetConnectMethod.getPortNumber(jdbcUrl);
            databaseName = GetConnectMethod.getDatabaseName(jdbcUrl);
            userName = properties.getProperty("username");
            password = properties.getProperty("password");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static Connection getConnection() {
        try {
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
                if (conn == null) {

                    conn = DriverManager.getConnection("jdbc:" +
                                    dataDriver + "://" +
                                    serverName + ":" +
                                    portNumber + "/" +
                                    databaseName,
                            userName, password);
                    conn.setCatalog(databaseName);
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
               if (conn == null) {
                   conn = DriverManager.getConnection("jdbc:" +
                                   dataDriver + ":" +
                                   "thin:@//" +
                                   serverName + ":" +
                                   portNumber + "/" +
                                   databaseName,
                           userName, password);
                   conn.setCatalog(databaseName);
               }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }
}
