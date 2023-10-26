package co.inventorsoft.academy.jdbc.service.impl;

import co.inventorsoft.academy.jdbc.dao.CompanyDao;
import co.inventorsoft.academy.jdbc.exception.DaoException;
import co.inventorsoft.academy.jdbc.exception.ServiceException;
import co.inventorsoft.academy.jdbc.model.Company;

import co.inventorsoft.academy.jdbc.service.CompanyService;


import java.util.List;
import java.util.Optional;

public class CompanyServiceImpl implements CompanyService {
    private final CompanyDao companyDao;

    public CompanyServiceImpl(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Override
    public Long add(Company company) throws ServiceException {
        try {
            return companyDao.add(company);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Company> get(Long id) throws ServiceException {
        try {
            return companyDao.get(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(Company company) throws ServiceException {
        try {
            return companyDao.update(company);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            return companyDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Company> getAll() throws ServiceException {
        try {
            return companyDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
