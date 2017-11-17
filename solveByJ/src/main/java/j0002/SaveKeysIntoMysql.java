package j0002;

import j0001.GenerateKey;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class SaveKeysIntoMysql {

    private static final String PATH = SaveKeysIntoMysql.class.getClassLoader().getResource("").getPath()+"r0002/";
    private static final String PROP_URL = PATH+"jdbc.properties";

    private static Properties properties;
    private static String driverClassName;
    private static String url;
    private static String username;
    private static String password;

    static {
        loadProperties();
        loadAttr();
    }

    public static void main(String[] args) throws SQLException{
        String[] keys = GenerateKey.geneKeys(200);
        batchInsert(keys);
    }

    /**
     * 批量插入
     * @param keys
     */
    public static void batchInsert(String[] keys){
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            String sql = "INSERT INTO testkey (key_str) values (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            for(String key : keys){
                ps.setString(1,key);
                ps.addBatch();
            }
            ps.executeBatch();
            conn.commit();
            ps.close();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    private static Connection getConnection() throws SQLException{
        try{
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(url,username,password);
        return connection;
    }

    /**
     * 读取参数
     */
    private static void loadAttr(){
        driverClassName = properties.getProperty("driverClassName");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    /**
     * 读取properties文件
     */
    private static void loadProperties(){
        properties = new Properties();
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(PROP_URL));
            properties.load(in);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(in != null){
                try{
                    in.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
