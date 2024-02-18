package brainacad_org.dao;

import java.util.List;

public interface CRUDInterface<T> {

    void save(T course);

    void saveMany(List<T> courses) ;

    void update(T course);

    void delete(T course);

    List<T> findAll();

    void deleteAll();

}