package brainacad_org.dao.staffDAO;


import brainacad_org.model.Staff;

import java.util.List;

public interface StaffDao {

    void save(Staff staff) ;

    void saveMany(List<Staff> staff) ;

    void update(Staff staff ) ;

    void delete(long staffId) ;

    List<Staff> findAll() ;

    List<Staff> findByStaffPosition(int flag);

    void deleteAll() ;
}
