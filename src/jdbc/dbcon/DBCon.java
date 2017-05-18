package jdbc.dbcon;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBCon
{
    // connect
    public static Connection getConn()
    {
        Properties pro = new Properties();
        try
        {
            pro.load(DBCon.class.getResourceAsStream("/jdbc/dbcon/init.properties"));
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }
        String ip = pro.getProperty("ip");
        String prot = pro.getProperty("prot");
        String dname = pro.getProperty("dname");
        String username = pro.getProperty("username");
        String password = pro.getProperty("password");
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("驱动加载失败");
        }
        StringBuffer urlsb = new StringBuffer();
        urlsb.append("jdbc:mysql://").append(ip).append(":").append(prot).append("/").append(dname).append("?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=10");
        String url = urlsb.toString();
        try
        {
            conn = DriverManager.getConnection(url, username, password);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return conn;
    }

    // close
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs)
    {
        try
        {
            if (conn != null)
            {

                conn.close();

            }
            if (ps != null)
            {

                ps.close();

            }
            if (rs != null)
            {

                rs.close();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public static void main(String[] args)
    {
        Connection conn = getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from TinyId";
        //String sql1 = "Select replace(CAST(3567890/10000 AS DECIMAL(10,2)),'.00','')";
        String sql1 = "Select @x:=1 as m";
        try
        {
            //ps = conn.prepareStatement(sql);
            ps = conn.prepareStatement(sql1);
            // ps.setInt(1, 1);
            rs = ps.executeQuery();
            while (rs.next())
            {
                System.out.println(rs.getObject(1));
            }
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            close(conn, ps, rs);
        }

    }
}
