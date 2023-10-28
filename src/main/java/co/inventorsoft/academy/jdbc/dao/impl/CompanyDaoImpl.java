package co.inventorsoft.academy.jdbc.dao.impl;

import co.inventorsoft.academy.jdbc.dao.AbstractDao;
import co.inventorsoft.academy.jdbc.dao.CompanyDao;
import co.inventorsoft.academy.jdbc.dao.constants.Field;
import co.inventorsoft.academy.jdbc.exception.DaoException;
import co.inventorsoft.academy.jdbc.model.Company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static co.inventorsoft.academy.jdbc.dao.constants.Query.*;

public class CompanyDaoImpl extends AbstractDao<Company> implements CompanyDao {
    @Override
    public String getSelectQuery() {
        return SQL_COMPANY_GET;
    }

    @Override
    public String getCreateQuery() {
        return SQL_COMPANY_CREATE;
    }

    @Override
    public String getUpdateQuery() {
        return SQL_COMPANY_UPDATE;
    }

    @Override
    public String getSelectAllQuery() {
        return SQL_COMPANY_GET_ALL;
    }

    @Override
    public String getDeleteQuery() {
        return SQL_COMPANY_DELETE;
    }

    protected Company mapEntity(ResultSet rs) throws DaoException {
        try {
            Company company = new Company();
            company.setId(rs.getLong(Field.ID));
            company.setCompanyName(rs.getString(Field.COMPANY_NAME));
            company.setPhone(rs.getString(Field.COMPANY_PHONE));
            company.setAddress(rs.getString(Field.COMPANY_ADDRESS));
            company.setCity(rs.getString(Field.COMPANY_CITY));
            company.setRegion(rs.getString(Field.COMPANY_REGION));
            company.setZip(rs.getString(Field.COMPANY_ZIP));
            company.setCountry(rs.getString(Field.COMPANY_COUNTRY));

            return company;
        } catch (SQLException e) {
            throw new DaoException("Error of ResultSet processing! " + e);
        }
    }

    @Override
    protected List<Object> getEntityFields(Company entity) throws DaoException {
        if (entity == null) {
            throw new DaoException("Company object is null! Can't get fields!");
        }

        List<Object> list = new ArrayList<>();
        list.add(entity.getCompanyName());
        list.add(entity.getPhone());
        list.add(entity.getAddress());
        list.add(entity.getCity());
        list.add(entity.getRegion());
        list.add(entity.getZip());
        list.add(entity.getCountry());

        Long id = entity.getId();
        if (id != null) { // id == null in case of insert operation
            list.add(id);
        }

        return list;
    }

}
