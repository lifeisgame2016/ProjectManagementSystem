package com.goit.domain.jdbc;

import com.goit.model.Project;
import com.goit.domain.dao.ProjectDAO;
import com.goit.model.builder.ProjectBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JDBCProjectDAO implements ProjectDAO {

    private DataSource dataSource;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectDAO.class);

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Project find(Integer id) {
        Project project = null;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement =
                connection.prepareStatement("SELECT * FROM PROJECTS WHERE ID_PROJECT=?")){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return retrieve(resultSet);
            } else{
                throw new RuntimeException("Cannot find Project with id " + id);

            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connection to DB : ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Project> findAll() {
        List<Project> result = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM PROJECTS;";
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Project project = retrieve(resultSet);
                result.add(project);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    @SuppressWarnings("all")
    @Transactional(propagation = Propagation.MANDATORY)
    public void delete(Integer projectId) {
        String sql = "DELETE FROM PROJECTS WHERE ID_PROJECT =?;";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);){

            statement.setInt(1, projectId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void save(Project project) {
        String sql;
        if(project.getId() == null){
            sql = "INSERT INTO PROJECTS" +
                    "(NAME,DAT_BEG,DAT_END,COST,ID_COMPANY,ID_CUSTOMER)" +
                    "VALUES(?,?,?,?,?,?);";
        } else {
            sql = "UPDATE PROJECTS SET " +
                    "NAME=?, DAT_BEG=?, DAT_END=?, " +
                    "COST=?, ID_COMPANY=?, ID_CUSTOMER=? " +
                    "WHERE ID_PROJECT=?;";
        }
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, project.getName());
            statement.setDate(2, Date.valueOf(project.getDatBeg()));
            statement.setDate(3, Date.valueOf(project.getDatEnd()));
            statement.setInt(4, project.getCost());
            statement.setInt(5, project.getCompanyId());
            statement.setInt(6, project.getCustomerId());
            if(project.getId() != null){
                statement.setInt(7, project.getId());
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }

    }


    private Project retrieve(ResultSet resultSet) throws SQLException{
        return ProjectBuilder.aProject()
                .withId(resultSet.getInt("ID_PROJECT"))
                .withName(resultSet.getString("NAME"))
                .withDatBeg(resultSet.getDate("DAT_BEG").toLocalDate())
                .withDatEnd(resultSet.getDate("DAT_END").toLocalDate())
                .withCost(resultSet.getInt("COST"))
                .withCompanyId(resultSet.getInt("ID_COMPANY"))
                .withCustomerId(resultSet.getInt("ID_CUSTOMER"))
                .build();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
