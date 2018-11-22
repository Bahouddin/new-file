package by.bsu.appAds.jdbc.dao;

import java.util.List;

import by.bsu.appAds.jdbc.domain.Role;
import by.bsu.appAds.jdbc.domain.User;
import by.bsu.appAds.jdbc.exceptions.PersistentException;

public interface UserDAO extends DAO<User> {
    List<User> read(Role role) throws PersistentException;
    List<User> readAll() throws PersistentException;
}
