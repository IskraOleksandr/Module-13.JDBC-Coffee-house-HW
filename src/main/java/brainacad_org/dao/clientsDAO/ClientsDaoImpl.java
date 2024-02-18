package brainacad_org.dao.clientsDAO;

import brainacad_org.dao.ConnectionFactory;
import brainacad_org.model.Clients;
import brainacad_org.service.exception.ConnectionDBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientsDaoImpl implements ClientsDao {

    private static final String SAVE_CLIENT = "INSERT INTO clients(full_name, date_of_birth, contact_phone, contact_address, discount) VALUES(?,?,?,?,?)";
    private static final String UPDATE_CLIENT = "UPDATE clients SET full_name=?,date_of_birth=?,contact_phone=?,contact_address=?,discount=? WHERE id = ?";
    private static final String FIND_ALL_CLIENTS = "SELECT * FROM clients";
 private static final String DELETE_ALL_CLIENTS = "DELETE FROM clients";
    private static final String DELETE_CLIENT = "DELETE FROM clients WHERE clients.id = ?";

    @Override
    public void save(Clients client){
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_CLIENT)) {
            ps.setString(1, client.getFull_name());
            ps.setDate(2, new java.sql.Date(client.getDate_of_birth().getDate()));
            ps.setString(3, client.getContact_phone());
            ps.setString(4, client.getContact_address());
            ps.setFloat(5, client.getDiscount());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void saveMany(List<Clients> clients) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_CLIENT)) {

            for (var currentClient : clients) {
                ps.setString(1, currentClient.getFull_name());
                ps.setDate(2, new java.sql.Date(currentClient.getDate_of_birth().getDate()));
                ps.setString(3, currentClient.getContact_phone());
                ps.setString(4, currentClient.getContact_address());
                ps.setFloat(5, currentClient.getDiscount());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    @Override
    public void update(Clients client) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_CLIENT)) {
            ps.setString(1, client.getFull_name());
            ps.setDate(2, new java.sql.Date(client.getDate_of_birth().getDate()));
            ps.setString(3, client.getContact_phone());
            ps.setString(4, client.getContact_address());
            ps.setFloat(5, client.getDiscount());
            ps.setLong(6, client.getId());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(long clientId) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_CLIENT)) {
            ps.setLong(1, clientId);
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Clients> findAll() {
        List<Clients> resultClients = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL_CLIENTS);
             ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                Clients addClient = new Clients();
                addClient.setId(result.getLong(1));
                addClient.setFull_name(result.getString(2));
                addClient.setDate_of_birth(result.getDate(3));
                addClient.setContact_phone(result.getString(4));
                addClient.setContact_address(result.getString(5));
                addClient.setDiscount(result.getFloat(6));
                resultClients.add(addClient);
            }
            return resultClients;
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultClients;
    }

    @Override
    public void deleteAll() {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ALL_CLIENTS)) {
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
