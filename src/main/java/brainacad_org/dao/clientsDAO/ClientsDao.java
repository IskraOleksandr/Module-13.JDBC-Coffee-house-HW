package brainacad_org.dao.clientsDAO;


import brainacad_org.model.Clients;

import java.util.List;

public interface ClientsDao {

    void save(Clients client) ;

    void saveMany(List<Clients> clients) ;

    void update(Clients client ) ;

    void delete(long clientId) ;

    List<Clients> findAll() ;

    //List<Clients> findAllFromCourse(String courseName) ;

    void deleteAll() ;
}
