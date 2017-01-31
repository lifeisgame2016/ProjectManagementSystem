package com.goit.model.jdbc;


import com.goit.model.Company;
import com.goit.model.CompanyDAO;
import com.goit.model.DeveloperDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
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
        List<Company> result = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();){
            String sql = "SELECT * FROM COMPANIES;";
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Company company = createCompany(resultSet);
                result.add(company);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addCompany(Company company) {
        String sql = "INSERT INTO companies " +
                "(id_company, name, address, id_project) " +
                "VALUES (?, ?, ?, ?);";
        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, company.getId_company());
            statement.setString(2, company.getName());
            statement.setString(3, company.getAddress());
            statement.setInt(4, company.getId_project());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteCompany(int id_company) {
        String sql = "DELETE FROM COMPANIES WHERE ID_COMPANY = ? ;";
        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, id_company);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void updateCompany(Company company) {
        String sql = "UPDATE COMPANIES SET " +
                "NAME = ?, ADDRESS = ?, ID_PROJECT = ? " +
                "WHERE id_company = ? ;";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, company.getName());
            statement.setString(2, company.getAddress());
            statement.setInt(3, company.getId_project());
            statement.setInt(4, company.getId_company());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }

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
