package com.goit.domain.jdbc;


import com.goit.model.Customer;
import com.goit.domain.dao.CustomerDAO;
import com.goit.model.builder.CustomerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCCustomerDAO implements CustomerDAO {

    private DataSource dataSource;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDAO.class);

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Customer find(Integer id) {
        Customer customer = null;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("SELECT * FROM CUSTOMERS WHERE id_customer = ?;");){

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return retrieve(resultSet);
            } else {
                throw new RuntimeException("Cannot find Developer with id " + id);
            }

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connection to DB : ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Customer> findAll() {
        List<Customer> result = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();) {

            String sql = "SELECT * FROM CUSTOMERS;";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
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
    public void delete(Integer customerId) {
        String sql = "DELETE FROM CUSTOMERS WHERE ID_CUSTOMER = ?;";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, customerId);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void save(Customer customer){
        String sql;
        if(customer.getId() == null){
            sql = "INSERT INTO CUSTOMERS " +
                    "(NAME, ACCOUNT, ID_PROJECT) " +
                    "VALUES(?, ?, ?);";
        } else {
            sql = "UPDATE CUSTOMERS SET " +
                    "NAME = ?, ACCOUNT = ?, ID_PROJECT = ? " +
                    "WHERE ID_CUSTOMER = ?;";
        }

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, customer.getName());
            statement.setDouble(2, customer.getAccount());
            statement.setInt(3, customer.getProjectId());
            if(customer.getId() != null){
                statement.setInt(4, customer.getId());
            }
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }
    }


    private Customer retrieve(ResultSet resultSet) throws SQLException{
        return CustomerBuilder.aCustomer()
                .withId(resultSet.getInt("ID_CUSTOMER"))
                .withName(resultSet.getString("NAME"))
                .withAccount(resultSet.getDouble("ACCOUNT"))
                .withProjectId(resultSet.getInt("ID_PROJECT"))
                .build();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
