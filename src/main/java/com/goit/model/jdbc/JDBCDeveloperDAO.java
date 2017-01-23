package com.goit.model.jdbc;

import com.goit.model.Developer;
import com.goit.model.DeveloperDAO;
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
    /* private String url = "jdbc:postgresql://localhost:5432/company";
    private String user = "";
    private String pass = "";*/

//    public JDBCDeveloperDAO(){
//        loadDriver();
//    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Developer load(int id){
        Developer developer = null;
        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement =
                connection.prepareStatement("SELECT * FROM DEVELOPERS WHERE id_developer = ?")){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return createDeveloper(resultSet);
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
                Developer developer = createDeveloper(resultSet);
                result.add(developer);
            }

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addDeveloper(Developer developer){
        try(Connection connection = dataSource.getConnection();) {
            String sql = "INSERT INTO DEVELOPERS" +
                    "(id_developer,name,age,address,salary,id_project)" +
                    "values(?,?,?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, developer.getId_developer());
            statement.setString(2, developer.getName());
            statement.setInt(3, developer.getAge());
            statement.setString(4, developer.getAddress());
            statement.setInt(5, developer.getSalary());
            statement.setInt(6, developer.getId_project());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteDeveloper(int id_developer){
        try(Connection connection = dataSource.getConnection();){
            String sql = "DELETE FROM DEVELOPERS WHERE ID_DEVELOPER=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id_developer);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void updateDeveloper(Developer developer){
        try(Connection connection = dataSource.getConnection();){
           String sql = "UPDATE DEVELOPER SET " +
                   "NAME=?, AGE=?, ADDRESS=?, SALARY=?, ID_PROJECT=?" +
                   "WHERE ID_DEVELOPER=?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, developer.getName());
            statement.setInt(2, developer.getAge());
            statement.setString(3, developer.getAddress());
            statement.setInt(4, developer.getSalary());
            statement.setInt(5, developer.getId_project());
            statement.setInt(6, developer.getId_developer());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }
    }

    private Developer createDeveloper(ResultSet resultSet) throws SQLException {
        Developer developer = new Developer();
        developer.setId_developer(resultSet.getInt("ID_DEVELOPER"));
        developer.setName(resultSet.getString("NAME"));
        developer.setAge(resultSet.getInt("AGE"));
        developer.setAddress(resultSet.getString("ADDRESS"));
        developer.setSalary(resultSet.getInt("SALARY"));
        developer.setId_project(resultSet.getInt("ID_PROJECT"));
        return developer;
    }

//    private void loadDriver() {
//        try {
//            LOGGER.info("loading JDBC driver: org.postgresql.Driver");
//            Class.forName("org.postgresql.Driver");
//            LOGGER.info("Driver loaded successfully");
//        } catch (ClassNotFoundException e) {
//            LOGGER.error("Can not find driver: org.postgresql.Driver");
//            throw new RuntimeException(e);
//        }
//    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
