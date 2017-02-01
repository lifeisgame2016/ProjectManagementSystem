package com.goit.model.jdbc;


import com.goit.model.Customer;
import com.goit.model.CustomerDAO;
import com.goit.model.DeveloperDAO;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(DeveloperDAO.class);

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Customer load(int id) {
        Customer customer = null;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("SELECT * FROM CUSTOMERS WHERE id_customer = ?;");){

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return createCustomer(resultSet);
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
                Customer customer = createCustomer(resultSet);
                result.add(customer);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO CUSTOMERS " +
                "(ID_CUSTOMER, NAME, ACCOUNT, ID_PROJECT) " +
                "VALUES(?, ?, ?, ?);";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, customer.getId_customer());
            statement.setString(2, customer.getName());
            statement.setDouble(3, customer.getAccount());
            statement.setInt(4, customer.getId_project());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteCustomer(int id_customer) {
        String sql = "DELETE FROM CUSTOMERS WHERE ID_CUSTOMER = ?;";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, id_customer);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void updateCustomer(Customer customer) {
        String sql = "UPDATE CUSTOMERS SET " +
                "NAME = ?, ACCOUNT = ?, ID_PROJECT = ? " +
                "WHERE ID_CUSTOMER = ?;";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, customer.getName());
            statement.setDouble(2, customer.getAccount());
            statement.setInt(3, customer.getId_project());
            statement.setInt(4, customer. getId_customer());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }

    }

    private Customer createCustomer(ResultSet resultSet) throws SQLException{
        Customer customer = new Customer();
        customer.setId_customer(resultSet.getInt("ID_CUSTOMER"));
        customer.setName(resultSet.getString("NAME"));
        customer.setAccount(resultSet.getInt("ACCOUNT"));
        customer.setId_project(resultSet.getInt("ID_PROJECT"));
        return customer;
    }
}
