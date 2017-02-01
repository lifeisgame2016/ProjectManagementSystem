package com.goit.model.jdbc;


import com.goit.model.Skill;
import com.goit.model.SkillDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCSkillDAO implements SkillDAO {

    private DataSource dataSource;
    private static final Logger LOGGER = LoggerFactory.getLogger(SkillDAO.class);

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Skill load(int id) {
        Skill skill = null;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM SKILLS WHERE id_skills = ?")){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return createSkill(resultSet);
            } else {
                throw new RuntimeException("Cannot find Skill with id " + id);
            }

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connection to DB : ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Skill> findAll() {
        List<Skill> result = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement()){
            String sql = "SELECT * FROM SKILLS";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                Skill skill = createSkill(resultSet);
                result.add(skill);
            }
        } catch (SQLException e) {
           LOGGER.error("Exception occurred while connecting to DB: ", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addSkill(Skill skill) {
        String sql = "INSERT INTO SKILLS" +
                "(id_skills, name, id_developer)" +
                "values(?,?,?);";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);){

            statement.setInt(1, skill.getId_skills());
            statement.setString(2, skill.getName());
            statement.setInt(3, skill.getId_developer());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB: ", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteSkill(int id_skill) {
        String sql = "DELETE FROM SKILLS WHERE ID_SKILLS=?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);){

            statement.setInt(1, id_skill);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB: ", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void updateSkill(Skill skill) {
        String sql = "UPDATE SKILLS SET " +
                "ID_SKILLS=?, NAME=?, ID_DEVELOPER=?" +
                "WHERE ID_SKILLS=?;";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);){

            statement.setInt(1, skill.getId_skills());
            statement.setString(2, skill.getName());
            statement.setInt(3, skill.getId_developer());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB: ", e);
            throw new RuntimeException(e);
        }

    }


    private Skill createSkill(ResultSet resultSet) throws SQLException{
        Skill skill = new Skill();
        skill.setId_skills(resultSet.getInt("ID_SKILLS"));
        skill.setName(resultSet.getString("NAME"));
        skill.setId_developer(resultSet.getInt("ID_DEVELOPER"));
        return skill;
    }
}
