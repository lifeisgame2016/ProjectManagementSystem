package com.goit.domain.jdbc;

import com.goit.model.Developer;
import com.goit.domain.dao.DeveloperDAO;
import com.goit.model.builder.DeveloperBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JDBCDeveloperDAO implements DeveloperDAO {

    private DataSource dataSource;

   private static final Logger LOGGER = LoggerFactory.getLogger(DeveloperDAO.class);

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Developer find(Integer id){
        Developer developer = null;
        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement =
                connection.prepareStatement("SELECT * FROM DEVELOPERS WHERE id_developer = ?")){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return retrieve(resultSet);
            } else {
                throw new RuntimeException("Cannot find Developer with id " + id);
            }
        }catch (SQLException e) {
            LOGGER.error("Exception occurred while connection to DB : ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Developer> findAll(){
        List<Developer> result = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()){
            LOGGER.info("Successfully connected to DB");
            String sql = "SELECT * FROM DEVELOPERS";
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
    public void delete(Integer developerId){
        String sql = "DELETE FROM DEVELOPERS WHERE ID_DEVELOPER = ? ;";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, developerId);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void save(Developer developer){
        String sql;
        if(developer.getId() == null){
            sql = "INSERT INTO DEVELOPERS " +
                    "(name,age,address,salary,id_project)" +
                    "values(?,?,?,?,?);";
        } else {
            sql = "UPDATE DEVELOPERS SET " +
                    "NAME= ?, AGE=?, ADDRESS=?, SALARY=?, ID_PROJECT=? " +
                    "WHERE ID_DEVELOPER =? ;";
        }
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, developer.getName());
            statement.setInt(2, developer.getAge());
            statement.setString(3, developer.getAddress());
            statement.setInt(4, developer.getSalary());
            statement.setInt(5, developer.getProjectId());
            if(developer.getId() != null){
                statement.setInt(6, developer.getId());
            }
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }
    }

    private Developer retrieve(ResultSet resultSet) throws SQLException {
        return DeveloperBuilder.aDeveloper()
                .withId(resultSet.getInt("ID_DEVELOPER"))
                .withName(resultSet.getString("NAME"))
                .withAge(resultSet.getInt("AGE"))
                .withAddress(resultSet.getString("ADDRESS"))
                .withSalary(resultSet.getInt("SALARY"))
                .withProjectId(resultSet.getInt("ID_PROJECT"))
                .build();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
