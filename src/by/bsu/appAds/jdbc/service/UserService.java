package by.bsu.appAds.jdbc.service;

import java.util.List;

import by.bsu.appAds.jdbc.domain.User;
import by.bsu.appAds.jdbc.exceptions.PersistentException;

public interface UserService {
    List<User> findAll() throws PersistentException;
    Integer create(User user);
	Integer delete(User user);
    
}

