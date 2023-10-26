package co.inventorsoft.academy.jdbc.dao.impl;

import co.inventorsoft.academy.jdbc.dao.CompanyDao;
import co.inventorsoft.academy.jdbc.dao.constants.Field;
import co.inventorsoft.academy.jdbc.dao.utils.QueryUtil;
import co.inventorsoft.academy.jdbc.exception.DaoException;
import co.inventorsoft.academy.jdbc.model.Company;
import co.inventorsoft.academy.jdbc.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static co.inventorsoft.academy.jdbc.dao.constants.Query.*;

public class CompanyDaoImpl implements CompanyDao {

    @Override
    public Long add(Company company) throws DaoException {
        long result = -1;

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement prst = QueryUtil.prepareStatement(connection, SQL_COMPANY_CREATE,true,
                     QueryUtil.getFieldsForStatement(getAllFieldsOfObject(company), false))) {
            if (prst.executeUpdate() > 0) {
                ResultSet rs = prst.getGeneratedKeys();
                if (rs.next()) {
                    result = rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }

        return result;

    }

    @Override
    public Optional<Company> get(Long id) throws DaoException {
        Company company = null;

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement prst = connection.prepareStatement(SQL_COMPANY_GET)) {
            prst.setLong(1, id);
            ResultSet rs = prst.executeQuery();
            if (rs.next()) {
                company = mapEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return Optional.ofNullable(company);

    }

    @Override
    public boolean update(Company company) throws DaoException {
        boolean result;

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement prst = QueryUtil.prepareStatement(connection, SQL_COMPANY_UPDATE,
                     false, QueryUtil.getFieldsForStatement(getAllFieldsOfObject(company), true)))  {
            result = prst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }

        return result;

    }

    @Override
    public boolean delete(Long id) throws DaoException {
        boolean result;

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement prst = connection.prepareStatement(SQL_COMPANY_DELETE)) {
            prst.setLong(1, id);
            result = prst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }

        return result;
    }

    @Override
    public List<Company> getAll() throws DaoException {
        List<Company> companies = new ArrayList<>();

        try (Connection con = ConnectionUtil.getConnection();
             Statement st = con.createStatement();) {

            ResultSet rs = st.executeQuery(SQL_COMPANY_GET_ALL);
            while (rs.next()) {
                companies.add(mapEntity(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return companies;
    }

    protected Company mapEntity(ResultSet rs) throws DaoException {
        try {
            Company company = new Company();
            company.setId(rs.getLong(Field.COMPANY_ID));
            company.setCompanyName(rs.getString(Field.COMPANY_NAME));
            company.setPhone(rs.getString(Field.COMPANY_PHONE));
            company.setAddress(rs.getString(Field.COMPANY_ADDRESS));
            company.setCity(rs.getString(Field.COMPANY_CITY));
            company.setRegion(rs.getString(Field.COMPANY_REGION));
            company.setZip(rs.getString(Field.COMPANY_ZIP));
            company.setCountry(rs.getString(Field.COMPANY_COUNTRY));

            return company;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected Object[] getAllFieldsOfObject(Company company) throws DaoException {
        try {
            return new Object[]{
                    company.getCompanyName(),
                    company.getPhone(),
                    company.getAddress(),
                    company.getCity(),
                    company.getRegion(),
                    company.getZip(),
                    company.getCountry(),
                    company.getId(),
            };
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new DaoException("Company object is null! Can't get fields!");
        }
    }

}
