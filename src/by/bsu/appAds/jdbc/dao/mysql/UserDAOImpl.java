package by.bsu.appAds.jdbc.dao.mysql;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.bsu.appAds.jdbc.dao.UserDAO;
import by.bsu.appAds.jdbc.domain.Role;
import by.bsu.appAds.jdbc.domain.User;
import by.bsu.appAds.jdbc.exceptions.PersistentException;

public class UserDAOImpl implements UserDAO {
	//user
	public static final String SQL_INSERT_USER = "INSERT INTO `user` (`login`,`password`, `role`) VALUES (?, ?,?)";
	//user_info insert
	public static final String SQL_INSERT_USER_INFO = "INSERT INTO `user_info`(`id`,`lastname`,`firstname`,`patronymic`,`email`,`phone`,`city`,`img`) VALUES (?, ?, ?,?,?,?,?,null)";
	//user insert
	public static final String SQL_SELECT_USER = "SELECT  `login`,`password`, `role` FROM `users` WHERE `` = ?";
	//user_info select
	public static final String SQL_SELECT_USER_INFO = "Select `id`,`lastname`,`firstname`,`patronymic`,`email`,`phone`,`city`,`img` from `user_info` where `` = ?";  
	//user updat
	public static final String SQL_UPDATE_USER = "UPDATE `user` SET `login` = ?,`password` = ?, `role` = ? WHERE `id` = ?";
	//user_info update
	public static final String SQL_UPDATE_USER_INFO ="UPDATE `user_info` SET 'id' = ?, 'lastname' = ?, 'firstname' = ?, 'patronymic' = ?, 'email' = ?, 'phone'= ?, 'city' = ?, 'img' = ?, where  `id` = ? ";
	//user delete 
	public static final String SQL_DELETE_USER = "DELETE FROM `user` WHERE `id` = ?";
    //user	
	public static final String SQL_SELECT_USER_ON_ROLE = "SELECT `id`, `login`, `role`,`password` FROM `user` WHERE `role`=?";
    // select all users
	public static final String SQL_SELECT_ALL_USERS = "SELECT `user`.`id` AS `id`,  `login`, `role`,`password`,`lastname`,`firstname`,`phone`,`email`,`city` FROM `user` INNER JOIN `user_info` on user.id = user_info.id";
    //delete user_info 
	public static final String SQL_DELETE_USER_INFO = "DELETE FROM `user_info` SET `id` = ?";	
    //insert advercitetment
	public static final String SQL_INSERT_ADVERTISEMENT = "INSERT INTO `advertisement`(`id`,`date`,`user_info_id`,`text`,`path_img`,`price`,`status`,`isSell`,`category_id`) VALUES(?,?,?,?,?,?,?,?,?)";
	//SELECT advercitetment
	public static final String SQL_SELECT_ADVERTISEMENT = "SELECT `id`,`date`,`user_info_id`,`text`,`path_img`,`price`,`status`,`isSell`,`category_id` where `` = ?";  
	//update advercitetment		
	public static final String SQL_UPDATE_ADVERTISEMENT = "UPDATE `advercitetment` SET `id` = ?,`date` = ?, `user_info_id` = ?,`text` = ?, `path_img` = ?, `price` = ?, `status`= ?,`isSell` = ?,`category_id` = ?, where `id` = ? ";
	//delete advercitetment	
	public static final String SQL_DELETE_ADVERTISEMENT = "DELETE FROM `advercitetment` SET `id`";	
	 
	private Connection connection;

	public UserDAOImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Integer create(User user) throws PersistentException {
		System.out.println(user.toString());
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		PreparedStatement statement3 = null;
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		ResultSet resultSet3 = null;
		Integer id = 0;
		try {
			statement = connection.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());
			statement.setInt(3, user.getRole().ordinal());
			
			statement.executeQuery();
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				System.out.println("Пользоветель создан");
				System.out.println("id = " + resultSet.getInt(1));
				id = resultSet.getInt(1);
				
			}
			user.setId(id);
			///insert user_info 
			//statement.close();
			statement2 = connection.prepareStatement(SQL_INSERT_USER_INFO, Statement.RETURN_GENERATED_KEYS);
		
