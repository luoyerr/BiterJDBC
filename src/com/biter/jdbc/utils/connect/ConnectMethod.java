
package com.biter.jdbc.utils.connect;

import java.sql.*;

/**
 * <h2>这个接口是这个biter项目里获取连接字段的方法和关闭对象连接的方法</h2>
 * <br> </br>
 * This interface is the method for getting connection fields in the biter project
 * @author 小帅
 * @version 1.0
 * @date 2023/11/15 21:47
 */
public interface ConnectMethod {
    /**
     *  <h2>获取数据驱动的静态接口方法</h2>
     *  Get a data-driven static interface method
     *  <p></p>
     *  案例 :
     *      <br></br>
     *       String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/test3";
     *       <br></br>
     *       String dataDriver = <b>StringMethod.getDataDriver(jdbcUrl)</b>;
     *       <br></br>
     *       System.out.println("MySQL dataDriver: " + dataDriver);
     *        <br></br>
     *
     *<p></p>
     * <p></p>
     *  For example :
     *
     *     <br></br>
     *     String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/test3";
     *     <br></br>
     *     String dataDriver = <b>StringMethod.getDataDriver(jdbcUrl)</b>;
     *     <br></br>
     *     System.out.println("MySQL dataDriver: " + dataDriver);
     *     <br></br>
     *
     * @param jdbcUrl 传入的 jdbcUrl 如 : jdbc:mysql://127.0.0.1:3306/test3
     *                <p></p>
     *                Pass in jdbcUrl for example jdbc:mysql://127.0.0.1:3306/test3
     * @return String
     *      这个字符返回的是一个 <b>dataDriver</b>
     *      <br></br>
     *      This character returns a <b>dataDriver</b>
     */
    static String getDataDriver(String jdbcUrl) {
        /*
            获取第一个冒号的位置

            Gets the position of the first colon
         */
        int startIndex = jdbcUrl.indexOf(":");
        /*
            获取第二个冒号的位置

            Gets the position of the second colon
         */
        int endIndex = jdbcUrl.indexOf(":", startIndex + 1);
        /*
            返回 dataDriver jdbcUrl 里的驱动

            Return the driver in the dataDriver jdbcUrl
         */
        return jdbcUrl.substring(startIndex + 1, endIndex);
    }


    /**
     * <h2>获取数据ip地址的静态接口方法</h2>
     * Static interface method for obtaining data ip address
     * <p></p>
     *
     * 案例 :
     *          <br></br>
     *         String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/test3";
     *         <br></br>
     *         String databaseHost = <b>GetConnectMethod.getServerName(jdbcUrl)</b>;
     *         <br></br>
     *         System.out.println("MySQL Database host: " + databaseHost);
     *         <br></br>
     *<p></p>
     * <p></p>
     *  For example :
     *  <br></br>
     *          String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/test3";
     *          <br></br>
     *         String databaseHost = <b>GetConnectMethod.getServerName(jdbcUrl)</b>;
     *         <br></br>
     *         System.out.println("MySQL Database host: " + databaseHost);
     *          <br></br>
     *
     * @param jdbcUrl 传入的 jdbcUrl 如 : jdbc:mysql://127.0.0.1:3306/test3
     *             <p></p>
     *                  Pass in jdbcUrl for example jdbc:mysql://127.0.0.1:3306/test3
     *
     *
     * @return String
     *          这个字符返回的是一个 <b>serverName</b>
     */
    static String getServerName(String jdbcUrl) {

        /*
            获取从斜杠（//）开始的子字符串

            Gets a substring starting with a slash (//)
         */
        String serverName = jdbcUrl.substring(jdbcUrl.indexOf("//") + 2);


        /*
            获取从serverName字符串开始到第一个冒号（:）的子字符串

            Gets a substring from the serverName string up to the first colon (:)
         */
        serverName = serverName.substring(0, serverName.indexOf(":"));
        /*
            返回 serverName jdbcUrl 里的服务器ip地址

            Return the server ip address in serverName jdbcUrl
         */
        return serverName;
    }

