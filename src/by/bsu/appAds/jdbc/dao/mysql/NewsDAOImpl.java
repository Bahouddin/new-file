package by.bsu.appAds.jdbc.dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.bsu.appAds.jdbc.dao.NewsDAO;
import by.bsu.appAds.jdbc.domain.Note;
import by.bsu.appAds.jdbc.domain.User;
import by.bsu.appAds.jdbc.exceptions.PersistentException;



public class NewsDAOImpl implements NewsDAO {
	public static final String SQL_INSERT_USER = "INSERT INTO `notes` (`name`, `text`, `date`, `complete`, `user_id`) VALUES (?, ?, ?, ?, ?)";
	public static final String SQL_SELECT_USER = "SELECT `id`, `name`, `text`, `date`, `complete`, `user_id` FROM `user` WHERE `id` = ?";
	public static final String SQL_UPDATE_USER = "UPDATE `notes` SET `name` = ?, `text` = ?, `date` = ?, `complete` = ?, `user_id` = ? WHERE `id` = ?";
	public static final String SQL_DELETE_USER = "DELETE FROM `notes` WHERE `id` = ?";
	public static final String SQL_SELECT_USER_two = "SELECT `id`, `name`, `text`, `date`, `complete`, `user_id` FROM `notes` WHERE `user_id` = ?";
	
    private Connection connection;

    public NewsDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer create(Note note) throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_INSERT_USER,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, note.getName());
            statement.setString(2, note.getText());
            statement.setDate(3, new Date(note.getDate().getTime()));
            statement.setBoolean(4, note.isComplete());
            statement.setInt(5, note.getUser().getId());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            }
            return null;
        } catch(SQLException e) {
            throw new PersistentException();
        } finally {
            try {
                resultSet.close();
            } catch(Exception e) {
            }
            try {
                statement.close();
            } catch(Exception e) {
            }
        }
    }

    @Override
    public Note read(Integer id) throws PersistentException {
        Note note = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            
            statement = connection.prepareStatement(SQL_SELECT_USER );
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                note = new Note();
                note.setId(resultSet.getInt("id"));
                note.setName(resultSet.getString("name"));
                note.setText(resultSet.getString("text"));
                note.setDate(new java.util.Date(resultSet.getDate("date").getTime()));
                note.setComplete(resultSet.getBoolean("complete"));
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                note.setUser(user);
            }
            return note;
        } catch(SQLException e) {
            throw new PersistentException();
        } finally {
            try {
                resultSet.close();
            } catch(Exception e) {
            }
            try {
                statement.close();
            } catch(Exception e) {
            }
        }
    }

    @Override
    public void update(Note note) throws PersistentException {
        PreparedStatement statement = null;
        try {
            
            statement = connection.prepareStatement(SQL_UPDATE_USER);
            statement.setString(1, note.getName());
            statement.setString(2, note.getText());
            statement.setDate(3, new Date(note.getDate().getTime()));
            statement.setBoolean(4, note.isComplete());
            statement.setInt(5, note.getUser().getId());
            statement.setInt(6, note.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new PersistentException();
        } finally {
            try {
                statement.close();
            } catch(Exception e) {
            }
        }
    }

    @Override
    public void delete(Integer identity) throws PersistentException {
        PreparedStatement statement = null;
        try { 
            statement = connection.prepareStatement(SQL_DELETE_USER);
            statement.setInt(1, identity);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new PersistentException();
        } finally {
            try {
                statement.close();
            } catch(Exception e) {
            }
        }
    }

    @Override
    public List<Note> read(User user) throws PersistentException {
        List<Note> notes = new ArrayList<Note>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
             
            statement = connection.prepareStatement(SQL_SELECT_USER_two);
            statement.setInt(1, user.getId());
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Note note = new Note();
                note.setId(resultSet.getInt("id"));
                note.setName(resultSet.getString("name"));
                note.setText(resultSet.getString("text"));
                note.setDate(new java.util.Date(resultSet.getDate("date").getTime()));
                note.setComplete(resultSet.getBoolean("complete"));
                note.setUser(user);
                notes.add(note);
            }
            return notes;
        } catch(SQLException e) {
            throw new PersistentException();
        } finally {
            try {
                resultSet.close();
            } catch(Exception e) {
            }
            try {
                statement.close();
            } catch(Exception e) {
            }
        }
    }

}