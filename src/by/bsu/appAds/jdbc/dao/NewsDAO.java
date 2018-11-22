package by.bsu.appAds.jdbc.dao;

import java.util.List;

import by.bsu.appAds.jdbc.domain.Note;
import by.bsu.appAds.jdbc.domain.User;
import by.bsu.appAds.jdbc.exceptions.PersistentException;

public interface NewsDAO extends DAO<Note> {
    List<Note> read(User user) throws PersistentException;
}
