package com.wbq.person.dbutils;

import com.wbq.person.entity.Person;
import com.wbq.person.mapper.IQueryMapper;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName DBUtils
 * @Description 数据库工具类
 * @Author Administrator
 * @Date 2020/12/11 16:59
 * @Version 1.0
 */
public class DBUtils<T> {
    public static final Properties PROPERTIES = new Properties();
    public static final ThreadLocal<Connection> THREAD_LOCAL = new ThreadLocal<>();

    static {
        InputStream resourceAsStream = DBUtils.class.getResourceAsStream("/db.properties");
        try {
            PROPERTIES.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection con = THREAD_LOCAL.get();
        try {
            if (con == null) {
                String url = PROPERTIES.getProperty("url");
                String username = PROPERTIES.getProperty("username");
                String password = PROPERTIES.getProperty("password");
                Connection connection = DriverManager.getConnection(url, username, password);
                THREAD_LOCAL.set(connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void begin() {
        Connection con = getConnection();
        try {
            con.setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void commit() {
        Connection con = getConnection();
        try {
            con.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void rollback() {
        Connection con = getConnection();
        try {
            con.rollback();
            closeAll(null, null, con);
            THREAD_LOCAL.remove();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void closeAll(ResultSet rs, Statement st, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static int update(String sql, Object... args) {
        Connection con = getConnection();
        Statement st = null;
        try {
            st = con.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ((PreparedStatement) st).setObject(i + 1, args[i]);
            }
            return ((PreparedStatement) st).executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll(null, st, con);
        }
        return 0;
    }

    public static <T> List<T> queryListPerson(String sql, IQueryMapper<T> mapper, Object... args) {
        Connection con = getConnection();
        Statement st = null;
        ResultSet rs;
        List<T> list = new ArrayList<>();
        try {
            st = con.prepareStatement(sql);
            if (args != null & args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    ((PreparedStatement) st).setObject(i + 1, args[i]);
                }
            }
            rs = ((PreparedStatement) st).executeQuery();
            list = mapper.makeData(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll(null, st, con);
        }
        return list;
    }

    public static <T> T queryPerson(String sql, IQueryMapper<T> mapper, int id) {
        Connection con = getConnection();
        Statement st = null;
        ResultSet rs;
        try {
            st = con.prepareStatement(sql);
            ((PreparedStatement) st).setInt(0, id);
            rs = ((PreparedStatement) st).executeQuery();
            List<Person> list = mapper.makeData(rs);
            if (list != null && list.size() > 0) {
                return (T) list.get(0);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll(null, st, con);
        }
        return null;
    }

}
