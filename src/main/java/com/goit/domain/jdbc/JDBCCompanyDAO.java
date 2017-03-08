package com.goit.domain.jdbc;


import com.goit.domain.dao.CompanyDAO;
import com.goit.model.Company;
import com.goit.model.builder.CompanyBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCCompanyDAO implements CompanyDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAO.class);
    private DataSource dataSource;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Company find(Integer id){
        Company company = null;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM COMPANIES WHERE id_company = ?;");){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return retrieve(resultSet);
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
                result.add(retrieve(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    @SuppressWarnings("all")
    public void delete(Integer companyId) {
        String sql = "DELETE FROM COMPANIES WHERE ID_COMPANY = ? ;";
        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, companyId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void save(Company company) {
        String sql;
        if(company.getId() == null){
            sql = "INSERT INTO companies " +
                    "(name, address, id_project) " +
                    "VALUES (?, ?, ?);";
        } else {
            sql = "UPDATE companies SET " +
                    "NAME=?, ADDRESS=?, ID_PROJECT=? " +
                    "WHERE ID_COMPANY=? ;";
        }
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);){

            statement.setString(1, company.getName());
            statement.setString(2, company.getAddress());
            statement.setInt(3, company.getProjectId());
            if(company.getId() != null){
                statement.setInt(4, company.getId());
            }
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }
    }

    private Company retrieve(ResultSet resultSet) throws SQLException{
        return CompanyBuilder.aCompany()
                .withId(resultSet.getInt("ID_COMPANY"))
                .withName(resultSet.getString("NAME"))
                .withAddress(resultSet.getString("ADDRESS"))
                .withProjectId(resultSet.getInt("ID_PROJECT"))
                .build();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    @Deprecated
    @Transactional(propagation = Propagation.MANDATORY)
    public void updateCompany(Company company) {
        String sql = "UPDATE COMPANIES SET " +
                "NAME = ?, ADDRESS = ?, ID_PROJECT = ? " +
                "WHERE id_company = ? ;";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, company.getName());
            statement.setString(2, company.getAddress());
            statement.setInt(3, company.getProjectId());
            statement.setInt(4, company.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Deprecated
    @Transactional(propagation = Propagation.MANDATORY)
    public void addCompany(Company company) {
        String sql = "INSERT INTO companies " +
                "(id_company, name, address, id_project) " +
                "VALUES (?, ?, ?, ?);";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, company.getId());
            statement.setString(2, company.getName());
            statement.setString(3, company.getAddress());
            statement.setInt(4, company.getProjectId());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }
    }
}
