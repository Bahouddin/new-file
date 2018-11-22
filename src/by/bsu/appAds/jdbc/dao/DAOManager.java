package by.bsu.appAds.jdbc.dao;

import by.bsu.appAds.jdbc.exceptions.PersistentException;

public interface DAOManager {
    <Type> Type createDao(Class<Type> key);

    void transactionCommit() throws PersistentException;

    void transactionRollback() throws PersistentException;

    void close();
}