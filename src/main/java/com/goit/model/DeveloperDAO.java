package com.goit.model;

import java.util.List;

/**
 * Created by Den on 18-Dec-16.
 */
public interface DeveloperDAO {

    Developer load(int id);

    List<Developer> findAll();

    void addDeveloper(Developer developer);

    void deleteDeveloper(int id_developer);

    void updateDeveloper(Developer developer);


}
