package brainacad_org.dao.cafeassortmentDAO;

import brainacad_org.dao.ConnectionFactory;
import brainacad_org.model.CafeAssortment;
import brainacad_org.service.exception.ConnectionDBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CafeAssortmentImpl implements CafeAssortmentDao {

    private static final String SAVE_CAFE_ASSORTMENT = "INSERT INTO CafeAssortment(title_eng, title_rus, assortment_type, price) VALUES(?,?,?,?)";
    private static final String FIND_ALL_CAFE_ASSORTMENTS = "SELECT * FROM CafeAssortment";
    private static final String FIND_CAFE_ASSORTMENT = "SELECT * FROM CafeAssortment WHERE CafeAssortment.id = ?";
    private static final String FIND_CAFE_ASSORTMENTS_BY_DESSERT = "SELECT * FROM CafeAssortment WHERE assortment_type = 'Дессерт'";//Dessert
    private static final String FIND_CAFE_ASSORTMENTS_BY_DRINK = "SELECT * FROM CafeAssortment WHERE assortment_type = 'Напиток'";//Drink
    private static final String DELETE_ALL_CAFE_ASSORTMENTS = "DELETE FROM CafeAssortment";
    private static final String UPDATE_CAFE_ASSORTMENT = "UPDATE CafeAssortment SET title_eng = ?, title_rus = ?, assortment_type = ?, price = ? " +
            " WHERE CafeAssortment.id = ? ";
    private static final String DELETE_CAFE_ASSORTMENT = "DELETE FROM CafeAssortment WHERE CafeAssortment.id = ?";

    @Override
    public void save(CafeAssortment cafeAssortment) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_CAFE_ASSORTMENT)) {
            ps.setString(1, cafeAssortment.getTitle_eng());
            ps.setString(2, cafeAssortment.getTitle_rus());
            ps.setString(3, cafeAssortment.getAssortment_type());
            ps.setFloat(4, cafeAssortment.getPrice());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void saveMany(List<CafeAssortment> cafeAssortments) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_CAFE_ASSORTMENT)) {

            for (var currentCafeAssortment : cafeAssortments) {
                ps.setString(1, currentCafeAssortment.getTitle_eng());
                ps.setString(2, currentCafeAssortment.getTitle_rus());
                ps.setString(3, currentCafeAssortment.getAssortment_type());
                ps.setFloat(4, currentCafeAssortment.getPrice());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(CafeAssortment cafeAssortment) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_CAFE_ASSORTMENT)) {
            ps.setString(1, cafeAssortment.getTitle_eng());
            ps.setString(2, cafeAssortment.getTitle_rus());
            ps.setString(3, cafeAssortment.getAssortment_type());
            ps.setFloat(4, cafeAssortment.getPrice());
            ps.setLong(5, cafeAssortment.getId());
            ps.execute();
        } catch (ConnectionDBException |SQLException e) {
            System.err.println(e.getMessage());
        }
    }



    @Override
    public void delete(long cafeAssortmentId) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_CAFE_ASSORTMENT)) {
            ps.setLong(1, cafeAssortmentId);
            ps.execute();
        } catch (ConnectionDBException |SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    @Override
    public List<CafeAssortment> findAll() {
        List<CafeAssortment> resultAddCafeAssortments = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL_CAFE_ASSORTMENTS);
             ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                CafeAssortment addCafeAssortment = new CafeAssortment();
                addCafeAssortment.setId(result.getLong(1));
                addCafeAssortment.setTitle_eng(result.getString(2));
                addCafeAssortment.setTitle_rus(result.getString(3));
                addCafeAssortment.setAssortment_type(result.getString(4));
                addCafeAssortment.setPrice(result.getFloat(5));
                resultAddCafeAssortments.add(addCafeAssortment);
            }
            return resultAddCafeAssortments;
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultAddCafeAssortments;
    }
    @Override
    public List<CafeAssortment> findByAssortmenType(boolean flag) {
        List<CafeAssortment> resultAddCafeAssortments = new ArrayList<>();
        String str = FIND_CAFE_ASSORTMENTS_BY_DESSERT;
        if (flag == true){
            str = FIND_CAFE_ASSORTMENTS_BY_DRINK;
        }
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(str);
             ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                CafeAssortment addCafeAssortment = new CafeAssortment();
                addCafeAssortment.setId(result.getLong(1));
                addCafeAssortment.setTitle_eng(result.getString(2));
                addCafeAssortment.setTitle_rus(result.getString(3));
                addCafeAssortment.setAssortment_type(result.getString(4));
                addCafeAssortment.setPrice(result.getFloat(5));
                resultAddCafeAssortments.add(addCafeAssortment);
            }
            return resultAddCafeAssortments;
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultAddCafeAssortments;
    }


    @Override
    public void deleteAll() {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ALL_CAFE_ASSORTMENTS)) {
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}
