package com.goit.model.jdbc;

import com.goit.model.Project;
import com.goit.model.ProjectDAO;
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
    public Project load(int id) {
        Project project = null;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement =
                connection.prepareStatement("SELECT * FROM PROJECTS WHERE ID_PROJECT=?")){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return createProject(resultSet);
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
                Project project = createProject(resultSet);
                result.add(project);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addProject(Project project) {
        try(Connection connection = dataSource.getConnection();) {
            String sql = "INSERT INTO PROJECTS" +
                    "(ID_PROJECT,NAME,DAT_BEG,DAT_END,COST,ID_COMPANY,ID_CUSTOMER)" +
                    "VALUES(?,?,?,?,?,?,?);";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, project.getId_project());
            statement.setString(2, project.getName());
            statement.setDate(3, Date.valueOf(project.getDat_beg()));
            statement.setDate(4, Date.valueOf(project.getDat_end()));
            statement.setInt(5, project.getId_company());
            statement.setInt(6, project.getId_customer());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteProject(int id_project) {
        try(Connection connection = dataSource.getConnection();){
            String sql = "DELETE FROM PROJECTS WHERE ID_PROJECT =?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id_project);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void updateProject(Project project) {
        try(Connection connection = dataSource.getConnection();){
            String sql = "UPDATE PROJECTS SET " +
                    "ID_PROJECT=?, NAME=?, DAT_BEG=?, DAT_END=?" +
                    ",ID_COMPANY=?, ID_CUSTOMER=?, COST=?" +
                    "WHERE ID_PROJECT=?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, project.getId_project());
            statement.setString(2, project.getName());
            statement.setDate(3, Date.valueOf(project.getDat_beg()));
            statement.setDate(4, Date.valueOf(project.getDat_end()));
            statement.setInt(5, project.getId_company());
            statement.setInt(6, project.getId_customer());
            statement.setInt(7, project.getCost());
            statement.setInt(8, project.getId_project());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB : ", e);
            throw new RuntimeException(e);
        }
    }

    private Project createProject(ResultSet resultSet) throws SQLException{
        Project project = new Project();
        project.setId_project(resultSet.getInt("ID_PROJECT"));
        project.setName(resultSet.getString("NAME"));
        project.setDat_beg(resultSet.getDate("DAT_BEG").toLocalDate());
        project.setDat_end(resultSet.getDate("DAT_END").toLocalDate());
        project.setCost(resultSet.getInt("COST"));
        project.setId_company(resultSet.getInt("ID_COMPANY"));
        project.setId_customer(resultSet.getInt("ID_CUSTOMER"));
        return project;
    }
}
