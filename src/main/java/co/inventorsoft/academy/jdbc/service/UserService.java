package co.inventorsoft.academy.jdbc.service;

import co.inventorsoft.academy.jdbc.exception.ServiceException;
import co.inventorsoft.academy.jdbc.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Long add(User user) throws ServiceException;
    Optional<User> get(Long id) throws ServiceException;
    boolean update(User user) throws ServiceException;
    boolean delete(Long id) throws ServiceException;
    List<User> getAll() throws ServiceException;

}