    /**
     * <h2>获取数据ip地址后面的端口的静态接口方法</h2>
     * 首先 你要找到最后一个冒号的位置，然后在找到反斜杠的位置 提取中间的文本
     * <p></p>
     * Static interface method for obtaining data ip address behind the port
     * <p></p>
     * First you find the location of the last colon, then extract the text in the middle where you found the backslash
     * <p></p>
     * 案例 :
     *          <br></br>
     *         String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/test3";
     *         <br></br>
     *         String port = <b>GetConnectMethod.getPortNumber(jdbcUrl)</b>;
     *         <br></br>
     *         System.out.println("MySQL Database port: " + port);
     *         <br></br>
     * <p></p>
     * <p></p>
     *  For example :
     *  <br></br>
     *          String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/test3";
     *          <br></br>
     *         String port = <b>GetConnectMethod.getPortNumber(jdbcUrl)</b>;
     *         <br></br>
     *         System.out.println("MySQL Database port: " + port);
     *          <br></br>
     *
     * @param jdbcUrl 传入的 jdbcUrl 如 : jdbc:mysql://127.0.0.1:3306/test3
     *             <p></p>
     *                  Pass in jdbcUrl for example jdbc:mysql://127.0.0.1:3306/test3
     *
     *
     * @return String
     *          这个字符返回的是一个 <b>portNumber</b>
     */
    static String getPortNumber(String jdbcUrl) {
        /*
            找到最后一个冒号的位置

            Find the last colon
         */
        int lastColonIndex = jdbcUrl.lastIndexOf(":");
        if (lastColonIndex != -1) {
            /*
                提取端口号

                Extract port number.
             */
            String port = jdbcUrl.substring(lastColonIndex + 1);
            /*
                找到斜杠的位置

                Find slash index.
             */
            int slashIndex = port.indexOf("/");
            /*
                如果找到斜杠，就返回端口号，否则返回在字符串中找不到斜杠。

                If found slash, return port, otherwise return not found slash.

             */
            if (slashIndex != -1) {
                /*
                    返回 端口号

                    return port
                 */
                return port.substring(0, slashIndex);
            } else {
                /*
                    返回 在字符串中找不到斜杠。

                    return Slash not found in the string.

                    return null
                 */
                return null;
            }
        } else {
            /*
                返回 在字符串中找不到冒号

                return "Colon not found in the URL".

                return null;
             */
            return null;


        }
    }

    /**
     * <h2>获取数据库名字的静态接口方法</h2>
     *
     * <p></p>
     * Static interface method for getting the database name
     * <p></p>
     *
     * <p></p>
     * 案例 :
     *          <br></br>
     *         String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/test3";
     *         <br></br>
     *         String databaseName = <b>GetConnectMethod.getDatabaseName(jdbcUrl)</b>;
     *         <br></br>
     *         System.out.println("MySQL Database databaseName: " + databaseName);
     *         <br></br>
     * <p></p>
     * <p></p>
     *  For example :
     *  <br></br>
     *          String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/test3";
     *          or
     *          String url = "jdbc:mysql://localhost:3306/qq_system?useUnicode=true&characterEncoding=utf-8
     *          <br></br>
     *         String databaseName = <b>GetConnectMethod.getDatabaseName(jdbcUrl)</b>;
     *         <br></br>
     *         System.out.println("MySQL Database databaseName: " + databaseName);
     *          <br></br>
     *
     * @param jdbcUrl 传入的 jdbcUrl 如 : jdbc:mysql://127.0.0.1:3306/test3
     *             <p></p>
     *                  Pass in jdbcUrl for example jdbc:mysql://127.0.0.1:3306/test3
     *
     *
     * @return String
     *          这个字符返回的是一个 <b>databaseName</b>
     */
    static String getDatabaseName(String jdbcUrl) {
        /*
          寻找问号的位置

          Look for the location of the question mark
         */
       int questionMarkIndex = jdbcUrl.indexOf('?');


        String databaseName;
        /*
            如果问号找到

            If the question mark is found
         */
        if (questionMarkIndex != -1) {
            /*
                获取最后一个'/'的索引和问号之间的字段

                Gets the field between the index of the last '/' and the question mark
             */
            databaseName = jdbcUrl.substring(jdbcUrl.lastIndexOf('/', questionMarkIndex) + 1, questionMarkIndex);
        } else {
            /*
                获取最后一个'/'的索引

                Gets the index of the last '/'
             */
            databaseName = jdbcUrl.substring(jdbcUrl.lastIndexOf('/') + 1);
        }
        return databaseName;

    }


    /**
     * <h2>关闭 AutoCloseable 对象的方法</h2>
     *
     * 可以传多个参数 , 如 Connection , PreparedStatement , ResultSet 等一些对象的方法
     *
     * 案例 :
     *  <br></br>
     * <p></p>
     * close() 或 close(Connection conn) 或 close(PreparedStatement pst) 或
     * close(ResultSet rs) 或 close(Connection conn, PreparedStatement pst)
     * close(Connection conn, PreparedStatement pst ,ResultSet rs)...
     *
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
    static void close(AutoCloseable ...objs) {

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
     *
     *
     * @param conn 连接对象
     *                    connecting object
     */
    static void close (Connection conn) {
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
     *
     *
     * @param pst 连接对象
     *                    connecting object
     */
    static void close (PreparedStatement pst) {
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
     *
     * @param st 连接对象
     *                    connecting object
     */
    static void close (Statement st) {
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
     *
     *
     * @param rs 连接对象
     *                    connecting object
     */
    static void close (ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    };




}
