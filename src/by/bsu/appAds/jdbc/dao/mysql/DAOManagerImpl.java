package by.bsu.appAds.jdbc.dao.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import by.bsu.appAds.jdbc.dao.DAOManager;
import by.bsu.appAds.jdbc.dao.NewsDAO;
import by.bsu.appAds.jdbc.dao.UserDAO;
import by.bsu.appAds.jdbc.exceptions.PersistentException;

public class DAOManagerImpl implements DAOManager {
    private static final Map<Class<?>, Class<?>> classes;

    static {
        classes = new ConcurrentHashMap<Class<?>, Class<?>>();
        classes.put(UserDAO.class, UserDAOImpl.class);
        classes.put(NewsDAO.class, NewsDAOImpl.class);
    }

    private Connection connection;

    public DAOManagerImpl() throws PersistentException {
        connection = ConnectionPool.getInstance().getConnection();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <Type> Type createDao(Class<Type> key) {
        Class<?> value = classes.get(key);
        if(value != null) {
            try {
                return (Type)value.getConstructor(Connection.class)
                        .newInstance(connection);
            } catch(Exception e) {
            }
        }
        return null;
    }

    @Override
    public void transactionCommit() throws PersistentException {
        try {
            connection.commit();
        } catch(SQLException e) {
            throw new PersistentException();
        }
    }

    @Override
    public void transactionRollback() throws PersistentException {
        try {
            connection.rollback();
        } catch(SQLException e) {
            throw new PersistentException();
        }
    }

    @Override
    public void close() {
        ConnectionPool.getInstance().freeConnection(connection);
    }
}