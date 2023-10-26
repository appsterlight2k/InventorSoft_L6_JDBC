package co.inventorsoft.academy.jdbc.service;

import co.inventorsoft.academy.jdbc.exception.ServiceException;
import co.inventorsoft.academy.jdbc.model.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Long add(Company company) throws ServiceException;
    Optional<Company> get(Long id) throws ServiceException;
    boolean update(Company company) throws ServiceException;
    boolean delete(Long id) throws ServiceException;
    List<Company> getAll() throws ServiceException;

}
