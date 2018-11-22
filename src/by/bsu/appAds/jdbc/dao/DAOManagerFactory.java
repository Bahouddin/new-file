package by.bsu.appAds.jdbc.dao;

import by.bsu.appAds.jdbc.dao.mysql.DAOManagerImpl;
import by.bsu.appAds.jdbc.exceptions.PersistentException;

public class DAOManagerFactory {
    public static DAOManager getInstance() throws PersistentException {
        return new DAOManagerImpl();
    }
}