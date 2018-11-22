package by.bsu.appAds.jdbc.dao;

import by.bsu.appAds.jdbc.domain.Entity;
import by.bsu.appAds.jdbc.exceptions.PersistentException;

public interface DAO<Type extends Entity> {
    Integer create(Type entity) throws PersistentException;

    Type read(Integer identity) throws PersistentException;

    void update(Type entity) throws PersistentException;

    void delete(Integer identity) throws PersistentException;
    
}
