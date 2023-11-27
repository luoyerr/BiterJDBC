package com.biter.jdbc.pool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.biter.jdbc.utils.connect.ConnectMethod;
import com.biter.jdbc.utils.connect.ExecuteConnect;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
*@author  小帅
*@date 2023/11/27 20:39
*@version 1.0
*/
public class Pool {

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
     * 创建druid 连接池对象
     */
    public static DruidDataSource DruidSource;

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
    public static Properties properties = new Properties();

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

                    /*
                        DruidDataSourceFactory的createDataSource()方法创建一个DruidDataSource实例，然后将该实例转换为DruidDataSource类型，赋值给DruidSource变量。
                     */
                DruidSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            }catch (IOException e) {
               /*
                    在web项目中加载如果加载 但一定要制源配置文件才能找到

                    Load in the web project if you load but be sure to make the source profile to find
                */
                properties.load(ExecuteConnect.class.getClassLoader().getResourceAsStream("db.properties"));

                try {
                    /*
                        DruidDataSourceFactory的createDataSource()方法创建一个DruidDataSource实例，然后将该实例转换为DruidDataSource类型，赋值给DruidSource变量。
                     */
                    DruidSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
                } catch (Exception ex) {
                    e.printStackTrace();
                }


            } catch (Exception e) {
                e.printStackTrace();
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


}
