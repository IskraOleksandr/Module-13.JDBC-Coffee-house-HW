package brainacad_org.dao.cafeassortmentDAO;


import brainacad_org.dao.CRUDInterface;
import brainacad_org.model.CafeAssortment;

import java.util.List;

public interface CafeAssortmentDao {

   void save(CafeAssortment cafeAssortment);
   void saveMany(List<CafeAssortment> cafeAssortments);
   void update(CafeAssortment cafeAssortment);
   void delete(long cafeAssortmentId);
   List<CafeAssortment> findAll();
   List<CafeAssortment> findByAssortmenType(boolean flag);


   void deleteAll();
}
