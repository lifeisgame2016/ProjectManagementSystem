package com.goit.domain.jdbc;


import com.goit.model.Skill;
import com.goit.domain.dao.SkillDAO;
import com.goit.model.builder.SkillBuilder;
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
    public Skill find(Integer id) {
        Skill skill = null;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM SKILLS WHERE id_skills = ? ;")){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return retrieve(resultSet);
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
            String sql = "SELECT * FROM SKILLS;";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                Skill skill = retrieve(resultSet);
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
    public void delete(Integer skillId) {
        String sql = "DELETE FROM SKILLS WHERE ID_SKILLS=? ;";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);){

            statement.setInt(1, skillId);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB: ", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void save(Skill skill){
        String sql;
        if(skill.getId() == null){
            sql = "INSERT INTO SKILLS" +
                    "(name, id_developer)" +
                    "values(?,?);";
        } else {
            sql = "UPDATE SKILLS SET " +
                    "NAME=?, ID_DEVELOPER=?" +
                    "WHERE ID_SKILLS=?;";
        }
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);){

            statement.setString(1, skill.getName());
            statement.setInt(2, skill.getDeveloperId());
            if(skill.getId() != null){
                statement.setInt(3, skill.getId());
            }
            statement.executeUpdate();
        }
        catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB: ", e);
            throw new RuntimeException(e);
        }

    }


    private Skill retrieve(ResultSet resultSet) throws SQLException{
        return SkillBuilder.aSkill()
                .withId(resultSet.getInt("ID_SKILLS"))
                .withName(resultSet.getString("NAME"))
                .withDeveloperId(resultSet.getInt("ID_DEVELOPER"))
                .build();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