			statement2.setInt(1, user.getId());
			statement2.setString(2, user.getLastname());
			statement2.setString(3, user.getFirstname());
			statement2.setString(4, user.getPatronymic());
			statement2.setString(5, user.getEmail());
			statement2.setLong(6, user.getPhone());
			statement2.setString(7, user.getCity());
			//statement2.setString(8, null);
			System.out.println(statement2.toString());
			statement2.executeQuery() ;//executeUpdate();
			resultSet2 = statement2.getGeneratedKeys();
			if (resultSet.next()) {
				System.out.println("Пользоветель инфо создано");
				System.out.println("id = " + resultSet.getInt(1));
			}
			statement3 = connection.prepareStatement(SQL_INSERT_ADVERTISEMENT, Statement.RETURN_GENERATED_KEYS);
			statement3.setInt(1, user.getId());
			statement3.setDate(2, user.getDate());
			statement3.setInt(3, user.getUser_info_id());
			statement3.setString(4, user.getText());
			statement3.setString(5, user.getPath_img());
			statement3.setString(6, user.getText());
			statement3.setLong(7, user.getPrice());
			statement3.setBoolean(8, user.getStatus());
			statement3.setBoolean(9, user.getIsSell());
			statement3.setInt(10, user.getCategory_id());
		} catch (SQLException e) {
			throw new PersistentException();
		} finally {
			
			System.out.println("id = " + id);
			try {
				resultSet.close();
				resultSet2.close();
				resultSet3.close();
			} catch (Exception e) {
			}
			try {
				statement.close();
				statement2.close();
				statement3.close();
			} catch (Exception e) {
			}
		}
		System.out.println("id = " + id);
		return id;
	}

	@Override
	public User read(Integer identity) throws PersistentException {
		User user = null;
		User user2 = null;
		User user3 = null;
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		PreparedStatement statement3 = null;
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		ResultSet resultSet3 = null;
		try {

			//select user
			statement = connection.prepareStatement(SQL_SELECT_USER);
			statement.setInt(1, identity);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				user.setRole(Role.values()[resultSet.getInt("role")]);
			}
			//select user_info
			statement2 = connection.prepareStatement(SQL_SELECT_USER_INFO);
			statement2.setInt(2,identity);
			resultSet2 = statement.executeQuery();
			if (resultSet2.next()) {
				user2 = new User();
				user2.setId(resultSet.getInt("id"));
				user2.setLastname(resultSet.getString("lastname"));
				user2.setFirstname(resultSet.getString("firstname"));
				user2.setPatronymic(resultSet.getString("patronymic"));
				user2.setEmail(resultSet.getString("email"));
				user2.setPhone(resultSet.getInt("phone"));
				user2.setCity(resultSet.getString("city"));
				user2.setPicture(null);
			}
			statement3 = connection.prepareStatement(SQL_SELECT_ADVERTISEMENT);
			statement3.setInt(2,identity);
			resultSet3 = statement.executeQuery();
			if (resultSet3.next()) {
				user3 = new User();
				user3.setId(resultSet.getInt("id"));
				user3.setDate(resultSet.getDate("date"));
				user3.setUser_info_id(resultSet.getInt("user_info_id"));
				user3.setText(resultSet.getString("text"));
				user3.setPath_img(resultSet.getString("path_img"));
				user3.setPrice(resultSet.getInt("price"));
				user3.setStatus(resultSet.getString("status"));
				user3.setIsSell(resultSet.getBoolean("isSell"));
				user3.setCategory_id(resultSet.getInt("category_id"));
			}
			return user;
		} catch (SQLException e) {
			throw new PersistentException();
		} finally {
			try {
				resultSet.close();
			} catch (Exception e) {
			}
			try {
				statement.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public void update(User unit) throws PersistentException {
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		PreparedStatement statement3 = null;
		try {
              ///user update 
			statement = connection.prepareStatement(SQL_UPDATE_USER);
			statement.setString(1, unit.getLogin());
			statement.setString(2, unit.getPassword());
			statement.setInt(3, unit.getRole().ordinal());
			statement.setInt(4, unit.getId());
			statement.executeUpdate();
			//user_info update
			statement2 = connection.prepareStatement(SQL_UPDATE_USER_INFO);
			statement2.setLong(1, unit.getId());
			statement2.setString(2, unit.getLastname());
			statement2.setString(2, unit.getFirstname());
			statement2.setString(2, unit.getPatronymic());
			statement2.setString(2, unit.getEmail());
			statement2.setLong(2, unit.getPhone());
			statement2.setString(2, unit.getCity());
			statement2.setBlob(2, unit.getPicture());
			statement2.executeUpdate();
			
			statement3 = connection.prepareStatement(SQL_UPDATE_ADVERTISEMENT);
			statement3.setLong(1, unit.getId());
			statement3.setDate(2, unit.getDate());
			statement3.setInt(3, unit. getUser_info_id());
			statement3.setString(4, unit. getText());
			statement3.setString(5, unit. getPath_img());
			statement3.setInt(6, unit. getPrice());
			statement3.setBoolean(7, unit.getStatus());
			statement3.setBoolean(8, unit.getIsSell());
			statement3.setInt(9, unit.getCategory_id());
		} catch (SQLException e) {
			throw new PersistentException();
		} finally {
			try {
				statement.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public void delete(Integer id) throws PersistentException {
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		PreparedStatement statement3 = null;
		try {
            //delete user
			statement = connection.prepareStatement(SQL_DELETE_USER);
			statement.setInt(1, id);
			//delete user_info
			statement2 = connection.prepareStatement(SQL_DELETE_USER_INFO);
			statement2.setInt(1, id);
			statement3 = connection.prepareStatement(SQL_DELETE_ADVERTISEMENT);
			statement3.setInt(1,id);
			statement.executeUpdate();
			statement2.executeUpdate();
		} catch (SQLException e) {
			throw new PersistentException();
		} finally {
			try {
				statement.close();
				statement2.close();
				statement3.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public List<User> read(Role role) throws PersistentException {
		List<User> result = new ArrayList<User>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {

			statement = connection.prepareStatement(SQL_SELECT_USER_ON_ROLE);
			statement.setInt(1, role.ordinal());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				user.setRole(Role.values()[resultSet.getInt("role")]);
				result.add(user);
			}
			return result;
		} catch (SQLException e) {
			throw new PersistentException();
		} finally {
			try {
				resultSet.close();
			} catch (Exception e) {
			}
			try {
				statement.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public List<User> readAll() throws PersistentException {
		List<User> result = new ArrayList<User>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			//"SELECT `user`.`id` AS `id`,  `login`, `role`,`password`,`lastname`,
			//`firstname`,`phone`,`email`,`city` FROM `user`
			//INNER JOIN `user_info` on user.id = user_info.id";
			statement = connection.prepareStatement(SQL_SELECT_USER_ON_ROLE);
			statement.setInt(0, 0);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(("password"));
				user.setRole(Role.values()[resultSet.getInt("role")]);
				user.setLastname(resultSet.getString("lastname"));
				user.setFirstname(resultSet.getString("firstname"));
				user.setPhone(resultSet.getInt("phone"));
				user.setEmail(resultSet.getString("email"));
				user.setCity(resultSet.getString("city"));
				result.add(user);
				
			}
			return result;
		} catch (SQLException e) {
			throw new PersistentException();
		} finally {
			try {
				resultSet.close();
			} catch (Exception e) {
			}
			try {
				statement.close();
			} catch (Exception e) {
			}
		}
	
	}
}