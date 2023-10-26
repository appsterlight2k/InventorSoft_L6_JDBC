package co.inventorsoft.academy.jdbc.dao;

import co.inventorsoft.academy.jdbc.exception.DaoException;
import co.inventorsoft.academy.jdbc.model.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyDao extends Dao<Company> {
    Long add(Company company) throws DaoException;
    Optional<Company> get(Long id) throws DaoException;
    boolean update(Company company) throws DaoException;
    boolean delete(Long id) throws DaoException;
    List<Company> getAll() throws DaoException;
}
