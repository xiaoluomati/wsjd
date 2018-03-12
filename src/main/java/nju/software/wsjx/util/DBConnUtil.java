																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																			package nju.software.wsjx.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 连接Sybase数据库工具类
 * Created by Hufk on 2017/08/08.
 */
public class DBConnUtil {
    public static Connection getSybaseConnection(){
        String Driver = "com.sybase.jdbc3.jdbc.SybDriver";  //这里要特别注意，驱动有的网上给的是错误的
        String url = "jdbc:sybase:Tds:130.1.198.53:5000/WSJX?charset=cp936"; // 连接的数据库是WSJX
        String Userid = "sa";
        String Password = "";
        Connection conn = null;

        try {
            Class.forName(Driver);
            conn = DriverManager.getConnection(url, Userid, Password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection getMysqlConnection(){
        String Driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/hufk";
        String Userid = "root";
        String Password = "123456";
        Connection conn = null;

        try {
            Class.forName(Driver);
            conn = DriverManager.getConnection(url, Userid, Password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
