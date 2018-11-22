package by.bsu.appAds.jdbc.service;

import java.util.ArrayList;
import java.util.List;

import by.bsu.appAds.jdbc.dao.DAOManager;
import by.bsu.appAds.jdbc.dao.NewsDAO;
import by.bsu.appAds.jdbc.dao.UserDAO;
import by.bsu.appAds.jdbc.domain.Role;
import by.bsu.appAds.jdbc.domain.User;
import by.bsu.appAds.jdbc.exceptions.PersistentException;

public class UserServiceImpl extends Service implements UserService {
    private Integer id;

	public UserServiceImpl(DAOManager daoManager) {
        super(daoManager);
    }

    @Override
    public List<User> findAll() throws PersistentException {
        try {
            UserDAO userDAO = daoManager.createDao(UserDAO.class);
            List<User> users = new ArrayList<User>();
            for(Role role : Role.values()) {
                users.addAll(userDAO.read(role));
            }
            /*NewsDAO newsDAO = daoManager.createDao(NewsDAO.class);
            for(User user : users) {
                user.getNotes().addAll(newsDAO.read(user));
            }*/
            daoManager.transactionCommit();
            return users;
        } catch(PersistentException e) {
            daoManager.transactionRollback();
            throw e;
        }
    }

	@Override
	public Integer create(User user) {
		Integer id = 0;
		 UserDAO userDAO = daoManager.createDao(UserDAO.class);
		 try {
			 System.out.println("baha");
			id = userDAO.create(user);
			
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}

	@Override
	public Integer delete(User user) {
		// TODO Auto-generated method stub
		return null;
	}
}
