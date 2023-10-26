package co.inventorsoft.academy.jdbc.service.impl;

import co.inventorsoft.academy.jdbc.dao.UserDao;
import co.inventorsoft.academy.jdbc.exception.DaoException;
import co.inventorsoft.academy.jdbc.exception.ServiceException;
import co.inventorsoft.academy.jdbc.model.User;
import co.inventorsoft.academy.jdbc.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Long add(User user) throws ServiceException {
        try {
            return userDao.add(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> get(Long id) throws ServiceException {
        try {
            return userDao.get(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(User user) throws ServiceException {
        try {
            return userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            return userDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getAll() throws ServiceException {
        try {
            return userDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
