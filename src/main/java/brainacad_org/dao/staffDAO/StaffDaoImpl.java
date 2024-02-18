package brainacad_org.dao.staffDAO;

import brainacad_org.dao.ConnectionFactory;
import brainacad_org.dao.clientsDAO.ClientsDao;
import brainacad_org.model.CafeAssortment;
import brainacad_org.model.Clients;
import brainacad_org.model.Staff;
import brainacad_org.service.exception.ConnectionDBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDaoImpl implements StaffDao {

    private static final String SAVE_STAFF = "INSERT INTO staff(full_name, date_of_birth, contact_phone, contact_address, position) VALUES(?,?,?,?,?)";
    private static final String UPDATE_STAFF = "UPDATE staff SET full_name=?,date_of_birth=?,contact_phone=?,contact_address=?,position=? WHERE id = ?";
    private static final String FIND_ALL_STAFFS = "SELECT * FROM staff";
    private static final String FIND_STAFFS_BY_BARISTA = "SELECT * FROM staff where staff.position = 'Бариста'";
    private static final String FIND_STAFFS_BY_WAITER = "SELECT * FROM staff where staff.position = 'Официант'";
    private static final String FIND_STAFFS_BY_CONFECTIONER = "SELECT * FROM staff where staff.position = 'Кондитер'";
    private static final String DELETE_ALL_STAFFS = "DELETE FROM staff";
    private static final String DELETE_STAFF = "DELETE FROM staff WHERE staff.id = ?";

    @Override
    public void save(Staff staff){
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_STAFF)) {
            ps.setString(1, staff.getFull_name());
            ps.setDate(2,  new java.sql.Date(staff.getDate_of_birth().getDate()));
            ps.setString(3, staff.getContact_phone());
            ps.setString(4, staff.getContact_address());
            ps.setString(5, staff.getPosition());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void saveMany(List<Staff> staffs) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_STAFF)) {

            for (var currentStaff : staffs) {
                ps.setString(1, currentStaff.getFull_name());
                ps.setDate(2, new java.sql.Date(currentStaff.getDate_of_birth().getDate()));
                ps.setString(3, currentStaff.getContact_phone());
                ps.setString(4, currentStaff.getContact_address());
                ps.setString(5, currentStaff.getPosition());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    @Override
    public void update(Staff staff) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_STAFF)) {
            ps.setString(1, staff.getFull_name());
            ps.setDate(2,  new java.sql.Date(staff.getDate_of_birth().getDate()));
            ps.setString(3, staff.getContact_phone());
            ps.setString(4, staff.getContact_address());
            ps.setString(5, staff.getPosition());
            ps.setLong(6, staff.getId());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(long staffId) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_STAFF)) {
            ps.setLong(1, staffId);
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Staff> findAll() {
        List<Staff> resultStaff = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL_STAFFS);
             ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                Staff addStaff = new Staff();
                addStaff.setId(result.getLong(1));
                addStaff.setFull_name(result.getString(2));
                addStaff.setDate_of_birth(result.getDate(3));
                addStaff.setContact_phone(result.getString(4));
                addStaff.setContact_address(result.getString(5));
                addStaff.setPosition(result.getString(6));
                resultStaff.add(addStaff);
            }
            return resultStaff;
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultStaff;
    }
    @Override
    public List<Staff> findByStaffPosition(int flag) {
        List<Staff> resultAddStaffs = new ArrayList<>();
        String str = "";
        if (flag == 1){
            str = FIND_STAFFS_BY_BARISTA;
        }
        else if (flag == 2){
            str = FIND_STAFFS_BY_WAITER;
        }
        else if (flag == 3)
        {
            str = FIND_STAFFS_BY_CONFECTIONER;
        }

        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(str);
             ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                Staff addStaff = new Staff();
                addStaff.setId(result.getLong(1));
                addStaff.setFull_name(result.getString(2));
                addStaff.setDate_of_birth(result.getDate(3));
                addStaff.setContact_phone(result.getString(4));
                addStaff.setContact_address(result.getString(5));
                addStaff.setPosition(result.getString(6));
                resultAddStaffs.add(addStaff);
            }
            return resultAddStaffs;
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultAddStaffs;
    }

    @Override
    public void deleteAll() {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ALL_STAFFS)) {
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
