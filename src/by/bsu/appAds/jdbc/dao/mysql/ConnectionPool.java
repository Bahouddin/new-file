package by.bsu.appAds.jdbc.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import by.bsu.appAds.jdbc.exceptions.PersistentException;

;

final public class ConnectionPool {
    public static final boolean AUTO_COMMIT = false;

    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/ads?encoding=UTF-8&useUnicode=true&characterEncoding=UTF-8";
    public static final String DB_USER = "user_ads";
    public static final String DB_PASSWORD = "user_pw";

    private BlockingQueue<Connection> connections = new LinkedBlockingQueue<Connection>();

    private ConnectionPool() {
    	System.out.println("create instance of ConnectionPool");
    }

    synchronized public Connection getConnection() throws PersistentException {
        Connection connection = null;
        while(connection == null) {
            try {
                if(connections.isEmpty()) {
                    connection = DriverManager.getConnection(DB_URL, DB_USER,
                            DB_PASSWORD);
                    connection.setAutoCommit(AUTO_COMMIT);
                } else {
                    connection = connections.take();
                    if(!connection.isValid(0)) {
                        connection = null;
                    }
                }
            } catch(InterruptedException e) {
                throw new PersistentException();
            } catch(SQLException e) {
                throw new PersistentException();
            }
        }
        return connection;
    }

    public void freeConnection(Connection connection) {
        try {
            connections.put(connection);
        } catch(InterruptedException e) {
        }
    }

    public void init() throws PersistentException {
        try {
            Class.forName(DRIVER_CLASS);
        } catch(ClassNotFoundException e) {
            throw new PersistentException();
        }
    }

    private static ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance() {
    	System.out.println("get instance of ConnectionPool");
        return instance;
    }
}