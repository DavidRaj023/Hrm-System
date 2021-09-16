package com.infinira.hr.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.Properties;
import java.text.MessageFormat;

public class DatabaseService {

    private volatile static DatabaseService instance = null;

    private String url;
    private String user;
    private String password;

    private final String PROPERTIES_FILE = "hr.properties";
    private final String DB_URL = "db.url";
    private final String DB_USER = "db.user";
    private final String DB_PASSWORD = "db.password";

    public static DatabaseService getInstance() {
        if ( instance == null ) {
            synchronized(DatabaseService.class) {
                if ( instance == null ) {
                    instance = new DatabaseService();
                }
            }
        }
        return instance;
    }
    
    
    private DatabaseService() {
        InputStream stream = null;
        try {
            stream = ClassLoader.getSystemClassLoader().getResourceAsStream(PROPERTIES_FILE);
            if (stream == null) {
                throw new RuntimeException( MessageFormat.format(INVALID_FILE, PROPERTIES_FILE ) );
            } else {
                Properties properties = new Properties();
                properties.load(stream);
                url = properties.getProperty(DB_URL);
                user = properties.getProperty(DB_USER);
                password = properties.getProperty(DB_PASSWORD);
            }
        } catch (Throwable th) {
            throw new RuntimeException(MessageFormat.format(LOAD_FAILED, PROPERTIES_FILE), th);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (Throwable th) { }
            }
        }
    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (Throwable th) {
            throw new RuntimeException( MessageFormat.format(CONNECTION_FAILED, url, user), th);
        }
    }
    
    public void closeResources(ResultSet rs, PreparedStatement pstmt, Connection conn){
        if (rs != null) {
            try {
                rs.close();
            } catch (Throwable th) { }
        }
        
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (Throwable th) { }
        }
        
        if (conn != null) {
            try {
                conn.close();
            } catch (Throwable th) { }
        }
    }   

    private final String INVALID_FILE = "Can not Find this {0} file.";
    private final String LOAD_FAILED = "Could not load this {0} file.";
    private final String CONNECTION_FAILED = "Unable to Connect this Database url:  {0} , User: {1}";

}
