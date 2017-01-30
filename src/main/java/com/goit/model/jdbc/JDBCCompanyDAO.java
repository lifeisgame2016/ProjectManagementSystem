package com.goit.model.jdbc;


import com.goit.model.Company;
import com.goit.model.CompanyDAO;
import com.goit.model.DeveloperDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JDBCCompanyDAO implements CompanyDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeveloperDAO.class);
    private DataSource dataSource;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Company load(int id) {
        Company company = null;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM COMPANIES WHERE id_company = ?;");){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return createCompany(resultSet);
            } else {
                throw new RuntimeException("Cannot find Company with id " + id);
            }
        }catch (SQLException e) {
            LOGGER.error("Exception occurred while connection to DB : ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Company> findAll() {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addCompany(Company company) {

    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteCompany(int id_company) {

    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void updateCompany(Company company) {

    }

    private Company createCompany(ResultSet resultSet) throws SQLException{
        Company company = new Company();
        company.setId_company(resultSet.getInt("ID_COMPANY"));
        company.setName(resultSet.getString("NAME"));
        company.setAddress(resultSet.getString("ADDRESS"));
        company.setId_project(resultSet.getInt("ID_PROJECT"));
        return company;
    }
}
